 /*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.gesture_module;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs DYNAMIC THRESHOLD TRUNCATION
 * @author pra
 */
public class GenerateFeature {
    
    //truncated signals
    List<Double> xT = new ArrayList();
    List<Double> yT = new ArrayList();
    List<Double> zT = new ArrayList();
    
    public void testDataProcess(List<Double> x, List<Double> y, List<Double> z) {
    
        if(!x.isEmpty()) {
            
            //x
            FeatureCal fc = new FeatureCal();
            double tx = fc.getTruncationThreshold(x);
            double intX = x.get(0);
            double endX = x.get(x.size()-1);

            int i=0;
            while(Math.abs(x.get(i)-intX) < tx && i < x.size())
                i++;

            int j = x.size() - 1;
            while(Math.abs(x.get(j)-endX) < tx && j >= 0)
                j--;

            for(int k=i;k<=j;k++) {

                xT.add(x.get(k));

            }

            //y
            double ty = fc.getTruncationThreshold(y);
            double intY = y.get(0);
            double endY = y.get(y.size()-1);

            i=0;
            while(Math.abs(y.get(i)-intY) < ty && i < y.size())
                i++;

            j = y.size() - 1;
            while(Math.abs(y.get(j)-endY) < ty && j >= 0)
                j--;

            for(int k=i;k<=j;k++) {

                yT.add(y.get(k));

            }

            
            //z
            double tz = fc.getTruncationThreshold(z);
            double intZ = z.get(0);
            double endZ = z.get(x.size()-1);

            i=0;
            while(Math.abs(z.get(i)-intZ) < tz && i < z.size())
                i++;

            j = z.size() - 1;
            while(Math.abs(z.get(j)-endZ) < tz && j >= 0)
                j--;

            for(int k=i;k<=j;k++) {

                zT.add(z.get(k));

            }

            //compare
            GestureComparator gc = new GestureComparator();
            gc.getCompares(xT, yT, zT);
            
            xT.clear();
            yT.clear();
            zT.clear();
        }
        else {
        
            System.out.println("The signal came has zero values.");
        }
        
    }
    
}
