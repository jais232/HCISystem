/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.gesture_module;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pra
 */
public class FeatureCal {
    
    private static final Double THRESHOLD = 0.3d;
    
    public double getTruncationThreshold(List<Double> vals) {
    
        double sdV;
        sdV = this.getStandardDeviation(vals, this.getMean(vals));
        double threshold;
        threshold = THRESHOLD * Math.sqrt(sdV);
        return threshold;
    }
    
    public double getStandardDeviation(List<Double> vals, double meanV) {
    
        double summ = 0;
        if(vals.isEmpty())
            return 0;
        
        for(int i=0;i<vals.size();i++) {
        
            double d = vals.get(i) - meanV;
            d *= d;
            summ += d;
        }
        
        summ /= vals.size();
        double sd = Math.sqrt(summ);
        return sd;
    }
    
    public double getMean(List<Double> vals) {
    
        double sum = 0;
        if(vals.isEmpty())
            return 0;
        for(int i=0;i<vals.size();i++) {
        
            sum += vals.get(i);
        }
        return sum/vals.size();
    }
    
    
    
    
}
