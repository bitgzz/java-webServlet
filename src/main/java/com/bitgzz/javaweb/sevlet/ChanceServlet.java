package com.bitgzz.javaweb.sevlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: bitgzz
 * Created: 2018/7/27
 */
public class ChanceServlet extends HttpServlet {
    
    private int chanceNumber;
    
    private ServletContext context;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //获取初始化参数的值
        chanceNumber = Integer.parseInt(config.getInitParameter("chanceNumber"));
        //ServletContext,可以共享从应用程序中的所有资料处访问到的信息，并且可以动态注册web对象。
        //共享信息可以将对象保存在ServletContext中的一个内部Map中，保存在ServletContext中的对象被称作属性。
        context = config.getServletContext();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("</head>")
                .append("<body>");
        String value = req.getParameter("name");
        Integer number = (Integer) context.getAttribute(value);
        if (number == null) {
            number = chanceNumber;
            context.setAttribute(value, number);
        }
        if (number > 0) {
            number = number - 1;
            context.setAttribute(value, number);
            writer.append("<h1>").append("剩余").append(String.valueOf(number)).append("次机会").append("</h1>");
        } else {
            writer.append("<h1>").append("没机会了").append("</h1>");
        }
        writer.append("</body>").append("</html>");
    }
    
    
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy");
    }
}
