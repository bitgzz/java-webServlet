package com.bitgzz.javaweb.sevlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: bitgzz
 * Created: 2018/7/27
 */
public class IndexServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("</head>")
                .append("<body>")
                .append("<h1>").append("Welcome to Java web !!!  on ")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date
                ()))
                .append("</h1>")
                .append("</body>")
                .append("</html>");
    }
    
    
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy");
    }
}
