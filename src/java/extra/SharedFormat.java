/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rubysenpaii
 */
public class SharedFormat {
    public static final SimpleDateFormat TIME_STAMP = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DB_DATE_ENTRY = new SimpleDateFormat("YYYY-MM-dd");
    
    public static String doubleToString(double value) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        if (value > 0 && value < 1) {
            return "0" + df.format(value);
        } else if (value != 0) {
            return df.format(value);
        } else {
            return "0.00";
        }
    }
    public static Date stringToDate(String date){
        try {
            return DB_DATE_ENTRY.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(SharedFormat.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String getQuarter() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        switch (month) {
            case 1:
            case 2:
            case 3: return "Q1";
            case 4: 
            case 5:
            case 6: return "Q2";
            case 7:
            case 8:
            case 9: return "Q3";
            case 10:
            case 11:
            case 12:
            default: return "Q4";
        }
    }
}
