package mum.wap.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends  HttpServlet {

    public void init() {
        try {
            System.out.println("Servlet " + this.getServletName() + " has started.");

            ServletContext servletContext = this.getServletContext();
            String initParam = servletContext.getInitParameter("support-email");
            System.out.println("Context param: " + initParam);

            ServletConfig servletConfig = this.getServletConfig();
            servletConfig.getInitParameter("database");
            System.out.println("Initial param: " + initParam);


            super.init();
        }catch( Exception ex){
            System.out.println(ex);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("<form method='post'>");
        out.print("<input name='userName' type='text' />");
        out.print("<p>Please click the button</p>");
        out.print("<input type='submit' value='Click me'/>");
        out.print("</form>");
        out.print("</body></html>");

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException{
        String input = req.getParameter("userName");
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("<p>Postback received: " + input + "</p>");
        out.print("</body></html>");
    }
}
