/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.gesture_module;

import hcisystem.HCISystem;
import hcisystem.gesture_module.FeatureCal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pra
 */



public class TruncFolder {

    private static String gestureName;
    public static void setCurrentName(String val) {
    
        gestureName = val;
        System.out.println("The current gesture name is set to :" + gestureName);
    }

    public TruncFolder(String folderName) {
    
        
        double res[][] = new double[6][6];
        int resind = 0;
        
        List<Double> x = new ArrayList();
        List<Double> y = new ArrayList();
        List<Double> z = new ArrayList();
        
        List<Double> xT = new ArrayList();
        List<Double> yT = new ArrayList();
        List<Double> zT = new ArrayList();
        
        File currentF = new File(folderName);
        String[] fileArr = currentF.list();
        
        
        //no need for output folder
        /*
        File outputF = new File(folderName + "-trunc");
        
        if(outputF.mkdir()) {
        
            System.out.println("OUTPUT DIRECTORY CREATED");
        }
        else {
        
            System.out.println("FAILED TO CREATE OUTPUT DIRECTORY");
        }
        */
        for(String eachFile : fileArr) {
        
            FileReader currFile = null;
            try {
                
                String theF = currentF.getPath() + "\\" + eachFile;
                System.out.println(theF);
                currFile = new FileReader(theF);
                BufferedReader br = new BufferedReader(currFile);
                String aline;

                aline = br.readLine();

                while(aline != null) {
                    
                    
                    StringTokenizer st = new StringTokenizer(aline);
                    
                    x.add(Double.parseDouble(st.nextToken()));
                    y.add(Double.parseDouble(st.nextToken()));
                    z.add(Double.parseDouble(st.nextToken()));
                    aline = br.readLine();
                }
                
                String outF = new File(HCISystem.GESTUREFOLDERPATH).getPath() + "\\" + eachFile + ".trnc";
                FileWriter fw = new FileWriter(outF);
                BufferedWriter bw = new BufferedWriter(fw);
                
                
                
                
                System.out.println("sizes " + x.size() + " " + y.size() + " " + z.size());
                
                FeatureCal fc = new FeatureCal();
                double tx = fc.getTruncationThreshold(x);
                double intX = x.get(0);
                double endX = x.get(x.size()-1);
                
                int i=0;
                while(Math.abs(x.get(i)-intX) < tx)
                    i++;
                
                int j = x.size() - 1;
                while(Math.abs(x.get(j)-endX) < tx)
                    j--;
                
                for(int k=i;k<=j;k++) {
                
                    bw.write(x.get(k) + "\n");
                    xT.add(x.get(k));
                    
                }
                bw.write("end\n");
                
                double ty = fc.getTruncationThreshold(y);
                double intY = y.get(0);
                double endY = y.get(y.size()-1);
                i=0;
                while(Math.abs(y.get(i)-intY) < ty)
                    i++;
                
                j = y.size() - 1;
                while(Math.abs(y.get(j)-endY) < ty)
                    j--;
                
                for(int k=i;k<=j;k++) {
                
                    bw.write(y.get(k) + "\n");
                    yT.add(y.get(k));
                    
                }
                bw.write("end\n");
                
                
                double tz = fc.getTruncationThreshold(z);
                double endZ = z.get(z.size()-1);
                double intZ = z.get(0);
                i=0;
                while(Math.abs(z.get(i)-intZ) < tz)
                    i++;
                
                j = z.size() - 1;
                while(Math.abs(z.get(j)-endZ) < tz)
                    j--;
                
                for(int k=i;k<=j;k++) {
                
                    bw.write(z.get(k) + "\n");
                    zT.add(z.get(k));
                }
                bw.write("end\n");
                
                bw.write(gestureName);
                
                bw.close();
                fw.close();
                
                
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
            
                System.out.println(e.getMessage());
            } 
            finally {
                try {
                    currFile.close();
                } catch (IOException ex) {
                    //Logger.getLogger(PlotFolder.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (Exception e) {
                    
                    System.out.println(e.getMessage());
                }
            }
            
            //calculating features for truncated values.
            
            FeatureCal fc = new FeatureCal();
            res[resind][0] = fc.getMean(xT);
            res[resind][1] = fc.getMean(yT);
            res[resind][2] = fc.getMean(zT);
            res[resind][3] = fc.getStandardDeviation(xT,res[resind][0]);
            res[resind][4] = fc.getStandardDeviation(yT,res[resind][1]);
            res[resind][5] = fc.getStandardDeviation(zT,res[resind][2]);
            
            resind++;
            
            
            
            xT.clear();
            yT.clear();
            zT.clear();
            x.clear();
            y.clear();
            z.clear();
        }
        
        
        /*
        for(int i=0;i<6;i++) {
        
            double summ = 0;
            for(int j=0;j<5;j++) {
            
                System.out.println(res[j][i] + " ");
                summ += res[j][i];
            }
            
            res[5][i] = summ/5;
            System.out.println("\n" + res[5][i] + "\n");
        }
        */
        try {
            FileWriter fw = new FileWriter(HCISystem.GESTUREFOLDERPATH + "\\feature_list.feat", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i=0;i<5;i++) {
                
                for(int j=0;j<6;j++) {
                
                    bw.write(res[i][j] + " ");
                }
                bw.write(gestureName + "\n");
                
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(TruncFolder.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e) {
                    
            System.out.println(e.getMessage());
        }
        
    }
//    
//    public static void main(String args[]) {
//    
//        new TruncFolder("C:\\HCISystem\\TRAIN_DATA_20170219_011824");
//    }
}
