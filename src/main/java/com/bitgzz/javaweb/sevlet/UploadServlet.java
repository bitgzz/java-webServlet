package com.bitgzz.javaweb.sevlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Author: bitgzz
 * Created: 2018/7/28
 */
public class UploadServlet extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取上传请求中的文件
        Part part = req.getPart("filename");
        
        InputStream is = part.getInputStream();
        //获取上传文件的路径
        String appUploadPath = req.getServletContext().getRealPath("/upload");
        //1.jpg
        String filename = part.getSubmittedFileName();
        
        // --> /upload/abc.jpg
        
        File file = new File(appUploadPath, filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        
        OutputStream os = new FileOutputStream(file);
        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        os.close();
        is.close();
        
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                .append("<meta charset='UTF-8'>")
                .append("<title>File</title>")
                .append("</head>")
                .append("<body>")
                .append("<a href='")
                .append("/upload/").append(part.getSubmittedFileName())
                .append("'>")
                .append("<img src='")
                .append("/upload/").append(part.getSubmittedFileName())
                .append("'>")
                .append("上传的文件")
                .append("</a>")
                .append("</body>")
                .append("</html>");
    }
}
