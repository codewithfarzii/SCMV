/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scmv;

import ExternalJavaFiles.EmailValidator;
import ExternalJavaFiles.Database;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author farzeen
 */
public class SignInUp extends javax.swing.JFrame {

    /**
     * Creates new form SignInUp
     */
    Connection con=null;
ResultSet rs=null;
PreparedStatement pstm=null;
    public SignInUp() {
        initComponents();
         try{
        Database db=new Database();
        con=db.openConnection();
        }catch(HeadlessException| SQLException e){
            JOptionPane.showMessageDialog(null, "DB not connected");
        }
        // luserName.requestFocus();
      showLoginPanel();
    }
       
    public void showLoginPanel(){
          //login back labels
        jLabel_SoftZyd.setVisible(false);
        jLabel_inven.setVisible(false);
        jLabel_inven1.setVisible(false);
        //signup Back Labels
        jLabel_SoftZyd1.setVisible(true);
        jLabel_inven2.setVisible(true);
        jLabel_inven3.setVisible(true);
        // panels
        loginPanel.setVisible(true);
        signUpPanel.setVisible(false);
    }
    public void showSignUpPanel(){
         //login back labels
        jLabel_SoftZyd.setVisible(true);
        jLabel_inven.setVisible(true);
        jLabel_inven1.setVisible(true);
        //signup Back Labels
        jLabel_SoftZyd1.setVisible(false);
        jLabel_inven2.setVisible(false);
        jLabel_inven3.setVisible(false);
        // panels
        loginPanel.setVisible(false);
        signUpPanel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        loginBack = new javax.swing.JPanel();
        jLabel_inven = new javax.swing.JLabel();
        jLabel_SoftZyd = new javax.swing.JLabel();
        jLabel_inven1 = new javax.swing.JLabel();
        loginPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        luserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        luserPass = new javax.swing.JPasswordField();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        signUpBack = new javax.swing.JPanel();
        signUpPanel = new javax.swing.JPanel();
        userName = new javax.swing.JTextField();
        userEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        userPass1 = new javax.swing.JPasswordField();
        userPass2 = new javax.swing.JPasswordField();
        jLabel_inven2 = new javax.swing.JLabel();
        jLabel_SoftZyd1 = new javax.swing.JLabel();
        jLabel_inven3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(169, 224, 49));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText("SCMV");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton11.setToolTipText("Minimize");
        jButton11.setBorder(null);
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

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton10.setToolTipText("Close");
        jButton10.setBorder(null);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        loginBack.setBackground(new java.awt.Color(0, 0, 0));
        loginBack.setPreferredSize(new java.awt.Dimension(330, 535));

        jLabel_inven.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        jLabel_inven.setForeground(new java.awt.Color(169, 224, 49));
        jLabel_inven.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_inven.setText("Visualizer In JAVA");

        jLabel_SoftZyd.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel_SoftZyd.setForeground(new java.awt.Color(169, 224, 49));
        jLabel_SoftZyd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_SoftZyd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_DOT_100px.png"))); // NOI18N
        jLabel_SoftZyd.setText("SCMV");

        jLabel_inven1.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        jLabel_inven1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel_inven1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_inven1.setText("Source Code Metrics");

