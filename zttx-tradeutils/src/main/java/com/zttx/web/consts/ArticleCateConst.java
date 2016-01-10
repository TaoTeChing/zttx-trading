package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

public enum ArticleCateConst implements EnumDescribable
{
    SUCCESS(131000, "成功"),
    DBERROR(131001, "数据访问失败"),
    NULERROR(131002, "请求参数为空"),
    DAOERROR(131003, "数据操作失败"),
    PARENTNOEXITS(131004, "上层不存在"),
    NOEXITS(131005, "数据不存在"),
    EXITSCHILDREN(131006, "存在下层数据"),
    EXITS(131007, "数据已存在"),
    EXITSARTICLE(131008, "存在文章"),
    FAILURE(131009, "失败"),
    UPIMGERROR(131010, "上传图片失败");

    private ArticleCateConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    /**
     * 根据状态码获取状态码描述
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (ArticleCateConst c : ArticleCateConst.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
            }
        }
        return result;
    }
    
    public Integer code;
    
    public String  message;
    
    @Override
    public Integer getCode()
    {
        return code;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
