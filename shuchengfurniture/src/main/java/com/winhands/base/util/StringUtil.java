package com.winhands.base.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;
public class StringUtil { 
	private static final char LT_ENCODE[] = "&lt;".toCharArray();

	private static final char GT_ENCODE[] = "&gt;".toCharArray();


	private static final char BR_TAG[] = "<BR>".toCharArray();

	// private static Object initLock = new Object();

	/**
	 * 字符串替换
	 * 
	 * @param strSrc
	 *            String 源字符串
	 * @param strOld
	 *            String 被替换的字符串
	 * @param strNew
	 *            String 欲替换的字符串
	 * @return String
	 */
	public static final String replace(String strSrc, String strOld,
			String strNew) {

		String szReturn = "";
		String szTemp = null;
		int iIndex = -1;
		boolean bFirst = true;

		if (strSrc == null) {
			return null;
		}
		while ((iIndex = strSrc.indexOf(strOld)) != -1) {
			szTemp = strSrc.substring(0, iIndex);
			if (bFirst) {
				szReturn = "";
				bFirst = false;
			}
			szReturn += szTemp + strNew;
			strSrc = strSrc.substring(iIndex + strOld.length());
		}
		szReturn += strSrc;
		return szReturn;
	}

	/**
	 * 把字符中的html特殊符号"<",">"换成对应的转义符
	 * 
	 * @param s
	 *            String 字符串
	 * @return String
	 */
	public static final String escapeHTMLTags(String s) {
		if (s == null)
			return null;
		int i = 0;
		int j = 0;
		char ac[] = s.toCharArray();
		int k = ac.length;
		StringBuffer stringbuffer = new StringBuffer((int) ((double) k * 1.3D));
		for (; i < k; i++) {
			char c = ac[i];
			if (c > '>')
				continue;
			if (c == '<') {
				if (i > j)
					stringbuffer.append(ac, j, i - j);
				j = i + 1;
				stringbuffer.append(LT_ENCODE);
				continue;
			}
			if (c != '>')
				continue;
			if (i > j)
				stringbuffer.append(ac, j, i - j);
			j = i + 1;
			stringbuffer.append(GT_ENCODE);
		}

		if (j == 0)
			return s;
		if (i > j)
			stringbuffer.append(ac, j, i - j);
		return stringbuffer.toString();
	}

	/**
	 * 把字符串的"\n","\r\n"换成hmtl标签
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String convertNewlines(String s) {
		char ac[] = s.toCharArray();
		int i = 0;
		int j = ac.length;
		StringBuffer stringbuffer = new StringBuffer(j);
		for (int k = 0; k < j; k++) {
			if (ac[k] == '\n') {
				stringbuffer.append(ac, i, k - i).append(BR_TAG);
				i = k + 1;
				continue;
			} else if (ac[k] == '\r' && k < j - 1 && ac[k + 1] == '\n') {
				stringbuffer.append(ac, i, k - i).append(BR_TAG);
				i = ++k + 1;
			}
		}

		stringbuffer.append(ac, i, j - i);
		return stringbuffer.toString();
	}

	/**
	 * 作些相应的转化成sql字符串
	 * 
	 * @param s
	 *            String 要转换的字符串
	 * @param s1
	 *            String like的主体
	 * @return String
	 */
	public static String getTranslateStr(String s, String s1) {
		String s2 = "";
		if (s.indexOf(" ") > 0) {
			boolean flag = true;
			String as[] = s.split(" ");
			for (int i = 0; i < as.length; i++) {
				if (as[i].equals("AND") || as[i].equals("&")) {
					s2 = s2 + " and ";
					flag = true;
					continue;
				}
				if (as[i].equals("OR") || as[i].equals("|")) {
					s2 = s2 + " or ";
					flag = true;
					continue;
				}
				if (as[i].equals("NOT") || as[i].equals("!")
						|| as[i].equals("！")) {
					s2 = s2 + " not ";
					flag = true;
					continue;
				}
				if (as[i].equals("(") || as[i].equals("（") || as[i].equals("（")) {
					s2 = s2 + " ( ";
					flag = true;
					continue;
				}
				if (as[i].equals(")") || as[i].equals("）") || as[i].equals("）")) {
					s2 = s2 + " ) ";
					flag = true;
					continue;
				}
				if ("".equals(as[i]))
					continue;
				if (!flag)
					s2 = s2 + " and ";
				if (as[i].indexOf("%") > 0)
					s2 = s2 + " " + s1 + " like '"
							+ as[i].replaceAll("'", "''") + "' ";
				else
					s2 = s2 + " " + s1 + " like '%"
							+ as[i].replaceAll("'", "''") + "%' ";
				flag = false;
			}

			return s2;
		}
		if (s.indexOf("%") > 0)
			s2 = s2 + " " + s1 + " like '" + s.replaceAll("'", "''") + "' ";
		else
			s2 = s2 + " " + s1 + " like '%" + s.replaceAll("'", "''") + "%' ";
		return s2;
	}

