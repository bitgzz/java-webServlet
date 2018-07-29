package com.bitgzz.javaweb.sevlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: bitgzz
 * Created: 2018/7/28
 */
public class FormServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Form</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\" action=\"/form\">\n" +
                "    <input type=\"text\" name=\"name\" value=\"\" placeholder=\"请输入姓名\">\n" +
                "    <input type=\"submit\" value=\"提交\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }
    
    @Override
    //做出响应
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得参数名字
        String name = req.getParameter("name");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                .append("<meta charset='UTF-8'>")
                .append("<title>Form</title>")
                .append("</head>")
                .append("<body>")
                .append("<h1>")
                .append("欢迎，")
                .append(name)
                .append("</h1>")
                .append("</body>")
                .append("</html>");
        
    }
}
