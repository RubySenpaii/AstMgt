package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Employee;
import objects.ExpenditureTracking;
import services.EmployeeService;
import services.ExpenditureTrackingService;
// THIS IS THE CODE TO GET TODAYS' DIFFERENT DETAILS (JUST COPY PASTE IT 
//TO THE METHODS AND AVOID USING THIS IN SERVLETS AS TO LESSEN ERRORS
//AND MAKE THINGS MORE READABLE)               
//CalendarDB caldb= new CalendarDB();
//ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
//int cropyr=calist.get(0).getYear();

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Entering Log In Service : " + Calendar.getInstance().getTime());
        try {
            Employee oneUser = new Employee();
            EmployeeService employeeDB = new EmployeeService();
            oneUser.Username = request.getParameter("username");
            oneUser.Password = request.getParameter("password");
            System.out.println("Authenticating user ......" + Calendar.getInstance().getTime());
            Employee checker = employeeDB.Authenticate(oneUser.Username, oneUser.Password);
            System.out.println("Returning with object : " + checker);
            HttpSession session = request.getSession();
            if (checker != null) {
                System.out.println("AHHHHHHHHHHHHHHHHHHHHH " + checker.Division );
                session.setAttribute("UserDivision", checker.Division);
                session.setAttribute("UserLevel", checker.UserLevel);
                session.setAttribute("user", checker);
                session.setAttribute("UserName", checker.FullName());
                boolean logged = true;
                session.setAttribute("logged", logged);
                
                ExpenditureTracking limit = new ExpenditureTrackingService().GetCurrentExpenditure(checker.Division);
                session.setAttribute("limit", limit);
                
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/HomeServlet");
                rd.forward(request, response);
            } else {
                boolean logged = false;
                session.setAttribute("logged", logged);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
            boolean logged = false;
            HttpSession session = request.getSession();
            session.setAttribute("logged", logged);
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
