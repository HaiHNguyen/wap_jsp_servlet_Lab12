package mum.wap.servlet;


import mum.wap.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PostLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPostLoginForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         renderPostLoginForm(req, resp);

    }

    private void renderPostLoginForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        String username =  user.getUserName();
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Post Login From</title></head><body>");
        out.print("<form action = 'logout' method='post'>");
        out.print(String.format("<h1> Welcome %s!</h1>", username));

        out.print("<a href='/lab-jsp-servlet/logout'> Logout </a>");
        out.print("</form>");
        out.print("</body></html>");
    }
  }

