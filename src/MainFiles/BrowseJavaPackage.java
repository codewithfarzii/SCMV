/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import ExternalJavaFiles.Database;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import scmv.Dashboard;
import scmv.GoodBye;
import scmv.SignInUp;

/**
 *
 * @author farzeen
 */
public class BrowseJavaPackage extends javax.swing.JFrame {

    /**
     * Creates new form BrowseJavaPackage
     */
     DefaultTableModel modl;
    
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement pstm=null;
    public static String selected;
    public  static String fileName;
    DefaultListModel m= new DefaultListModel();
    List<String> alist= new ArrayList<String>();
    public BrowseJavaPackage() {
        initComponents();
         
        try{
        Database db=new Database();
        con=db.openConnection();
        }catch(HeadlessException| SQLException e){
            JOptionPane.showMessageDialog(null, "DB not connected");
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
        jScrollPane3 = new javax.swing.JScrollPane();
        packageNameTxt = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOfFiles = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        noOfFiles = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

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
        header.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 40, 40));

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
        header.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 40, 40));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText(" SCMV");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 40));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        packageNameTxt.setColumns(20);
        packageNameTxt.setRows(5);
        jScrollPane3.setViewportView(packageNameTxt);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 46, 490, 40));

        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton1.setText("Browse Package");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 46, 180, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backRollOver.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        listOfFiles.setMaximumSize(new java.awt.Dimension(33, 500));
        listOfFiles.setMinimumSize(new java.awt.Dimension(33, 500));
        listOfFiles.setPreferredSize(new java.awt.Dimension(33, 500));
        listOfFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listOfFilesMouseClicked(evt);
            }
        });
        listOfFiles.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listOfFilesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listOfFiles);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 140, 260, 340));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Names Of Files");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 490, 45));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel3.setText("No. Of Files :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 130, 45));

        noOfFiles.setEditable(false);
        noOfFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfFilesActionPerformed(evt);
            }
        });
        jPanel1.add(noOfFiles, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 45, 45));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name of Class", "Class Name Having Objects", "Coupled Class Objects"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 157, 460, 320));

        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton3.setText("Count");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 180, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(750, 540));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       this.dispose();
        new GoodBye().setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         ClearTables();
         m.removeAllElements();
         listOfFiles.removeAll();
         packageNameTxt.setText(null);
         noOfFiles.setText(null);
         
         // select Java package              
          JFileChooser  chooser = new JFileChooser();
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          chooser.showOpenDialog(this);
          File f = chooser.getCurrentDirectory();
         String input= chooser.getSelectedFile().getAbsolutePath();
          packageNameTxt.setText(input);
    try {
        listFilesAndFilesSubDirectories(input);
    } catch (IOException ex) {
        Logger.getLogger(BrowseJavaPackage.class.getName()).log(Level.SEVERE, null, ex);
    }
       listOfFiles.setModel(m);
       int a=m.getSize();
       noOfFiles.setText(String.valueOf(a));
       insertClassNameToDB();
       
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new Dashboard().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void listOfFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listOfFilesMouseClicked
       selected=listOfFiles.getSelectedValue().toString();
       fileName=extractFileName(selected);    
    }//GEN-LAST:event_listOfFilesMouseClicked

    private void listOfFilesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listOfFilesValueChanged

    }//GEN-LAST:event_listOfFilesValueChanged

    private void noOfFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfFilesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOfFilesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         modl= (DefaultTableModel)jTable2.getModel();
         modl.setRowCount(0);
        for(String name:alist){
            try {                
                System.out.println(name);
                readFileLineByLineForPRC(selected,fileName,name);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BrowseJavaPackage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    String fg;
    public static String extractFileName( String filePathName ){
    if ( filePathName == null )
      return null;

    int dotPos = filePathName.lastIndexOf( '.' );
    int slashPos = filePathName.lastIndexOf( '\\' );
    if ( slashPos == -1 )
      slashPos = filePathName.lastIndexOf( '/' );
    if ( dotPos > slashPos )
    {
      return filePathName.substring( slashPos > 0 ? slashPos + 1 : 0,
          dotPos );
    }
    return filePathName.substring( slashPos > 0 ? slashPos + 1 : 0 );  
  }
    public void listFilesAndFilesSubDirectories(String directoryName) throws IOException{
        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles(new FileFilter(){
               public boolean accept (File pathname){
                   String st=pathname.getName();
                   if(pathname.isDirectory()||st.endsWith(".java"))
                return true;
               else
               return false;    
               } });
        
        for (File file : fList){
            if (file.isFile()){
            String ad=file.getCanonicalPath();
                   m.addElement(ad);
                   fg=extractFileName(ad);
                   alist.add(fg);  
            }    
            else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }
    public void insertClassNameToDB(){
         String query="INSERT INTO `classnamestbl`(`c_name`) VALUES (?);";
            try {             
                pstm=con.prepareStatement(query);
                for(String name: alist){
                pstm.setString(1,name);            
                pstm.execute();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BrowseJavaPackage.class.getName()).log(Level.SEVERE, null, ex);
            }       
    }
    public void ClearTables(){
         try {
            // TODO add your handling code here:
            pstm=con.prepareStatement("delete  from classnamestbl");
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrowseJavaPackage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public List<String> listOfCBO=new ArrayList();
    public void readFileLineByLineForPRC(String Tfname,String SelectClassName,String Cfname) throws FileNotFoundException{
          
            listOfCBO.removeAll(listOfCBO);
            if(SelectClassName.matches(Cfname))
                return;
        
            FileReader fr = new FileReader(Tfname);          
            BufferedReader br=new BufferedReader(fr);
            String str,completeLine="";
            Boolean flag=false;
            try {
            while((str=br.readLine())!=null){
            if(str.contains(Cfname+" ") || flag){
                completeLine+=" "+str;
                if(str.contains (";")){ 
                flag=false; 
                listOfCBO.add(completeLine);
                completeLine="";
                
                }else
                    flag=true;
            }            
                }
            } catch (IOException ex) {
                Logger.getLogger(BrowseJavaPackage.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    fr.close();  
                    countCBO(SelectClassName,Cfname);
                } catch (IOException ex) {
                    Logger.getLogger(BrowseJavaPackage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    } 
     public void countCBO(String selectedClassName,String TargetClassName){       
         int count=0;
         for(String str:listOfCBO){
             System.out.println(str);
             StringTokenizer tk= new StringTokenizer(str,",");
             System.out.println(tk.countTokens());
             count+=tk.countTokens();
         }
         System.out.println(count);
         modl.addRow(new Object[]{selectedClassName,TargetClassName,count});
         
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
            java.util.logging.Logger.getLogger(BrowseJavaPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BrowseJavaPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BrowseJavaPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BrowseJavaPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BrowseJavaPackage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JList listOfFiles;
    private javax.swing.JTextField noOfFiles;
    private javax.swing.JTextArea packageNameTxt;
    // End of variables declaration//GEN-END:variables
}
