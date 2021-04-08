/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import ExternalJavaFiles.Database;
import java.awt.Color;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYZDataset;

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
int t_if,t_else,t_elseif,t_switch,t_case,t_try,t_catch,t_finally;
public JavaFileCharts(){   
    try{
           Database db=new Database();
           con=db.openConnection();
           }catch(HeadlessException| SQLException e){
               JOptionPane.showMessageDialog(null, "DB not connected");
           }
    getValuesFromLOCtbl();
    getValuesFromDATATYPEtbl();
    getValuesFromLOOPtbl();
    getValuesFromConditionalStatetbl();
   }

    // getting data from DB Tabels
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
    public void getValuesFromConditionalStatetbl(){
    try {
        String query= "SELECT * FROM conditionalstatetbl";
        pstm=con.prepareStatement(query);
        rs=pstm.executeQuery();
        if(rs.next()){
            String a=rs.getString("total_if");
            t_if= Integer.parseInt(a);
            String b=rs.getString("total_else");
            t_else= Integer.parseInt(b);
            String c=rs.getString("total_else_if");
            t_elseif= Integer.parseInt(c);
            String d=rs.getString("total_switch");
            t_switch= Integer.parseInt(d);
            String e=rs.getString("total_case");
            t_case= Integer.parseInt(e);
            String f=rs.getString("total_try");
            t_try= Integer.parseInt(f);
            String g=rs.getString("total_catch");
            t_catch= Integer.parseInt(g);
            String h=rs.getString("total_finally");
            t_finally= Integer.parseInt(h);
        }
    } catch (SQLException ex) {
        Logger.getLogger(JavaFileCharts.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        
                    // LOC 3D Charts
        public void LOC3D_BarChart(){
                    DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(loc,"LOC", "LOC");
                    dfg.setValue(bline,"LOC", "Blank lines");
                    dfg.setValue(cline,"LOC", "Commented lines");
                    dfg.setValue(pline,"LOC", "Physical lines");
                    dfg.setValue(lline,"LOC", "Logical Lines");
                    dfg.setValue(obrace,"LOC", "Open Braces");
                    dfg.setValue(cbrace,"LOC", "Closes Braces");
                   JFreeChart jfc= ChartFactory.createBarChart3D("LOC 3D Bar Chart", "LOC", "LOC", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                    hy = new ChartFrame("3D Bar Chart Visualization of LOC",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 } 
        public void LOC3D_LineChart(){
                    DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                   // dfg.setValue(loc,"LOC", "LOC");
                    dfg.setValue(bline,"LOC", "Blank lines");
                    dfg.setValue(cline,"LOC", "Commented lines");
                    dfg.setValue(pline,"LOC", "Physical lines");
                    dfg.setValue(lline,"LOC", "Logical Lines");
                    dfg.setValue(obrace,"LOC", "Open Braces");
                    dfg.setValue(cbrace,"LOC", "Closes Braces");
                    JFreeChart jfc= ChartFactory.createLineChart3D("LOC 3D Line Chart", "Metrics","Total LOC",dfg,PlotOrientation.VERTICAL,true, true, false);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("3D Line Chart Visualization of LOC",jfc);
                    hy.setVisible(true);                    
                     hy.setSize(900,500);
 }
        public void LOC3D_StackBarChart() {
          DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                   // dfg.setValue(loc,"LOC", "LOC");
                    dfg.setValue(bline,"LOC", "Blank lines");
                    dfg.setValue(cline,"LOC", "Commented lines");
                    dfg.setValue(pline,"LOC", "Physical lines");
                    dfg.setValue(lline,"LOC", "Logical Lines");
                    dfg.setValue(obrace,"LOC", "Open Braces");
                    dfg.setValue(cbrace,"LOC", "Closes Braces");
                   JFreeChart jfc= ChartFactory.createStackedBarChart3D("LOC 3D Stacked Bar Chart", "Metrics", "Total LOC", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                   plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("3D Stack Bar Chart Visualization of LOC",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void LOC3D_PieChart(){
                      DefaultPieDataset dfgg= new DefaultPieDataset();
                       //   dfgg.setValue("LOC",loc);
                          dfgg.setValue("Commented lines",cline);
                          dfgg.setValue("Blank lines",bline);
                          dfgg.setValue("Logical lines",lline);
                          dfgg.setValue("Physical lines",pline);
                          dfgg.setValue("Open Braces",obrace);
                          dfgg.setValue("Closes Braces",cbrace);                  
                   JFreeChart jfc= ChartFactory.createPieChart3D("LOC 3D Pie Chart", dfgg, true, true, false);
                   PiePlot3D plot = (PiePlot3D)jfc.getPlot();
                   PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(loc), 0.10);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("3D Pie Chart Visualization of LOC",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);      
 }
        
                    // LOC 2D Charts
        public void LOC2D_PieChart(){
                      DefaultPieDataset dfgg= new DefaultPieDataset();
                         // dfgg.setValue("LOC",loc);
                          dfgg.setValue("Commented lines",cline);
                          dfgg.setValue("Blank lines",bline);
                          dfgg.setValue("Logical lines",lline);
                          dfgg.setValue("Physical lines",pline);
                          dfgg.setValue("Open Braces",obrace);
                          dfgg.setValue("Closes Braces",cbrace);   
                   JFreeChart jfc= ChartFactory.createPieChart("LOC 2D Pie Char", dfgg, true, true, false);
                      PiePlot plot = (PiePlot)jfc.getPlot();
                      PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(loc), 0.10);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("2D Pie Chart Visualization of LOC",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);        
 }
        public void LOC2D_BarChart(){              
       
                    DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(loc,"LOC", "LOC");
                    dfg.setValue(bline,"LOC", "Blank lines");
                    dfg.setValue(cline,"LOC", "Commented lines");
                    dfg.setValue(pline,"LOC", "Physical lines");
                    dfg.setValue(lline,"LOC", "Logical Lines");
                    dfg.setValue(obrace,"LOC", "Open Braces");
                    dfg.setValue(cbrace,"LOC", "Closes Braces");
              JFreeChart jfc= ChartFactory.createBarChart("LOC 2D Bar Chart","LOC","LOC", dfg, PlotOrientation.VERTICAL, false, true, false);
              CategoryPlot plot=jfc.getCategoryPlot();
              plot.setRangeGridlinePaint(Color.CYAN);
              ChartFrame hy;
              hy = new ChartFrame("3D Bar Chart Visualization of LOC",jfc);
              hy.setVisible(true);
              hy.setSize(900,500);              
   }
        public void LOC2D_LineChart(){
                    DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                  //  dfg.setValue(loc,"LOC", "LOC");
                    dfg.setValue(bline,"LOC", "Blank lines");
                    dfg.setValue(cline,"LOC", "Commented lines");
                    dfg.setValue(pline,"LOC", "Physical lines");
                    dfg.setValue(lline,"LOC", "Logical Lines");
                    dfg.setValue(obrace,"LOC", "Open Braces");
                    dfg.setValue(cbrace,"LOC", "Closes Braces");
                   JFreeChart jfc= ChartFactory.createLineChart("LOC 2D Line Chart", "X","Y",dfg,PlotOrientation.VERTICAL,true, true, false);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("2D Line Chart Visualization of LOC",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
     }     
        public void LOC2D_WaterfallChart(){
                          DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                       //   dfg.setValue(loc,"LOC", "LOC");
                          dfg.setValue(bline,"LOC", "Blank lines");
                          dfg.setValue(cline,"LOC", "Commented lines");
                          dfg.setValue(pline,"LOC", "Physical lines");
                          dfg.setValue(lline,"LOC", "Logical Lines");
                          dfg.setValue(obrace,"LOC", "Open Braces");
                          dfg.setValue(cbrace,"LOC", "Closes Braces");
                         JFreeChart jfc= ChartFactory.createWaterfallChart("SCMV","Metrics","Total LOC", dfg, PlotOrientation.VERTICAL, true, true, true);
                          CategoryPlot plot=jfc.getCategoryPlot();
                          plot.setRangeGridlinePaint(Color.BLUE);
                          ChartFrame hy;
                      hy = new ChartFrame("2D Waterfall Chart Visualization of LOC",jfc);
                          hy.setVisible(true);
                           hy.setSize(900,500);

       }
        public void LOC2D_DualChart(){
                       final String series1 = "Series 1";
                       final String series2 = "Series 2";
              // column keys...
              final String category1 = "LOC";
              final String category2 = "Commented LOC";
              final String category3 = "Blank LOC";
              final String category4 = "Logical LOC";
              final String category5 = "Physical LOC";
              final String category6 = "Open Braces";
              final String category7 = "Close Braces";

                  DefaultCategoryDataset dfg1= new DefaultCategoryDataset();
                      //    dfg1.setValue(loc,series1, category1);
                          dfg1.setValue(cline,series1, category2);
                          dfg1.setValue(bline,series1, category3);
                          dfg1.setValue(lline,series1, category4);
                          dfg1.setValue(pline,series1, category5);
                          dfg1.setValue(obrace,series1, category6);
                          dfg1.setValue(cbrace,series1, category7);

                  DefaultCategoryDataset dfg2= new DefaultCategoryDataset();
                       //   dfg2.setValue(loc,series2, category1);
                          dfg2.setValue(cline,series2, category2);
                          dfg2.setValue(bline,series2, category3);
                          dfg2.setValue(lline,series2, category4);
                          dfg2.setValue(pline,series2, category5);
                          dfg2.setValue(obrace,series2, category6);
                          dfg2.setValue(cbrace,series2, category7);

                         JFreeChart jfc= ChartFactory.createBarChart("LOC 2D Dual Axis Chart","Metrics","Total LOC",dfg1, PlotOrientation.VERTICAL, true, true, true);
                         final CategoryPlot plot=jfc.getCategoryPlot();
                          plot.setRangeGridlinePaint(Color.BLUE);
                           plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                           plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
               plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);        
               plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

              final CategoryAxis domainAxis = plot.getDomainAxis();
              domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

              final CategoryItemRenderer renderer1 = plot.getRenderer();
              renderer1.setSeriesPaint(0, Color.red);
              renderer1.setSeriesPaint(1, Color.yellow);
              renderer1.setSeriesPaint(2, Color.green);  


              final ValueAxis axis2 = new NumberAxis3D("Secondary Dataset");
              plot.setRangeAxis(1, axis2);
              plot.setDataset(1, dfg2);
              plot.mapDatasetToRangeAxis(1, 1);
              final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
              renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
              plot.setRenderer(1, renderer2);
              plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);

                          plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                          ChartFrame hy;
                      hy = new ChartFrame("2D Dual Chart Visualization of LOC",jfc);
                          hy.setVisible(true);
                           hy.setSize(900,500);                        
       }
        public void LOC2D_AreaChart() {
                      DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                        //  dfg.setValue(loc,"LOC", "LOC");
                          dfg.setValue(bline,"LOC", "Blank lines");
                          dfg.setValue(cline,"LOC", "Commented lines");
                          dfg.setValue(pline,"LOC", "Physical lines");
                          dfg.setValue(lline,"LOC", "Logical Lines");
                          dfg.setValue(obrace,"LOC", "Open Braces");
                          dfg.setValue(cbrace,"LOC", "Closes Braces");
                         JFreeChart jfc= ChartFactory.createAreaChart("LOC 2D Area Chart", "LOC", "LOC", dfg, PlotOrientation.VERTICAL, true, true, true);
                          CategoryPlot plot=jfc.getCategoryPlot();
                          plot.setRangeGridlinePaint(Color.CYAN);
                          plot.setNoDataMessage("No data available");
                          ChartFrame hy;
                      hy = new ChartFrame("2D Area Chart Visualization of LOC",jfc);
                          hy.setVisible(true);
                           hy.setSize(900,500);                    
       }   
        public void LOC2D_StackAreaChart() {
                        DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                        //  dfg.setValue(loc,"LOC", "LOC");
                          dfg.setValue(bline,"LOC", "Blank lines");
                          dfg.setValue(cline,"LOC", "Commented lines");
                          dfg.setValue(pline,"LOC", "Physical lines");
                          dfg.setValue(lline,"LOC", "Logical Lines");
                          dfg.setValue(obrace,"LOC", "Open Braces");
                          dfg.setValue(cbrace,"LOC", "Closes Braces");
                         JFreeChart jfc= ChartFactory.createStackedAreaChart("LOC 2D Stacked Area Chart", "Metrics", "Total LOC", dfg, PlotOrientation.VERTICAL, true, true, true);
                          CategoryPlot plot=jfc.getCategoryPlot();
                          plot.setRangeGridlinePaint(Color.BLUE);
                           plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                          plot.setNoDataMessage("No data available");
                          ChartFrame hy;
                      hy = new ChartFrame("2D Stack Area Chart Visualization of LOC",jfc);
                          hy.setVisible(true);
                           hy.setSize(900,500);
       }        
        public void LOC2D_StackBarChart() {
                       DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                       //   dfg.setValue(loc,"LOC", "LOC");
                          dfg.setValue(bline,"LOC", "Blank lines");
                          dfg.setValue(cline,"LOC", "Commented lines");
                          dfg.setValue(pline,"LOC", "Physical lines");
                          dfg.setValue(lline,"LOC", "Logical Lines");
                          dfg.setValue(obrace,"LOC", "Open Braces");
                          dfg.setValue(cbrace,"LOC", "Closes Braces");
                         JFreeChart jfc= ChartFactory.createStackedBarChart("LOC 2D Stacked Bar Chart", "LOC", "LOC", dfg, PlotOrientation.VERTICAL, true, true, true);
                          CategoryPlot plot=jfc.getCategoryPlot();
                          plot.setRangeGridlinePaint(Color.CYAN);
                          plot.setNoDataMessage("No data available");
                          ChartFrame hy;
                      hy = new ChartFrame("2D Stack Bar Chart Visualization of LOC",jfc);
                          hy.setVisible(true);
                           hy.setSize(900,500);
       }           
        
                    // Data Types 2D Charts
        public void DT2D_PieChart(){
                  DefaultPieDataset dfgg= new DefaultPieDataset();
                    dfgg.setValue("INT",t_int);
                    dfgg.setValue("DOUBLE",t_double);
                    dfgg.setValue("FLOAT",t_float);
                    dfgg.setValue("LONG_INT",t_longint);
                    dfgg.setValue("SHORT_INT",t_shortint);
                    dfgg.setValue("BOOL",t_bool);
                    dfgg.setValue("STRING",t_string);
                    dfgg.setValue("CHAR",t_char);
                   JFreeChart jfc= ChartFactory.createPieChart("Data Types 2D Pie Char", dfgg, true, true, false);
                      PiePlot plot = (PiePlot)jfc.getPlot();
                      PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(t_int), 0.10);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("2D Pie Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void DT2D_BarChart(){      
                 DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_int,"INT", "INT");
                    dfg.setValue(t_double,"DOUBLE", "DOUBLE");
                    dfg.setValue(t_float,"FLOAT", "FLOAT");
                    dfg.setValue(t_longint,"LONG_INT", "LONG INT");
                    dfg.setValue(t_shortint,"SHORT INT", "SHORT INT");
                    dfg.setValue(t_bool,"BOOL", "BOOL");
                    dfg.setValue(t_string,"STRING", "STRING");
                    dfg.setValue(t_char,"CHAR", "CHAR");
              JFreeChart jfc= ChartFactory.createBarChart("Data Types 2D Bar Chart","Metrics","Max Values", dfg, PlotOrientation.VERTICAL, false, true, false);
              CategoryPlot plot=jfc.getCategoryPlot();
              plot.setRangeGridlinePaint(Color.CYAN);
              ChartFrame hy;
              hy = new ChartFrame("2D Bar Chart Visualization of Data Types",jfc);
              hy.setVisible(true);
              hy.setSize(900,500);
}
        public void DT2D_LineChart(){
           DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_int,"INT", "INT");
                    dfg.setValue(t_double,"DOUBLE", "DOUBLE");
                    dfg.setValue(t_float,"FLOAT", "FLOAT");
                    dfg.setValue(t_longint,"LONG_INT", "LONG INT");
                    dfg.setValue(t_shortint,"SHORT_INT", "SHORT INT");
                    dfg.setValue(t_bool,"BOOL", "BOOL");
                    dfg.setValue(t_string,"STRING", "STRING");
                    dfg.setValue(t_char,"CHAR", "CHAR");
                   JFreeChart jfc= ChartFactory.createLineChart("Data Types 2D Line Chart","Metrics","Max Values",dfg,PlotOrientation.VERTICAL,true, true, false);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("2D Line Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
          }
        public void DT2D_WaterfallChart(){                 
                    DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_int,"DATA_TYPE", "INT");
                    dfg.setValue(t_double,"DATA_TYPE", "DOUBLE");
                    dfg.setValue(t_float,"DATA_TYPE", "FLOAT");
                    dfg.setValue(t_longint,"DATA_TYPE", "LONG INT");
                    dfg.setValue(t_shortint,"DATA_TYPE", "SHORT INT");
                    dfg.setValue(t_bool,"DATA_TYPE", "BOOL");
                    dfg.setValue(t_string,"DATA_TYPE", "STRING");
                    dfg.setValue(t_char,"DATA_TYPE", "CHAR");
                   JFreeChart jfc= ChartFactory.createWaterfallChart("Data Types 2D Waterfall Chart","Metrics","Max  Values", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("2D Waterfall Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void DT2D_DualChart(){               
                // row keys...
            final String series1 = "Series 1";
            final String series2 = "Series 2";
            // column keys...
            final String category1 = "int";
            final String category2 = "double";
            final String category3 = "float";
            final String category4 = "long int";
            final String category5 = "short int";
            final String category6 = "bool";
            final String category7 = "string";
            final String category8 = "char";        
            DefaultCategoryDataset dfg1= new DefaultCategoryDataset();
                 dfg1.setValue(t_int,series1, category1);
                    dfg1.setValue(t_double,series1, category2);
                    dfg1.setValue(t_float,series1, category3);
                    dfg1.setValue(t_longint,series1, category4);
                    dfg1.setValue(t_shortint,series1, category5);
                    dfg1.setValue(t_bool,series1, category6);
                    dfg1.setValue(t_string,series1, category7);
                    dfg1.setValue(t_char,series1, category8);
           DefaultCategoryDataset dfg2= new DefaultCategoryDataset();
                    dfg2.setValue(t_int,series2, category1);
                    dfg2.setValue(t_double,series2, category2);
                    dfg2.setValue(t_float,series2, category3);
                    dfg2.setValue(t_longint,series2, category4);
                    dfg2.setValue(t_shortint,series2, category5);
                    dfg2.setValue(t_bool,series2, category6);
                    dfg2.setValue(t_string,series2, category7);
                     dfg1.setValue(t_char,series2, category8);
                   JFreeChart jfc= ChartFactory.createBarChart("Data Types 2D Dual Axis Chart","Metrics","Max Value",dfg1, PlotOrientation.VERTICAL, true, true, true);
                   final CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                     plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
         plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);        
         plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
         final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
      
        final CategoryItemRenderer renderer1 = plot.getRenderer();
          renderer1.setSeriesPaint(0, Color.red);
        renderer1.setSeriesPaint(1, Color.yellow);
        renderer1.setSeriesPaint(2, Color.green);  
     
       final ValueAxis axis2 = new NumberAxis3D("Secondary Dataset");
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dfg2);
        plot.mapDatasetToRangeAxis(1, 1);
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);       
                    plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                    ChartFrame hy;
                hy = new ChartFrame("2D Dual Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void DT2D_AreaChart() {
                 
                DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_int,"DATA_TYPE", "INT");
                    dfg.setValue(t_double,"DATA_TYPE", "DOUBLE");
                    dfg.setValue(t_float,"DATA_TYPE", "FLOAT");
                    dfg.setValue(t_longint,"DATA_TYPE", "LONG INT");
                    dfg.setValue(t_shortint,"DATA_TYPE", "SHORT INT");
                    dfg.setValue(t_bool,"DATA_TYPE", "BOOL");
                    dfg.setValue(t_string,"DATA_TYPE", "STRING");
                    dfg.setValue(t_char,"DATA_TYPE", "CHAR");

                    JFreeChart jfc= ChartFactory.createAreaChart("Data Types 2D Area Chart","Metrics","Max Values", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Area Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void DT2D_StackAreaChart() {
              DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_int,"INT", "INT");
                    dfg.setValue(t_double,"DOUBLE", "DOUBLE");
                    dfg.setValue(t_float,"FLOAT", "FLOAT");
                    dfg.setValue(t_longint,"LONG_INT", "LONG INT");
                    dfg.setValue(t_shortint,"SHORT_INT", "SHORT INT");
                    dfg.setValue(t_bool,"BOOL", "BOOL");
                    dfg.setValue(t_string,"STRING", "STRING");
                    dfg.setValue(t_char,"CHAR", "CHAR");
                    
                   JFreeChart jfc= ChartFactory.createStackedAreaChart("Data Types 2D Stacked Area Chart", "Metrics", "Max Values", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Stack Area Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500); 
 }
        public void DT2D_StackBarChart() {
               DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_int,"INT", "INT");
                    dfg.setValue(t_double,"DOUBLE", "DOUBLE");
                    dfg.setValue(t_float,"FLOAT", "FLOAT");
                    dfg.setValue(t_longint,"LONG_INT", "LONG INT");
                    dfg.setValue(t_shortint,"SHORT_INT", "SHORT INT");
                    dfg.setValue(t_bool,"BOOL", "BOOL");
                    dfg.setValue(t_string,"STRING", "STRING");
                    dfg.setValue(t_char,"CHAR", "CHAR");
                   JFreeChart jfc= ChartFactory.createStackedBarChart("Data Types 2D Stacked Bar Chart","Metrics","Max Values", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Stack Bar Chart Visualization of Data Types",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        
                    // Data Types 3D Charts
        public void DT3D_PieChart(){
                        DefaultPieDataset dfgg= new DefaultPieDataset();
                        dfgg.setValue("INT",t_int);
                        dfgg.setValue("DOUBLE",t_double);
                        dfgg.setValue("FLOAT",t_float);
                        dfgg.setValue("LONG_INT",t_longint);
                        dfgg.setValue("SHORT_INT",t_shortint);
                        dfgg.setValue("BOOL",t_bool);
                        dfgg.setValue("STRING",t_string);
                        dfgg.setValue("CHAR",t_char);
                       JFreeChart jfc= ChartFactory.createPieChart3D("Data Types 3D Pie Chart", dfgg, true, true, false);
                       PiePlot3D plot = (PiePlot3D)jfc.getPlot();
                       PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            plot.setLabelGenerator(gen);
            plot.setExplodePercent(String.valueOf(t_int), 0.10);
            plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.setNoDataMessage("No data available");
            plot.setCircular(false);
            plot.setLabelGap(0.02);
                        ChartFrame hy;
                    hy = new ChartFrame("3D Pie Chart Visualization of Data Types",jfc);
                        hy.setVisible(true);
                         hy.setSize(900,500);
     }
        public void DT3D_BarChart(){
                DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                        dfg.setValue(t_int,"INT", "INT");
                        dfg.setValue(t_double,"DOUBLE", "DOUBLE");
                        dfg.setValue(t_float,"FLOAT", "FLOAT");
                        dfg.setValue(t_longint,"LONG_INT", "LONG INT");
                        dfg.setValue(t_shortint,"SHORT_INT", "SHORT INT");
                        dfg.setValue(t_bool,"BOOL", "BOOL");
                        dfg.setValue(t_string,"STRING", "STRING");
                        dfg.setValue(t_char,"CHAR", "CHAR");
                       JFreeChart jfc= ChartFactory.createBarChart3D("Data Types 3D Bar Chart","Metrics","Max Values", dfg,PlotOrientation.VERTICAL, true, true,true);
                        CategoryPlot plot=jfc.getCategoryPlot();
                        plot.setRangeGridlinePaint(Color.CYAN);
                        ChartFrame hy;
                    hy = new ChartFrame("3D Bar Chart Visualization of Data Types",jfc);
                        hy.setVisible(true);
                         hy.setSize(900,500);
     }
        public void DT3D_LineChart(){       
                DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                        dfg.setValue(t_int,"DATA_TYPE", "INT");
                        dfg.setValue(t_double,"DATA_TYPE", "DOUBLE");
                        dfg.setValue(t_float,"DATA_TYPE", "FLOAT");
                        dfg.setValue(t_longint,"DATA_TYPE", "LONG INT");
                        dfg.setValue(t_shortint,"DATA_TYPE", "SHORT INT");
                        dfg.setValue(t_bool,"DATA_TYPE", "BOOL");
                        dfg.setValue(t_string,"DATA_TYPE", "STRING");
                        dfg.setValue(t_char,"DATA_TYPE", "CHAR");
                       JFreeChart jfc= ChartFactory.createLineChart3D("Data Types 3D Line Chart", "Metrics","Max Values",dfg,PlotOrientation.VERTICAL,true, true, false);
                        CategoryPlot plot=jfc.getCategoryPlot();
                        plot.setRangeGridlinePaint(Color.BLUE);
                         plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                        ChartFrame hy;
                    hy = new ChartFrame("3D Line Chart Visualization of Data Types",jfc);
                        hy.setVisible(true);
                         hy.setSize(900,500);
     }
        public void DT3D_StackBarChart() {            
                  DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                        dfg.setValue(t_int,"INT", "INT");
                        dfg.setValue(t_double,"DOUBLE", "DOUBLE");
                        dfg.setValue(t_float,"FLOAT", "FLOAT");
                        dfg.setValue(t_longint,"LONG_INT", "LONG_INT");
                        dfg.setValue(t_shortint,"SHORT_INT", "SHORT_INT");
                        dfg.setValue(t_bool,"BOOL", "BOOL");
                        dfg.setValue(t_string,"STRING", "STRING");
                        dfg.setValue(t_char,"CHAR", "CHAR");
                       JFreeChart jfc= ChartFactory.createStackedBarChart3D("Data Types 3D Stacked Bar Chart", "Metrics", "Max Values", dfg, PlotOrientation.VERTICAL, true, true, true);
                        CategoryPlot plot=jfc.getCategoryPlot();
                        plot.setRangeGridlinePaint(Color.BLUE);
                         plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                        plot.setNoDataMessage("No data available");
                        ChartFrame hy;
                    hy = new ChartFrame("3D Stack Bar Chart Visualization of Data Types",jfc);
                        hy.setVisible(true);
                         hy.setSize(900,500);
     }
        
                    // Loop Statements 2D Charts
        public void LOOP2D_BarChart() {
                  DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                    
                   JFreeChart jfc= ChartFactory.createBarChart("2D Bar Chart of Loops", "Loop Statements", "No. of Loops Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                     plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Bar Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);     
    }
        public void LOOP2D_LineChart() {
                     DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                    
                   JFreeChart jfc= ChartFactory.createLineChart("2D Line Chart of Loop Statements", "Loop Statements", "No. of Loops Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                     plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Line Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
    }
        public void LOOP2D_PieChart() {
                DefaultPieDataset dfg= new DefaultPieDataset();
                    dfg.setValue("for loop",t_for);
                    dfg.setValue("while loop",t_while);
                    dfg.setValue("do while loop",t_do_while);
                    
                   JFreeChart jfc= ChartFactory.createPieChart("2D Pie Chart of Loop Statements", dfg, true, true,false);
                    PiePlot plot = (PiePlot)jfc.getPlot();
                       PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(t_for), 0.10);
          plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("2D Pie Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
    }
        public void LOOP2D_WaterfallChart(){
                 DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                    
                   JFreeChart jfc= ChartFactory.createWaterfallChart("2D Waterfall Chart of Loops","Loop Statements","No. of Loops Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("2D Waterfall Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void LOOP2D_DualChart(){
            
            // row keys...
                final String series1 = "Series 1";
                final String series2 = "Series 2";
            // column keys...
                final String category1 = "for loop";
                final String category2 = "do while loop";
                final String category3 = "while loop";
       
            DefaultCategoryDataset dfg1= new DefaultCategoryDataset();
                 dfg1.setValue(t_for,series1, category1);
                    dfg1.setValue(t_do_while,series1, category2);
                    dfg1.setValue(t_while,series1, category3);
                  
           DefaultCategoryDataset dfg2= new DefaultCategoryDataset();
                    dfg2.setValue(t_for,series2, category1);
                    dfg2.setValue(t_do_while,series2, category2);
                    dfg2.setValue(t_while,series2, category3);
                    
                   JFreeChart jfc= ChartFactory.createBarChart("2D Dual Axis Chart of Loops","Loop Statements","No. of Loops Statements Used",dfg1, PlotOrientation.VERTICAL, true, true, true);
                   final CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                     plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
         plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);        
         plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
         
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
      
        final CategoryItemRenderer renderer1 = plot.getRenderer();
        renderer1.setSeriesPaint(0, Color.red);
        renderer1.setSeriesPaint(1, Color.yellow);
        renderer1.setSeriesPaint(2, Color.green);  
      
        final ValueAxis axis2 = new NumberAxis3D("Secondary Dataset");
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dfg2);
        plot.mapDatasetToRangeAxis(1, 1);
        
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                  
            ChartFrame hy;
                   hy = new ChartFrame("2D Dual Chart Visualization of Loop Statements",jfc);
                   hy.setVisible(true);
                   hy.setSize(900,500);
        }
        public void LOOP2D_AreaChart() {
              DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
               
                   JFreeChart jfc= ChartFactory.createAreaChart("2D Area Chart of Loops","Loop Statements","No. of Loops Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Area Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void LOOP2D_StackAreaChart() {
              DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                   JFreeChart jfc= ChartFactory.createStackedAreaChart("2D Stacked Area Chart of Loops","Loop Statements","No. of Loops Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Stack Area Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        public void loop2d_StackBarchart() {
               DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                    
                   JFreeChart jfc= ChartFactory.createStackedBarChart("2D Stacked Bar Chart of Loops","Loop Statements","No. of Loops Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("2D Stack Bar Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
         
                    // Loop Statements 3D Charts
        public void LOOP3D_BarChart() {
                DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                    
                   JFreeChart jfc= ChartFactory.createBarChart3D("3D Bar Chart of Loops", "Loop Statements", "No. of Loops Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                     plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("3D Bar Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
    }
        public void LOOP3D_LineChart() {
            DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                   JFreeChart jfc= ChartFactory.createLineChart3D("3D Line Chart of Loop Statements", "Metrics", "No. of Loops Statements Used", dfg,PlotOrientation.VERTICAL,true,true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                     plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("3D Line Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
    }
        public void LOOP3D_PieChart() {
                  DefaultPieDataset dfg= new DefaultPieDataset();
                    dfg.setValue("for loop",t_for);
                    dfg.setValue("while loop",t_while);
                    dfg.setValue("do while loop",t_do_while);
                    
                   JFreeChart jfc= ChartFactory.createPieChart3D("3D Pie Chart of Loop Statements", dfg, true, true,false);
                    PiePlot3D plot = (PiePlot3D)jfc.getPlot();
                       PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(t_for), 0.10);
          plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("3D Pie Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
    }
        public void LOOP3D_StackBarChart(){
              DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_for,"loop", "for");
                    dfg.setValue(t_while,"loop", "while");
                    dfg.setValue(t_do_while,"loop", "do while");
                    
                   JFreeChart jfc= ChartFactory.createStackedBarChart3D("3D Stacked Bar Chart of Loops","Loop Statements","No. of Loops Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                     plot.setNoDataMessage("No data available");
                    ChartFrame hy;
                hy = new ChartFrame("3D Stack Bar Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
    }
        
                    // Conditional Statements 2D Charts
        public void CST2D_BarChart() {
                  DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally");
                   JFreeChart jfc= ChartFactory.createBarChart("2D Bar Chart of Conditional Statements", "Conditonal Statements", "No. of Conditional Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("2D Bar Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
                     
             }
        public void CST2D_LineChart() {
               DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally");
                 JFreeChart jfc= ChartFactory.createLineChart("2D Line Chart of Conditional Statements","Conditional Statements", "No. of Conditional Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("2D Line Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);     
 }  
        public void CST2D_PieChart() {
                  DefaultPieDataset dfg= new DefaultPieDataset();
                    dfg.setValue("if",t_if);
                    dfg.setValue("else if",t_elseif);
                    dfg.setValue("else",t_else);
                    dfg.setValue("switch",t_switch);
                    dfg.setValue("case",t_case);
                    dfg.setValue("try",t_try);
                    dfg.setValue("catch",t_catch);
                    dfg.setValue("finally",t_finally);
                 JFreeChart jfc= ChartFactory.createPieChart("2D Pie Chart of Conditional Statements", dfg, true, true, false);
                   PiePlot plot = (PiePlot)jfc.getPlot();
                       PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(t_if), 0.10);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("2D Pie Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);     
 }
        public void CST2D_AreaChart() {
                DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally");                  
                   JFreeChart jfc= ChartFactory.createAreaChart("2D Area Chart of Conditional Statement","Conditional Statements", "NO. of Conditionaal Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("2D Area Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
}
        public void CST2D_DualChart(){
                
        // row keys...
            final String series1 = "Series 1";
            final String series2 = "Series 2";
        // column keys...
            final String category1 = "if";
            final String category2 = "else if";
            final String category3 = "else";
            final String category4 = "switch";
            final String category5 = "case";
            final String category6 = "try";
            final String category7 = "catch";
            final String category8 = "finally";
      
            DefaultCategoryDataset dfg1= new DefaultCategoryDataset();
                 dfg1.setValue(t_if,series1, category1);
                    dfg1.setValue(t_elseif,series1, category2);
                    dfg1.setValue(t_else,series1, category3);
                    dfg1.setValue(t_switch,series1, category4);
                    dfg1.setValue(t_case,series1, category5);
                    dfg1.setValue(t_try,series1, category6);
                    dfg1.setValue(t_catch,series1, category7);
                    dfg1.setValue(t_finally,series1, category8);
                
           DefaultCategoryDataset dfg2= new DefaultCategoryDataset();
                    dfg2.setValue(t_if,series2, category1);
                    dfg2.setValue(t_elseif,series2, category2);
                    dfg2.setValue(t_else,series2, category3);
                    dfg2.setValue(t_switch,series2, category4);
                    dfg2.setValue(t_case,series2, category5);
                    dfg2.setValue(t_try,series2, category6);
                    dfg2.setValue(t_catch,series2, category7);
                    dfg2.setValue(t_finally,series2, category8);
                    
                   JFreeChart jfc= ChartFactory.createBarChart("2D Dual Axis Chart of Conditional Statement", "Conditional Statements", "NO. of Conditionaal Statements Used", dfg1, PlotOrientation.VERTICAL, true, true, true);
                   final CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                     plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
            plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);        
            plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
            
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
      
        final CategoryItemRenderer renderer1 = plot.getRenderer();
        renderer1.setSeriesPaint(0, Color.red);
        renderer1.setSeriesPaint(1, Color.yellow);
        renderer1.setSeriesPaint(2, Color.green);     
      
       final ValueAxis axis2 = new NumberAxis3D("Secondary Dataset");
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dfg2);
        plot.mapDatasetToRangeAxis(1, 1);
         
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                   
                 ChartFrame hy;
                    hy = new ChartFrame("3D Dual Chart Visualization of Loop Statements",jfc);
                    hy.setVisible(true);
                    hy.setSize(900,500);
 }
        public void CST2D_StackAreaChart() {
               DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally");   
                   JFreeChart jfc= ChartFactory.createStackedAreaChart("2D Stack Area Chart of Conditional Statement","Conditional Statements", "NO. of Conditionaal Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("2D Stack Area Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
}
        public void CST2D_StackBarChart() {
               DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally"); 
                   JFreeChart jfc= ChartFactory.createStackedBarChart("2D Stacked Bar Chart of Conditional Statement","Conditional Statements", "NO. of Conditionaal Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("2D Stack Bar Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
}
        public void CST2D_WaterfallChart(){
              DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally"); 
                   JFreeChart jfc= ChartFactory.createWaterfallChart("2D Waterfall Chart of Conditional Statement", "Conditional Statements", "No. of Conditional Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                   plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("Visualizar",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }
        
                     // Conditional Statements 3D Charts
        public void CST3D_BarChart() {
            DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_catch,"Conditional", "finally");
                 JFreeChart jfc= ChartFactory.createBarChart3D("3D Bar Chart of Conditional Statement", "Conditional Statements", "No. of Conditional Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.CYAN);
                    ChartFrame hy;
                hy = new ChartFrame("3D Bar Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
                    }   
        public void CST3D_LineChart() {
                 DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_catch,"Conditional", "finally");
                 
                 JFreeChart jfc= ChartFactory.createLineChart3D("3D Line Chart of Conditional Statements", "Conditional Statements", "No. of Conditional Statements Used", dfg, PlotOrientation.VERTICAL, true, true, true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("3D Line Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);     
 } 
        public void CST3D_PieChart() {
                 DefaultPieDataset dfg= new DefaultPieDataset();
                    dfg.setValue("if",t_if);
                    dfg.setValue("else if",t_elseif);
                    dfg.setValue("else",t_else);
                    dfg.setValue("switch",t_switch);
                    dfg.setValue("case",t_case);
                    dfg.setValue("try",t_try);
                    dfg.setValue("catch",t_catch);
                    dfg.setValue("finally",t_finally);
                 JFreeChart jfc= ChartFactory.createPieChart3D("3D Pie Chart of Conditional Statements", dfg, true, true, false);
                   PiePlot3D plot = (PiePlot3D)jfc.getPlot();
                       PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setExplodePercent(String.valueOf(t_if), 0.10);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
                    ChartFrame hy;
                hy = new ChartFrame("3D Pie Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
 }  
        public void CST3D_StackBarChart() {
               DefaultCategoryDataset dfg= new DefaultCategoryDataset();
                    dfg.setValue(t_if,"Conditional", "if");
                    dfg.setValue(t_elseif,"Conditional", "else if");
                    dfg.setValue(t_else,"Conditional", "else");
                    dfg.setValue(t_switch,"Conditional", "switch");
                    dfg.setValue(t_case,"Conditional", "case");
                    dfg.setValue(t_try,"Conditional", "try");
                    dfg.setValue(t_catch,"Conditional", "catch");
                    dfg.setValue(t_finally,"Conditional", "finally"); 
                   JFreeChart jfc= ChartFactory.createStackedBarChart3D("3D Stacked Bar Chart of Conditional Statement","Conditional Statements", "NO. of Conditionaal Statements Used", dfg,PlotOrientation.VERTICAL, true, true,true);
                    CategoryPlot plot=jfc.getCategoryPlot();
                    plot.setRangeGridlinePaint(Color.BLUE);
                     plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
                    ChartFrame hy;
                hy = new ChartFrame("3D Stack Bar Chart Visualization of Conditional Statements",jfc);
                    hy.setVisible(true);
                     hy.setSize(900,500);
}
}