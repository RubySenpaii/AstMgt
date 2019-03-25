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
import objects.ExpenditureLimit;
import objects.ExpenditureTracking;
import report.Equipment;
import report.Asset;
import report.AssetRepair;
import report.Expenditure;
import report.ReportService;
import report.ReportingModule;
import report.RequestParameter;
import report.SpecificEquipment;
import services.AssetService;
import services.EquipmentService;
import services.ExpenditureLimitService;
import services.ExpenditureTrackingService;
import services.RepairLogService;

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
                    url = GenerateSpecificPropertyPlantEquipmentReport(request);
                    break;
                case "GeneralSupplies":
                    url = DirectToPage(request, "general-supplies");
                    break;
                case "GenerateGeneralSupplies":
                    url = GenerateSuppliesReport(request);
                    break;
                case "SpecificSupplies":
                    url = DirectToPage(request, "specific-supplies");
                    break;
                case "GenerateExpenditure":
                    url = GenerateExpenditureReport(request);
                    break;
                case "Expenditure":
                    url = DirectToPage(request, "expenditure");
                    break;
                case "GenerateAssetRepair":
                    url = GenerateAssetRepairReport(request);
                    break;
                case "AssetRepair":
                    url = DirectToPage(request, "asset-repair");
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
            RequestParameter reqParameter = new RequestParameter();
            ArrayList<Asset> assets = new ReportService().GetGeneralPPEData();
            logo += File.separator + "darlogo.jpg";
            reqParameter.Logo = logo;
            reqParameter.CertifiedBy = request.getParameter("certified-by");
            reqParameter.ApprovedBy = request.getParameter("approved-by");
            reqParameter.VerifiedBy = request.getParameter("verified-by");
            String jasperFile = jasperPath + File.separator + "PropertyPlantEquipmentReport.jasper";
            String fileName = pdfReportsPath + File.separator + "general-ppe" + File.separator + "PropertyPlantEquipmentReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            reports.createPropertyPlantEquipment(reqParameter, jasperFile, fileName, assets);
        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/GeneralPPE";
    }
    
    private String GenerateSpecificPropertyPlantEquipmentReport(HttpServletRequest request) {
        try {
            SpecificEquipment equipment = new ReportService().GetSpecificEquipmentDetails(request.getParameter("asset-name"));
            RequestParameter reqParameter = new RequestParameter();
            logo += File.separator + "darlogo.jpg";
            reqParameter.Logo = logo;
            reqParameter.CertifiedBy = request.getParameter("certified-by");
            reqParameter.ApprovedBy = request.getParameter("approved-by");
            reqParameter.VerifiedBy = request.getParameter("verified-by");
            String jasperFile = jasperPath + File.separator + "SpecificPropertyPlantEquipment.jasper";
            String fileName = pdfReportsPath + File.separator + "specific-ppe" + File.separator + equipment.AssetName + "ReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            reports.createSpecificPropertyPlantEquipment(reqParameter, jasperFile, fileName, equipment);
        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/SpecificPPE";
    }
    
    private String GenerateSuppliesReport(HttpServletRequest request) {
        try {
            RequestParameter reqParameter = new RequestParameter();
            ArrayList<Asset> assets = new ReportService().GetGeneralSuppliesData();
            logo += File.separator + "darlogo.jpg";
            String jasperFile = jasperPath + File.separator + "GeneralSuppliesReport.jasper";
            reqParameter.Logo = logo;
            reqParameter.CertifiedBy = request.getParameter("certified-by");
            reqParameter.ApprovedBy = request.getParameter("approved-by");
            reqParameter.VerifiedBy = request.getParameter("verified-by");
            String fileName = pdfReportsPath + File.separator + "general-supplies" + File.separator + "SuppliesReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            reports.createPropertyPlantEquipment(reqParameter, jasperFile, fileName, assets);
        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/GeneralSupplies";
    }
    
    private String GenerateExpenditureReport(HttpServletRequest request) {
        try {
            ExpenditureLimit procurement = new ExpenditureLimitService().GetExpenditureLimitForYear(Calendar.getInstance().get(Calendar.YEAR), "Procurement");
            ExpenditureLimit personnel = new ExpenditureLimitService().GetExpenditureLimitForYear(Calendar.getInstance().get(Calendar.YEAR), "Personnel");
            ExpenditureLimit general = new ExpenditureLimitService().GetExpenditureLimitForYear(Calendar.getInstance().get(Calendar.YEAR), "General");
            ExpenditureLimit records = new ExpenditureLimitService().GetExpenditureLimitForYear(Calendar.getInstance().get(Calendar.YEAR), "Records");
            ExpenditureLimit admin = new ExpenditureLimitService().GetExpenditureLimitForYear(Calendar.getInstance().get(Calendar.YEAR), "Admin");
            ExpenditureTracking procurementTracking = new ExpenditureTrackingService().GetCurrentExpenditure("Procurement");
            ExpenditureTracking personnelTracking = new ExpenditureTrackingService().GetCurrentExpenditure("Personnel");
            ExpenditureTracking generalTracking = new ExpenditureTrackingService().GetCurrentExpenditure("General");
            ExpenditureTracking recordsTracking = new ExpenditureTrackingService().GetCurrentExpenditure("Records");
            ExpenditureTracking adminTracking = new ExpenditureTrackingService().GetCurrentExpenditure("Admin");
            
            ArrayList<Expenditure> expenditures = new ArrayList<>();
            Expenditure procurementExpenditure = new Expenditure();
            procurementExpenditure.setName("Procurement");
            procurementExpenditure.setAllocated(procurement.Equipment);
            procurementExpenditure.setCurrent(procurement.Equipment - procurementTracking.Equipment);
            expenditures.add(procurementExpenditure);
            
            Expenditure personnelExpenditure = new Expenditure();
            personnelExpenditure.setName("Personnel");
            personnelExpenditure.setAllocated(personnel.Equipment);
            personnelExpenditure.setCurrent(personnel.Equipment - personnelTracking.Equipment);
            expenditures.add(personnelExpenditure);
            
            Expenditure generalExpenditure = new Expenditure();
            generalExpenditure.setName("General");
            generalExpenditure.setAllocated(general.Equipment);
            generalExpenditure.setCurrent(general.Equipment - generalTracking.Equipment);
            expenditures.add(generalExpenditure);
            
            Expenditure recordsExpenditure = new Expenditure();
            recordsExpenditure.setName("Records");
            recordsExpenditure.setAllocated(records.Equipment);
            recordsExpenditure.setCurrent(records.Equipment - recordsTracking.Equipment);
            expenditures.add(recordsExpenditure);
            
            Expenditure adminExpenditure = new Expenditure();
            adminExpenditure.setName("Admin");
            adminExpenditure.setAllocated(admin.Equipment);
            adminExpenditure.setCurrent(admin.Equipment - adminTracking.Equipment);
            expenditures.add(adminExpenditure);
            
            RequestParameter reqParameter = new RequestParameter();
            logo += File.separator + "darlogo.jpg";
            String jasperFile = jasperPath + File.separator + "ExpenditureReport.jasper";
            reqParameter.Logo = logo;
            reqParameter.CertifiedBy = request.getParameter("certified-by");
            reqParameter.ApprovedBy = request.getParameter("approved-by");
            reqParameter.VerifiedBy = request.getParameter("verified-by");
            String fileName = pdfReportsPath + File.separator + "expenditure" + File.separator + "ExpenditureReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            System.out.println("reportservlet 235");
            reports.createExpenditureReport(reqParameter, jasperFile, fileName, expenditures);
            
        } catch (Exception ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/Expenditure";
    }
    
    private String GenerateAssetRepairReport(HttpServletRequest request) {
        try {
            ArrayList<AssetRepair> assetRepairs = new ArrayList<>();
            ArrayList<objects.Asset> assets = new AssetService().GetAssets();
            for (objects.Asset asset: assets) {
                ArrayList<objects.Equipment> equipments = new EquipmentService().GetListOfEquipmentsWithAssetName(asset.AssetName);
                int totalCount = 0;
                double totalAmount = 0;
                for (objects.Equipment equipment: equipments) {
                    ArrayList<objects.RepairLog> repairs = new RepairLogService().GetApprovedRepairLogs(equipment.AssetTag);
                    totalCount += repairs.size();
                    for (objects.RepairLog repair: repairs) {
                        totalAmount += repair.TotalCost;
                    }
                }
                AssetRepair assetRepair = new AssetRepair();
                assetRepair.setName(asset.AssetName);
                assetRepair.setType(asset.AssetType);
                assetRepair.setRepairCount(totalCount);
                assetRepair.setTotalRepair(totalAmount);
                assetRepairs.add(assetRepair);
            }
            
            RequestParameter reqParameter = new RequestParameter();
            logo += File.separator + "darlogo.jpg";
            String jasperFile = jasperPath + File.separator + "RepairReport.jasper";
            reqParameter.Logo = logo;
            reqParameter.CertifiedBy = request.getParameter("certified-by");
            reqParameter.ApprovedBy = request.getParameter("approved-by");
            reqParameter.VerifiedBy = request.getParameter("verified-by");
            String fileName = pdfReportsPath + File.separator + "asset-repair" + File.separator + "RepairReportAsOf" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()) + ".pdf";
            System.out.println("reportservlet 250 method");
            reports.createRepairReport(reqParameter, jasperFile, fileName, assetRepairs);
            
        } catch (Exception ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/ReportServlet/AssetRepair";
    }
}
