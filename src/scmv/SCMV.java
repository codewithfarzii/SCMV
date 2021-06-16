package scmv;

import ExternalJavaFiles.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SCMV {
         static Connection con=null;
        static ResultSet rs=null;
         static PreparedStatement pstm=null;
 
    public static void main(String[] args) throws SQLException {
        
         try{
             Database d1,d2;
        Database db=new Database();
        con=db.openConnection();
        }catch(HeadlessException| SQLException e){
          //  JOptionPane.showMessageDialog(null, "DB not connected");
           ImageIcon icon = new ImageIcon("src/images/close.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("Database not Connected! ");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " ALert !", JOptionPane.PLAIN_MESSAGE, icon);
              
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
                    clearDBtbl();
            pstm=con.prepareStatement("select * from currentUsertbl");
            rs=pstm.executeQuery();
            if(rs.next()){
                 S.setVisible(false);
                    S.dispose();
                    Dashboard db=new Dashboard();
                    db.setVisible(true);
            }else{                
                    S.setVisible(false);
                    S.dispose();
                    SignInUp sp=new SignInUp();
                    sp.setVisible(true);
            }

           }
            }
            
        }
        catch(InterruptedException e){
               System.out.println("Connection Failed Again!"+e.getMessage());
        }
       
    }
    public static void clearDBtbl(){
        try {
            // TODO add your handling code here:
            pstm=con.prepareStatement("delete  from loctbl");
            pstm.executeUpdate();
            pstm=con.prepareStatement("delete  from datatypetbl");
            pstm.executeUpdate();
            pstm=con.prepareStatement("delete  from looptbl");
            pstm.executeUpdate();
            pstm=con.prepareStatement("delete  from conditionalstatetbl");
            pstm.executeUpdate();            
            pstm=con.prepareStatement("delete  from variablestbl");
            pstm.executeUpdate();
            pstm=con.prepareStatement("delete  from classinfotbl");
            pstm.executeUpdate();
             System.out.println("DB cleared!!!");
        } catch (SQLException ex) {
             System.out.println("DB not cleared!!!");
        }
    }
   
}
