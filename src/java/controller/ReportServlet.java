/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.SharedFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import report.Asset;
import report.ReportService;
import report.ReportingModule;
import services.AssetService;

/**
 *
 * @author rubysenpaii
 */
public class ReportServlet extends HttpServlet {

    private ReportingModule reports = new ReportingModule();
    private String logo;
    private String jasperPath;
    private String pdfReportsPath;
    
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
        String action = request.getRequestURI();
        try {
            String url;
            logo = getServletContext().getRealPath("/img");
            jasperPath = getServletContext().getRealPath("/reports");
            pdfReportsPath = getServletContext().getRealPath("/pdf");
            
            switch (action.split("/")[action.split("/").length - 1]) {
                case "GeneratePropertyPlantEquipmentReport":
                    url = generatePropertyPlantEquipmentReport(request);
                    break;
                default:
                    url = "";
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }
    
    private String generatePropertyPlantEquipmentReport(HttpServletRequest request) {
        try {
            ArrayList<Asset> assets = new ReportService().GetAssets();
            logo += File.separator + "darlogo.jpg";
            String jasperFile = jasperPath + File.separator + "PropertyPlantEquipmentReport.jasper";
            String fileName = pdfReportsPath + File.separator + "PropertyPlantEquipmentReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            reports.createPropertyPlantEquipment(logo, jasperFile, fileName, assets);
        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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