	/**
	 * 对字符串进行html过滤
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String toHtmlInput(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = new String(s);
			s1 = replace(s1, "&", "&amp;");
			s1 = replace(s1, "<", "&lt;");
			s1 = replace(s1, ">", "&gt;");
			s1 = replace(s1, "\"", "&quot;");
			s1 = replace(s1, "'", "''");
			return s1;
		}
	}

	/**
	 * 把字符串转换成html字符串
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String toHtml(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = new String(s);
			s1 = toHtmlInput(s1);
			s1 = replace(s1, "\r\n", "\n");
			s1 = replace(s1, "\n", "<br>\n");
			s1 = replace(s1, "\t", "    ");
			s1 = replace(s1, "  ", " &nbsp;");
			return s1;
		}
	}

	 

	/**
	 * 返回要截取的字符串中的分量数
	 * 
	 * @param sz_theChar
	 *            String 要截取的字符串
	 * @param sz_Separator
	 *            String 分隔符
	 * @return int eg: 若sz_theChar="1,2,3,4";sz_Separator=",";那么就返回4
	 * 
	 */
	public static int getCharTolNum(String sz_theChar, String sz_Separator) {
		return sz_theChar.split(sz_Separator).length;
	}

	/**
	 * 返回要截取的字符串中的分量数
	 * 
	 * @param sz_theChar
	 *            String 要截取的字符串
	 * @param sz_Separator
	 *            String 分隔符
	 * @return String[] eg:
	 *         若sz_theChar="1,2,3,4";sz_Separator=",";那么就返回String[]={"1","2","3","4"}
	 * 
	 */
	public static String[] getSepStr(String sz_theChar, String sz_Separator) {
		return sz_theChar.split(sz_Separator);
	}

	/**
	 * 返回要截取的字符串中的分量
	 * 
	 * @param sz_theChar
	 *            String 要截取的字符串
	 * @param sz_Separator
	 *            String 分隔符
	 * @param i_CharNum
	 *            String 要取得的字符串位置
	 * @return String eg:
	 *         若sz_theChar="1,3,2,4";sz_Separator=",";i_CharNum=2;那么就返回"3"
	 * 
	 */
	public static String getSeparatorChar(String sz_theChar,
			String sz_Separator, int i_CharNum) {
		String[] str = sz_theChar.split(sz_Separator);
		return str[i_CharNum - 1];
	}

