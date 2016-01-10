package com.zttx.web.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.zttx.sdk.utils.JedisUtils;
import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.module.common.service.RegionalService;

/**
 * 生成城市名称拼音,包括全拼和简拼,放置到缓存中
 * 用于终端商搜索页面
 * Created by 李星 on 2015/11/30.
 */
public class PinyinUtils
{
    public static String           REGIONAL_PINYIN_KEY = "_regional_pinyin_";
    
    private static RegionalService regionalService     = SpringUtils.getBean(RegionalService.class);
    
    /**
     * 初始化城市名称拼音
     */
    static
    {
        initRedisRegionalInfo();
    }
    
    /**
     * 输入汉字获取该汉字的拼音HashMap key为全拼，value为简拼
     * @param src
     * @return
     */
    public static HashMap<String, String> getPinyin(String src)
    {
        if (src != null && !src.trim().equalsIgnoreCase(""))
        {
            char[] srcChar;
            srcChar = src.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
            // 输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            String[][] qp_temp = new String[src.length()][];// 全拼
            String[][] jp_temp = new String[src.length()][];// 首字母简拼
            for (int i = 0; i < srcChar.length; i++)
            {
                char c = srcChar[i];
                // 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+"))
                {// 判断中文
                    try
                    {
                        qp_temp[i] = PinyinHelper.toHanyuPinyinStringArray(c, hanYuPinOutputFormat);
                        jp_temp[i] = new String[qp_temp[i].length];
                        for (int k = 0; k < qp_temp[i].length; k++)
                        {
                            jp_temp[i][k] = "" + qp_temp[i][k].charAt(0);
                        }
                    }
                    catch (BadHanyuPinyinOutputFormatCombination e)
                    {
                        e.printStackTrace();
                    }
                }
                else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122))
                {// 判断字母
                    qp_temp[i] = new String[]{String.valueOf(srcChar[i])};
                    jp_temp[i] = qp_temp[i];
                }
                else
                {
                    qp_temp[i] = new String[]{""};
                    jp_temp[i] = qp_temp[i];
                }
            }
            HashMap<String, String> map = DoExchange(qp_temp, jp_temp);
            return map;
        }
        return null;
    }
    
    /**
     * 递归
     * @return HashMap<String, String>
     * key全拼，value简拼
     */
    private static HashMap<String, String> DoExchange(String[][] qp_pyArrar, String[][] jp_pyArrar)
    {
        int len = qp_pyArrar.length;
        if (len >= 2)
        {
            int qp_len1 = qp_pyArrar[0].length;
            int qp_len2 = qp_pyArrar[1].length;
            int qp_newlen = qp_len1 * qp_len2;
            String[] qp_temp = new String[qp_newlen];
            String[] jp_temp = new String[qp_newlen];
            int Index = 0;
            for (int i = 0; i < qp_len1; i++)
            {
                for (int j = 0; j < qp_len2; j++)
                {
                    qp_temp[Index] = qp_pyArrar[0][i] + qp_pyArrar[1][j];
                    jp_temp[Index] = jp_pyArrar[0][i] + jp_pyArrar[1][j];
                    Index++;
                }
            }
            String[][] qp_newArray = new String[len - 1][];
            String[][] jp_newArray = new String[len - 1][];
            for (int i = 2; i < len; i++)
            {
                qp_newArray[i - 1] = qp_pyArrar[i];
                jp_newArray[i - 1] = jp_pyArrar[i];
            }
            qp_newArray[0] = qp_temp;
            jp_newArray[0] = jp_temp;
            return DoExchange(qp_newArray, jp_newArray);
        }
        else
        {
            HashMap<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < qp_pyArrar[0].length; i++)
            {
                map.put(qp_pyArrar[0][i], jp_pyArrar[0][i]);
            }
            return map;
        }
    }
    
    /**
     * 初始化地址拼音 
     */
    private static void initRedisRegionalInfo()
    {
        List<String> reginalNameList = regionalService.getRegionalNameByAreaLevel(1);
        reginalNameList.addAll(regionalService.getRegionalNameByAreaLevel(2));
        for (String name : reginalNameList)
        {
            name = name.replaceAll("省", "").replaceAll("市", "").replaceAll("自治区", "").replaceAll("特别行政区", "").replaceAll("地区", "").replaceAll("自治州", "");
            name = name.replaceAll("维吾尔", "").replaceAll("壮族", "").replaceAll("藏族", "").replaceAll("回族", "");
            Map<String, String> pinyins = PinyinUtils.getPinyin(name);
            Iterator<Map.Entry<String, String>> iter = pinyins.entrySet().iterator();
            while (iter.hasNext())
            {
                Map.Entry<String, String> entry = iter.next();
                String qp = entry.getKey();
                String jp = entry.getValue();
                JedisUtils.set(REGIONAL_PINYIN_KEY + qp, name, 0);
                JedisUtils.set(REGIONAL_PINYIN_KEY + jp, name, 0);
                // System.out.println(name + ":" + qp + "," + jp);
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String str = "宁波";
        HashMap<String, String> map = getPinyin(str);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry<String, String> entry = iter.next();
            String qp = entry.getKey();
            String jp = entry.getValue();
            System.out.println(qp + "," + jp);
        }
    }
}
