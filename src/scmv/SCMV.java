package scmv;

import ExternalJavaFiles.Database;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SCMV {

 
    public static void main(String[] args) throws SQLException {
         Connection con=null;
         ResultSet rs=null;
         PreparedStatement pstm=null;
         try{
             Database d1,d2;
        Database db=new Database();
        con=db.openConnection();
        }catch(HeadlessException| SQLException e){
            JOptionPane.showMessageDialog(null, "DB not connected");
        }
           // new Home().setVisible(true);
        SplashScreen S=new SplashScreen();
        S.setVisible(true);
        try{
            for(int i=0;i<=100;i++){
                Thread.sleep(40);
                S.loadingnumber.setText(Integer.toString(i)+"%");
                S.loadingbar.setValue(i);
                if(i==100){                   
            pstm=con.prepareStatement("select * from currentUsertbl");
            rs=pstm.executeQuery();
            if(rs.next()){
                 S.setVisible(false);
                    S.dispose();
                    new Dashboard().setVisible(true);
            }else{                
                    S.setVisible(false);
                    S.dispose();
                    new SignInUp().setVisible(true);
            }

           }
            }
            
        }
        catch(InterruptedException e){
               System.out.println("Connection Failed Again!"+e.getMessage());
        }
       
    }
   
}
