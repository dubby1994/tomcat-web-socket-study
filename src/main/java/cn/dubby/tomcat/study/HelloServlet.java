package cn.dubby.tomcat.study;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        resp.getWriter().write("Hello, " + name + ",Thread Id:" + Thread.currentThread().getId() + ",Thread Name:" + Thread.currentThread().getName());
    }
}
