package com.amzi.servlets;  
  
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import com.amzi.dao.LoginDao;  
import mappable.User;
  
public class LoginServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        String n=request.getParameter("username");    
        String p=request.getParameter("password");   
  
        User user = User.find_by_authentication(n,p);
        if(user != null){
        	out.print("Test");
            HttpSession session = request.getSession(false);  
            if(session!=null)  
            session.setAttribute("name", n);  

            RequestDispatcher rd=request.getRequestDispatcher("/Login/welcome.jsp");    
            rd.forward(request,response);
        }    
        else{    
            //out.print("<p style=\"color:red\">Sorry username or password error</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("Login/login.jsp");
            response.addHeader("login", "fail");
            rd.forward(request,response);
        }    
  
        out.close();    
    }    
}   