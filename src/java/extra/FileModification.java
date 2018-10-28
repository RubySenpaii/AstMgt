/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author RubySenpaii
 */
public class FileModification {

    public int SaveFile(String appPath, Part file, String fileName) {
        try {
            System.out.println("begin: saving file");
            File fileDir = new File(appPath);
            if (!fileDir.exists()) {
                System.out.println(appPath + " created: " + fileDir.mkdir());
            }
            System.out.println("save path: " + appPath);
            OutputStream out = new FileOutputStream(new File(appPath + File.separator + fileName));
            InputStream fileContent = file.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != 1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return 1;
        } catch (IOException x) {
            System.err.println(x);
            return 0;
        }
    }
}
