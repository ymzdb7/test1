package com.winhands.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipUtil {
    /**使用GBK编码可以避免压缩中文文件名乱码*/
    private static final String CHINESE_CHARSET = "GBK";
    /**文件读取缓冲区大小*/
    private static final int CACHE_SIZE = 1024;
	/**
	 * 创建目录
	 * 
	 * @param path
	 *            目录绝对路径名
	 */
	static void createDir(String path) {
		File dir = new File(path);
		if (dir.exists() == false)
			dir.mkdir();
	}

	/**
	 * 取得文件名,不包含后缀名
	 * 
	 * @param name
	 *            完整文件名
	 * @return 文件名(不包含后缀名)
	 */
	static String getSuffixName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	public static void main(String[] args) throws Exception {
		System.out.println("1111");
		//"D:/123/123.zip"
		unZip("D:/test/1.zip","D:/test/1018/","1234567");
	}

	/**
	 * 解压zip文件
	 * 
	 * @param zipFilePath
	 *            zip文件绝对路径
	 * @param unzipDirectory
	 *            解压到的确
	 * @throws Exception
	 */
	public static void unZip(String zipFilePath, String destDir,String article_id) {
        ZipFile zipFile = null;
        try {
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
            Enumeration<ZipEntry> zipEntries = zipFile.getEntries();
            File file, parentFile;
            ZipEntry entry;
            byte[] cache = new byte[CACHE_SIZE];
            while (zipEntries.hasMoreElements()) {
                entry = (ZipEntry) zipEntries.nextElement();
                String entryName = new String(entry.getName());
    			if (entry.isDirectory()) {
    				System.out.println("目录:" + entry.getName());
                    new File(destDir + article_id +"/"+ entry.getName()).mkdirs();
                    continue;
                }
                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                System.out.println("文件:" + entry.getName());
                if (entry.getName().contains(".html")) {
					String[] htmlDir = entry.getName().split("/");
					String htmlName = htmlDir[htmlDir.length - 1];
					entryName = entryName.replace(htmlName, article_id
							+ ".html");
				}
                file = new File(destDir + article_id+"/" + entryName);
                parentFile = file.getParentFile();
                if(parentFile != null && (!parentFile.exists())) {
                    parentFile.mkdirs();
                }
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos, CACHE_SIZE);
                int readIndex = 0;
                while ((readIndex = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                    fos.write(cache, 0, readIndex);
                }
                bos.flush();
                bos.close();
                fos.close();
                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