        loginPanel.setBackground(new java.awt.Color(31, 36, 42));
        loginPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 255, 0)));
        loginPanel.setForeground(new java.awt.Color(51, 255, 0));
        loginPanel.setPreferredSize(new java.awt.Dimension(330, 535));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(169, 224, 49));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_User_Shield_100px.png"))); // NOI18N
        jLabel2.setText("Log In");
        loginPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 360, -1));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(169, 224, 49));
        jLabel19.setText("User Name");
        loginPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 159, 100, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(169, 224, 49));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Male_User_35px.png"))); // NOI18N
        loginPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 173, -1, 45));

        luserName.setBackground(new java.awt.Color(31, 36, 42));
        luserName.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        luserName.setForeground(new java.awt.Color(255, 255, 255));
        luserName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        luserName.setCaretColor(new java.awt.Color(255, 255, 255));
        luserName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                luserNameMouseMoved(evt);
            }
        });
        luserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luserNameActionPerformed(evt);
            }
        });
        luserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                luserNameKeyPressed(evt);
            }
        });
        loginPanel.add(luserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 183, 250, 35));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(169, 224, 49));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Lock_35px.png"))); // NOI18N
        loginPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 250, -1, 45));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(169, 224, 49));
        jLabel20.setText("Password");
        loginPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 236, 70, -1));

        luserPass.setBackground(new java.awt.Color(31, 36, 42));
        luserPass.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        luserPass.setForeground(new java.awt.Color(255, 255, 255));
        luserPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        luserPass.setCaretColor(new java.awt.Color(255, 255, 255));
        luserPass.setFocusCycleRoot(true);
        loginPanel.add(luserPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 250, 35));

        jButton7.setBackground(new java.awt.Color(152, 201, 45));
        jButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(21, 25, 28));
        jButton7.setText("Sign In");
        jButton7.setBorder(null);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        loginPanel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 313, 250, 35));

        jButton1.setBackground(new java.awt.Color(169, 224, 49));
        jButton1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(169, 224, 49));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icon_signup.png"))); // NOI18N
        jButton1.setText("Create New User?");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRequestFocusEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        loginPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 200, 47));

        javax.swing.GroupLayout loginBackLayout = new javax.swing.GroupLayout(loginBack);
        loginBack.setLayout(loginBackLayout);
        loginBackLayout.setHorizontalGroup(
            loginBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_SoftZyd, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_inven1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_inven, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        loginBackLayout.setVerticalGroup(
            loginBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginBackLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel_SoftZyd)
                .addGap(6, 6, 6)
                .addComponent(jLabel_inven1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel_inven, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        signUpBack.setBackground(new java.awt.Color(0, 0, 0));
        signUpBack.setPreferredSize(new java.awt.Dimension(330, 535));

        signUpPanel.setBackground(new java.awt.Color(31, 36, 42));
        signUpPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 255, 0)));
        signUpPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signUpPanel.setPreferredSize(new java.awt.Dimension(330, 535));
        signUpPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userName.setBackground(new java.awt.Color(31, 36, 42));
        userName.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        userName.setCaretColor(new java.awt.Color(255, 255, 255));
        userName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userName.setDisabledTextColor(new java.awt.Color(31, 36, 42));
        userName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                userNameMouseMoved(evt);
            }
        });
        userName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userNameKeyPressed(evt);
            }
        });
        signUpPanel.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 250, 35));

        userEmail.setBackground(new java.awt.Color(31, 36, 42));
        userEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        userEmail.setForeground(new java.awt.Color(255, 255, 255));
        userEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        userEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        userEmail.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                userEmailMouseMoved(evt);
            }
        });
        userEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userEmailKeyPressed(evt);
            }
        });
        signUpPanel.add(userEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 250, 35));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(169, 224, 49));
        jLabel14.setText("User Name");
        signUpPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 90, 20));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(169, 224, 49));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Male_User_100px.png"))); // NOI18N
        jLabel13.setText("Sign Up");
        signUpPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 320, 80));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(169, 224, 49));
        jLabel18.setText("Password");
        signUpPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 314, -1));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(169, 224, 49));
        jLabel17.setText("Confirm Password");
        signUpPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 314, -1));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(169, 224, 49));
        jLabel16.setText("Email");
        signUpPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 314, -1));

        jButton14.setBackground(new java.awt.Color(169, 224, 49));
        jButton14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(169, 224, 49));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_25px.png"))); // NOI18N
        jButton14.setBorder(null);
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.setLabel("Back To Login");
        jButton14.setRequestFocusEnabled(false);
        jButton14.setVerifyInputWhenFocusTarget(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        signUpPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 180, -1));

        jButton8.setBackground(new java.awt.Color(152, 201, 45));
        jButton8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(21, 25, 28));
        jButton8.setText("Sign up");
        jButton8.setBorder(null);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        signUpPanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 250, 35));

        userPass1.setBackground(new java.awt.Color(31, 36, 42));
        userPass1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        userPass1.setForeground(new java.awt.Color(255, 255, 255));
        userPass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        userPass1.setCaretColor(new java.awt.Color(255, 255, 255));
        userPass1.setFocusCycleRoot(true);
        userPass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userPass1KeyPressed(evt);
            }
        });
        signUpPanel.add(userPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 250, 35));

        userPass2.setBackground(new java.awt.Color(31, 36, 42));
        userPass2.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        userPass2.setForeground(new java.awt.Color(255, 255, 255));
        userPass2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        userPass2.setCaretColor(new java.awt.Color(255, 255, 255));
        userPass2.setFocusCycleRoot(true);
        signUpPanel.add(userPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 250, 35));

        jLabel_inven2.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        jLabel_inven2.setForeground(new java.awt.Color(169, 224, 49));
        jLabel_inven2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_inven2.setText("Visualizer In JAVA");

        jLabel_SoftZyd1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel_SoftZyd1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel_SoftZyd1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_SoftZyd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_DOT_100px.png"))); // NOI18N
        jLabel_SoftZyd1.setText("SCMV");

        jLabel_inven3.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        jLabel_inven3.setForeground(new java.awt.Color(169, 224, 49));
        jLabel_inven3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_inven3.setText("Source Code Metrics");

        javax.swing.GroupLayout signUpBackLayout = new javax.swing.GroupLayout(signUpBack);
        signUpBack.setLayout(signUpBackLayout);
        signUpBackLayout.setHorizontalGroup(
            signUpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_SoftZyd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_inven3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_inven2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(signUpBackLayout.createSequentialGroup()
                .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        signUpBackLayout.setVerticalGroup(
            signUpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpBackLayout.createSequentialGroup()
                .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_SoftZyd1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_inven3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_inven2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(signUpBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signUpBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(660, 576));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
      this.dispose();
        new GoodBye().setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void luserNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luserNameMouseMoved

    }//GEN-LAST:event_luserNameMouseMoved

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
          if(luserName.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Please Enter User Name!!!"); 
             return;
       }
     if(luserPass.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Enter Password!!!");
                  return;          
     }
       
     String str1 = luserName.getText();
     char[] p = luserPass.getPassword();
     String str2 = new String(p);

        try{
            PreparedStatement ps = con.prepareStatement("select userName,userPass from logintbl where userName=? and userPass=?"); 
            ps.setString(1, str1);
            ps.setString(2, str2);
            ps.execute();
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
             String sqll= "INSERT INTO currentusertbl (`userName`) VALUES (?);";
             pstm=con.prepareStatement(sqll);
             pstm.setString(1,str1);
             pstm.execute();

             this.dispose();
             new Dashboard().setVisible(true);
             } 
            else{
         JOptionPane.showMessageDialog(null,"Incorrect User Name or password!!!\nPlease Try Again with correct detail...");
         luserName.setText(null);
         luserPass.setText(null);
         }
        }catch (Exception ex)
        {
             JOptionPane.showMessageDialog(null,ex);
         }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       userName.requestFocus();
        showSignUpPanel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void userNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userNameMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameMouseMoved

    private void userEmailMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userEmailMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_userEmailMouseMoved

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
      showLoginPanel();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
       String uName=userName.getText();
       String uEmail=userEmail.getText();
       String uPass1=userPass1.getText();
       String uPass2=userPass2.getText();
      if(uName.isEmpty()|| uEmail.isEmpty()||uPass1.isEmpty()||uPass2.isEmpty()){
             JOptionPane.showMessageDialog(null, "All Fields Must be Filled!!!");
      }else{
       EmailValidator emailValidator = new EmailValidator();
 
       if(!emailValidator.validate(userEmail.getText().trim())) {
    
           JOptionPane.showMessageDialog(null,"Invalid Email ID");   
        
    }else{
       if (uPass1.length()>=6){
           
            if(!uPass1.matches(uPass2)){
            JOptionPane.showMessageDialog(null, "Password does not match!!!");
      }else{
          // sign_up user here
           try{

        String sql= "select * from logintbl where userName=?";    
                pstm=con.prepareStatement(sql);
                pstm.setString(1,userName.getText());
                rs=pstm.executeQuery();

                  if(rs.next()){
                         JOptionPane.showMessageDialog(null, "User Name is already in use!!!");
                         userName.setText(null);
                  }
                  else{
            String sqll= "insert into logintbl(`userName`, `userEmail`, `userPass`) VALUES (?,?,?);";
             pstm=con.prepareStatement(sqll);
             pstm.setString(1,uName);
             pstm.setString(2,uEmail);
             pstm.setString(3,uPass1); 
             pstm.execute();
             
             JOptionPane.showMessageDialog(null, "Account Created Successffully!!!\nNow Login");
             
           
          showLoginPanel();  
        }
            }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
       }
       }else{
            JOptionPane.showMessageDialog(null, "Password must cotain 6 charaters!!!");
       }    
       }
      }                                      
    }//GEN-LAST:event_jButton8ActionPerformed

    private void luserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_luserNameActionPerformed

    private void luserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_luserNameKeyPressed
        // TODO add your handling code here:
        int key =evt.getKeyCode();
        if(key==10){
           luserPass.requestFocus();
        }
    }//GEN-LAST:event_luserNameKeyPressed

    private void userNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userNameKeyPressed
        // TODO add your handling code here:
         int key =evt.getKeyCode();
        if(key==10){
           userEmail.requestFocus();
        }
    }//GEN-LAST:event_userNameKeyPressed

    private void userPass1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userPass1KeyPressed
        // TODO add your handling code here:
         int key =evt.getKeyCode();
        if(key==10){
           userPass2.requestFocus();
        }
    }//GEN-LAST:event_userPass1KeyPressed

    private void userEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userEmailKeyPressed
        // TODO add your handling code here:
         int key =evt.getKeyCode();
        if(key==10){
           userPass1.requestFocus();
        }
    }//GEN-LAST:event_userEmailKeyPressed

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
            java.util.logging.Logger.getLogger(SignInUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignInUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignInUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignInUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_SoftZyd;
    private javax.swing.JLabel jLabel_SoftZyd1;
    private javax.swing.JLabel jLabel_inven;
    private javax.swing.JLabel jLabel_inven1;
    private javax.swing.JLabel jLabel_inven2;
    private javax.swing.JLabel jLabel_inven3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel loginBack;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField luserName;
    private javax.swing.JPasswordField luserPass;
    private javax.swing.JPanel signUpBack;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JTextField userEmail;
    private javax.swing.JTextField userName;
    private javax.swing.JPasswordField userPass1;
    private javax.swing.JPasswordField userPass2;
    // End of variables declaration//GEN-END:variables
}
