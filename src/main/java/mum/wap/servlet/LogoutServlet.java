package mum.wap.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleLogoutRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleLogoutRequest(req, resp);
    }

    private void handleLogoutRequest(HttpServletRequest req, HttpServletResponse resp)throws IOException{

        //invalidate the session if exists
        HttpSession session = req.getSession();
        System.out.println("User="+session.getAttribute("user"));
        if(session != null){
            session.invalidate();
        }
        resp.sendRedirect("/lab-jsp-servlet/login");
    }
}

