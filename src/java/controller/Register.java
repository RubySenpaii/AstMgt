/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.SharedFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Employee;
import services.EmployeeService;

/**
 *
 * @author Bryll Joey Delfin
 */
public class Register extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            Employee oneUser = new Employee();
            EmployeeService employeeDB = new EmployeeService();
            SharedFormat sf = new SharedFormat();
            int eid = employeeDB.FindAllEmployee().size() + 1;
            oneUser.EmployeeId = eid;
            oneUser.LastName = request.getParameter("LastName");
            oneUser.FirstName = request.getParameter("FirstName");
            oneUser.Email = request.getParameter("Email");
            oneUser.ContactNumber = request.getParameter("ContactNo");
            oneUser.EntityName = request.getParameter("EntityName");
            oneUser.Office = request.getParameter("Office");
            oneUser.Division = request.getParameter("Division");
            oneUser.CivilStatus = request.getParameter("CivilStatus");
            oneUser.BirthDate = sf.stringToDate(request.getParameter("BirthDate"));
            oneUser.Gender = request.getParameter("Gender");
            oneUser.EmployeeStatus = request.getParameter("EmployeeStatus");
            oneUser.Username = request.getParameter("Username");
            oneUser.Password = request.getParameter("Password");
            oneUser.StartDate = new Date(System.currentTimeMillis());
            oneUser.EndDate = sf.stringToDate(request.getParameter("EndDate"));
            oneUser.Flag = Integer.parseInt(request.getParameter("Flag"));
            oneUser.UserLevel = request.getParameter("UserLevel");
            System.out.println("Adding user ......" + Calendar.getInstance().getTime());
            int checker = employeeDB.AddEmployee(oneUser);
            System.out.println("Returning with object : " + checker);
            if (checker != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("user", checker);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            } else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/404.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/404.jsp");
            rd.forward(request, response);
        } finally {
            out.close();
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
