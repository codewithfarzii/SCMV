/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import ExternalJavaFiles.Database;
import ExternalJavaFiles.JavaMailUtil;
import com.sun.javafx.collections.MappingChange.Map;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import scmv.ChangePassword;
import scmv.Dashboard;
import scmv.ForgetPassword;
import scmv.GoodBye;
import scmv.SignInUp;

/**
 *
 * @author farzeen
 */
public class BrowseJavaFile extends javax.swing.JFrame {

    /**
     * Creates new form BrowseJavaFile
     */
Connection con=null;
ResultSet rs=null;
PreparedStatement pstm=null;
    final String fname="temporary//temp.txt";
    public static String Originalfname;
    boolean ClassNameExtracted=false;
    
    public BrowseJavaFile() {
        initComponents();
        connectWithDB();
        BarPanel.setVisible(false);
        countClicked=false;
        
    }    
    public void AlertMessage(String message,String path,String title){
            ImageIcon icon = new ImageIcon(path);
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
            JOptionPane.showMessageDialog(null, panel, title, JOptionPane.PLAIN_MESSAGE, icon);
               
    }
  
    public void connectWithDB(){
        try{
           Database db=new Database();
           con=db.openConnection();
           }catch(HeadlessException| SQLException e){
                AlertMessage("Database Not Connected!!!","src/images/close.png"," Alert!!!");            
           }
    }
    public  void LOC(){
         FileReader fr = null;
          String str = null;
          int loc=0;
          int elineno=0;
        try {
            fr = new FileReader(Originalfname);
            BufferedReader reader = new BufferedReader(fr);
             while ((str = reader.readLine()) != null) {
                loc++;                           // Lines of Code ++
                
                if (str.isEmpty()) {            
                        elineno++;               // emptyLine ++
                    }else{
                boolean flag=true;
                 for(int a=0;a<str.length();a++){                     
                 char ch=str.charAt(a);
                 int c=ch;
                
                if(c==9 || c==32 )  
                     continue;
                else{
                 flag=false;
                 break;                 
                }
            }
                 if(flag)
                      elineno++; 
            }
             }
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
                 totalLOCtxt.setText(Integer.toString(loc));
            blankLinestxt.setText(Integer.toString(elineno));
//                 System.out.println("loc --> "+loc);
//                 System.out.println("emptyline --> "+elineno);
            } catch (IOException ex) {
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public int calculateLevelOfCC()throws FileNotFoundException{
           int levelofCurlyBrace=0;
           int deepLevel=0;
          try {          
            FileReader fr = new FileReader(fname);
            BufferedReader br=new BufferedReader(fr);
          
            int ch;
            while((ch=br.read())!=-1){
              
                if(ch==123 ){
                    levelofCurlyBrace++;
                    
                }
                 if(deepLevel<levelofCurlyBrace){
                  deepLevel= levelofCurlyBrace;
               }
                if(ch==125){
               levelofCurlyBrace--;
            }
            }
          
            // System.out.println("level --> "+deepLevel);
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
            return deepLevel;
    }
    
    DefaultTableModel model;
    
    public static String Table_click1;
    public static String Table_click2;
    public static String Table_click3;
    public static String Table_click4;
    public static int r1;
    public static String hyu;
    public static String func_cc;
    public static String func_status;
    public static String refec_sug;
           static int INT_Count=0;
           static int SHORT_INT_Count=0;
           static int LONG_INT_Count=0;
           static int STRING_Count=0;
           static int BOOL_Count=0;
           static int FLOAT_Count=0;
           static int DOUBLE_Count=0;
           static int CHAR_Count=0;
        public static List<String> listOfPhyLines=new ArrayList();
        public static List<String> listOfBraceLines=new ArrayList();
        public static List<String> INT_Lines=new ArrayList();
        public static List<String> SHORT_INT_Lines=new ArrayList();
        public static List<String> LONG_INT_Lines=new ArrayList();
        public static List<String> CHAR_Lines=new ArrayList();
        public static List<String> BOOL_Lines=new ArrayList();
        public static List<String> FLOAT_Lines=new ArrayList();
        public static List<String> DOUBLE_Lines=new ArrayList();
        public static List<String> STRING_Lines=new ArrayList();
        
      public void calculateCurlyBrace()throws FileNotFoundException{
         
         int openBrace=0,closeBrace=0; 
         try {          
            FileReader fr = new FileReader(fname);
            BufferedReader br=new BufferedReader(fr);           
            int ch;
            while((ch=br.read())!=-1){              
                if(ch==123 ){
                    openBrace++;
                }                 
                if(ch==125){
               closeBrace++;
            }
            }
            openBracestxt.setText(Integer.toString(openBrace));
            closeBracestxt.setText(Integer.toString(closeBrace));
            // System.out.println("Open --> "+openBrace);
           //  System.out.println("close --> "+closeBrace);
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
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
        BarPanel = new javax.swing.JPanel();
        ProgressBar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fileNametxt = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        classNametxt = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        classStatustxt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        classCCtxt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        refactoringSuggestion = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        noOfChildClasstxt = new javax.swing.JTextField();
        noOfParentClasstxt = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        totalLOCtxt = new javax.swing.JTextField();
        blankLinestxt = new javax.swing.JTextField();
        commentedLinestxt = new javax.swing.JTextField();
        physicalLinestxt = new javax.swing.JTextField();
        logicalLinestxt = new javax.swing.JTextField();
        openBracestxt = new javax.swing.JTextField();
        closeBracestxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        visualizeComboBox = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        noOfINT = new javax.swing.JTextField();
        noOfShortINT = new javax.swing.JTextField();
        noOfLongINT = new javax.swing.JTextField();
        noOfSTRING = new javax.swing.JTextField();
        noOfDOUBLE = new javax.swing.JTextField();
        noOfFLOAT = new javax.swing.JTextField();
        noOfBOOL = new javax.swing.JTextField();
        noOfCHAR = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        visualizeComboBox1 = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        noOfINTV = new javax.swing.JTextField();
        noOfShortINTV = new javax.swing.JTextField();
        noOfLongINTV = new javax.swing.JTextField();
        noOfSTRINGV = new javax.swing.JTextField();
        noOfDOUBLEV = new javax.swing.JTextField();
        noOfFLOATV = new javax.swing.JTextField();
        noOfBOOLV = new javax.swing.JTextField();
        noOfCHARV = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        visualizeComboBox2 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        noOfFORLOOP = new javax.swing.JTextField();
        noOfDOWHILELOOP = new javax.swing.JTextField();
        noOfWHILELOOP = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        visualizeComboBox4 = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        noOfIFStatement = new javax.swing.JTextField();
        noOfSwitchStatement = new javax.swing.JTextField();
        noOfTryBlock = new javax.swing.JTextField();
        noOfElseIfStatement = new javax.swing.JTextField();
        noOfElseStatement = new javax.swing.JTextField();
        noOfCases = new javax.swing.JTextField();
        noOfCatchBlocks = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        noOfFinallyBlocks = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        visualizeComboBox5 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

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
        header.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 40, 40));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton11.setToolTipText("Minimize");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
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
        header.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 40, 40));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/metrics 64.png"))); // NOI18N
        jLabel1.setText("  Source Code Metrics Visualizer of JAVA");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        BarPanel.setBackground(new java.awt.Color(0, 0, 0));

        ProgressBar.setBackground(new java.awt.Color(0, 0, 0));
        ProgressBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProgressBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loadingBar.gif"))); // NOI18N

        javax.swing.GroupLayout BarPanelLayout = new javax.swing.GroupLayout(BarPanel);
        BarPanel.setLayout(BarPanelLayout);
        BarPanelLayout.setHorizontalGroup(
            BarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(BarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BarPanelLayout.createSequentialGroup()
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        BarPanelLayout.setVerticalGroup(
            BarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(BarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BarPanelLayout.createSequentialGroup()
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        header.add(BarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(header);
        header.setBounds(0, 0, 749, 70);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true)));

        fileNametxt.setEditable(false);
        fileNametxt.setColumns(20);
        fileNametxt.setRows(2);
        jScrollPane3.setViewportView(fileNametxt);

        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton1.setText("Count Metrics");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backRollOver.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(900, 500));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(900, 500));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel26.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel26.setText("Class Name");

        classNametxt.setEditable(false);
        classNametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classNametxtActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel27.setText("Class Status");

        classStatustxt.setEditable(false);
        classStatustxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classStatustxtActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel28.setText("Class Cyclomatic Complexity");

        classCCtxt.setEditable(false);
        classCCtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classCCtxtActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel30.setText("Refactoring Suggestion:");

        refactoringSuggestion.setBackground(new java.awt.Color(204, 204, 204));
        refactoringSuggestion.setForeground(new java.awt.Color(255, 51, 51));
        refactoringSuggestion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createTitledBorder(null, "Cyclomatic Complexity Threshold Index", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12)))); // NOI18N

        jLabel48.setText("High");

        jLabel49.setText("Medium");

        jLabel50.setText("Low");

        jLabel51.setText(">= 7");

        jLabel52.setText("4 - 6");

        jLabel53.setText("<= 3");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel51))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel49))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel51))
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel52))
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel53))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        noOfChildClasstxt.setEditable(false);
        noOfChildClasstxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfChildClasstxtActionPerformed(evt);
            }
        });

        noOfParentClasstxt.setEditable(false);
        noOfParentClasstxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfParentClasstxtActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel32.setText("No of Parent Class");

        jLabel33.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel33.setText("No of Child Class");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createTitledBorder(null, "Class Complexity Threshold Index (LOC)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12)))); // NOI18N

        jLabel54.setText("Large Class");

        jLabel55.setText("Medium Class");

        jLabel56.setText("Small Class");

        jLabel57.setText(">= 201");

        jLabel58.setText("101 - 200");

        jLabel59.setText("<= 100");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel54)
                            .addGap(26, 26, 26))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel55)
                            .addGap(18, 18, 18))))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel57)
                    .addComponent(jLabel59))
                .addGap(43, 43, 43))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel57))
                .addGap(10, 10, 10)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel58))
                .addGap(10, 10, 10)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(jLabel56))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(classStatustxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(classCCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(classNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noOfParentClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfChildClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(refactoringSuggestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(classNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(noOfParentClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(classStatustxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel33)
                            .addComponent(noOfChildClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classCCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(refactoringSuggestion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel16.getAccessibleContext().setAccessibleName("Class Complexity Threshold Index");

        jTabbedPane1.addTab("Class Info", jPanel5);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        totalLOCtxt.setEditable(false);
        totalLOCtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalLOCtxtActionPerformed(evt);
            }
        });

        blankLinestxt.setEditable(false);
        blankLinestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blankLinestxtActionPerformed(evt);
            }
        });

        commentedLinestxt.setEditable(false);
        commentedLinestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentedLinestxtActionPerformed(evt);
            }
        });

        physicalLinestxt.setEditable(false);
        physicalLinestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                physicalLinestxtActionPerformed(evt);
            }
        });

        logicalLinestxt.setEditable(false);
        logicalLinestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logicalLinestxtActionPerformed(evt);
            }
        });

        openBracestxt.setEditable(false);
        openBracestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBracestxtActionPerformed(evt);
            }
        });

        closeBracestxt.setEditable(false);
        closeBracestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBracestxtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel2.setText("Total LOC");

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel3.setText("Blank Lines");

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel4.setText("Commented Lines");

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel5.setText("Physical Lines");

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel6.setText("Logical Lines");

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel7.setText("Open Braces");

        jLabel34.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel34.setText("Close Braces");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton4.setText("Show");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        visualizeComboBox.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        visualizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...............Select...............", "2D Bar Chart", "2D Pie Chart", "2D Line Chart", "2D Waterfall Chart", "2D Dual Chart", "2D Area Chart", "2D Stack Area Chart", "2D Stack Bar Chart", "3D Bar Chart", "3D Pie Chart", "3D Line Chart", "3D Stack Bar Chart", "Scatter_Plot" }));
        visualizeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visualizeComboBoxItemStateChanged(evt);
            }
        });
        visualizeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(visualizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalLOCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logicalLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commentedLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(physicalLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blankLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(openBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(544, 544, 544))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLOCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(openBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blankLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel34)
                    .addComponent(closeBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(commentedLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(physicalLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(logicalLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LOC", jPanel2);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        noOfINT.setEditable(false);
        noOfINT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfINTActionPerformed(evt);
            }
        });

        noOfShortINT.setEditable(false);
        noOfShortINT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfShortINTActionPerformed(evt);
            }
        });

        noOfLongINT.setEditable(false);
        noOfLongINT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfLongINTActionPerformed(evt);
            }
        });

        noOfSTRING.setEditable(false);
        noOfSTRING.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfSTRINGActionPerformed(evt);
            }
        });

        noOfDOUBLE.setEditable(false);
        noOfDOUBLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfDOUBLEActionPerformed(evt);
            }
        });

        noOfFLOAT.setEditable(false);
        noOfFLOAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfFLOATActionPerformed(evt);
            }
        });

        noOfBOOL.setEditable(false);
        noOfBOOL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfBOOLActionPerformed(evt);
            }
        });

        noOfCHAR.setEditable(false);
        noOfCHAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfCHARActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel8.setText("No. of int");

        jLabel9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel9.setText("No. of short int");

        jLabel10.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel10.setText("No. of long int");

        jLabel11.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel11.setText("No. of String");

        jLabel12.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel12.setText("No. of double");

        jLabel13.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel13.setText("No. of float");

        jLabel14.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel14.setText("No. of boolean");

        jLabel15.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel15.setText("No. of char");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton5.setText("Show");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        visualizeComboBox1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        visualizeComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...............Select...............", "2D Bar Chart", "2D Pie Chart", "2D Line Chart", "2D Waterfall Chart", "2D Dual Chart", "2D Area Chart", "2D Stack Area Chart", "2D Stack Bar Chart", "3D Bar Chart", "3D Pie Chart", "3D Line Chart", "3D Stack Bar Chart" }));
        visualizeComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visualizeComboBox1ItemStateChanged(evt);
            }
        });
        visualizeComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(visualizeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(noOfINT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfShortINT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfLongINT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfSTRING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(noOfFLOAT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noOfDOUBLE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noOfBOOL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(noOfCHAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfINT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfDOUBLE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(noOfShortINT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noOfFLOAT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(noOfLongINT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noOfBOOL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel14))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfSTRING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfCHAR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Types", jPanel9);

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));

        noOfINTV.setEditable(false);
        noOfINTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfINTVActionPerformed(evt);
            }
        });

        noOfShortINTV.setEditable(false);
        noOfShortINTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfShortINTVActionPerformed(evt);
            }
        });

        noOfLongINTV.setEditable(false);
        noOfLongINTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfLongINTVActionPerformed(evt);
            }
        });

        noOfSTRINGV.setEditable(false);
        noOfSTRINGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfSTRINGVActionPerformed(evt);
            }
        });

        noOfDOUBLEV.setEditable(false);
        noOfDOUBLEV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfDOUBLEVActionPerformed(evt);
            }
        });

        noOfFLOATV.setEditable(false);
        noOfFLOATV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfFLOATVActionPerformed(evt);
            }
        });

        noOfBOOLV.setEditable(false);
        noOfBOOLV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfBOOLVActionPerformed(evt);
            }
        });

        noOfCHARV.setEditable(false);
        noOfCHARV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfCHARVActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel31.setText("int");

        jLabel35.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel35.setText("short int");

        jLabel36.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel36.setText("long int");

        jLabel37.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel37.setText("String");

        jLabel38.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel38.setText("double");

        jLabel39.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel39.setText("float");

        jLabel40.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel40.setText("boolean");

        jLabel41.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel41.setText("char");

        jLabel42.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel42.setText("Data Types");

        jLabel43.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel43.setText("No. of Variables");

        jLabel44.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel44.setText("Data Types");

        jLabel45.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel45.setText("No. of Variables");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton6.setText("Show");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        visualizeComboBox2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        visualizeComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...............Select...............", "2D Bar Chart", "2D Pie Chart", "2D Line Chart", "2D Waterfall Chart", "2D Dual Chart", "2D Area Chart", "2D Stack Area Chart", "2D Stack Bar Chart", "3D Bar Chart", "3D Pie Chart", "3D Line Chart", "3D Stack Bar Chart" }));
        visualizeComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visualizeComboBox2ItemStateChanged(evt);
            }
        });
        visualizeComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(visualizeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(noOfShortINTV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfINTV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfLongINTV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfSTRINGV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel43)))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel45))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel38)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(noOfCHARV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(noOfDOUBLEV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noOfFLOATV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noOfBOOLV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfINTV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel38)
                            .addComponent(noOfDOUBLEV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfShortINTV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(noOfFLOATV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfLongINTV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(noOfBOOLV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfSTRINGV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel41)
                            .addComponent(noOfCHARV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Variables Created", jPanel13);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        noOfFORLOOP.setEditable(false);
        noOfFORLOOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfFORLOOPActionPerformed(evt);
            }
        });

        noOfDOWHILELOOP.setEditable(false);
        noOfDOWHILELOOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfDOWHILELOOPActionPerformed(evt);
            }
        });

        noOfWHILELOOP.setEditable(false);
        noOfWHILELOOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfWHILELOOPActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel23.setText("No. of for Loop");

        jLabel24.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel24.setText("No. of do while Loop");

        jLabel25.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel25.setText("No. of while Loop");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton8.setText("Show");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        visualizeComboBox4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        visualizeComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...............Select...............", "2D Bar Chart", "2D Pie Chart", "2D Line Chart", "2D Waterfall Chart", "2D Dual Chart", "2D Area Chart", "2D Stack Area Chart", "2D Stack Bar Chart", "3D Bar Chart", "3D Pie Chart", "3D Line Chart", "3D Stack Bar Chart" }));
        visualizeComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visualizeComboBox4ItemStateChanged(evt);
            }
        });
        visualizeComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(visualizeComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(noOfDOWHILELOOP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfWHILELOOP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfFORLOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfFORLOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfDOWHILELOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfWHILELOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loop Statements", jPanel4);

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));

        noOfIFStatement.setEditable(false);
        noOfIFStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfIFStatementActionPerformed(evt);
            }
        });

        noOfSwitchStatement.setEditable(false);
        noOfSwitchStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfSwitchStatementActionPerformed(evt);
            }
        });

        noOfTryBlock.setEditable(false);
        noOfTryBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfTryBlockActionPerformed(evt);
            }
        });

        noOfElseIfStatement.setEditable(false);
        noOfElseIfStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfElseIfStatementActionPerformed(evt);
            }
        });

        noOfElseStatement.setEditable(false);
        noOfElseStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfElseStatementActionPerformed(evt);
            }
        });

        noOfCases.setEditable(false);
        noOfCases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfCasesActionPerformed(evt);
            }
        });

        noOfCatchBlocks.setEditable(false);
        noOfCatchBlocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfCatchBlocksActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel16.setText("No. of If Statement");

        jLabel17.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel17.setText("No. of switch Statement");

        jLabel18.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel18.setText("No. of try Blocks");

        jLabel19.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel19.setText("No. of else if Statement");

        jLabel20.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel20.setText("No. of else Statement");

        jLabel21.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel21.setText("No. of cases");

        jLabel22.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel22.setText("No. of catch Blocks");

        jLabel29.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel29.setText("No. of finally Blocks");

        noOfFinallyBlocks.setEditable(false);
        noOfFinallyBlocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfFinallyBlocksActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton9.setText("Show");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        visualizeComboBox5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        visualizeComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...............Select...............", "2D Bar Chart", "2D Pie Chart", "2D Line Chart", "2D Waterfall Chart", "2D Dual Chart", "2D Area Chart", "2D Stack Area Chart", "2D Stack Bar Chart", "3D Bar Chart", "3D Pie Chart", "3D Line Chart", "3D Stack Bar Chart" }));
        visualizeComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visualizeComboBox5ItemStateChanged(evt);
            }
        });
        visualizeComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeComboBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(visualizeComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(visualizeComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noOfElseStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfIFStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noOfElseIfStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noOfCases, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(26, 26, 26)
                            .addComponent(noOfSwitchStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(noOfTryBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addGap(25, 25, 25)
                                    .addComponent(noOfCatchBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(25, 25, 25)
                                .addComponent(noOfFinallyBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 91, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfIFStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(noOfTryBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfElseStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(noOfCatchBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfElseIfStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel29)
                    .addComponent(noOfFinallyBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfSwitchStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfCases, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Conditional Statements", jPanel10);

        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton3.setText("Browse File");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton12.setText("Generate Report");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                        .addGap(63, 63, 63)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 65, 749, 490);

        setSize(new java.awt.Dimension(749, 557));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    boolean countClicked=false;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           BarPanel.setVisible(true);                                                                                        
        if(fileNametxt.getText().isEmpty()){
             AlertMessage("Browse a File First!!!","src/images/close.png"," Alert!!!");              
        }else{
       
                    listOfPhyLines.clear();
                    listOfBraceLines.clear();
                    INT_Lines.clear();
                    SHORT_INT_Lines.clear();
                    LONG_INT_Lines.clear();
                    BOOL_Lines.clear();
                    DOUBLE_Lines.clear();
                    CHAR_Lines.clear();
                    FLOAT_Lines.clear();
                    STRING_Lines.clear();
                    ClassNameExtracted=false;
            clearDBtbl();  
             try {
                eliminateCommentedLines();
                //   fname = ""
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            countClicked=true;
            String[] allKeywords = {
            "abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "boolean", "do", "if",
            "private", "this", "break", "double", "implements",
            "protected", "throw", "byte", "else", "import", "public",
            "throws", "case", "enum", "instanceof", "return", "transient",
            "catch", "extends", "int", "short", "try", "String", "char",
            "final", "interface", "static", "void", "class", "finally",
            "long", "strictfp", "volatile", "const", "float", "native",
            "super", "while", "null", "true", "false"};

            String[] wdKeywords = {
            "abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "do", "if", "private",
            "this", "break", "implements", "protected", "throw", "else",
            "import", "public", "throws", "case", "enum", "instanceof",
            "return", "transient", "catch", "extends", "try", "final",
            "interface", "static", "class", "finally", "strictfp",
            "volatile", "const", "native", "super", "while", "null",
            "true", "false"};        
        
            try {   
            FileReader fr = new FileReader(fname);
            hyu=fname;
            BufferedReader reader = new BufferedReader(fr);
            BufferedReader readerInner;
            String classname = null;
            String str = null;
            int classno = 0;
            int pass_by_value=0;
            int pass_by_reference=0;
            int funno = 0;
            int commentno = 0;
            int openbrace = 0;
            int closebrace = 0;
            int loc = 0;
            int elineno=0;
            int lloc=0;
            int ifno = 0;
            int elseno = 0;
            int elseifno = 0;
            int tryno = 0;
            int catchno = 0;
            int swhno = 0;
            int caseno = 0;
            int finallyno=0;
            int b_no = 0;
            int i_no = 0;
            int si_no = 0;
            int li_no = 0;
            int s_no = 0;
            int f_no = 0;
            int d_no = 0;
            int c_no = 0;
            int trno=0;
            int dono=0;
            int whlno=0;
            int frno=0;
            int pc=0;
            int child=0;
            
            // Callig for to calculate no of variables
            readFileLineByLineForDTV();
            calculateCurlyBrace();
            LOC();
            
            ifno= calculateIF();
            elseno = calculateELSE();
            elseifno= calculateELSEIF();                  
            if(ifno>0)
            ifno=ifno-elseifno;
            if(elseno>0)
            elseno=elseno-elseifno;
            List<String> lines =  new LinkedList<String>() {};
           
             while ((str = reader.readLine()) != null) {
                reader.mark(100000000);
                                  
                lines.add(str);
                                
                if(str.contains("int")){               
              i_no=i_no+INT_calculate(str);
             // System.out.println("int--> "+i_no);
            }
            if(str.contains("short")){               
              si_no=si_no+SHORT_INT_calculate(str);
            }
            if(str.contains("long")){               
              li_no=li_no+LONG_INT_calculate(str);
            }
            if(str.contains("float")){               
              f_no=f_no+FLOAT_calculate(str);
            }
            if(str.contains("double")){               
              d_no=d_no+DOUBLE_calculate(str);
            }
            if(str.contains("String")){               
              s_no=s_no+STRING_calculate(str);
            }
            if(str.contains("char")){               
              c_no=c_no+CHAR_calculate(str);
            }
            if(str.contains("boolean")){               
              b_no=b_no+BOOL_calculate(str);
            }           
            
                
            if(str.contains("for")){               
               frno=frno+calculateFOR(str);                   
            }            
            if(str.contains("while")){               
              whlno=whlno+calculateWHILE(str);
            }
            if(str.contains("do")){               
               dono=dono+calculateDOWHILE(str);         
            }
            if(str.contains("switch")){               
                swhno=swhno+calculateSWITCH(str);                            
            }            
            if(str.contains("case")){               
               caseno=caseno+calculateCASE(str);               
            }  
            if(str.contains("try")){               
               tryno=tryno+calculateTRY(str);              
            }  
            if(str.contains("catch")){               
               catchno=catchno+calculateCATCH(str);     
            }  
            if(str.contains("finally")){               
               finallyno=finallyno+calculateFINALLY(str);            
            }  
            
                boolean flag1 = true, flag2 = true;
                StringTokenizer strTokens = new StringTokenizer(str);
                while (strTokens.hasMoreTokens()) {
                    String token = strTokens.nextToken();
                    
                    flag1 = flag2 = true;
                    for (String wdKeyword : wdKeywords) {
                        if (token.equalsIgnoreCase(wdKeyword)) {
                            flag1 = false;
                            break;
                        }
                    }
                    
                    
                    if (token.equalsIgnoreCase("class") && !ClassNameExtracted) {
                        
                        int index = str.indexOf("class");
                        index += 6;
                        int lastinx = str.indexOf(" ", index);
                        classname = str.substring(index, lastinx);                        
                        classno++;
                        ClassNameExtracted=true;
                    } 
                     if (token.equalsIgnoreCase("?:")
                            ) {
                        trno++;
                    }                     
                      if(token.contains("+")|
                            token.contains(".")|
                            token.contains("-")|
                            token.contains("*")|
                            (token.contains("/") && !token.contains("//"))|
                            token.contains("%")|
                            token.contains(">=")|
                            token.contains("=")|
                            token.contains("<=")|
                            (token.contains(">") && !token.contains("Hashtable") && !token.contains("HashSet") && !token.contains("Array") && !token.contains("Abstract"))|
                            (token.contains("<") && !token.contains("Hashtable") && !token.contains("HashSet") && !token.contains("Array") && !token.contains("Abstract"))|
                            token.contains("&")|
                            token.contains("|")|
                            token.contains("while")|
                            token.contains("for")|
                            token.contains("if")|
                            token.contains("super")|
                            token.contains("return")|
                            token.contains("catch")|
                            token.contains("break")|
                            token.contains("continue")|
                            token.contains("goto")|
                            token.contains("else if")  ){
                              lloc=lloc+1;
                    }
                    if (flag1 && strTokens.hasMoreTokens()) {
                        
                        StringTokenizer newTokenizer = strTokens;
                        String newToken = newTokenizer.nextToken();
                        for (int j = 0; (j < allKeywords.length)
                                && !newToken.isEmpty(); j++) {
                            if (newToken
                                    .equalsIgnoreCase(allKeywords[j])) {
                                flag2 = false;
                                break;
                            }
                            
                        }
                        if (newTokenizer.hasMoreTokens()) {
                            if (flag2 && (newToken.contains("(") || newTokenizer
                                    .nextToken()
                                    .startsWith("(") )) {
                                // System.out.println("function name is  "+ newToken);
                                int gft=1;
                                if(str.contains(newToken)&&str.contains(";")){
                                    gft++;}
                                int parameters;
                                String newString = str.substring(str.indexOf(newToken));
                                if (newString.contains("()") || newString.contains("( )") || newString.contains("(  )")) {
                                    parameters = 0;
                                } else {
                                    parameters = (newString.split(",")).length;
                                    
                                }
                                //System.out.println("no of arguments are ... " + parameters);
                                funno++;
                                
                                readerInner = reader;
                                boolean open = true;
                                String funLine;
                                int lineOfFunction = 0, openBraceCounter = 1;
//                                    try (FileWriter fw = new FileWriter("function" + funno + ".txt")) {
//                                        fw.write(str + "\r\n");
//                                        while ((funLine = readerInner.readLine()) != null && open) {
//                                            
//                                            int k = 0;
//                                            while (k < funLine.length()) {
//                                                if (funLine.charAt(k) == '{') {
//                                                    openBraceCounter++;
//                                                }
//                                                if (funLine.charAt(k) == '}') {
//                                                    openBraceCounter--;
//                                                }
//                                                if (openBraceCounter < 1) {
//                                                    open = false;
//                                                    break;
//                                                }
//                                                k++;
//                                            }
//                                            fw.write(funLine + "\r\n");
//                                            lineOfFunction++;
//                                            
//                                        }
//                                    }
                            }
                        }
                    }
                    
                
                }// end of inner while(tokens)
                // System.out.println(str);               
                reader.reset();
            }
       try {
        String s1;
        loc=Integer.parseInt(totalLOCtxt.getText());
           if(loc >200){
               s1="Large Class!";
               refactoringSuggestion.setText("Team Should Extract New Class!");
           }else if(loc >100 && loc<=200){
               s1="Average Class!";
               refactoringSuggestion.setText("Class is Fine!");
           }else{
               s1="Small Class!";
               refactoringSuggestion.setText("Class is Perfect!");
           }
        String cc;
       // System.out.println("trno --> "+trno);
        if(whlno>0)
            whlno=whlno-dono;
          int coc=calculateLevelOfCC();
           if(coc>=7){
               cc="High Complexity";
               }
           else if(coc>=4 && coc<=6){
               cc="Medium Complexity";          
           }
           else
           {  cc="Low Complexity";           
           }
           
           commentno= calcComments();
           int ploc=Integer.parseInt(totalLOCtxt.getText())-(commentno+Integer.parseInt(blankLinestxt.getText()));
           //tryno=catchno;
            str = reader.readLine();
            Scanner scan = new Scanner(fr);
            reader.close(); 
            if(i_no>0)
            i_no= i_no-si_no;
             if(i_no>0)
            i_no= i_no-li_no;
             
           pc=countParentsChild(classname);
           child= countChild(classname);
            
           // LOC INFO           
            commentedLinestxt.setText(Integer.toString(commentno));
            physicalLinestxt.setText(Integer.toString(ploc));//p
            logicalLinestxt.setText(Integer.toString(lloc));//l
            
                       
            // Class_INFO
            classNametxt.setText(classname);
            classStatustxt.setText(s1);
            classCCtxt.setText(cc);            
            noOfChildClasstxt.setText(Integer.toString(child));
            noOfParentClasstxt.setText(Integer.toString(pc));
            // Loop Statements INFO           
            noOfFORLOOP.setText(Integer.toString(frno));
            noOfDOWHILELOOP.setText(Integer.toString(dono));
            noOfWHILELOOP.setText(Integer.toString(whlno));
            // Conditional Statements INFO            
            noOfIFStatement.setText(Integer.toString(ifno));
            noOfSwitchStatement.setText(Integer.toString(swhno));
            noOfTryBlock.setText(Integer.toString(tryno));
            noOfElseIfStatement.setText(Integer.toString(elseifno));
            noOfElseStatement.setText(Integer.toString(elseno));
            noOfCases.setText(Integer.toString(caseno));
            noOfCatchBlocks.setText(Integer.toString(catchno));
            noOfFinallyBlocks.setText(Integer.toString(finallyno));
              if(i_no==0)
                 INT_Count=0;    
             if(si_no==0)
                 SHORT_INT_Count=0;
             if(li_no==0)
                 LONG_INT_Count=0;
             if(s_no==0)
                 STRING_Count=0;
             if(d_no==0)
                 DOUBLE_Count=0;
             if(f_no==0)
                 FLOAT_Count=0;
             if(b_no==0)
                 BOOL_Count=0;
             if(c_no==0)
                 CHAR_Count=0;
            // DATA TYPES INFO
            noOfINT.setText(Integer.toString(i_no));
            noOfShortINT.setText(Integer.toString(si_no));
            noOfLongINT.setText(Integer.toString(li_no));            
            noOfSTRING.setText(Integer.toString(s_no));
            noOfDOUBLE.setText(Integer.toString(d_no));
            noOfFLOAT.setText(Integer.toString(f_no));
            noOfBOOL.setText(Integer.toString(b_no));
            noOfCHAR.setText(Integer.toString(c_no)); 
            //     Varaibles Created          
            noOfINTV.setText(Integer.toString(INT_Count));
            noOfShortINTV.setText(Integer.toString(SHORT_INT_Count));
            noOfLongINTV.setText(Integer.toString(LONG_INT_Count));
            noOfSTRINGV.setText(Integer.toString(STRING_Count));
            noOfDOUBLEV.setText(Integer.toString(DOUBLE_Count));
            noOfFLOATV.setText(Integer.toString(FLOAT_Count));
            noOfBOOLV.setText(Integer.toString(BOOL_Count));
            noOfCHARV.setText(Integer.toString(CHAR_Count));     
                   
            insertInClassInfotbl(classname,s1,cc,refactoringSuggestion.getText().toString(),pc,child);
            insertInLOCtbl(Integer.parseInt(totalLOCtxt.getText()),Integer.parseInt(blankLinestxt.getText()),commentno,ploc,lloc,Integer.parseInt(openBracestxt.getText()),Integer.parseInt(closeBracestxt.getText()));
            insertInDATATYPEtbl(i_no,si_no,li_no,s_no,d_no,f_no,b_no,c_no);
            insertInLOOPtbl(frno,dono,whlno);
            insertInCoditionalStatetbl(ifno,elseno,elseifno,swhno,caseno,tryno,catchno,finallyno);
            insertInVariablestbl(INT_Count,SHORT_INT_Count,LONG_INT_Count,STRING_Count,DOUBLE_Count,FLOAT_Count,BOOL_Count,CHAR_Count);
           
       } catch (IOException e) {
           System.out.print(e);
       }         
        }catch (FileNotFoundException ex){
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        BarPanel.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.dispose();
       new Dashboard().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void noOfINTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfINTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfINTActionPerformed

    private void noOfShortINTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfShortINTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfShortINTActionPerformed

    private void noOfLongINTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfLongINTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfLongINTActionPerformed

    private void noOfSTRINGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfSTRINGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfSTRINGActionPerformed

    private void noOfDOUBLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfDOUBLEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfDOUBLEActionPerformed

    private void noOfFLOATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfFLOATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfFLOATActionPerformed

    private void noOfBOOLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfBOOLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfBOOLActionPerformed

    private void noOfCHARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfCHARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfCHARActionPerformed

    private void classNametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classNametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classNametxtActionPerformed

    private void classStatustxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classStatustxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classStatustxtActionPerformed

    private void classCCtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classCCtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classCCtxtActionPerformed

    private void noOfChildClasstxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfChildClasstxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfChildClasstxtActionPerformed

    private void noOfParentClasstxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfParentClasstxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfParentClasstxtActionPerformed

    private void totalLOCtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalLOCtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalLOCtxtActionPerformed

    private void blankLinestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blankLinestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blankLinestxtActionPerformed

    private void commentedLinestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentedLinestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentedLinestxtActionPerformed

    private void physicalLinestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_physicalLinestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_physicalLinestxtActionPerformed

    private void logicalLinestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logicalLinestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logicalLinestxtActionPerformed

    private void openBracestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBracestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openBracestxtActionPerformed

    private void closeBracestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBracestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeBracestxtActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
               
        countClicked=false;
                     totalLOCtxt.setText(null);
                     blankLinestxt.setText(null);
                     commentedLinestxt.setText(null);
                     physicalLinestxt.setText(null);//p
                     logicalLinestxt.setText(null);//l
                     openBracestxt.setText(null);
                     closeBracestxt.setText(null);
                     noOfINT.setText(null);
                     noOfShortINT.setText(null);
                     noOfLongINT.setText(null);
                     noOfSTRING.setText(null);
                     noOfDOUBLE.setText(null);
                     noOfFLOAT.setText(null);
                     noOfBOOL.setText(null);
                     noOfCHAR.setText(null);
                     noOfINTV.setText(null);
                     noOfShortINTV.setText(null);
                     noOfLongINTV.setText(null);
                     noOfSTRINGV.setText(null);
                     noOfDOUBLEV.setText(null);
                     noOfFLOATV.setText(null);
                     noOfBOOLV.setText(null);
                     noOfCHARV.setText(null);
                     classNametxt.setText(null);
                     classStatustxt.setText(null);
                     classCCtxt.setText(null);
                     noOfChildClasstxt.setText(null);
                     noOfParentClasstxt.setText(null);
                     noOfIFStatement.setText(null);
                     noOfSwitchStatement.setText(null);
                     noOfTryBlock.setText(null);
                     noOfFinallyBlocks.setText(null);
                     noOfElseIfStatement.setText(null);
                     noOfElseStatement.setText(null);
                     noOfCases.setText(null);
                     noOfCatchBlocks.setText(null);
                     noOfFORLOOP.setText(null);
                     noOfDOWHILELOOP.setText(null);
                     noOfWHILELOOP.setText(null);
                     fileNametxt.setText(null);  
                     browse_file();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
BarPanel.setVisible(true);   
        if(fileNametxt.getText().isEmpty())   {
            AlertMessage("Browse a File First!!!","src/images/close.png"," Alert!!!");                  
        }
        else if(!countClicked)   {
            AlertMessage("First Count Metrics!!!","src/images/close.png"," Alert!!!");    
        }
        else{
            if(visualizeComboBox.getSelectedItem().equals("...............Select...............")){
                   AlertMessage("Please Select a Chart!!!","src/images/close.png"," Alert!!!"); 
              return;
            }           
            JavaFileCharts dt=new JavaFileCharts();
            if(visualizeComboBox.getSelectedItem().equals("2D Bar Chart")){
                dt.LOC2D_BarChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Pie Chart")){
                 dt.LOC2D_PieChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Line Chart")){
                dt.LOC2D_LineChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Waterfall Chart")){
                dt.LOC2D_WaterfallChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Dual Chart")){
                dt.LOC2D_DualChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Area Chart")){
                dt.LOC2D_AreaChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Stack Area Chart")){
                dt.LOC2D_StackAreaChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("2D Stack Bar Chart")){
                dt.LOC2D_StackBarChart();
            }                    
            if(visualizeComboBox.getSelectedItem().equals("3D Bar Chart")){
                dt.LOC3D_BarChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("3D Pie Chart")){
                dt.LOC3D_PieChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("3D Line Chart")){
                dt.LOC3D_LineChart();
            }
            if(visualizeComboBox.getSelectedItem().equals("3D Stack Bar Chart")){
                dt.LOC3D_StackBarChart();
            }   
            if(visualizeComboBox.getSelectedItem().equals("Scatter_Plot")){
                dt.getscatter_chart();
            }  
          
        }
        BarPanel.setVisible(false);   
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void visualizeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBoxItemStateChanged

    private void visualizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBoxActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
BarPanel.setVisible(true);   
        if(fileNametxt.getText().isEmpty())   {
                           AlertMessage("First Browse a File!!!","src/images/close.png"," Alert!!!");
    }
        else if(!countClicked)   {
                           AlertMessage("First Count Metrics!!!","src/images/close.png"," Alert!!!"); 
        }
        else{
            if(visualizeComboBox1.getSelectedItem().equals("...............Select...............")){
                 AlertMessage("Please Select a Chart!!!","src/images/close.png"," Alert!!!"); 
               return;
            }           
            JavaFileCharts dt=new JavaFileCharts();
            if(visualizeComboBox1.getSelectedItem().equals("2D Bar Chart")){
                dt.DT2D_BarChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Pie Chart")){
                 dt.DT2D_PieChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Line Chart")){
                dt.DT2D_LineChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Waterfall Chart")){
                dt.DT2D_WaterfallChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Dual Chart")){
                dt.DT2D_DualChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Area Chart")){
                dt.DT2D_AreaChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Stack Area Chart")){
                dt.DT2D_StackAreaChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("2D Stack Bar Chart")){
                dt.DT2D_StackBarChart();
            }                    
            if(visualizeComboBox1.getSelectedItem().equals("3D Bar Chart")){
                dt.DT3D_BarChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("3D Pie Chart")){
                dt.DT3D_PieChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("3D Line Chart")){
                dt.DT3D_LineChart();
            }
            if(visualizeComboBox1.getSelectedItem().equals("3D Stack Bar Chart")){
                dt.DT3D_StackBarChart();
            }            
        }
        BarPanel.setVisible(false);   
    }//GEN-LAST:event_jButton5ActionPerformed

    private void visualizeComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBox1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox1ItemStateChanged

    private void visualizeComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox1ActionPerformed

    private void noOfFORLOOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfFORLOOPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfFORLOOPActionPerformed

    private void noOfDOWHILELOOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfDOWHILELOOPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfDOWHILELOOPActionPerformed

    private void noOfWHILELOOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfWHILELOOPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfWHILELOOPActionPerformed

    private void noOfIFStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfIFStatementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfIFStatementActionPerformed

    private void noOfSwitchStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfSwitchStatementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfSwitchStatementActionPerformed

    private void noOfTryBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfTryBlockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfTryBlockActionPerformed

    private void noOfElseIfStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfElseIfStatementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfElseIfStatementActionPerformed

    private void noOfElseStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfElseStatementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfElseStatementActionPerformed

    private void noOfCasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfCasesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfCasesActionPerformed

    private void noOfCatchBlocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfCatchBlocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfCatchBlocksActionPerformed

    private void noOfFinallyBlocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfFinallyBlocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfFinallyBlocksActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
BarPanel.setVisible(true);   
        if(fileNametxt.getText().isEmpty())   {
               AlertMessage("Browse a File First!!!","src/images/close.png"," Alert!!!");             
         }
        else if(!countClicked)   {
              AlertMessage("Please Count Metrics!!!","src/images/close.png"," Alert!!!"); 
        }
        else{
            if(visualizeComboBox4.getSelectedItem().equals("...............Select...............")){
                  AlertMessage("Please Select a Chart!!!","src/images/close.png"," Alert!!!"); 
               return;
            }           
            JavaFileCharts dt=new JavaFileCharts();
            if(visualizeComboBox4.getSelectedItem().equals("2D Bar Chart")){
                dt.LOOP2D_BarChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Pie Chart")){
                 dt.LOOP2D_PieChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Line Chart")){
                dt.LOOP2D_LineChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Waterfall Chart")){
                dt.LOOP2D_WaterfallChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Dual Chart")){
                dt.LOOP2D_DualChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Area Chart")){
                dt.LOOP2D_AreaChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Stack Area Chart")){
                dt.LOOP2D_StackAreaChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("2D Stack Bar Chart")){
                dt.loop2d_StackBarchart();
            }                    
            if(visualizeComboBox4.getSelectedItem().equals("3D Bar Chart")){
                dt.LOOP3D_BarChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("3D Pie Chart")){
                dt.LOOP3D_PieChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("3D Line Chart")){
                dt.LOOP2D_LineChart();
            }
            if(visualizeComboBox4.getSelectedItem().equals("3D Stack Bar Chart")){
                dt.LOOP3D_StackBarChart();
            }            
        }
        BarPanel.setVisible(false);   
    }//GEN-LAST:event_jButton8ActionPerformed

    private void visualizeComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBox4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox4ItemStateChanged

    private void visualizeComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
BarPanel.setVisible(true);   
        if(fileNametxt.getText().isEmpty())   {
            AlertMessage("First Browse a File!!!","src/images/close.png"," Alert!!!");          
        }
        else if(!countClicked)   {
             AlertMessage("Please Count Metrics!!!","src/images/close.png"," Alert!!!");
        }
        else{
            if(visualizeComboBox5.getSelectedItem().equals("...............Select...............")){
                 AlertMessage("Please Select a Chart!!!","src/images/close.png"," Alert!!!");
               return;
            }           
            JavaFileCharts dt=new JavaFileCharts();
            if(visualizeComboBox5.getSelectedItem().equals("2D Bar Chart")){
                dt.CST2D_BarChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Pie Chart")){
                 dt.CST2D_PieChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Line Chart")){
                dt.CST2D_LineChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Waterfall Chart")){
                dt.CST2D_WaterfallChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Dual Chart")){
                dt.CST2D_DualChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Area Chart")){
                dt.CST2D_AreaChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Stack Area Chart")){
                dt.CST2D_StackAreaChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("2D Stack Bar Chart")){
                dt.CST2D_StackBarChart();
            }                    
            if(visualizeComboBox5.getSelectedItem().equals("3D Bar Chart")){
                dt.CST3D_BarChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("3D Pie Chart")){
                dt.CST3D_PieChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("3D Line Chart")){
                dt.CST2D_LineChart();
            }
            if(visualizeComboBox5.getSelectedItem().equals("3D Stack Bar Chart")){
                dt.CST3D_StackBarChart();
            }            
        }
        BarPanel.setVisible(false);   
    }//GEN-LAST:event_jButton9ActionPerformed

    private void visualizeComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBox5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox5ItemStateChanged

    private void visualizeComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox5ActionPerformed

    private void noOfINTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfINTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfINTVActionPerformed

    private void noOfShortINTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfShortINTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfShortINTVActionPerformed

    private void noOfLongINTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfLongINTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfLongINTVActionPerformed

    private void noOfSTRINGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfSTRINGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfSTRINGVActionPerformed

    private void noOfDOUBLEVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfDOUBLEVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfDOUBLEVActionPerformed

    private void noOfFLOATVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfFLOATVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfFLOATVActionPerformed

    private void noOfBOOLVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfBOOLVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfBOOLVActionPerformed

    private void noOfCHARVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfCHARVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfCHARVActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
BarPanel.setVisible(true);   
        if(fileNametxt.getText().isEmpty())   {
               AlertMessage("First Browse a File!!!","src/images/close.png"," Alert!!!");
    }
        else if(!countClicked)   {
             AlertMessage("First Count Metrics!!!","src/images/close.png"," Alert!!!");       
        }
        else{
            if(visualizeComboBox2.getSelectedItem().equals("...............Select...............")){
                 AlertMessage("Please Select a Chart!!!","src/images/close.png"," Alert!!!");            
               return;
            }           
            JavaFileCharts dt=new JavaFileCharts();
            if(visualizeComboBox2.getSelectedItem().equals("2D Bar Chart")){
                dt.DTV2D_BarChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Pie Chart")){
                 dt.DTV2D_PieChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Line Chart")){
                dt.DTV2D_LineChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Waterfall Chart")){
                dt.DTV2D_WaterfallChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Dual Chart")){
                dt.DTV2D_DualChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Area Chart")){
                dt.DTV2D_AreaChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Stack Area Chart")){
                dt.DTV2D_StackAreaChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("2D Stack Bar Chart")){
                dt.DTV2D_StackBarChart();
            }                    
            if(visualizeComboBox2.getSelectedItem().equals("3D Bar Chart")){
                dt.DTV3D_BarChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("3D Pie Chart")){
                dt.DTV3D_PieChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("3D Line Chart")){
                dt.DTV3D_LineChart();
            }
            if(visualizeComboBox2.getSelectedItem().equals("3D Stack Bar Chart")){
                dt.DTV3D_StackBarChart();
            }            
        }
        BarPanel.setVisible(false);   
    }//GEN-LAST:event_jButton6ActionPerformed

    private void visualizeComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBox2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox2ItemStateChanged

    private void visualizeComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
BarPanel.setVisible(true);   
        // These are created to show that our CBO function is working
        JavaPackageMetrics jpm1,jpm2,jpm3=new JavaPackageMetrics();
        BrowseJavaPackage bp1,bp2=new BrowseJavaPackage();
        SignInUp sp=new SignInUp();
        ForgetPassword fp=new ForgetPassword();
        Dashboard bd1,db2=new Dashboard();
        ChangePassword cp1,cp2=new ChangePassword();
        
        if(fileNametxt.getText().isEmpty())   {
       AlertMessage("Browse a File First!!!","src/images/close.png"," Alert!!!");        
        }
        else if(!countClicked)   {
              AlertMessage("First Count Metrics!!!","src/images/close.png"," Alert!!!");
        }
        else{           
            try {
                String report="src/Report/newReport.jrxml";
                JasperReport jasp_rep= JasperCompileManager.compileReport(report);
                JasperPrint jasp_print= JasperFillManager.fillReport(jasp_rep, null, con);
                jasp_print.setName("SCMV Report");
                JasperViewer.viewReport(jasp_print,false);
           

            } catch (JRException ex) {
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 BarPanel.setVisible(false);   
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        GoodBye gb=new GoodBye();
       gb.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    public void browse_file(){  
        Originalfname=null;
        JFileChooser  chooser = new JFileChooser();    
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Java files", "java"));
        chooser.setAcceptAllFileFilterUsed(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
           File f = chooser.getSelectedFile();       
        Originalfname=f.getAbsolutePath();        
        fileNametxt.setText(Originalfname);
           
        }
    }
    public int calcComments(){
         FileReader fr = null;
        String str = null;
        int count=0;
         List<String> lines =  new LinkedList<String>() {};
    try {       
        fr = new FileReader(Originalfname);
        BufferedReader reader = new BufferedReader(fr);
        while ((str = reader.readLine()) != null) {
            lines.add(str);
        }
          count=calcComments(lines);
       
    } catch (FileNotFoundException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }
     return count;
    }
    public  int calcComments(List<String> lines){
		//temporary placeholder for number of comment lines
            int commentLines = 0;
		//temporary placeholder for line being checked
            String line;
            boolean MULTILINE = false;
            Iterator itr = lines.iterator();
		//parse entire file
            while(itr.hasNext()){
            line = ((String)itr.next());
            //remove strings within quotes, do not forget about escape sequences
            line.replaceAll("\\."," ");
            line.replaceAll("\\\"[^\\\"]*\\\""," ");
            //special case of multiline comments
            if (line.matches(".*[/][*].*") || MULTILINE) {
                    MULTILINE = true;
                    commentLines++;
                    //termination of multi line comments
                    if (line.matches(".*[*][/].*")){
                            MULTILINE = false;
                        }
                    continue;
		}
            //check using regular expression if the line contains any comments 
            if (line.matches(".*//.*") || line.matches(".*[/][*].*[*][/].*")){
                    commentLines++;
                    continue;
            }
            }
		return commentLines;
	} 
    
    // Variables Counting
    public  void readFileLineByLineForDTV() throws FileNotFoundException{  
                    listOfPhyLines.clear();
                    listOfBraceLines.clear();
                    INT_Lines.clear();
                    SHORT_INT_Lines.clear();
                    LONG_INT_Lines.clear();
                    BOOL_Lines.clear();
                    DOUBLE_Lines.clear();
                    CHAR_Lines.clear();
                    FLOAT_Lines.clear();
                    STRING_Lines.clear();
                    INT_Count=0;
                    SHORT_INT_Count=0;
                    LONG_INT_Count=0;
                    STRING_Count=0;
                    BOOL_Count=0;
                    FLOAT_Count=0;
                    DOUBLE_Count=0;
                    CHAR_Count=0;
       try {          
            FileReader fr = new FileReader(fname);
            BufferedReader br=new BufferedReader(fr);
            String completeLine="";
            boolean brace_flag=false;
            int ch;
            
            //  ASCII Values
             // 40-> '(' 41-> ')' 10-> new line 9-> tab 59-> ';'
            
             while((ch=br.read())!=-1){
               // System.out.println(String.valueOf((char)ch)+" : "+ch);
                if(ch==40 || brace_flag){
                    if(ch==40){
                    listOfPhyLines.add(completeLine);
                    completeLine="";
                    }
                    brace_flag=true;
                    if(ch==10 || ch==9)
                        completeLine+=" ";
                        else
                    completeLine+=String.valueOf((char)ch);
                }                
                else{    
                    if(ch==10 || ch==9)
                        completeLine+=" ";
                    else
                    completeLine+=String.valueOf((char)ch);
                if(ch==59){
                    listOfPhyLines.add(completeLine);
                    completeLine="";
                }                
                }
                if(ch==41){
                  brace_flag=false;
                  listOfBraceLines.add(completeLine);
                  //System.out.println(completeLine);
                  completeLine="";
                }
               // System.out.println(completeLine+":"+ch);
            }
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        calculateDTVFromBrace();
        calculateDTVFromPHY();
//        System.out.println("int --> "+INT_Count);
//        System.out.println("short int --> "+SHORT_INT_Count);
//        System.out.println("long int --> "+LONG_INT_Count);
//        System.out.println("bool --> "+BOOL_Count);
//        System.out.println("double --> "+DOUBLE_Count);
//        System.out.println("Float --> "+FLOAT_Count);
    }
        
    public static void calculateDTVFromPHY(){
        for(String str:listOfPhyLines){
            
            if(str.contains(";"))
            {
                if(str.contains("String "))
                {
                    boolean flag=false;
                    String line="";
                   // System.out.println(str);
                 StringTokenizer tk= new StringTokenizer(str);        
                 while(tk.hasMoreElements())
                 {                                
                   String token=tk.nextToken();
                   if(token.contains("String ")||flag)
                   {
                    line+=token+" ";
                     flag=true;
                   }
                 }
                 STRING_Lines.add(line);
                }
                if(str.contains("double "))
                {
                    boolean flag=false;
                    String line="";
                   //  System.out.println(str);
                 StringTokenizer tk= new StringTokenizer(str);        
                 while(tk.hasMoreElements())
                 {                                
                   String token=tk.nextToken();
                   if(token.contains("double ")||flag)
                   {
                    line+=token+" ";
                     flag=true;
                   }
                 }
                 DOUBLE_Lines.add(line);
                }
                if(str.contains("float "))
                {
                    boolean flag=false;
                    String line="";
                   //  System.out.println(str);
                 StringTokenizer tk= new StringTokenizer(str);        
                 while(tk.hasMoreElements())
                 {                                
                   String token=tk.nextToken();
                   if(token.contains("float ")||flag)
                   {
                    line+=token+" ";
                     flag=true;
                   }
                 }
                 FLOAT_Lines.add(line);
                }
                if(str.contains("boolean "))
                {
                    boolean flag=false;
                    String line="";
                   //  System.out.println(str);
                 StringTokenizer tk= new StringTokenizer(str);        
                 while(tk.hasMoreElements())
                 {                                
                   String token=tk.nextToken();
                   if(token.contains("boolean ")||flag)
                   {
                    line+=token+" ";
                     flag=true;
                   }
                 }
                 BOOL_Lines.add(line);
                }
                if(str.contains("char "))
                {
                    boolean flag=false;
                    String line="";
                   //  System.out.println(str);
                 StringTokenizer tk= new StringTokenizer(str);        
                 while(tk.hasMoreElements())
                 {                                
                   String token=tk.nextToken();
                   if(token.contains("char ")||flag)
                   {
                    line+=token+" ";
                     flag=true;
                   }
                 }
                 CHAR_Lines.add(line);
                }
                if(str.contains("int ")){                    
                
                if(str.contains("long int "))
                {
                    boolean flag=false;
                    String line="";
               //    System.out.println(str);
                 StringTokenizer tk= new StringTokenizer(str);        
                 while(tk.hasMoreElements())
                 {                                
                   String token=tk.nextToken();
                   if(token.contains("long")||flag)
                   {
                    line+=token+" ";
                     flag=true;
                   }
                 }
                 LONG_INT_Lines.add(line);
                }
                else if(str.contains("short int "))
                {
                    boolean flag=false;
                    String line="";
                  //   System.out.println(str);
                    StringTokenizer tk= new StringTokenizer(str);
                    while(tk.hasMoreElements())
                    {
                        String token=tk.nextToken();
                        if(token.contains("short")||flag)
                        {
                            line+=token+" ";
                           ///  System.out.println("line->"+line);
                            flag=true;
                        }
                    }
                    SHORT_INT_Lines.add(line);
                }else{
                    boolean flag=false;
                    String line="";
                   //  System.out.println(str);
                    StringTokenizer tk= new StringTokenizer(str);
                    while(tk.hasMoreElements())
                    {
                        String token=tk.nextToken();
                        if(token.contains("int")||flag)
                        {
                            line+=token+" ";
                            flag=true;
                        }
                    }
                    INT_Lines.add(line);
                }
                }
            }
       
        } 
       //  System.out.println("------- lines -----------");
       for(String str:INT_Lines){
      // System.out.println(str);
        countDTVFromToken(str);
       }
        for(String str:SHORT_INT_Lines){
        countDTVFromToken(str);
       }
        for(String str:LONG_INT_Lines){
        countDTVFromToken(str);
       }
        for(String str:CHAR_Lines){
        countDTVFromToken(str);
       }
        for(String str:DOUBLE_Lines){
        countDTVFromToken(str);
       }
        for(String str:BOOL_Lines){
        countDTVFromToken(str);
       }
        for(String str:FLOAT_Lines){
        countDTVFromToken(str);
       }
        for(String str:STRING_Lines){
           System.out.println(str);
        countDTVFromToken(str);
       }
    }  
    public static void calculateDTVFromBrace(){
        for(String str:listOfBraceLines){
            if(str.contains(";"))
            {                
                StringTokenizer tk= new StringTokenizer(str,";");        
                while(tk.hasMoreElements())
                {                                   
                   String token=tk.nextToken();
                    countDTVFromToken(token);
                }
            }
            else{
                StringTokenizer tk= new StringTokenizer(str,",");        
                while(tk.hasMoreElements())
                {                    
                   String token=tk.nextToken(); 
                   countDTVFromToken(token);
                }
            }
        }       
    }        
    public static void countDTVFromToken(String str){
               
         if(str.contains("double "))
        {         
           // System.out.println(str);
              StringTokenizer tk= new StringTokenizer(str,",");            
              DOUBLE_Count+=tk.countTokens();  
             ///  System.out.println(tk.countTokens());
        }
          if(str.contains("float "))
        {          
              StringTokenizer tk= new StringTokenizer(str,",");            
              FLOAT_Count+=tk.countTokens();       
        }
           if(str.contains("char "))
        {          
              StringTokenizer tk= new StringTokenizer(str,",");            
              CHAR_Count+=tk.countTokens();       
        }
            if(str.contains("boolean "))
        {          
              StringTokenizer tk= new StringTokenizer(str,",");            
              BOOL_Count+=tk.countTokens();       
        }
             if(str.contains("String "))
        {          
             
              StringTokenizer tk= new StringTokenizer(str,",");            
              STRING_Count+=tk.countTokens();  
            
        }
              if(str.contains("int "))
        {        
             StringTokenizer tk= new StringTokenizer(str,",");    
              while(tk.hasMoreElements()){
                    String token=tk.nextToken(); 
                   // System.out.println(token);                
                
            if(token.contains("long int "))
            {                     
              LONG_INT_Count++;      
             }
            else if(token.contains("short int "))
            {                       
              SHORT_INT_Count++;       
             }
            else{          
                INT_Count++;  
            }
        } 
        }
    } 
    
    
    // Data Types Calculation
     public int INT_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("int")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("int")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("int")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("int")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("int")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("int")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("int"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int SHORT_INT_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("short")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("short")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("short")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("short")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("short")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("short")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("short"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int LONG_INT_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("long")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("long")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("long")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("long")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("long")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("long")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("long"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int STRING_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("String")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("String")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("String")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("String")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("String")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("String")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("String"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int DOUBLE_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("double")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("double")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("double")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("double")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("double")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("double")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("double"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int FLOAT_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("float")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("float")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("float")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("float")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("float")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("float")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("float"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int BOOL_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("boolean")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("boolean")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("boolean")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("boolean")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("boolean")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("boolean")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("boolean"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     public int CHAR_calculate(String str){
        
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
       
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("char")){                
            StringTokenizer tk2= new StringTokenizer(token,";");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("char")){           
            StringTokenizer tk3= new StringTokenizer(token2,"(");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("char")){
            StringTokenizer tk4= new StringTokenizer(token3,"{"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("char")){
            StringTokenizer tk5= new StringTokenizer(token4,"}"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("char")){
            StringTokenizer tk6= new StringTokenizer(token5,","); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.contains("char")){
            StringTokenizer tk7= new StringTokenizer(token6,"*/"); 
            while(tk7.hasMoreElements()){
            String token7=tk7.nextToken(); 
                if(token7.matches("char"))                  
                    count++;           
            }
            }            
            }
            }
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }
     
   // Conditional Statements Methods
    public int calculateIF() throws FileNotFoundException{
            int if_no=0;          
            FileReader fr = new FileReader(fname);          
            BufferedReader br=new BufferedReader(fr);
            String str,completeLine="";
            Boolean flag=false;
            try {
                    while((str=br.readLine())!=null){
 
                        StringTokenizer tk= new StringTokenizer(str);
                        while(tk.hasMoreElements()){
                            String token=tk.nextToken();
                            if(token.contains("if")){
                                
                                StringTokenizer tk2= new StringTokenizer(token,"(");
                                while(tk2.hasMoreElements()){
                                    String token2=tk2.nextToken();
                                    
                                    if(token2.contains("if")){
                                        StringTokenizer tk3= new StringTokenizer(token2,";");
                                        while(tk3.hasMoreElements()){
                                            String token3=tk3.nextToken();
                                            
                                            if(token3.contains("if")){
                                                StringTokenizer tk4= new StringTokenizer(token3,"}");
                                                while(tk4.hasMoreElements()){
                                                    String token4=tk4.nextToken();
                                                    
                                                    if(token4.contains("if")){
                                                        StringTokenizer tk5= new StringTokenizer(token4,"*/");
                                                        while(tk5.hasMoreElements()){
                                                            String token5=tk5.nextToken();
                                                            if(token5.matches("if")){
                                                                if_no++;
                                                                //System.out.println(token5);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                    }   
            } catch (IOException ex) {
                    Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            return if_no;   
    }
    public int calculateELSE()throws FileNotFoundException{
        FileReader fr=null;
        int else_no=0;
        fr = new FileReader(fname);
        BufferedReader br=new BufferedReader(fr);
        String str,completeLine="";
        Boolean flag=false;
            try {
                while((str=br.readLine())!=null){
                    
                    StringTokenizer tk= new StringTokenizer(str);
                    while(tk.hasMoreElements()){
                        String token=tk.nextToken();
                        if(token.contains("else")){
                            StringTokenizer tk2= new StringTokenizer(token,"}");
                            while(tk2.hasMoreElements()){
                                String token2=tk2.nextToken();
                                
                                if(token2.contains("else")){
                                    StringTokenizer tk3= new StringTokenizer(token2,"{");
                                    while(tk3.hasMoreElements()){
                                        String token3=tk3.nextToken();
                                        
                                        if(token3.contains("else")){
                                            StringTokenizer tk4= new StringTokenizer(token3,"*/");
                                            while(tk4.hasMoreElements()){
                                                String token4=tk4.nextToken();                                                
                                                if(token4.matches("else"))
                                                    else_no++;
                                                //  System.out.println(token4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }                   
                }
            } catch (IOException ex) {
                    Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
                }
         return else_no;        
    }
    public int calculateELSEIF() throws FileNotFoundException{            
        int  else_if_no=0; 
            FileReader fr = new FileReader(fname);          
            BufferedReader br=new BufferedReader(fr);
            String str,completeLine="";
            Boolean flag=false;
            try {
            while((str=br.readLine())!=null){
            if(str.contains("else") || flag){
                completeLine+=str;
                if(str.contains ("{")){
                                    
                StringTokenizer tk= new StringTokenizer(completeLine,"}");
                while(tk.hasMoreElements()){
                String token=tk.nextToken();       
                 
                if(token.contains("else")){
                StringTokenizer tk2= new StringTokenizer(token,"(");
                while(tk2.hasMoreElements()){
                String token2=tk2.nextToken();
                
                if(token2.contains("else")){
                StringTokenizer tk3= new StringTokenizer(token2,"*/");
                while(tk3.hasMoreElements()){
                String token3=tk3.nextToken();
                
                if(token3.contains("else")){
                StringTokenizer tk4= new StringTokenizer(token3);
                while(tk4.hasMoreElements()){
                String token4=tk4.nextToken();
                if(token4.matches("else"))
                    if(tk4.nextToken().matches("if")){
                   else_if_no++;
                        //System.out.println(token3);
                   break;
               }
                }
                }
                }
                }
                completeLine="";
                }
                }
                }
                }else
                    flag=true;
            }            
                }
                       
            } catch (IOException ex) {
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    fr.close();                    
                } catch (IOException ex) {
                    Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return else_if_no;
  }
    public int calculateSWITCH(String str){
        int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("switch")){                
            StringTokenizer tk2= new StringTokenizer(token,"(");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("switch")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("switch")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("switch")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("switch")){
            StringTokenizer tk6= new StringTokenizer(token5,"{"); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken();
            
            if(token6.matches("switch"))
                 count++;
                //System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
            }
            }
        }
         return count;
   }       
    public int calculateCASE(String str){
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("case")){                
            StringTokenizer tk2= new StringTokenizer(token,"{");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("case")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("case")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("case")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            if(token5.matches("case"))
                 count++;
                //System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
            }            
          return count;
   }   
    public int calculateTRY(String str){
        int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("try")){                
            StringTokenizer tk2= new StringTokenizer(token,"{");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("try")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("try")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("try")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            if(token5.matches("try"))
                count++;
                //System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
        }
         return count;
   }   
    public  int calculateCATCH(String str){
        int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("catch")){                
            StringTokenizer tk2= new StringTokenizer(token,"(");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("catch")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("catch")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("catch")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            if(token5.matches("catch"))
                count++;
                //System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }   
    public  int calculateFINALLY(String str){
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        
         while(tk.hasMoreElements()){
            String token=tk.nextToken();       
            if(token.contains("finally")){                
            StringTokenizer tk2= new StringTokenizer(token,"{");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("finally")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("finally")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("finally")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            if(token5.matches("finally"))
                count++;
               // System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
        }
          return count;
   }   
   
    //Loops Calculator
    public int calculateFOR(String str){        
        int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        while(tk.hasMoreElements()){
        String token=tk.nextToken(); 
            
            if(token.contains("for")){                
            StringTokenizer tk2= new StringTokenizer(token,"{");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("for")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("for")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("for")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            
            if(token5.contains("for")){
            StringTokenizer tk6= new StringTokenizer(token5,"("); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken(); 
            
            if(token6.matches("for"))
             count++;
                //System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
            }
            }
        }
       return count;
   }
    public int calculateWHILE(String str){
        int count=0;
            StringTokenizer tk= new StringTokenizer(str);
            while(tk.hasMoreElements()){
            String token=tk.nextToken(); 
            
            if(token.contains("while")){                
            StringTokenizer tk2= new StringTokenizer(token,"{");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("while")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("while")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("while")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken();
            
            if(token5.contains("while")){
            StringTokenizer tk6= new StringTokenizer(token5,"("); 
            while(tk6.hasMoreElements()){
            String token6=tk6.nextToken();        
            
            if(token6.matches("while"))
                count++;
              //  System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
            }
            }
        }
             return count;     
   }    
    public int calculateDOWHILE(String str){
       int count=0;
        StringTokenizer tk= new StringTokenizer(str);
        while(tk.hasMoreElements()){
        String token=tk.nextToken(); 
            
            if(token.contains("do")){                
            StringTokenizer tk2= new StringTokenizer(token,"{");            
            while(tk2.hasMoreElements()){
            String token2=tk2.nextToken();
            
            if(token2.contains("do")){           
            StringTokenizer tk3= new StringTokenizer(token2,";");            
            while(tk3.hasMoreElements()){
            String token3=tk3.nextToken(); 
            
            if(token3.contains("do")){
            StringTokenizer tk4= new StringTokenizer(token3,"}"); 
            while(tk4.hasMoreElements()){
            String token4=tk4.nextToken(); 
            
            if(token4.contains("do")){
            StringTokenizer tk5= new StringTokenizer(token4,"*/"); 
            while(tk5.hasMoreElements()){
            String token5=tk5.nextToken(); 
            if(token5.matches("do"))
              count++;
               // System.out.println(token5);
            } 
            }
            }
            }
            }
            }
            }
            }
        }
        return count;
   }
    //   eliminating commented lines
      public  void eliminateCommentedLines()throws FileNotFoundException{
         
        String lines="";
         try {          
            FileReader fr = new FileReader(Originalfname);
            BufferedReader br=new BufferedReader(fr);
          
            int ch;
            boolean multiComSlash=false;
            boolean multiCom=false;
            boolean multiComclose=false;
            
            //  47-> '/'  42-> '*'
            
            while((ch=br.read())!=-1){
                
               
                if(ch==47 && multiComclose){
                    multiCom=false;
                }
                if(multiCom){
                  if(ch==42){
                      multiComclose=true;
                  }else{
                      multiComclose=false;
                  }
              }
                
                if(ch==42 && multiComSlash){
                      multiCom=true;
                  } 
              if(ch==47){
                  multiComSlash=true;                 
              }else{
                   multiComSlash=false;      
              }
              
              
               if(!multiCom ){
                  lines+=String.valueOf((char)ch);                
          
              }                
            }
            createTemporaryFile(lines);
            eliminateSingleCommentedLines();
         //   System.out.println(lines);
           //eliminateSingleCommentedLines();
          //   System.out.println("level --> "+deepLevel);
          
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public  void eliminateSingleCommentedLines()throws FileNotFoundException{
         
        String lines="";
         try {          
            FileReader fr = new FileReader(fname);
            BufferedReader br=new BufferedReader(fr);
          
            int ch;
            boolean ComSlash=false;
            boolean Comment=false;
            
            //  47-> '/'  42-> '*'
            
            while((ch=br.read())!=-1){
              
                if(Comment){
                  if(ch==10){
                      Comment=false;
                  }else{
                      Comment=true;
                  }
              }
                
                if(ch==47 && ComSlash){
                      Comment=true;
                  } 
              if(ch==47){
                  ComSlash=true;                 
              }else{
                   ComSlash=false;      
              }             
              
               if(!Comment && !ComSlash ){
                  lines+=String.valueOf((char)ch);               
          
              }                
            }
            createTemporaryFile(lines);         
           // System.out.println(lines);
           
          //   System.out.println("level --> "+deepLevel);
          
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public  void createTemporaryFile(String str){
        try {
            
            File file =new File(fname);               
                file.createNewFile();
                PrintWriter pw=new PrintWriter(file); 
                pw.println(str);
                pw.close();                 
             } catch (IOException ex) {              
            }
    }
      public int countParentsChild(String s){
        FileReader fr = null;
         String str = "";
         String Line = "";
         boolean doWrite=false;
         int parent=0;
         String className="class "+s+" ";
          
        try {
            fr = new FileReader(fname);
            BufferedReader reader = new BufferedReader(fr);
            
            while ((str = reader.readLine()) != null){
                 
                 if(str.contains(className) ){
                 doWrite=true;
                 }
                 if(doWrite)
                     Line+=str;
                 if(str.contains("{")){
                         doWrite=false;
                     }
                 }
                 
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }    
        if(Line.contains("extends")){
            parent++;
        }
           // System.out.println(Line); 
            
            if(Line.contains("implements"))
            {
                int indexofImp = Line.indexOf("implements ");
                int indexofbrace = Line.indexOf("{");
                String interfaceString=Line.substring(indexofImp, indexofbrace);
               // System.out.println(interfaceString); 
               StringTokenizer tk1= new StringTokenizer(interfaceString,","); 
               parent+=tk1.countTokens();
            }
           // System.out.println(parent); 
            return parent;
    }
        public int countChild(String s){
        FileReader fr = null;
         String str = null;
         String Line = null;
         int child=0;
         String className=" extends "+s;
          
        try {
            fr = new FileReader(fname);
            BufferedReader reader = new BufferedReader(fr);
            
            while ((str = reader.readLine()) != null){
                 
                 if(str.contains(className) ){
                child++;
                 }
                
            }   
        } catch (IOException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }           
               // System.out.println(child); 
              return child;           
    }
   // Data Base Inserting Methods
    public void clearDBtbl(){
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
        } catch (SQLException ex) {
            Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertInDATATYPEtbl(int t_int,int t_shortint,int t_longint,int t_string,int t_double,int t_float,int t_bool,int t_char){
    String query= "    INSERT INTO `datatypetbl`(`total_int`, `total_short_int`, `total_long_int`, `total_string`, `total_double`, `total_float`, `total_bool`, `total_char`) VALUES (?,?,?,?,?,?,?,?);";
    try {
        pstm=con.prepareStatement(query);
        pstm.setString(1,String.valueOf(t_int));
        pstm.setString(2,String.valueOf(t_shortint));
        pstm.setString(3,String.valueOf(t_longint));
        pstm.setString(4,String.valueOf(t_string));
        pstm.setString(5,String.valueOf(t_double));
        pstm.setString(6,String.valueOf(t_float));
        pstm.setString(7,String.valueOf(t_bool));
        pstm.setString(8,String.valueOf(t_char));
        pstm.execute();
    } catch (SQLException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }             
    }  
    public void insertInVariablestbl(int t_int,int t_shortint,int t_longint,int t_string,int t_double,int t_float,int t_bool,int t_char){
    String query= " INSERT INTO `variablestbl`(`total_intv`, `total_short_intv`, `total_long_intv`, `total_stringv`, `total_doublev`, `total_floatv`, `total_boolv`, `total_charv`) VALUES (?,?,?,?,?,?,?,?);";
    try {
        pstm=con.prepareStatement(query);
        pstm.setString(1,String.valueOf(t_int));
        pstm.setString(2,String.valueOf(t_shortint));
        pstm.setString(3,String.valueOf(t_longint));
        pstm.setString(4,String.valueOf(t_string));
        pstm.setString(5,String.valueOf(t_double));
        pstm.setString(6,String.valueOf(t_float));
        pstm.setString(7,String.valueOf(t_bool));
        pstm.setString(8,String.valueOf(t_char));
        pstm.execute();
    } catch (SQLException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }             
    }  
    public void insertInLOCtbl(int loc,int bline,int cline,int pline,int lline,int obrace,int cbrace){
             String query= "INSERT INTO `loctbl`(`loc`, `bLines`, `cLines`, `pLines`, `lLines`, `oBraces`, `cBraces`) VALUES (?,?,?,?,?,?,?);";
    try {
        pstm=con.prepareStatement(query);
        pstm.setString(1,String.valueOf(loc));
        pstm.setString(2,String.valueOf(bline));
        pstm.setString(3,String.valueOf(cline));
        pstm.setString(4,String.valueOf(pline));
        pstm.setString(5,String.valueOf(lline));
        pstm.setString(6,String.valueOf(obrace));
        pstm.setString(7,String.valueOf(cbrace));
        pstm.execute();
    } catch (SQLException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }
             
    }
    public void insertInLOOPtbl(int t_for,int t_while,int t_do_while){
             String query= "INSERT INTO `looptbl`(`total_for`, `total_while`, `total_do_while`)  VALUES (?,?,?);";
    try {
        pstm=con.prepareStatement(query);
        pstm.setString(1,String.valueOf(t_for));
        pstm.setString(2,String.valueOf(t_while));
        pstm.setString(3,String.valueOf(t_do_while));
        pstm.execute();
    } catch (SQLException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }
             
    }
    public void insertInCoditionalStatetbl(int t_if,int t_else,int t_elseif,int t_switch,int t_case,int t_try,int t_catch,int t_finally){
    String query= "INSERT INTO `conditionalstatetbl`(`total_if`, `total_else`, `total_else_if`, `total_switch`, `total_case`, `total_try`, `total_catch`, `total_finally`) VALUES (?,?,?,?,?,?,?,?);";
    try {
        pstm=con.prepareStatement(query);
        pstm.setString(1,String.valueOf(t_if));
        pstm.setString(2,String.valueOf(t_else));
        pstm.setString(3,String.valueOf(t_elseif));
        pstm.setString(4,String.valueOf(t_switch));
        pstm.setString(5,String.valueOf(t_case));
        pstm.setString(6,String.valueOf(t_try));
        pstm.setString(7,String.valueOf(t_catch));
        pstm.setString(8,String.valueOf(t_finally));
        pstm.execute();
    } catch (SQLException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }             
    }  
    public void insertInClassInfotbl(String cname,String cstatus,String classcc,String classrefsug,int pc,int cc){
    String query= "INSERT INTO `classinfotbl`(`classname`, `classstatus`, `classcc`, `nopc`, `nocc`, `refsug`) VALUES (?,?,?,?,?,?);";
    try {
        pstm=con.prepareStatement(query);
        pstm.setString(1,String.valueOf(cname));
        pstm.setString(2,String.valueOf(cstatus));
        pstm.setString(3,String.valueOf(classcc));
        pstm.setString(4,String.valueOf(pc));
        pstm.setString(5,String.valueOf(cc));
        pstm.setString(6,String.valueOf(classrefsug));
        pstm.execute();
    } catch (SQLException ex) {
        Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
    }             
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
            java.util.logging.Logger.getLogger(BrowseJavaFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BrowseJavaFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BrowseJavaFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BrowseJavaFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                new BrowseJavaFile().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarPanel;
    private javax.swing.JLabel ProgressBar;
    private javax.swing.JTextField blankLinestxt;
    private javax.swing.JTextField classCCtxt;
    private javax.swing.JTextField classNametxt;
    private javax.swing.JTextField classStatustxt;
    private javax.swing.JTextField closeBracestxt;
    private javax.swing.JTextField commentedLinestxt;
    private javax.swing.JTextArea fileNametxt;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField logicalLinestxt;
    private javax.swing.JTextField noOfBOOL;
    private javax.swing.JTextField noOfBOOLV;
    private javax.swing.JTextField noOfCHAR;
    private javax.swing.JTextField noOfCHARV;
    private javax.swing.JTextField noOfCases;
    private javax.swing.JTextField noOfCatchBlocks;
    private javax.swing.JTextField noOfChildClasstxt;
    private javax.swing.JTextField noOfDOUBLE;
    private javax.swing.JTextField noOfDOUBLEV;
    private javax.swing.JTextField noOfDOWHILELOOP;
    private javax.swing.JTextField noOfElseIfStatement;
    private javax.swing.JTextField noOfElseStatement;
    private javax.swing.JTextField noOfFLOAT;
    private javax.swing.JTextField noOfFLOATV;
    private javax.swing.JTextField noOfFORLOOP;
    private javax.swing.JTextField noOfFinallyBlocks;
    private javax.swing.JTextField noOfIFStatement;
    private javax.swing.JTextField noOfINT;
    private javax.swing.JTextField noOfINTV;
    private javax.swing.JTextField noOfLongINT;
    private javax.swing.JTextField noOfLongINTV;
    private javax.swing.JTextField noOfParentClasstxt;
    private javax.swing.JTextField noOfSTRING;
    private javax.swing.JTextField noOfSTRINGV;
    private javax.swing.JTextField noOfShortINT;
    private javax.swing.JTextField noOfShortINTV;
    private javax.swing.JTextField noOfSwitchStatement;
    private javax.swing.JTextField noOfTryBlock;
    private javax.swing.JTextField noOfWHILELOOP;
    private javax.swing.JTextField openBracestxt;
    private javax.swing.JTextField physicalLinestxt;
    private javax.swing.JLabel refactoringSuggestion;
    private javax.swing.JTextField totalLOCtxt;
    private javax.swing.JComboBox visualizeComboBox;
    private javax.swing.JComboBox visualizeComboBox1;
    private javax.swing.JComboBox visualizeComboBox2;
    private javax.swing.JComboBox visualizeComboBox4;
    private javax.swing.JComboBox visualizeComboBox5;
    // End of variables declaration//GEN-END:variables
}
