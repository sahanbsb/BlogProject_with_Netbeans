/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post_store;

/**
 *
 * @author Sahan
 */
public class Welcome_guest extends HttpServlet {

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
            
            ServletContext servletContext = request.getSession().getServletContext();
            Post_store.root = servletContext.getRealPath("/");
            Post_store.root = Post_store.root + "/";
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Blog Project</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome To Project Blog !!!</h1>");
            
            int noOfPosts = Post_store.getlastid()-1;
            int limit = 10;
            for(int i=noOfPosts;i>0 && limit>=0;i--,limit--){
                String postTitle = Post_store.getposttitle(i);
                out.println("<a href=\"/BlogProject/show_post_guest?id="+i+"\"><h2>"+postTitle+"</h2></a>");
            }
            
            out.println("<br>");
            
            //out.println("<input type=button value=\"User Login\" onclick = \"document.location.href = '\\userpage'\"/>");
            
            out.println("<input type=button value=\"User Page\" onclick = \"parent.location='new_post'\"/>");
            
            //out.println("<input type=button value=\"Admin Page\" onclick = \"parent.location='adminpage'\"/>");
            
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
