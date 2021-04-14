/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalJavaFiles;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farzeen
 */
public class InternetConnectivity {
    
    public static boolean InternetConnectionCheck(){
         
            boolean flag=false;
            Socket soc=new Socket();
            InetSocketAddress isd=new InetSocketAddress("www.google.com",80);

        try {                
            soc.connect(isd,3000);
            flag=true;
           
        } catch (Exception ex) {
        flag=false;  
        }
        finally{
                try {
                    soc.close();
                } catch (IOException ex) {
                    Logger.getLogger(InternetConnectivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            return flag;
        }    
    }
}
