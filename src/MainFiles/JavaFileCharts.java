/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import ExternalJavaFiles.Database;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author farzeen
 */

public class JavaFileCharts {
Connection con=null;
ResultSet rs=null;
PreparedStatement pstm=null;
int loc, bline, cline, pline, lline, obrace, cbrace;
int t_int, t_shortint, t_longint, t_string, t_double, t_float, t_bool, t_char;
int t_for,t_while,t_do_while;
public JavaFileCharts(){   
    try{
           Database db=new Database();
           con=db.openConnection();
           }catch(HeadlessException| SQLException e){
               JOptionPane.showMessageDialog(null, "DB not connected");
           }
   }
  public void getValuesFromLOOPtbl(){
    try {
        String query= "SELECT * FROM `looptbl`";
        pstm=con.prepareStatement(query);
        rs=pstm.executeQuery();
        if(rs.next()){
            String a=rs.getString("total_for");
            t_for= Integer.parseInt(a);
            String b=rs.getString("total_while");
            t_while= Integer.parseInt(b);
            String c=rs.getString("total_do_while");
            t_do_while= Integer.parseInt(c);
        }
    } catch (SQLException ex) {
        Logger.getLogger(JavaFileCharts.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void getValuesFromDATATYPEtbl(){
    try {
        String query= "SELECT * FROM `datatypetbl`";
        pstm=con.prepareStatement(query);
        rs=pstm.executeQuery();
        if(rs.next()){
            String a=rs.getString("total_int");
            t_int= Integer.parseInt(a);
            String b=rs.getString("total_short_int");
            t_shortint= Integer.parseInt(b);
            String c=rs.getString("total_long_int");
            t_longint= Integer.parseInt(c);
            String d=rs.getString("total_string");
            t_string= Integer.parseInt(d);
            String e=rs.getString("total_double");
            t_double= Integer.parseInt(e);
            String f=rs.getString("total_float");
            t_float= Integer.parseInt(f);
            String g=rs.getString("total_bool");
            t_bool= Integer.parseInt(g);
            String h=rs.getString("total_char");
            t_char= Integer.parseInt(h);
        }
    } catch (SQLException ex) {
        Logger.getLogger(JavaFileCharts.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void getValuesFromLOCtbl(){
    try {
        String query= "SELECT * FROM `loctbl`";
        pstm=con.prepareStatement(query);
        rs=pstm.executeQuery();
        if(rs.next()){
            String a=rs.getString("loc");
            loc= Integer.parseInt(a);
            String b=rs.getString("bLines");
            bline= Integer.parseInt(b);
            String c=rs.getString("cLines");
            cline= Integer.parseInt(c);
            String d=rs.getString("pLines");
            pline= Integer.parseInt(d);
            String e=rs.getString("lLines");
            lline= Integer.parseInt(e);
            String f=rs.getString("oBraces");
            obrace= Integer.parseInt(f);
            String g=rs.getString("cBraces");
            cbrace= Integer.parseInt(g);
        }
    } catch (SQLException ex) {
        Logger.getLogger(JavaFileCharts.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void File3d_PieChart(){                 
             getValuesFromLOCtbl();
             System.out.println(loc+","+lline);
 }
}