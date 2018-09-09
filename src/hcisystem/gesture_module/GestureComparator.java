/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.gesture_module;

import hcisystem.HCISystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pra
 */
public class GestureComparator {
    
    float dx[];
    float dy[];
    float dz[];
    
    public void getCompares(List<Double> x, List<Double> y, List<Double> z) {
    
        int l = x.size();
        dx = new float[l];
        for(int i=0;i<l;i++) {
        
            dx[i] = x.get(i).floatValue();
        }
        
        l = y.size();
        dy = new float[l];
        for(int i=0;i<l;i++) {
        
            dy[i] = y.get(i).floatValue();
        }
        
        l = z.size();
        dz = new float[l];
        for(int i=0;i<l;i++) {
        
            dz[i] = z.get(i).floatValue();
        }
        try {
            startComparison();
        }
        catch(Exception e) {
        
            System.out.println("ERROE IN STARTCOMPARISN" + e.getMessage());
        }
        
    }
    
    public void startComparison() {
    
        File ges = new File(HCISystem.GESTUREFOLDERPATH);
        String fileArr[] = ges.list();
        
        
        String minWarpDistString = null;
        float minDTW = 1000;
        
        for(String eachFile : fileArr) {
        
            float tX[];
            float tY[];
            float tZ[];
            
            
            if(eachFile.charAt(0) == 'S') {
                
                FileReader redF = null;
                try {
                    
                    String fileName = ges.getPath() + "\\" + eachFile;
                    redF = new FileReader(fileName);
                    BufferedReader br = new BufferedReader(redF);
                    
                    String aline;
                    int l;

                    aline = br.readLine();
                    
                    
                    List<Float> tmp = new ArrayList();
                    while(!aline.equals("end")) {
                    
                        tmp.add(Float.parseFloat(aline));
                        aline = br.readLine();
                        
                    }
                    l = tmp.size();
                    tX = new float[l];
                    for(int i=0;i<l;i++) {
                    
                        tX[i] = tmp.get(i);
                    }
                    tmp.clear();
                    
                    
                    aline = br.readLine();

                    while(!aline.equals("end")) {
                    
                        tmp.add(Float.parseFloat(aline));
                        aline = br.readLine();
                        
                    }
                    l = tmp.size();
                    tY = new float[l];
                    for(int i=0;i<l;i++) {
                    
                        tY[i] = tmp.get(i);
                    }
                    tmp.clear();
                    
                    aline = br.readLine();

                    while(!aline.equals("end")) {
                    
                        tmp.add(Float.parseFloat(aline));
                        aline = br.readLine();
                        
                    }
                    l = tmp.size();
                    tZ = new float[l];
                    for(int i=0;i<l;i++) {
                    
                        tZ[i] = tmp.get(i);
                    }
                    
                    aline = br.readLine();
                    //System.out.println("This is the out put :" + aline);
                    float ans = 0;
                    try {
                        
                        DTW dtw = new DTW(dx, tX);
                        ans += dtw.getDistance();
                        DTW dtw1 = new DTW(dy, tY);
                        ans += dtw1.getDistance();
                        DTW dtw2 = new DTW(dz, tZ);
                        ans += dtw2.getDistance();
                    }
                    catch(Exception e) {
                    
                        System.out.println("ERROR IN DTW you shit: " + e.getMessage());
                    }

                    if(ans < minDTW && ans < 10) {
                    
                        minDTW = ans;
                        minWarpDistString = aline;
                        if(minDTW < 2)
                            break;
                    }
                    
                    
                    
                    
                    
                    
                } catch (Exception ex) {
                    
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        redF.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GestureComparator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (Exception e) {
                    
                        System.out.println(e.getMessage());
                    }
                }
                
                
                
                
            }
        }
        
        System.out.println("THE FINAL OUTPUT IS : " + minWarpDistString);
        if(minWarpDistString != null) {
        
            try {
                //finding the corresponding action.
                FileReader fr = new FileReader(HCISystem.GESTUREFOLDERPATH + "//name_comm.act");
                BufferedReader br = new BufferedReader(fr);
                
                String aline = "";
                
                while(aline != null) {
                
                    aline = br.readLine();
                    if(aline.equals(minWarpDistString)) {
                    
                        KeyboardInterfaceClass kic = new KeyboardInterfaceClass();
                        kic.executeKeyboardCommand(br.readLine());
                        break;
                    }
                    br.readLine();
                        
                    
                }
                
                
                
            } catch (Exception ex) {
                Logger.getLogger(GestureComparator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        
    }
    
}
