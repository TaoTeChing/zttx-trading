package com.zttx.web.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 流水号生产类
 * @author adm
 *
 */
public class SerialNumberUtil {

	
	/**
	 * 根据时间戳和随机数生产18位流水号,11位时间戳(截取前2位)+7位随机数
	 * @return
	 */
	public synchronized static String getSerialNumber18(){
		
		//根据UUID的HASHCODE作为种子，生产7位随机数
		int radomint  = new Random(UUID.randomUUID().hashCode()).nextInt(1000000);
		
		String radomStr = String.valueOf(radomint);
		
		//不够7位,前面补0
		switch (radomStr.length()) {
        case 1: radomStr = "000000" + radomStr; break;
        case 2: radomStr = "00000" + radomStr;  break;
        case 3: radomStr = "0000" + radomStr;   break;
        case 4: radomStr = "000" + radomStr; break;
        case 5: radomStr = "00" + radomStr;  break;
        case 6: radomStr = "0" + radomStr;   break;
        
        }
		
		String millStr = String.valueOf(System.currentTimeMillis());
		
		millStr = millStr.substring(2);
		
		return millStr+radomStr;
	}
	
	public static void main(String[] args) {  
		
		System.out.println(SerialNumberUtil.getSerialNumber18());
	}
}
