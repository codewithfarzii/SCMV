/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import ExternalJavaFiles.Database;
import static MainFiles.BrowseJavaFile.fname;
import static MainFiles.BrowseJavaFile.hyu;
import static MainFiles.ClassCoupling.fileName;
import static MainFiles.ClassCoupling.selected;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import scmv.GoodBye;

/**
 *
 * @author farzeen
 */
public class JavaPackageMetrics extends javax.swing.JFrame {

    /**
     * Creates new form JavaPackageMetrics
     */
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pstm=null;
    public static String fname;
     DefaultTableModel CBOmodel;
     public static String selected;
     public List<String> listOfCBO=new ArrayList();
     List<String> alist= new ArrayList<String>();
     public String selectedPackageName;
     public  static String fileName;
   
     public JavaPackageMetrics() {
        initComponents();
        connectWithDB();
        defaultVisibilites();
        setClassInfoVis(true);
    }
    public JavaPackageMetrics(String path,String name, List<String> list,String PCKName) {
        initComponents();
        connectWithDB();        
        this.selected=path;
        this.fname=selected;  
        this.fileName=name;  
        this.alist=list;
        this.selectedPackageName=PCKName;
        fileNametxt.setText(selected);
        defaultVisibilites();
        setClassInfoVis(true);
        clearDBtbl(); 
        countMetrics();
        CBO();
        
    }
    public void connectWithDB(){
        try{
           Database db=new Database();
           con=db.openConnection();
           }catch(HeadlessException| SQLException e){
               JOptionPane.showMessageDialog(null, "DB not connected");
           }
    }
    // Coupling Methods
public void CBO(){
        CBOmodel= (DefaultTableModel)CBOTable.getModel();
        CBOmodel.setRowCount(0);
        for(String name:alist){
            try {                
                System.out.println(name);
                readFileLineByLineForPRC(selected,fileName,name);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClassCoupling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
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
                Logger.getLogger(ClassCoupling.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    fr.close();  
                    countCBO(SelectClassName,Cfname);
                } catch (IOException ex) {
                    Logger.getLogger(ClassCoupling.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    } 
    public void countCBO(String selectedClassName,String TargetClassName){       
         int count=0;
         for(String str:listOfCBO){
             System.out.println(str);
             StringTokenizer tk1= new StringTokenizer(str,"=");
             StringTokenizer tk= new StringTokenizer(tk1.nextToken(),",");
             System.out.println(tk.countTokens());
             count+=tk.countTokens();
         }
         System.out.println(count);
         CBOmodel.addRow(new Object[]{selectedClassName,TargetClassName,count});         
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

     public void countMetrics() {                                                 
            
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
            
            ifno= calculateIF();
            elseno = calculateELSE();
            elseifno= calculateELSEIF();                  
            ifno=ifno-elseifno;
            elseno=elseno-elseifno;
            List<String> lines =  new LinkedList<String>() {};
           
             while ((str = reader.readLine()) != null) {
                reader.mark(100000000);
                loc++;                           // Lines of Code ++
                lines.add(str);
                
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
                    if(str.contains("int")){               
              i_no=i_no+INT_calculate(str);
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
            i_no= i_no-si_no;
            i_no= i_no-li_no;
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
                    
                    if (token.equalsIgnoreCase("class")) {
                        
                        int index = str.indexOf("class");
                        index += 6;
                        int lastinx = str.indexOf(" ", index);
                        classname = str.substring(index, lastinx);
                        
                        classno++;
                    }
                      if (str.contains("class")&&token.contains("public")) {
                        
                       // int index = str.indexOf("class");
                       // index += 6;
                        //int lastinx = str.indexOf(" ", index);
                        //classname = str.substring(index, lastinx);
                        //classno++;
                        pc++;
                    }
                     if (str.contains("extends")&&token.contains("public")) {
                        
                        int index = str.indexOf("extends");
                        index += 8;
                        int lastinx = str.indexOf(" ", index);
                       // classname = str.substring(index, lastinx);
                        //classno++;
                        child++;
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
                        
                        try {
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
                                    try (FileWriter fw = new FileWriter("function" + funno + ".txt")) {
                                        fw.write(str + "\r\n");
                                        while ((funLine = readerInner.readLine()) != null && open) {
                                            
                                            int k = 0;
                                            while (k < funLine.length()) {
                                                if (funLine.charAt(k) == '{') {
                                                    openBraceCounter++;
                                                }
                                                if (funLine.charAt(k) == '}') {
                                                    openBraceCounter--;
                                                }
                                                if (openBraceCounter < 1) {
                                                    open = false;
                                                    break;
                                                }
                                                k++;
                                            }
                                            fw.write(funLine + "\r\n");
                                            lineOfFunction++;
                                            
                                        }
                                    }
                                    }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(token.isEmpty()){
                 elineno= elineno+1;
              }
                for( int i=0; i<token.length(); i++ ) {
                if( token.charAt(i) == '{' ) {
                    openbrace=openbrace+1; 

                } 
                  else  if(token.charAt(i)=='}'){
                              closebrace=closebrace+1;
                        }
               
             
              }
                }// end of inner while(tokens)
                // System.out.println(str);
                openbrace=closebrace;
                reader.reset();
            }
       try {
        String s1;
           if(loc >=200)
               s1="Large Class";
           else if(loc >=100 && loc<200)
               s1="Average Class";
           else
               s1="Small Class";
        String cc;
          int coc=ifno+elseno+elseifno+caseno+swhno+tryno+catchno+trno+dono+whlno+frno;
           if(coc>50){
               cc="High";
               refactoringSuggestion.setText("Derive New Class!");}
           else if(coc>=20 && coc<50){
               cc="Medium";
           refactoringSuggestion.setText("Class is Fine!");
           }
           else
           {  cc="low";
           refactoringSuggestion.setText("Adjustable Class!");
           }
           
           commentno= calcComments(lines);
           int ploc=loc-(commentno+elineno);
           tryno=catchno;
            str = reader.readLine();
            Scanner scan = new Scanner(fr);
            reader.close();   
           
           // LOC INFO
            totalLOCtxt.setText(Integer.toString(loc));
            blankLinestxt.setText(Integer.toString(elineno));
            commentedLinestxt.setText(Integer.toString(commentno));
            physicalLinestxt.setText(Integer.toString(ploc));//p
            logicalLinestxt.setText(Integer.toString(lloc));//l
            openBracestxt.setText(Integer.toString(openbrace));
            closeBracestxt.setText(Integer.toString(closebrace));
            // DATA TYPES INFO
            noOfINT.setText(Integer.toString(i_no));
            noOfShortINT.setText(Integer.toString(si_no));
            noOfLongINT.setText(Integer.toString(li_no));
            noOfSTRING.setText(Integer.toString(s_no));
            noOfDOUBLE.setText(Integer.toString(d_no));
            noOfFLOAT.setText(Integer.toString(f_no));
            noOfBOOL.setText(Integer.toString(b_no));
            noOfCHAR.setText(Integer.toString(c_no));            
            // Class INFO
            classNametxt.setText(classname);
            classStatustxt.setText(s1);
            classCCtxt.setText(cc);            
            noOfChildClasstxt.setText(Integer.toString(child));
            noOfParentClasstxt.setText(Integer.toString(pc));
            // Loop Statements INFO
            whlno=whlno-dono;
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
                     
            insertInLOCtbl(loc,elineno,commentno,ploc,lloc,openbrace,closebrace);
            insertInDATATYPEtbl(i_no,si_no,li_no,s_no,d_no,f_no,b_no,c_no);
            insertInLOOPtbl(frno,dono,whlno);
            insertInCoditionalStatetbl(ifno,elseno,elseifno,swhno,caseno,tryno,catchno,finallyno);
           
       } catch (Exception e) {
           System.out.print(e);
       }         
        }catch (FileNotFoundException ex){
                Logger.getLogger(BrowseJavaFile.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        containerPanel = new javax.swing.JPanel();
        drawer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        classInfoSelected = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        locSelected = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        ccSelected = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        couplingSelected = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fileNametxt = new javax.swing.JTextField();
        classInfoPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
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
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        locPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        totalLOCtxt = new javax.swing.JTextField();
        blankLinestxt = new javax.swing.JTextField();
        commentedLinestxt = new javax.swing.JTextField();
        physicalLinestxt = new javax.swing.JTextField();
        logicalLinestxt = new javax.swing.JTextField();
        openBracestxt = new javax.swing.JTextField();
        closeBracestxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        visualizeComboBox1 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        ccPanel = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        noOfFORLOOP = new javax.swing.JTextField();
        noOfDOWHILELOOP = new javax.swing.JTextField();
        noOfWHILELOOP = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        visualizeComboBox4 = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        noOfIFStatement = new javax.swing.JTextField();
        noOfSwitchStatement = new javax.swing.JTextField();
        noOfTryBlock = new javax.swing.JTextField();
        noOfElseIfStatement = new javax.swing.JTextField();
        noOfElseStatement = new javax.swing.JTextField();
        noOfCases = new javax.swing.JTextField();
        noOfCatchBlocks = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        noOfFinallyBlocks = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        visualizeComboBox5 = new javax.swing.JComboBox();
        couplingPanel = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        CBOTable = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(760, 590));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

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
        header.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 40, 40));

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
        header.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 40, 40));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText(" SCMV");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 40));

        containerPanel.setBackground(new java.awt.Color(204, 204, 204));
        containerPanel.setPreferredSize(new java.awt.Dimension(750, 550));
        containerPanel.setLayout(null);

        drawer.setBackground(new java.awt.Color(102, 102, 102));
        drawer.setPreferredSize(new java.awt.Dimension(190, 500));
        drawer.setRequestFocusEnabled(false);
        drawer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SCMV");
        jLabel2.setToolTipText("");
        jLabel2.setAlignmentX(5.0F);
        drawer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 150, 80));

        jPanel3.setBackground(new java.awt.Color(42, 39, 41));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        classInfoSelected.setBackground(new java.awt.Color(204, 255, 51));
        classInfoSelected.setOpaque(true);
        jPanel3.add(classInfoSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Class Info");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 3, 170, 40));

        drawer.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 79, 190, -1));

        jPanel5.setBackground(new java.awt.Color(42, 39, 41));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        locSelected.setBackground(new java.awt.Color(204, 255, 51));
        locSelected.setOpaque(true);
        jPanel5.add(locSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("LOC");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 3, 170, 40));

        drawer.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 129, 190, -1));

        jPanel12.setBackground(new java.awt.Color(42, 39, 41));
        jPanel12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel12MouseExited(evt);
            }
        });
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ccSelected.setBackground(new java.awt.Color(204, 255, 51));
        ccSelected.setOpaque(true);
        jPanel12.add(ccSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("CC");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 3, 170, 40));

        drawer.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 179, 190, 50));

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backRollOver.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        drawer.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 80));

        jPanel13.setBackground(new java.awt.Color(42, 39, 41));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel13MouseExited(evt);
            }
        });
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        couplingSelected.setBackground(new java.awt.Color(204, 255, 51));
        couplingSelected.setOpaque(true);
        jPanel13.add(couplingSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Coupling");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 3, 170, 40));

        drawer.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 229, 190, 50));

        containerPanel.add(drawer);
        drawer.setBounds(0, 0, 190, 617);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel3.setText("Selected Java File:");

        fileNametxt.setEditable(false);
        fileNametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileNametxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addComponent(fileNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        containerPanel.add(jPanel4);
        jPanel4.setBounds(190, 0, 740, 57);

        classInfoPanel.setPreferredSize(new java.awt.Dimension(740, 550));

        jLabel26.setText("Class Name");

        classNametxt.setEditable(false);
        classNametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classNametxtActionPerformed(evt);
            }
        });

        jLabel27.setText("Class Status");

        classStatustxt.setEditable(false);
        classStatustxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classStatustxtActionPerformed(evt);
            }
        });

        jLabel28.setText("Class Cyclomatic Complexity");

        classCCtxt.setEditable(false);
        classCCtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classCCtxtActionPerformed(evt);
            }
        });

        jLabel30.setText("Refactoring Suggestion:");

        refactoringSuggestion.setBackground(new java.awt.Color(204, 204, 204));
        refactoringSuggestion.setForeground(new java.awt.Color(255, 51, 51));
        refactoringSuggestion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel16.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createTitledBorder(null, "Cyclomatic Complexity Threshold Index", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12)))); // NOI18N

        jLabel48.setText("High");

        jLabel49.setText("Medium");

        jLabel50.setText("Low");

        jLabel51.setText(">=50");

        jLabel52.setText("21-50");

        jLabel53.setText("<=10");

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

        jLabel32.setText("No of Parent Class");

        jLabel33.setText("No of Child Class");

        jPanel17.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createTitledBorder(null, "Class Complexity Threshold Index", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12)))); // NOI18N

        jLabel54.setText("Large Class");

        jLabel55.setText("Medium Class");

        jLabel56.setText("Small Class");

        jLabel57.setText(">=200");

        jLabel58.setText("100-200");

        jLabel59.setText("<=100");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(classStatustxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(classCCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(classNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noOfParentClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfChildClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(refactoringSuggestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(classNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(noOfParentClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(classStatustxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel33)
                            .addComponent(noOfChildClasstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(classCCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(refactoringSuggestion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        jLabel22.setBackground(new java.awt.Color(153, 153, 153));
        jLabel22.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Class Information");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(jLabel22)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout classInfoPanelLayout = new javax.swing.GroupLayout(classInfoPanel);
        classInfoPanel.setLayout(classInfoPanelLayout);
        classInfoPanelLayout.setHorizontalGroup(
            classInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        classInfoPanelLayout.setVerticalGroup(
            classInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, classInfoPanelLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        containerPanel.add(classInfoPanel);
        classInfoPanel.setBounds(190, 57, 740, 560);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));

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

        jLabel4.setText("Total LOC");

        jLabel5.setText("Blank Lines");

        jLabel6.setText("Commented Lines");

        jLabel7.setText("Physical Lines");

        jLabel8.setText("Logical Lines");

        jLabel9.setText("Open Braces");

        jLabel34.setText("Close Braces");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton6.setText("Show");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        visualizeComboBox.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        visualizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...............Select...............", "2D Bar Chart", "2D Pie Chart", "2D Line Chart", "2D Waterfall Chart", "2D Dual Chart", "2D Area Chart", "2D Stack Area Chart", "2D Stack Bar Chart", "3D Bar Chart", "3D Pie Chart", "3D Line Chart", "3D Stack Bar Chart" }));
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
                        .addGap(55, 55, 55)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(visualizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
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
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(openBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLOCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(openBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blankLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel34)
                    .addComponent(closeBracestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(commentedLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(physicalLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(logicalLinestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LOC", jPanel2);

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

        jLabel10.setText("No of Int");

        jLabel11.setText("No of Short Int");

        jLabel12.setText("No of Long Int");

        jLabel13.setText("No of String");

        jLabel14.setText("No of Double");

        jLabel15.setText("No of Float");

        jLabel16.setText("No of Bool");

        jLabel17.setText("No of Char");

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton7.setText("Show");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(visualizeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(noOfINT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfShortINT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfLongINT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfSTRING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(noOfFLOAT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noOfDOUBLE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noOfBOOL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(noOfCHAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfINT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfDOUBLE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfShortINT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfFLOAT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfLongINT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfBOOL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfSTRING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfCHAR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17))))
                .addContainerGap(183, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Types", jPanel9);

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setBackground(new java.awt.Color(153, 153, 153));
        jLabel23.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Lines of Code Metrics");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(jLabel23)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout locPanelLayout = new javax.swing.GroupLayout(locPanel);
        locPanel.setLayout(locPanelLayout);
        locPanelLayout.setHorizontalGroup(
            locPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(locPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        locPanelLayout.setVerticalGroup(
            locPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locPanelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        containerPanel.add(locPanel);
        locPanel.setBounds(190, 57, 740, 560);

        jPanel18.setBackground(new java.awt.Color(153, 153, 153));

        jLabel31.setBackground(new java.awt.Color(153, 153, 153));
        jLabel31.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Cyclometic Complexity Metrics");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addComponent(jLabel31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

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

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("No of For Loop");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("No of Do While Loop");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("No of While Loop");

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton8.setText("Show");
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
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(visualizeComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(noOfDOWHILELOOP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfWHILELOOP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfFORLOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfFORLOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfDOWHILELOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfWHILELOOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Loop Statements", jPanel10);

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

        jLabel18.setText("No of If Statement");

        jLabel19.setText("No of Switch Statement");

        jLabel20.setText("No of Try Blocks");

        jLabel21.setText("No of Else If Statement");

        jLabel35.setText("No of Else Statement");

        jLabel36.setText("No of cases");

        jLabel37.setText("No of Catch Blocks");

        jLabel38.setText("No of finally Blocks");

        noOfFinallyBlocks.setEditable(false);
        noOfFinallyBlocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfFinallyBlocksActionPerformed(evt);
            }
        });

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Visualization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 14))); // NOI18N

        jButton9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton9.setText("Show");
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

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(visualizeComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizeComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel35))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noOfElseStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noOfIFStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noOfElseIfStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noOfCases, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addGap(26, 26, 26)
                            .addComponent(noOfSwitchStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noOfTryBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(25, 25, 25)
                                .addComponent(noOfCatchBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(jLabel38)
                            .addGap(25, 25, 25)
                            .addComponent(noOfFinallyBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 148, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfIFStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(noOfTryBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfElseStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel37)
                    .addComponent(noOfCatchBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfElseIfStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel38)
                    .addComponent(noOfFinallyBlocks, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfSwitchStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noOfCases, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Conditional Statements", jPanel19);

        javax.swing.GroupLayout ccPanelLayout = new javax.swing.GroupLayout(ccPanel);
        ccPanel.setLayout(ccPanelLayout);
        ccPanelLayout.setHorizontalGroup(
            ccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ccPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(ccPanelLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ccPanelLayout.setVerticalGroup(
            ccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ccPanelLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        containerPanel.add(ccPanel);
        ccPanel.setBounds(194, 58, 730, 580);

        jPanel21.setBackground(new java.awt.Color(153, 153, 153));

        jLabel39.setBackground(new java.awt.Color(153, 153, 153));
        jLabel39.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Class Coupling");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        CBOTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name of Class", "Class Name Having Objects", "Coupled Class Objects"
            }
        ));
        jScrollPane4.setViewportView(CBOTable);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("CBO", jPanel22);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("IC", jPanel23);

        javax.swing.GroupLayout couplingPanelLayout = new javax.swing.GroupLayout(couplingPanel);
        couplingPanel.setLayout(couplingPanelLayout);
        couplingPanelLayout.setHorizontalGroup(
            couplingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(couplingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(couplingPanelLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        couplingPanelLayout.setVerticalGroup(
            couplingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(couplingPanelLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(502, 502, 502))
            .addGroup(couplingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(couplingPanelLayout.createSequentialGroup()
                    .addGap(95, 95, 95)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        containerPanel.add(couplingPanel);
        couplingPanel.setBounds(190, 60, 740, 550);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 617, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 40, Short.MAX_VALUE)
                    .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 930, 657);

        setSize(new java.awt.Dimension(928, 657));
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

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered

    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited

    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MouseEntered

    private void jPanel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new BrowseJavaPackage(selectedPackageName).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseEntered

    private void jPanel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        defaultVisibilites();
       setClassInfoVis(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         defaultVisibilites();
            setlocVis(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
           defaultVisibilites();
            setCCVis(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       defaultVisibilites();
        setCouplingVis(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fileNametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileNametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileNametxtActionPerformed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
            if(visualizeComboBox.getSelectedItem().equals("...............Select...............")){
                JOptionPane.showMessageDialog(null,"Please select a chart!");
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
    }//GEN-LAST:event_jButton6ActionPerformed

    private void visualizeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBoxItemStateChanged

    private void visualizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBoxActionPerformed

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
            if(visualizeComboBox1.getSelectedItem().equals("...............Select...............")){
                JOptionPane.showMessageDialog(null,"Please select a chart!");
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
    }//GEN-LAST:event_jButton7ActionPerformed

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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
            if(visualizeComboBox4.getSelectedItem().equals("...............Select...............")){
                JOptionPane.showMessageDialog(null,"Please select a chart!");
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
    }//GEN-LAST:event_jButton8ActionPerformed

    private void visualizeComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBox4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox4ItemStateChanged

    private void visualizeComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox4ActionPerformed

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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
            if(visualizeComboBox5.getSelectedItem().equals("...............Select...............")){
                JOptionPane.showMessageDialog(null,"Please select a chart!");
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
    }//GEN-LAST:event_jButton9ActionPerformed

    private void visualizeComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visualizeComboBox5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox5ItemStateChanged

    private void visualizeComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeComboBox5ActionPerformed

     public void defaultVisibilites(){   
        
         classInfoPanel.setVisible(false);
         locPanel.setVisible(false);
         ccPanel.setVisible(false);
         
         classInfoSelected.setVisible(false);         
         locSelected.setVisible(false);
         ccSelected.setVisible(false); 
         couplingSelected.setVisible(false); 
    }
     public void setClassInfoVis(boolean b){
        classInfoSelected.setVisible(b);
        classInfoPanel.setVisible(b);
    }
     public void setlocVis(boolean b){
        locSelected.setVisible(b);
       locPanel.setVisible(b);
    }
     public void setCCVis(boolean b){
        ccSelected.setVisible(b);
       ccPanel.setVisible(b);
    }
     public void setCouplingVis(boolean b){
        couplingSelected.setVisible(b);
        
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
            java.util.logging.Logger.getLogger(JavaPackageMetrics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JavaPackageMetrics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JavaPackageMetrics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JavaPackageMetrics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaPackageMetrics().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CBOTable;
    private javax.swing.JTextField blankLinestxt;
    private javax.swing.JPanel ccPanel;
    private javax.swing.JLabel ccSelected;
    private javax.swing.JTextField classCCtxt;
    private javax.swing.JPanel classInfoPanel;
    private javax.swing.JLabel classInfoSelected;
    private javax.swing.JTextField classNametxt;
    private javax.swing.JTextField classStatustxt;
    private javax.swing.JTextField closeBracestxt;
    private javax.swing.JTextField commentedLinestxt;
    public javax.swing.JPanel containerPanel;
    private javax.swing.JPanel couplingPanel;
    private javax.swing.JLabel couplingSelected;
    private javax.swing.JPanel drawer;
    private javax.swing.JTextField fileNametxt;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JPanel locPanel;
    private javax.swing.JLabel locSelected;
    private javax.swing.JTextField logicalLinestxt;
    private javax.swing.JTextField noOfBOOL;
    private javax.swing.JTextField noOfCHAR;
    private javax.swing.JTextField noOfCases;
    private javax.swing.JTextField noOfCatchBlocks;
    private javax.swing.JTextField noOfChildClasstxt;
    private javax.swing.JTextField noOfDOUBLE;
    private javax.swing.JTextField noOfDOWHILELOOP;
    private javax.swing.JTextField noOfElseIfStatement;
    private javax.swing.JTextField noOfElseStatement;
    private javax.swing.JTextField noOfFLOAT;
    private javax.swing.JTextField noOfFORLOOP;
    private javax.swing.JTextField noOfFinallyBlocks;
    private javax.swing.JTextField noOfIFStatement;
    private javax.swing.JTextField noOfINT;
    private javax.swing.JTextField noOfLongINT;
    private javax.swing.JTextField noOfParentClasstxt;
    private javax.swing.JTextField noOfSTRING;
    private javax.swing.JTextField noOfShortINT;
    private javax.swing.JTextField noOfSwitchStatement;
    private javax.swing.JTextField noOfTryBlock;
    private javax.swing.JTextField noOfWHILELOOP;
    private javax.swing.JTextField openBracestxt;
    private javax.swing.JTextField physicalLinestxt;
    private javax.swing.JLabel refactoringSuggestion;
    private javax.swing.JTextField totalLOCtxt;
    private javax.swing.JComboBox visualizeComboBox;
    private javax.swing.JComboBox visualizeComboBox1;
    private javax.swing.JComboBox visualizeComboBox4;
    private javax.swing.JComboBox visualizeComboBox5;
    // End of variables declaration//GEN-END:variables
}
