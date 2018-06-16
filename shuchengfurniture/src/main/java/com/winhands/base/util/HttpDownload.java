package com.winhands.base.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class HttpDownload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			//request.setCharacterEncoding("UTF-8");
			String path = request.getParameter("filepath");
			// path是指欲下载的文件的路径。
			if (path != null && !path.trim().equals("")) {
				path = new String(path.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println("filepath=" + path);
				String filename = path.substring(path.lastIndexOf("/") + 1);
				// 清空response
				response.reset();
				// 设置response的Header
				response.setHeader("Content-Type", "application/octet-stream");
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);
				// response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = response.getOutputStream();
//				this.getFile(path, toClient);
				System.out.println("发送成功");
			} else {
				System.out.println("文件名为空");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