	/**
	 * 将月份的格式由数字转换成英文简拼
	 * 
	 * @param i_theMonth
	 *            int 数字格式的月份
	 * @return String
	 */
	public static String convertMonthToChar(int i_theMonth) {
		String sz_theChar = "";
		String charMonth[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		switch (i_theMonth) {
		case 1:
			sz_theChar = charMonth[0];
			break;
		case 2:
			sz_theChar = charMonth[1];
			break;
		case 3:
			sz_theChar = charMonth[2];
			break;
		case 4:
			sz_theChar = charMonth[3];
			break;
		case 5:
			sz_theChar = charMonth[4];
			break;
		case 6:
			sz_theChar = charMonth[5];
			break;
		case 7:
			sz_theChar = charMonth[6];
			break;
		case 8:
			sz_theChar = charMonth[7];
			break;
		case 9:
			sz_theChar = charMonth[8];
			break;
		case 10:
			sz_theChar = charMonth[9];
			break;
		case 11:
			sz_theChar = charMonth[10];
			break;
		case 12:
			sz_theChar = charMonth[11];
			break;
		default:
			break;
		}
		return sz_theChar;
	}

	 

	 

	/**
	 * 截取一定长度的字符串
	 * 
	 * @param str
	 *            String 源字符串
	 * @param len
	 *            int 长度
	 * @return String
	 */
	public static String getSubString(String str, int len) {
		if(str!=null){
			int i = str.length();
			if (i <= len) {
				return str;
			} else {
				return str.substring(0, len) + "...";
			}
		}else{
			return "";
		}
	}

	/**
	 * 截取一定长度的字符串
	 * 
	 * @param str
	 *            String 源字符串
	 * @param len
	 *            int 长度
	 * @return String
	 */
	public static String getSubLenString(String str, int len) {
		int i = str.length();
		if (i <= len) {
			return str;
		} else {
			return str.substring(0, len) + "<br>"
					+ getSubLenString(str.substring(len), len);
		}
	}

	/**
	 * 替换ORACLE SQL中所用到String型参数中的单引号和'&'符号
	 * 
	 * @param strSrc
	 *            String 源字符串
	 * @return String
	 */
	public static final String formatOraSql(String strSrc) {
		if (strSrc == null) {
			return null;
		}
		if ("".equals(strSrc)) {
			return strSrc;
		}
		strSrc = replace(strSrc, "'", "''");
		// strSrc=replace(strSrc,"&","'||'&'||'");
		return strSrc;
	}

	/**
	 * 判断字符串是否在源字符串已经存在
	 * 
	 * @param source
	 *            源字符串
	 * @param curStr
	 *            要判断的字符串
	 * @param sep
	 *            分隔符
	 * @return boolean
	 */
	public static boolean isInStr(String source, String curStr, String sep) {
		if (isNull(source)) {
			return false;
		} else {
			String[] str = source.split(sep);
			for (int i = 0; i < str.length; i++) {
				if (curStr.equals(str[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否为空或""
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNull(String str) {
		if (str == null || str.trim().equals("")
				|| str.toLowerCase().equals("null")) {
			return true;
		} else {
			return false;
		}
	}
	
//	public static String parseNull(String str) {
//		if (str == null || str.trim().equals("")
//				|| str.toLowerCase().equals("null")) {
//			return null;
//		} else {
//			return str;
//		}
//	}

	/*
	 * 转换大小写
	 */
	public String ConvertSumTotal(String sz_Number, int i_Type) {
		String sz_RtnSumTol = "";
		String sz_Temp = "";
		String charSum[] = null;

		if (sz_Number.equals(""))
			return sz_RtnSumTol;
		if (i_Type == 0) {
			charSum = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒",
					"捌", "玖", "拾" };
		} else if (i_Type == 1) {
			charSum = new String[] { "○", "一", "二", "三", "四", "五", "六", "七",
					"八", "九", "十" };
		} else {
			charSum = new String[] { "0", "1", "2", "3", "4", "5", "6", "7",
					"8", "9", "" };
		}
		for (int i = 0; i < sz_Number.length(); i++) {
			sz_Temp = sz_Number.substring(i, i + 1);
			if (!sz_Temp.equals(".")) {
				sz_RtnSumTol = sz_RtnSumTol
						+ charSum[Integer.parseInt(sz_Temp)];
			} else {
				sz_RtnSumTol = sz_RtnSumTol + ".";
			}
		}
		return sz_RtnSumTol;
	}

	/**
	 * 空字符串处理
	 */
	public static String NullToBlank(String s) {
		if ((s == null) || "".equals(s) || "null".equals(s)) {
			return "";
		} else {
			return s;
		}
	}

	/**
	 * 空字符串处理,如果为空返回0
	 */
	public static String isZero(String s) {
		if ((s == null) || "".equals(s) || "null".equals(s)) {
			return "0";
		} else {
			return s;
		}
	}

	public static String NullBlankToNbsp(String s) {
		if (s == null) {
			return "&nbsp;";
		} else if (s.trim().equals("")) {
			return "&nbsp;";
		} else {
			return s;
		}
	}

	public static String trimNull(float num) {

		if (num == 0.0) {

			return "";
		}
		return String.valueOf(num);
	}

	public static float trimTo(String num) {

		if (num.trim().equals("")) {

			return 0;
		}
		return new Float(num).floatValue();
	}

	public static String NullToBlanPercent(String s) {
		if (s == null) {
			return "";
		} else {
			return s + "%";
		}
	}

	/**
	 * 在字符左边填充或截断的字符串，使其达到固定的长度
	 * 
	 * @param srcStr
	 * @param num
	 * @param ch
	 * @return
	 */
	public static String lpad(String srcStr, int num, char ch) {
		String destStr = srcStr;
		if (srcStr.length() > num) {
			destStr = srcStr.substring(srcStr.length() - num);
		} else if (srcStr.length() < num) {
			StringBuffer strBuf = new StringBuffer(16);
			for (int i = 0; i < num - srcStr.length(); i++) {
				strBuf.append(ch);
			}
			destStr = strBuf.toString() + srcStr;
		}
		return destStr;
	}

	public static double getDouble(String s) {
		double d = 0.0;
		if (null == s || "null".equals(s) || "".equals(s)) {
			return d;
		} else {
			return Double.parseDouble(s);
		}
	}

	/**
	 * 检查字符是否满足长度要求 maxlength 0 不限制，minlength=0 不限制
	 */
	public static boolean checklength(String s, int maxlength, int minlength) {
		s = s.replaceAll("[^\\x00-\\xff]", "**");
		if ((s.length() >= minlength || minlength == 0)
				&& (s.length() <= maxlength || maxlength == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符是否满足长度要求 maxlength 0 不限制，minlength=0 不限制
	 */
	public static String clearSpace(String s) {
		s = replaceStr(s, " ", "");
		return s;
	}

	/**
	 * 检查字符字节长度是否满足长度要求 maxlength 0 不限制，minlength=0 不限制
	 */
	public static String checkBytelength(String s, int maxlength,
			int minlength, String errorInfo) {
		// s = s.replaceAll("[^\\x00-\\xff]", "**");
		if (s == null || "".equals(s)) {
			return errorInfo + "，不能为空!";
		} else {
			byte[] sb = s.getBytes();
			if ((sb.length >= minlength || minlength == 0)
					&& (sb.length <= maxlength || maxlength == 0)) {
				return "success";
			} else {
				return errorInfo + "，内容不符合规范(" + minlength / 2 + "~"
						+ maxlength / 2 + "个字符之间)";
			}
		}
	}

	public static String replaceStr(String s, String str, String str1) {
		s = s.replaceAll(str, str1);
		return s;
	}

	public static String parseNull(String str) {
		if (str == null || "".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
	
	
	public static String parseNullInd(String str) {
		
		if ("".equals(str)||"null".equals(str)) {
			return null;
		} else {
			return str.trim();
		}
	}
	
	public static String changeInd(String str,String accuracy) {
		if ("".equals(str)||"null".equals(str)) {
			return "--";
		} else {
		  try{
			  if("".equals(str)||"null".equals(accuracy)){
					return str.trim();
				}else{
				   String fmt = "#.";
				   for(int i=0;i<Integer.valueOf(accuracy);i++){
					   fmt = fmt+"0";
				   }
				   DecimalFormat df = new DecimalFormat(fmt);
				   return df.format(Double.valueOf(str));
				} 
		  }catch (Exception e) {
			e.printStackTrace();
			return "**";
		  }
	  }
	}

	/**
	 * 把字符串最后出现的","去掉
	 */
	public static String replaceLastSpild(String s) {
		if (s == null)
			return null;
		return s.substring(0, s.length() - 1);
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static long getUUIDLong() {
		return java.util.UUID.randomUUID().getLeastSignificantBits() * -1;
	}

	public static String getUUIDString() {
		return String.valueOf(java.util.UUID.randomUUID()
				.getLeastSignificantBits()
				* -1);
	}

	public static void main(String[] args) {
		System.out.println(getUUIDLong());
	}

	/**
	 * 用sign分隔字符串data
	 * 
	 * @param data
	 *            字符串
	 * @param sign
	 *            分隔符
	 * @return list 返回一个List
	 * @author BruceWang
	 */
	public static List<String> spit(String data, String sign) {
		StringTokenizer stkzer = new StringTokenizer(data, sign);
		String temp = null;
		List<String> list = new ArrayList<String>();
		while (stkzer.hasMoreTokens()) {
			temp = stkzer.nextToken();
			list.add(temp.trim());
		}
		return list;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static String TransferString2UTF8(String str) {
		try {
			return new String(str.getBytes("iso8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "转化发生错误!";
		}
	}
}
