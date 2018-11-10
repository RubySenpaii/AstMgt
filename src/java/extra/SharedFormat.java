/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
}
