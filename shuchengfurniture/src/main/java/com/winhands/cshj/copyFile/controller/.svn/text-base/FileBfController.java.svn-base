package com.winhands.cshj.copyFile.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.winhands.base.web.BaseController;
import com.winhands.cshj.article.action.ArticleController;

@Controller
@RequestMapping("/fileBf")
public class FileBfController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class); 
	@RequestMapping("/fileList") 
	public ModelAndView fileList(){
		
		ModelAndView mv = new ModelAndView("forward:/view/dataCopy/fileList.jsp?ac=200026");//redirect模式  
		return mv;
	}
	
	public void  test(){
		String filePath = "";
		File file = new File(filePath);

		List<File> list = getFiles(filePath, new ArrayList<File>());
		 
		        if (list != null && list.size() > 0) {
		 
		            Collections.sort(list, new Comparator<File>() {
		                public int compare(File file, File newFile) {
		                    if (file.lastModified() < newFile.lastModified()) {
		                        return 1;
		                    } else if (file.lastModified() == newFile.lastModified()) {
		                        return 0;
		                    } else {
		                        return -1;
		                    }
		 
		                }
		            });
		 
		        }

	}
	
	 public  List<File> getFiles(String realpath, List<File> files) {
		 
	        File realFile = new File(realpath);
	        if (realFile.isDirectory()) {
	            File[] subfiles = realFile.listFiles();
	            for (File file : subfiles) {
	                if (file.isDirectory()) {
	                    getFiles(file.getAbsolutePath(), files);
	                } else {
	                    files.add(file);
	                }
	            }
	        }
	        return files;
	    }
}
