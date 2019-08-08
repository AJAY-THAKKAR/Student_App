/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.noreply.studentApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ajay
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registrationServlet"})
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside method.......");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String to = request.getParameter("email");
            System.out.println("Inside method....... To email Id  " + to);

            Student std = new Student();
            std.setFirstName(request.getParameter("fname"));
            std.setLastName(request.getParameter("lname"));
            std.setAddress(request.getParameter("address"));
            std.setDob(request.getParameter("dob"));
            std.setGender(request.getParameter("gender"));
            std.setPincode(request.getParameter("pincode"));
            std.setContactNo(request.getParameter("contactNo"));
            std.setEmail(request.getParameter("email"));
            System.out.println("**********************************" + std);
            saveTheRecordToDatabase(std);
            
            boolean rs = false;
            String result="";
            if (to != null || to != "") {
                rs = sendEmail(to);
            }
            if (rs) {
                result= "Thank you for registering!!!!!Please check your email.";
            } else {
                result= "Please enter valid detail and try again..";
            }
            System.out.println("After process...");
            
            response.sendRedirect("index.jsp?result="+result);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside doPost method.......");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean sendEmail(String to) {
        String host = "smtp.gmail.com";
        final String user = "noreply.toautomated.mail@gmail.com";//change accordingly
        final String password = "NoReply@123";//change accordingly

        System.out.println("inside SendMail...... " + to);

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        System.out.println("before try...");

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("No-Reply to this automated mail.");
            message.setText("Dear Student,Thanks to you for registering with us.\n Regards,HR Team.");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveTheRecordToDatabase(Student std) {
        Connection con=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver class loaded and registered...........");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentApp", "root", "root");
            //here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            System.out.println(std);
            int rs = stmt.executeUpdate("INSERT INTO student(first_name,last_name,address,gender,pincode,email,dob,contactNo) VALUES ('" + std.getFirstName() + "','" + std.getLastName() + "','" + std.getAddress() + "','" + std.getGender() + "','" + std.getPincode() + "','" + std.getEmail() + "','" + std.getDob() + "','" + std.getContactNo() + "')");
            System.out.println("result of insert query...    " + rs);
            
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(con!=null)
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
