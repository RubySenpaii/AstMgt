/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import report.Asset;

/**
 *
 * @author rubysenpaii
 */
public class ReportingModule {
    
    public void createPropertyPlantEquipment(RequestParameter reqParameter, String jasperFile, String fileName, ArrayList<Asset> assets) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", reqParameter.Logo);
        parameters.put("approvedBy", reqParameter.ApprovedBy);
        parameters.put("certifiedBy", reqParameter.CertifiedBy);
        parameters.put("verifiedBy", reqParameter.VerifiedBy);
        JasperPrint jasperPrint;

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(assets, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    
    public void createSpecificPropertyPlantEquipment(RequestParameter reqParameter, String jasperFile, String fileName, SpecificEquipment equipment) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", reqParameter.Logo);
        parameters.put("approvedBy", reqParameter.ApprovedBy);
        parameters.put("certifiedBy", reqParameter.CertifiedBy);
        parameters.put("verifiedBy", reqParameter.VerifiedBy);
        parameters.put("assetName", equipment.AssetName);
        parameters.put("disposed", equipment.Disposed);
        parameters.put("used", equipment.BeingUsed);
        parameters.put("extended", equipment.Extended);
        parameters.put("expiring", equipment.Expiring);
        parameters.put("stocked", equipment.Stocked);
        JasperPrint jasperPrint;

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(equipment.Equipments, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    public void createExpenditureReport(RequestParameter reqParameter, String jasperFile, String fileName, ArrayList<Expenditure> expenditures) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", reqParameter.Logo);
        parameters.put("approvedBy", reqParameter.ApprovedBy);
        parameters.put("certifiedBy", reqParameter.CertifiedBy);
        parameters.put("verifiedBy", reqParameter.VerifiedBy);
        JasperPrint jasperPrint;
        
        System.out.println("module 78");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(expenditures, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    public void createExpenditureTrendReport(RequestParameter reqParameter, String jasperFile, String fileName, ArrayList<BudgetHistory> expenditures) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", reqParameter.Logo);
        parameters.put("approvedBy", reqParameter.ApprovedBy);
        parameters.put("certifiedBy", reqParameter.CertifiedBy);
        parameters.put("verifiedBy", reqParameter.VerifiedBy);
        JasperPrint jasperPrint;
        
        System.out.println("module 95");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(expenditures, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    public void createAssetShares(RequestParameter reqParameter, String jasperFile, String fileName, ArrayList<Asset> assets) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", reqParameter.Logo);
        parameters.put("approvedBy", reqParameter.ApprovedBy);
        parameters.put("certifiedBy", reqParameter.CertifiedBy);
        parameters.put("verifiedBy", reqParameter.VerifiedBy);
        JasperPrint jasperPrint;
        
        System.out.println("module 112");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(assets, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    public void createRepairReport(RequestParameter reqParameter, String jasperFile, String fileName, ArrayList<AssetRepair> assetRepairs) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", reqParameter.Logo);
        parameters.put("approvedBy", reqParameter.ApprovedBy);
        parameters.put("certifiedBy", reqParameter.CertifiedBy);
        parameters.put("verifiedBy", reqParameter.VerifiedBy);
        JasperPrint jasperPrint;
        
        System.out.println("module 78");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(assetRepairs, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    
    public void createOrderForm(String logo, OrderForm orderForm, String jasperFile, String fileName) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", logo);
        parameters.put("title", orderForm.getTitle());
        parameters.put("approvedBy", orderForm.getApprovedBy());
        parameters.put("requestedBy", orderForm.getRequestedBy());
        parameters.put("fromName", orderForm.getFromName());
        parameters.put("fromAddress", orderForm.getFromAddress());
        parameters.put("fromContact", orderForm.getFromContact());
        parameters.put("toName", orderForm.getToName());
        parameters.put("toAddress", orderForm.getToAddress());
        parameters.put("toContact", orderForm.getToContact());
        JasperPrint jasperPrint;
        
        System.out.println("creating order form");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderForm.getDetails(), false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
}
