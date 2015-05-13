/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post_store;

/**
 *
 * @author Sahan
 */
public class Show_post extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            int id = Integer.parseInt(request.getParameter("id"));
            String title = Post_store.getposttitle(id);
            String content = Post_store.getpostcontent(id);
            List<String> comments = Post_store.getpostcomments(id);
            List<String> UA_comments = Post_store.getpostUAcomments(id);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>"+title+"</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><u>"+title+"</u></h1>");
            out.println("<p>"+content+"</p>");
            out.println("<h2><u>Comments</u></h2>");
            
            for(String i : comments){
                out.println(i+"<br>");
            }
            
            out.println("<h2><u>Un Approved Comments</u></h2>");
            
            for(String i : UA_comments){
                out.println(i+"   <a href=\"/BlogProject/User/Approve_comment?choice=1&id="+id+"&comment="+i+"\">Approve</a>");
                out.println("   <a href=\"/BlogProject/User/Approve_comment?choice=0&id="+id+"&comment="+i+"\">Remove</a><br>");
            }
            
            out.println("<br><form action=\"/BlogProject/User/Add_comment\">");
            out.println("Enter your comment:<br>");
            out.println("<input type=\"text\" name=\"comment\" value=\"\"><br>");
            out.println("<input type=\"hidden\" name=\"id\" value=\""+id+"\">");
            out.println("<input type=\"submit\" value=\"Comment\">");
            out.println("</form>");
            
            out.println("<br><br><a href=\"/BlogProject/User/Edit_post?id="+id+"\">Edit Post</a>");
            out.println("<br><br><a href=\"/BlogProject/new_post\">Home</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
