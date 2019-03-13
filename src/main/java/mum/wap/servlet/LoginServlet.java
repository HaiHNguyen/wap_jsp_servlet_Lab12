package mum.wap.servlet;

import mum.wap.dao.UserDAO;
import mum.wap.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderLoginForm(req, resp);
        req.setAttribute("error", "");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();
        String username =  req.getParameter("username");
        String password =  req.getParameter("password");
        String rememberMe = req.getParameter("rememberme");
        User loggedUser =  userDAO.login(username, password);

        if(loggedUser != null) {
            //stored logged user on session
            HttpSession session = req.getSession();
            session.setAttribute("user", loggedUser);


            int maxInactiveInterval;
            if(rememberMe != null && rememberMe.equals("on")) {
                maxInactiveInterval = 30 * 24 * 60 * 60;

                //Set cookies
                ServletHelper helper = new ServletHelper();
                resp.addCookie(helper.createCookie("user", maxInactiveInterval));

            }else{
                maxInactiveInterval = 2*60;
                ServletHelper helper = new ServletHelper();
                helper.deleteCookie(req);
            }
            session.setMaxInactiveInterval(maxInactiveInterval);
            resp.sendRedirect("/lab-jsp-servlet/postLogin");

        }else{
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("error", "Invalid username or password!");
            renderLoginForm(req, resp);
        }

    }
    private void turnCookiesOn(HttpServletResponse resp, String userName){

    }
    private void turnCookiesOff(){

    }
    private void renderLoginForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String error = (String)req.getAttribute("error");
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Login</title></head><body>");
        out.print("<form action = 'login' method='post'>");
        out.print("<div> User Name: </div>");
        String userNameElement = "<input name='username', value = '%s' type='text' size='30'></input>";
        if(error == null || error.isEmpty()){
            userNameElement = String.format(userNameElement, "");
        }else{
            String username = (String)req.getAttribute("username");
            userNameElement = String.format(userNameElement, username);
        }
        out.print(userNameElement);

        out.print("<div> Password: </div>");
        String passwordElement = "<input name='password', type='password' size='30'></input>";
        if(error== null || error.isEmpty()){
            passwordElement = String.format(passwordElement, "");
        }else{
            String password = (String)req.getAttribute("password");
            passwordElement = String.format(passwordElement, password);
        }
        out.print(passwordElement);

        //Render remember checkbox
        ServletHelper helper = new ServletHelper();
        Cookie cookie = helper.getCookie(req);
        String isChecked = "";
        if(cookie != null) {
            isChecked = "checked";
        }
        String checkBoxElement = "<div> <label> <input type = 'checkbox' name='rememberme' %s </input> Remember me </label><div>";
        out.print(String.format(checkBoxElement, isChecked));

        out.print("<div><input type='submit' value='Login'/></div>");

        String infoElement = "<p>%s</p>";
        if(error == null || error.isEmpty()){
            infoElement = String.format(infoElement, "");
        }else{
            infoElement = String.format(infoElement, req.getAttribute("error"));
        }
        out.print(infoElement);
        out.print("</body></html>");
    }
}
