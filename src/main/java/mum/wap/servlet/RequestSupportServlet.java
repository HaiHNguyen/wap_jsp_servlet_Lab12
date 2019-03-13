package mum.wap.servlet;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RequestSupportServlet extends  HttpServlet {


    public void init() {
        try {
            System.out.println("Servlet " + this.getServletName() + " has started.");

            super.init();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        renderRequestForm(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
            renderReplyForm(req, resp);
    }

    private void renderRequestForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Request Support Form</title></head><body>");
        out.print("<form method='post'>");
        out.print("<div> Name: </div>");
        out.print("<input name='Name', type='text' size='30'></p>");
        out.print("<div> Email: </div>");
        out.print("<input name='email', type='text' size='30'></p>");
        out.print("<div> Problem: </div>");
        out.print("<input name='problem', type='text' size='60'></p>");
        out.print("<div> Problem Description: </div>");
        out.print("<textarea rows='10' cols='57'>Describe your problem here</textarea> </p>");
        out.print("<div><input type='submit' value='Help'/></div>");
        out.print("</form>");
        out.print("</body></html>");
    }
    private void renderReplyForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ServletContext servletContext = this.getServletContext();
        String supportEmail = servletContext.getInitParameter("support-email");
        String name = req.getParameter("Name");
        String email = req.getParameter("email");
        Random rand = new Random();
        int no = rand.nextInt(1000);
        String ticketNumber = String.format("%06d", no);

        String replyText = "Thank you! %s for contacting us." +
                " You should receive reply from us with in 24 hrs in your email address %s. " +
                "Let us know in our support email %s if you don't receive reply within 24 hrs." +
                " Please be sure to attach your reference %s in your email.";
        replyText = String.format(replyText, name, email, supportEmail, ticketNumber);

        PrintWriter out = resp.getWriter();
        out.print("<p>" + replyText +  "</p>");
    }
}