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
    
    public void createPropertyPlantEquipment(String logo, String jasperFile, String fileName, ArrayList<Asset> assets) 
            throws JRException, FileNotFoundException, SQLException {
        File file = new File(jasperFile);
        Map parameters = new HashMap();
        parameters.put("logo", logo);
        JasperPrint jasperPrint;

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(assets, false);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
}
