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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import report.Asset;
import report.ReportService;
import report.ReportingModule;
import services.AssetService;

/**
 *
 * @author rubysenpaii
 */
public class ReportServlet extends BaseServlet {
    
    private ReportingModule reports = new ReportingModule();
    private String logo;
    private String jasperPath;
    private String pdfReportsPath;

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            String url;
            logo = getServletContext().getRealPath("/img");
            jasperPath = getServletContext().getRealPath("/jasper");
            pdfReportsPath = getServletContext().getRealPath("/pdf");
            
            switch (action.split("/")[action.split("/").length - 1]) {
                case "GeneralPPE":
                    url = DirectToPage(request, "general-ppe");
                    break;
                case "GenerateGeneralPPE":
                    url = GenerateGeneralPropertyPlantEquipmentReport(request);
                    break;
                case "SpecificPPE":
                    url = DirectToPage(request, "specific-ppe");
                    break;
                case "GenerateSpecificPPE":
                case "GeneralSupplies":
                    url = DirectToPage(request, "general-supplies");
                    break;
                case "GenerateGeneralSupplies":
                    url = GenerateSuppliesReport(request);
                    break;
                case "SpecificSupplies":
                    url = DirectToPage(request, "specific-supplies");
                    break;
                case "GenerateSpecificSupplies":
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
    
    private String DirectToPage(HttpServletRequest request, String folder) {
        pdfReportsPath += File.separator + folder;
        File file = new File(pdfReportsPath);
        String fileNames[] = file.list();
        ArrayList<String> filtered = new ArrayList<>();
        for (int i = 0; i < fileNames.length; i++) {
            if (!fileNames[i].equals("placeholder.md")) {
                filtered.add(fileNames[i]);
            }
        }
        Collections.sort(filtered);
        HttpSession session = request.getSession();
        session.setAttribute("fileList", filtered);
        return "/report/" + folder + ".jsp";
    }
    
    private String GenerateGeneralPropertyPlantEquipmentReport(HttpServletRequest request) {
        try {
            ArrayList<Asset> assets = new ReportService().GetGeneralPPEData();
            logo += File.separator + "darlogo.jpg";
            String jasperFile = jasperPath + File.separator + "PropertyPlantEquipmentReport.jasper";
            String fileName = pdfReportsPath + File.separator + "general-ppe" + File.separator + "PropertyPlantEquipmentReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            reports.createPropertyPlantEquipment(logo, jasperFile, fileName, assets);
        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/GeneralPPE";
    }
    
    private String GenerateSuppliesReport(HttpServletRequest request) {
        try {
            ArrayList<Asset> assets = new ReportService().GetGeneralSuppliesData();
            logo += File.separator + "darlogo.jpg";
            String jasperFile = jasperPath + File.separator + "GeneralSuppliesReport.jasper";
            String fileName = pdfReportsPath + File.separator + "general-supplies" + File.separator + "SuppliesReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            reports.createPropertyPlantEquipment(logo, jasperFile, fileName, assets);
        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/GeneralSupplies";
    }
}
