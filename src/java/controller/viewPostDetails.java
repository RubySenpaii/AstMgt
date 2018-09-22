/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ForumDB;
import entity.Forum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bryll Joey Delfin
 */
public class viewPostDetails extends HttpServlet {

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
            Forum item = new Forum();
            ForumDB fdb = new ForumDB();
            String line = request.getParameter("id");
            String[] list = line.split(",");
            item.setId(Integer.parseInt(list[0]));
            ArrayList<Forum> comments = new ArrayList<>();
            ArrayList<String> images = new ArrayList<>();
            Forum post  = new Forum();
            post = fdb.getForumDetail(item.getId());
            int fid = post.getFields_id();
            System.out.println(fid + " FID IS THIS");
            comments =  fdb.getCommentDetails(item.getId());
            if(post.getProb_id()!= null){
                post.setName(post.getProblem_name());
            }else if(post.getRecom_id()!= null){
                post.setName(post.getRecommendation_name());
            }else{
                post.setName("N/A");
            }
            for(int i= 0 ; i<comments.size();i++){
                System.out.println("Farmer : " +comments.get(i).getComment_User() );
                System.out.println("Message : " + comments.get(i).getComment_message() );
            }
            for(int i= 0 ; i<post.getImage().size();i++){
                System.out.println(post.getImage().get(i)+ " YEAH BITCH");
            }
            if(post!=null){
                HttpSession session = request.getSession();
                session.setAttribute("comments", comments);
                session.setAttribute("post", post);
                session.setAttribute("fid", fid);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Post.jsp");
                rd.forward(request, response);
            }
            else{
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Homepage.jsp");
                rd.forward(request, response);
            }
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
