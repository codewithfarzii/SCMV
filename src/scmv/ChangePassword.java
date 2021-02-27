/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author farzeen
 */
public class ChangePassword extends javax.swing.JFrame {

    /**
     * Creates new form ChangePassword
     */
    Connection con=null;
ResultSet rs=null;
PreparedStatement pstm=null;
    public ChangePassword() {
        initComponents();
         try{
        Database db=new Database();
        con=db.openConnection();
        }catch(HeadlessException| SQLException e){
        //    JOptionPane.showMessageDialog(null, "DB not connected");
          ImageIcon icon = new ImageIcon("src/images/close.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("Database Not Connected! ");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " Alert !", JOptionPane.PLAIN_MESSAGE, icon);
               
        }
    }
    public String getCurretUserName(){
         String uName="";
        try {   
            pstm=con.prepareStatement("select * from currentUsertbl");
            rs=pstm.executeQuery();
            if(rs.next()){
                uName=rs.getString(2);
            }           
        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
         return uName;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        signUpPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        cPass = new javax.swing.JPasswordField();
        newPass = new javax.swing.JPasswordField();
        conPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        header.setBackground(new java.awt.Color(0, 0, 0));
        header.setForeground(new java.awt.Color(169, 224, 49));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton10.setToolTipText("Close");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setRequestFocusEnabled(false);
        jButton10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton10.setVerifyInputWhenFocusTarget(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        header.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton11.setToolTipText("Minimize");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);
        jButton11.setRequestFocusEnabled(false);
        jButton11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton11.setVerifyInputWhenFocusTarget(false);
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        header.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 40, 40));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText(" SCMV");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 40));

        signUpPanel.setBackground(new java.awt.Color(31, 36, 42));
        signUpPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 255, 0)));
        signUpPanel.setPreferredSize(new java.awt.Dimension(330, 535));
        signUpPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(169, 224, 49));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Lock_35px.png"))); // NOI18N
        jLabel13.setText("Change Password");
        signUpPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 360, 60));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(169, 224, 49));
        jLabel14.setText("Current Password");
        signUpPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 150, 20));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(169, 224, 49));
        jLabel16.setText("New Password");
        signUpPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 200, 20));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(169, 224, 49));
        jLabel17.setText("Confirm Password");
        signUpPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 250, 20));

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(169, 224, 49));
        jButton14.setText("Change Password");
        jButton14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 224, 49), 1, true));
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.setRequestFocusEnabled(false);
        jButton14.setVerifyInputWhenFocusTarget(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        signUpPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 180, 30));

        jButton15.setBackground(new java.awt.Color(169, 224, 49));
        jButton15.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(169, 224, 49));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_25px.png"))); // NOI18N
        jButton15.setText("Back");
        jButton15.setBorder(null);
        jButton15.setContentAreaFilled(false);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.setRequestFocusEnabled(false);
        jButton15.setVerifyInputWhenFocusTarget(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        signUpPanel.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 180, 30));

        cPass.setBackground(new java.awt.Color(31, 36, 42));
        cPass.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        cPass.setForeground(new java.awt.Color(255, 255, 255));
        cPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        cPass.setCaretColor(new java.awt.Color(255, 255, 255));
        cPass.setFocusCycleRoot(true);
        cPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cPassActionPerformed(evt);
            }
        });
        signUpPanel.add(cPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 250, 40));

        newPass.setBackground(new java.awt.Color(31, 36, 42));
        newPass.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        newPass.setForeground(new java.awt.Color(255, 255, 255));
        newPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        newPass.setCaretColor(new java.awt.Color(255, 255, 255));
        newPass.setFocusCycleRoot(true);
        signUpPanel.add(newPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 250, 40));

        conPass.setBackground(new java.awt.Color(31, 36, 42));
        conPass.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        conPass.setForeground(new java.awt.Color(255, 255, 255));
        conPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        conPass.setCaretColor(new java.awt.Color(255, 255, 255));
        conPass.setFocusCycleRoot(true);
        signUpPanel.add(conPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 250, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(360, 446));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       backToHome();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
         String uSName= getCurretUserName();
         String uSPas="";
          String uCPass=cPass.getText();
          String uPass1=newPass.getText();
          String uPass2=conPass.getText();
         if(uCPass.isEmpty()||uPass1.isEmpty()||uPass2.isEmpty()){
           //  JOptionPane.showMessageDialog(this, "All Fields Must be Filled!!!");
            ImageIcon icon = new ImageIcon("src/images/close.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("All Fields Must Be Filled! ");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " Alert !", JOptionPane.PLAIN_MESSAGE, icon);
              
      }else{
        try {
            String sql= "select * from logintbl where userName=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,uSName);
            rs=pstm.executeQuery();
            if(rs.next()){
                uSPas=rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(uSPas.matches(cPass.getText())){
            if (uPass1.length()>=6){
            if(!uPass1.matches(uPass2)){
          //  JOptionPane.showMessageDialog(null, "New Password does not match!!!");
           ImageIcon icon = new ImageIcon("src/images/close.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("New Password Does Not Match! ");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " Alert !", JOptionPane.PLAIN_MESSAGE, icon);
              
      }else{
                // update query here
                try {                    
                    pstm=con.prepareStatement("UPDATE logintbl SET userPass="+newPass.getText()+" WHERE userName=?");
                    pstm.setString(1,uSName);
                    pstm.executeUpdate();
                  //  JOptionPane.showMessageDialog(null, "Password Updated Successfully!!!");
                    ImageIcon icon = new ImageIcon("src/images/check.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("Password Updated Successfully! ");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " Congrats !", JOptionPane.PLAIN_MESSAGE, icon);
             
                    backToHome();
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
        }        
       }else{
         //   JOptionPane.showMessageDialog(null, "Password must cotain 6 charaters!!!");
           ImageIcon icon = new ImageIcon("src/images/close.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("Password Must Contain Six Characters!");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " Alert !", JOptionPane.PLAIN_MESSAGE, icon);
              
       }            
        }else{
          //  JOptionPane.showMessageDialog(this, "Please Enter Correct Current Password");
         ImageIcon icon = new ImageIcon("src/images/close.png");

        JPanel panel = new JPanel();
       Border blackline = BorderFactory.createLineBorder(Color.black);
       panel.setBorder(blackline);
        panel.setBackground(new Color(169,224,49));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("Please Enter Correct Current Password!");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120)); 
        UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
        JOptionPane.showMessageDialog(null, panel, " Alert !", JOptionPane.PLAIN_MESSAGE, icon);
            
        }
    }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       backToHome();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void cPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cPassActionPerformed

    /**
     * @param args the command line arguments
     */
    public void backToHome(){
        this.dispose();
        new Dashboard().setVisible(true);
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField cPass;
    private javax.swing.JPasswordField conPass;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPass;
    private javax.swing.JPanel signUpPanel;
    // End of variables declaration//GEN-END:variables
}
