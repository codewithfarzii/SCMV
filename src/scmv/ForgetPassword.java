/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scmv;

import ExternalJavaFiles.Database;
import ExternalJavaFiles.InternetConnectivity;
import ExternalJavaFiles.JavaMailUtil;
import MainFiles.BrowseJavaFile;
import MainFiles.BrowseJavaPackage;
import MainFiles.JavaPackageMetrics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ForgetPassword extends javax.swing.JFrame {

    /**
     * Creates new form ForgetPassword
     */
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement pstm=null;
    public ForgetPassword() {
        initComponents();
           
         try{
        Database db=new Database();
        con=db.openConnection();
        }catch(HeadlessException| SQLException e){
            AlertMessage("DB Not Connected!!!");
        }
       
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
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

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
        signUpPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 2, new java.awt.Color(51, 255, 0)));
        signUpPanel.setPreferredSize(new java.awt.Dimension(330, 535));
        signUpPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(169, 224, 49));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/forgetpass2.png"))); // NOI18N
        jLabel13.setText("Forget Password");
        signUpPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 360, 60));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(169, 224, 49));
        jLabel14.setText("User Name");
        signUpPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 150, 20));

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(169, 224, 49));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icon_signup.png"))); // NOI18N
        jButton14.setText("Next");
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.setRequestFocusEnabled(false);
        jButton14.setVerifyInputWhenFocusTarget(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        signUpPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 180, 30));

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
        signUpPanel.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 180, 30));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(31, 36, 42));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(169, 224, 49));
        jTextArea1.setRows(5);
        jTextArea1.setText(" Password of your account\n will be send to you via email\n which you have provided us at\n the time of registration.  ");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jScrollPane1.setViewportView(jTextArea1);

        signUpPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 270, 90));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Note!!!");
        signUpPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        username.setBackground(new java.awt.Color(31, 36, 42));
        username.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        username.setForeground(java.awt.Color.gray);
        username.setText(" username");
        username.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 224, 49), 2, true));
        username.setCaretColor(new java.awt.Color(255, 255, 255));
        username.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                usernameMouseMoved(evt);
            }
        });
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameKeyTyped(evt);
            }
        });
        signUpPanel.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 270, 45));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(169, 224, 49));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Male_User_35px.png"))); // NOI18N
        signUpPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 45));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
       BackToSignInUp();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    public void AlertMessage(String message){
        ImageIcon icon = new ImageIcon("src/images/close.png");
            JPanel panel = new JPanel();
            Border blackline = BorderFactory.createLineBorder(Color.black);
            panel.setBorder(blackline);
            panel.setBackground(new Color(169,224,49));
            panel.setSize(new Dimension(200, 64));
            panel.setLayout(null);

            JLabel label = new JLabel(message);
            label.setBounds(0, 0, 200, 64);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);

            UIManager.put("OptionPane.minimumSize",new Dimension(300, 120));
            UIManager.put("RootPane.DialogBorder", new LineBorder(Color.black));
            JOptionPane.showMessageDialog(null, panel, " Alert !", JOptionPane.PLAIN_MESSAGE, icon);
    }
    
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
 // These are created to show that our CBO function is working
        BrowseJavaFile bf1,bf2=new BrowseJavaFile();
        JavaPackageMetrics jpm1,jpm2,jpm3=new JavaPackageMetrics();
        BrowseJavaPackage bp1,bp2=new BrowseJavaPackage();
        SignInUp sp=new SignInUp();
        ForgetPassword fp=new ForgetPassword();
        Dashboard bd1,db2=new Dashboard();
        
        String userName= username.getText();
        String useremail="";
        String userpass="";
        
        if(!InternetConnectivity.InternetConnectionCheck()){
            AlertMessage("No Internet Connection Found!!!");
            return;
        }
        if(!(userName.length()>0) || userName.matches(" username"))
        {
            AlertMessage("Please Enter Username!!! ");
        }else
        {
          try
          {
            PreparedStatement ps = con.prepareStatement("select * from logintbl where userName=?"); 
            ps.setString(1, userName);
            ps.execute();            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
            {
               useremail= rs.getString(3);
               userpass= rs.getString(4);
             //  System.out.print(useremail+" <-> "+userpass);
                JavaMailUtil.sendMail(userName,useremail,userpass);
                 AlertMessage("Kindly Check your Email!!!");  
                 BackToSignInUp();
                 
             }else
            {
                 AlertMessage("Invalid Username!!!");
                 foucslost();
            }
          }catch (Exception ex){
             JOptionPane.showMessageDialog(null,ex);
             }
              
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        BackToSignInUp();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void usernameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseMoved

    }//GEN-LAST:event_usernameMouseMoved

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained

        if (username.getText().equals(" username")) {
            username.setText("");
            username.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_usernameFocusGained

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
        // TODO add your handling code here:
        foucslost();
    }//GEN-LAST:event_usernameFocusLost

    void foucslost(){
     if (username.getText().isEmpty()) {
            username.setForeground(Color.GRAY);
            username.setText(" username");
        }   
    }
    
    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        int key =evt.getKeyCode();
        if(key==10){
            username.requestFocus();
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_usernameKeyTyped

    public void BackToSignInUp(){
        this.dispose();
        SignInUp s=new SignInUp();
        s.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
