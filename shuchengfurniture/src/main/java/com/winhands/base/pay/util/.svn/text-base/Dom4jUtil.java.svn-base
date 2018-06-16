package com.winhands.base.pay.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
 
public class Dom4jUtil {
  private final static String defaultChar = "UTF-8";

	public static void writeDocToOut(Document doc,String sCharSet,OutputStream out) {
		OutputFormat fmt = new OutputFormat("",true,sCharSet);//输出流的格式
		try {
			org.dom4j.io.XMLWriter xmlWriter = new org.dom4j.io.XMLWriter(out,fmt);
			doc.setXMLEncoding(sCharSet);
			xmlWriter.write(doc);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  public static byte[] writeDocToBytes(Document doc,String sCharSet){
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    writeDocToOut(doc, sCharSet, bout);
    byte[] rtn = bout.toByteArray();
    try {
      bout.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public static byte[] writeDocToBytes(Document doc){
    return writeDocToBytes(doc, defaultChar);
  }
  
	public static void writeDocToFile(Document doc,String sCharSet,String sFN) {
		try {
			FileOutputStream fout = new FileOutputStream(sFN);
			writeDocToOut(doc, sCharSet, fout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String writeDocToStr(Document doc,String sCharSet){
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		writeDocToOut(doc, sCharSet, bout);
		try {
			return new String(bout.toByteArray(),sCharSet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Document getDocFromStream(InputStream in) {
		try {
			return new SAXReader().read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Document getDocFromStream(InputStream in,boolean fromReq) {
		if (fromReq){
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			try {
				IOUtils.copy(in,bout);
				return getDocFromBytes(bout.toByteArray(),"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else return getDocFromStream(in);
		return null;
	}
	
	public static Document getDocFromStr(String s) {
		return getDocFromStr(s, defaultChar);
	} 
	
	public static Document getJustDoc(String rootElmtName,String sCharSet){ 
		return getDocFromStr(MessageFormat.format("<?xml version=''1.0'' encoding=''{0}''?><{1}/>",new String[]{sCharSet,rootElmtName}));
	}

	public static Document getJustDoc(String rootElmtName){
		return getJustDoc(rootElmtName,defaultChar);
	}
	
  public static Document getDocFromBytes(byte[] dat,String sCharSet){
    InputStream is = new ByteArrayInputStream(dat);
    return getDocFromStream(is,sCharSet);
  }
  
  public static Document getDocFromBytes(byte[] dat){
    return getDocFromBytes(dat,defaultChar);
  }
  
	public static Document getDocFromStr(String s,String sCharSet) {
		try {
			ByteArrayInputStream bin = new ByteArrayInputStream(s.getBytes());
			return new SAXReader().read(bin,sCharSet);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Document getDocFromStream(InputStream in,String sCharSet) {
		try {
			InputStreamReader irer = new InputStreamReader(in,sCharSet);
			return new SAXReader().read(irer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Document getDocFromFile(String sFn) {
		try {
			return new SAXReader().read(sFn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,String> parseXMLMap(String xml) throws DocumentException{
		Map<String,String> result = new HashMap<String,String> ();
		Document document; 
		document = DocumentHelper.parseText(xml); 
		Element nodeElement = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> node = nodeElement.elements();
		for (Iterator<Element> it = node.iterator(); it.hasNext();) {
			Element elm = (Element) it.next(); 
			result.put(elm.getName(), elm.getText());
			elm = null;
		}
		node = null;  
        nodeElement = null;  
        document = null; 
		return result;
	}
		
	public static String serializeMapToXML(Map<String,String> map) {
		String result = "";
		if (map != null){
			Document doc = getDocFromStr("<?xml version='1.0' encoding='UTF-8'?><AttrDats></AttrDats>");
			Element root = doc.getRootElement();
			Element attribute = null;
			
			String keyStr = null;
			String valueStr = null;
			
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ){
				keyStr = (String) iter.next();
				valueStr = (String) map.get(keyStr);
				attribute = root.addElement("Attribute");
				attribute.addAttribute("Key",keyStr);
				attribute.addAttribute("Value",valueStr);
			}
			result = writeDocToStr(doc,"UTF-8");
		}
		return result;
	}

	
   //测试在文档之间拷贝元素
	private static void testCopyElmtOfDoc(){
		String sSourXML="<?xml version='1.0' encoding='gbk'?><DATAPACKET><METADATA><FIELDS><FIELD attrname='ID' fieldtype='fixed' WIDTH='20'/></FIELDS></METADATA></DATAPACKET>";
		Document doc = getDocFromStr(sSourXML);
		Document doc1 = getDocFromStr("<?xml version='1.0' encoding='UTF-8'?><DATAPACKET><aaa></aaa></DATAPACKET>");
		Element elmt = (Element)doc.selectSingleNode("/DATAPACKET/METADATA");
		elmt.setParent(null);
		((Element)doc1.selectSingleNode("/DATAPACKET/aaa")).add(elmt);
		System.out.println(doc1.asXML());
	}

	public static void main(String[] args) {
		testCopyElmtOfDoc();
		//System.out.println(getJustDoc("aa").asXML());
  }
}
