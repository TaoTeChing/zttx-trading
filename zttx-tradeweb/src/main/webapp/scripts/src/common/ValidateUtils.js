/**********************************************************************************
 * <p>JavaScript通用客户端数据验证</p>
 *  @Author:刘志坚(LzjLiu307@163.com)
 *  @Date 2014-03-04
 *  @Description：客户端通用IP，url，日期，字符串，邮件，邮编，文件，电话，手机等的验证。
 **********************************************************************************/
var Validate=
{
	dateFormat:"yyyy-mm-dd",
	montFormat:"yyyy-mm",
	timeFormat:"yyyy-mm-dd hh:mm",
	secdFormat:"yyyy-mm-dd hh:mm:ss",
	/**
	 * 验证某一字符串是否为空并提示错误
	 * @param checkString 要检查的字段值
	 * @return boolean 是否为空（true为空，false不为空）
	 */
	isNull:function(checkString)
	{
		var bool = true;
		if(this.trim(checkString)!="")
			bool = false;
		return bool;
	},
	
	/**
	 * 将一字符串去左空格处理
	 * @param checkString 要去除空格的字符串
	 * @return checkString 去除空格后的字符串
	 */
	lTrim:function(checkString)
	{
		var result = "";
		if(null != checkString)
			result = checkString.replace(/^\s*/, "");
		return result; 
	},
	
	/**
	 * 将一字符串去右空格处理
	 * @param checkString 要去除空格的字符串
	 * @return checkString 去除空格后的字符串
	 */
	rTrim:function(checkString)
	{
		var result = "";
		if(null != checkString)
			result = checkString.replace(/\s*$/, "");
		return result; 
	},
	
	/**
	 * 将一字符串删除所有空格处理
	 * @param checkString 要去除空格的字符串
	 * @return checkString 去除空格后的字符串
	 */
	aTrim:function(checkString)
	{
		var result = "";
		if(null != checkString)
		{
       		var temp = checkString.split(/\s/);
       		for(i = 0; i < temp.length; i++) 
       		{
              result += temp[i];
       		}
		}
		return result;
	},
	
	/**
	 * 将一字符串去左右空格处理
	 * @param checkString 要去除空格的字符串
	 * @return checkString 去除空格后的字符串
	 */
	trim:function(checkString)
	{
		var result = "";
		if(null != checkString)
			result = checkString.replace(/(^\s*)|(\s*$)/g, "");
		return result;
	},
	
	/**
	 * 取得一字符串的长度值
	 * @param checkString 要处理的字符串
	 * @return integer 字符串长度
	 */
	length:function(checkString)
	{
		var len=0;
		if(!this.isNull(checkString))
		{
		    for(var i=0;i<checkString.length;i++)
		    {
		 		if(!$V.isChinese(checkString.charAt(i)))
		 		{
		            len+=1;
		        }
		        else 
		        {
		            len+=2;
		        }
		    }
	    }
		return len;
	},
	
	/**
	 * 判断一字符串中是否含有中文字
	 * @param checkString 要验证的字符串
	 * @return bool 是否含有中文字
	 */
	isChinese:function(checkString)
	{
		var ret=false;
	    for(var i=0;i<checkString.length;i++)
	    {
	    	if(checkString.charCodeAt(i)>=256)
	    	{
				ret=true;
				break;
	    	}
		}
	   	return ret;
	},
	
	/**
	 * 验证某一字符串是否为有效的日期（格式为：2007-12-25或2007/12/25）
	 * @param checkString 要检查的字符串
	 * @return boolean 是否为有效日期
	 */
	isDate:function(checkString)
	{
		var bool=false;
		var r = this.trim(checkString).match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if(r!=null)
		{
			var d = new Date(r[1], r[3]-1,r[4]);
			bool = (d.getFullYear()==r[1]&&(d.getMonth()+1)==parseInt(r[3],10)&&d.getDate()==r[4]);
		}
		return bool;
	},
	
	/**
	 * 将指定字符串的全角内容转为半角内容
	 * @param str 要转换的字符串
	 * @param string 转换后的字符串
	 */	
	quanToBan:function(str)
	{
		var tmp = "";
		for(var i=0;i<str.length;i++)
		{
			if(str.charCodeAt(i)>65248&&str.charCodeAt(i)<65375)
			{
				tmp += String.fromCharCode(str.charCodeAt(i)-65248);
			}
			else
			{
				tmp += String.fromCharCode(str.charCodeAt(i));
			}
		}
		return tmp
	},
	
	/**
	 * 将指定字符串的半角内容转为全角内容
	 * @param txtstring 要转换的字符串
	 * @return string 转换后的内容
	 */
	banToQuan:function(txtstring)
	{
		var tmp = "";
		for(var i=0;i<txtstring.length;i++)
		{
			if(txtstring.charCodeAt(i)==32)
			{
				tmp= tmp+ String.fromCharCode(12288);
			}
			if(txtstring.charCodeAt(i)<127)
			{
				tmp=tmp+String.fromCharCode(txtstring.charCodeAt(i)+65248);
			}
		}
		return tmp;
	},
	
	/**
	 * 普通字符串验证，主要验证是否必须和最大长度
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isRange:function(checkString,isRequired,maxLength)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
			bChk = !boolNull;
		//console.info(checkString+"validateutils"+bChk+"==="+boolNull);
		if(!boolNull)
		{
			if(maxLength != 0)	//如果指定maxLength=0，则不验证字符串长度
			{
				checkString = this.trim(checkString);
				bChk=this.length(checkString)<=maxLength;
			}
		}
		return bChk;
	},
	
	/**
	 * 支持多个以;号隔开的限制最大长度，是否必须，匹配正则的抽象验证
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度 
	 * @param regu 正则表达式
	 */
	validate:function(checkString,isRequired,maxLength,regu)
	{
		var bChk=this.isRange(checkString,isRequired,maxLength);
		if(bChk && !this.isNull(checkString))
		{
			checkString = this.trim(checkString);
			checkString = this.quanToBan(checkString);//全角转半角
			var re   = new RegExp(regu);
			if(checkString.indexOf(";")==-1)
			{
				if (checkString.search(re) == -1)
					bChk=false;
			}
			else
			{	
				var strArray=checkString.split(";");
				for(var i=0;i<strArray.length;i++)
				{
					var str=strArray[i];
					if(str.search(re)==-1)
					{
						bChk=false;
						break;
					}
				}
			}
		}
		return bChk;
	},
	
	/**
 	 * 验证某一字符串是否为正确格式的电话号码(正确格式：0752-2563623或0752-2563623-998)支持多个验证
	 * 支持多个以;号隔开的电话号码验证
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度 
	 * @return boolean 是否为正确格式的电话号
	 */
	isValidPhone:function(checkString,isRequired,maxLength)
	{
		var regu = "^([0-9]{3,4}-{1}[0-9]{7,8}(-[0-9]{2,6})?)?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 邮件地址格式验证(支持以;号隔开的多个邮件地址验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidMail:function(checkString,isRequired,maxLength)
	{
		var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[_.0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+(.+)$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的手机号码格式(支持以;号隔开的多个手机号码验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidMobile:function(checkString,isRequired,maxLength)
	{
		var regu="^(1[0-9]{10})?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的ICCID号码格式(支持以;号隔开的多个ICC号码验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidIcc:function(checkString,isRequired,maxLength)
	{
		var regu="^([0-9A-Za-z]{21})?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的IMSI号码格式(支持以;号隔开的多个IMSI号码验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidImsi:function(checkString,isRequired,maxLength)
	{
		var regu="^([0-9]{15})?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的邮政编码格式(支持以;号隔开的多个邮政编码验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidZipCode:function(checkString,isRequired,maxLength)
	{
		var regu="^([1-9][0-9]{5})?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的身份证号码格式(支持以;号隔开的多个身份证号码验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidID:function(checkString,isRequired,maxLength)
	{
		var regu="^([1-9][0-9]{14}|[1-9][0-9]{17}|[1-9][0-9]{16}[0-9A-Za-z]{1})?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的IP地址格式(支持以;号隔开的多个IP地址验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidIP:function(checkString,isRequired,maxLength)
	{
		var regu="^(((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?))?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否为正确的URL地址格式(支持以;号隔开的多个URL地址验证)
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isValidURL:function(checkString,isRequired,maxLength)
	{
		var regu="^(http|https|ftp)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$";
		return this.validate(checkString,isRequired,maxLength,regu);
	},
	
	/**
	 * 验证某一字符串是否有效的文件路径及名称
	 * @param checkString 要检查的字符串
	 * @param extensions 允许的扩展名称（例：gif|jpg|png|bmp|jpeg）
	 * @param isRequired 是否必须
	 * @return boolean 是否正确的文件
	 */
	isValidFile:function(checkString,extensions,isRequired)
	{
		var bChk=true;
		if (isRequired) 
		{
			bChk = !this.isNull(checkString);
		}
		if(bChk)
		{		
			checkString=this.trim(checkString);
			if(bChk==true && extensions!="")
			{
				var re = new RegExp("\.(" + extensions.replace(/,/gi,"|") + ")$","i");
				if(!re.test(checkString))
				{
					bChk=false;
				}
			}
		}
		return bChk;
	},
	
	/**
	 * 验证一字符串是否匹配指定的正则表达式
	 * @param checkString 要验证的字符串
	 * @param isRequired 是否必须不为空
	 * @param match 指定的正则表达式
	 * @return boolean 验证是否通过
	 */
	isMatch:function(checkString,isRequired,match)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull && this.trim(match)!="")
		{
			var re = new RegExp(match);
			if(checkString.search(re) == -1)
			{
				bChk=false;
			}
		}
		return bChk;
	},
	
	/**
	 * 验证某一字符串是否为正确的日期格式或有效日期<yyyy-mm-dd>
	 * @param checkString 要检查的字符串
	 * @param format 指定的日期格式
	 * @param isRequired 是否必须输入
	 * @return boolean 是正确的有效的日期
	 */
	isValidDate:function(checkString,format,isRequired)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull)
		{
			checkString = this.trim(checkString);
			if(format==this.montFormat)
			{
				checkString+="-1";
			}
			bChk=this.isDate(checkString);
			if(bChk==true && format==this.dateFormat)
			{
				var regu = "^([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})?$";
				var re = new RegExp(regu);
				if(checkString.search(re) == -1)
				{
					bChk=false;
				}
			}
		}
		return bChk;
	},
	
	/**
	 * 是否正确时间的验证<格式：yyyy-mm-dd hh:mm:ss>
	 * @param checkString 要验证的字符串
	 * @return true/false 是否正确
	 */
	isTime:function(checkString)
	{
		var bool = false;
		var r = this.trim(checkString).match("^([0-9]{4})-([0-9]{1,2})-([0-9]{1,2}) ([0-9]{2})[:]{1}([0-9]{2})[:]{1}([0-9]{2})?$");
		//alert(r[1]);//年
		//alert(r[2]);//月
		//alert(r[3]);//日
		//alert(r[4]);//时
		//alert(r[5]);//分
		//alert(r[6]);//秒
		if(r !=null)
		{
			var d = new Date(r[1], r[2]-1,r[3],r[4],r[5],r[6]);
			//alert(d.getHours()+"==="+parseInt(r[4],10)+"===");
			bool = (d.getFullYear()==r[1]&&(d.getMonth()+1)==parseInt(r[2],10)&&d.getDate()==r[3] && d.getHours()==parseInt(r[4],10) && d.getMinutes()==parseInt(r[5],10) && d.getSeconds()==parseInt(r[6],10));
		}
		return bool;
	},
	
	/**
	 * 验证某一字符串是否为正确的日期格式或有效日期<yyyy-mm-dd hh:mm:ss)
	 * @param checkString 要检查的字符串
	 * @param format 指定的日期格式
	 * @param isRequired 是否必须输入
	 * @return boolean 是正确的有效的日期
	 */
	isValidSecTime:function(checkString,format,isRequired)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull)
		{
			checkString = this.trim(checkString);
			bChk=this.isTime(checkString);
			if(bChk==true && format==this.secdFormat)
			{
				var regu = "^([0-9]{4}-[0-9]{1,2}-[0-9]{1,2}) ([0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2})?$";
				var re = new RegExp(regu);
				if(checkString.search(re) == -1)
				{
					bChk=false;
				}
			}
		}
		return bChk;
	},
	
	/**
	 * 是否正确日期的验证<格式：yyyy-mm-dd hh:mm>
	 * @param checkString 要验证的字符串
	 * @return true/false 是否正确
	 */
	isDates:function(checkString)
	{
		var bool = false;
		var r = this.trim(checkString).match("^([0-9]{4})-([0-9]{1,2})-([0-9]{1,2}) ([0-9]{2})[:]{1}([0-9]{2})?$");
		//alert(r[1]);//年
		//alert(r[2]);//月
		//alert(r[3]);//日
		//alert(r[4]);//时
		//alert(r[5]);//分
		if(r!=null)
		{
			var d = new Date(r[1], r[2]-1,r[3],r[4],r[5]);
			//alert(d.getHours()+"==="+parseInt(r[4],10)+"===");
			bool = (d.getFullYear()==r[1]&&(d.getMonth()+1)==parseInt(r[2],10)&&d.getDate()==r[3] && d.getHours()==parseInt(r[4],10) && d.getMinutes()==parseInt(r[5],10));
		}
		return bool;
	},
	
	/**
	 * 验证某一字符串是否为正确的日期格式或有效日期<yyyy-mm-dd hh:mm>
	 * @param checkString 要检查的字符串
	 * @param format 指定的日期格式
	 * @param isRequired 是否必须输入
	 * @return boolean 是正确的有效的日期
	 */
	isValidDates:function(checkString,format,isRequired)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull)
		{
			checkString = this.trim(checkString);		
			bChk=this.isDates(checkString);
			//alert(checkString+"==="+bChk+"===");
			if(bChk==true && format==this.timeFormat)
			{
				var regu = "^([0-9]{4}-[0-9]{1,2}-[0-9]{1,2}) ([0-9]{2}[:]{1}[0-9]{2})?$";
				var re = new RegExp(regu);
				if(checkString.search(re) == -1)
				{
					bChk=false;
				}
			}
		}
		return bChk;
	},
	
	/**
	 * 验证某一整数是否在指定范围之内
	 * @param checkString 要检查的整数
	 * @param iMin 最小数值
	 * @param iMax 最大数值
	 * @param isRequired 是否必须输入
	 * @return boolean 是否正确的数字
	 */
	isIntRange:function(checkString,iMin,iMax,isRequired)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull)
		{
			bChk=!isNaN(checkString);
			if(bChk==true)
			{
				checkString = this.trim(checkString);
				//if(iMin!=0 && iMax!=0)
				//{
					iNum=parseInt(checkString);
					if(iNum>iMax || iNum<iMin)
						bChk=false;
				//}
			}
		}
		return bChk;
	},
	
	/**
	 * 验证某一字符串是否为正确的指定位数的英文字母格式
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须输入
	 * @param len 验证码长度
	 * @return boolean 是否正确的系统验证码
	 */
	isValidCode:function(checkString,isRequired,len)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull)
		{
			checkString = this.trim(checkString);
			var regu="^[a-zA-Z0-9_]{"+len+"}$";	//英文及数字
			var re = new RegExp(regu);
			if(checkString.search(re) == -1)
			{
				bChk=false;
			}
		}
		return bChk;
	},
	
	/**
	 * 验证某一字符串是否为指定长度范围的帐号密码格式
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须输入
	 * @param minLen 最小长度
	 * @param maxLen 最大长度
	 * @return boolean 是否正确的帐号格式
	 */
	isValidAccount:function(checkString,isRequired,minLen,maxLen)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
		{
			bChk = !boolNull;
		}
		if(!boolNull)
		{
			checkString = this.trim(checkString);
			var regu="^([0-9A-Za-z_.@-]{" + minLen + "," + maxLen + "})?$";
			var re = new RegExp(regu);
			if(checkString.search(re) == -1)
			{
				bChk=false;
			}
		}
		return bChk;
	},
	
	/**
	 * 普通字符串验证，主要验证是否必须和长度范围
	 * @param checkString 要检查的字符串
	 * @param isRequired 是否必须
	 * @param minLength 最小长度
	 * @param maxLength 最大长度
	 * @return boolean 是否通过验证
	 */
	isInRange:function(checkString,isRequired,minLength,maxLength)
	{
		var bChk=true;
		var boolNull=this.isNull(checkString);
		if (isRequired) 
			bChk = !boolNull;
		if(!boolNull)
		{
			if(minLength!=0  && maxLength!=0)//如果指定maxLength=0和minLength=0，则不验证字符串长度
			{
				checkString = this.trim(checkString);
				var iLen=this.length(checkString);
				bChk=(iLen>=minLength) && (iLen<=maxLength);
			}
		}
		return bChk;
	}
};
window.Validate = window.$V = Validate;