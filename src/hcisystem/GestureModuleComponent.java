/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import hcisystem.gesture_module.TruncFolder;
import hcisystem.gesture_module.GenerateFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 *
 * @author pra
 */
public class GestureModuleComponent {
 
    private final ServerComponent serverComp;
    
    private static final String OUTSIDE_GESTURE_MODE = "$$get_outside_gesture_mode_$$";
    private static final String SENSOR_READING_FINISHED = "$$sensor_readings_over$$";
    private static final String TRAINING_DATA_COMING = "$$get_ready_for_training$$";
    private static final String TRAINING_DATA_OVER = "$$training_readings_over$$";
    private static final String DISCARD_TRAINING_DATA = "$$discard_training_data$$";
    
    private static boolean isTraining = false;
    private static String newFolder;
    private static File createFolder;
    
    public GestureModuleComponent(ServerComponent serverComp) {
    
        this.serverComp = serverComp;
        
        
    }
    
    public void getSensorReadings() {
    
        String data;
        System.out.println("inside getSensorReadings");
        System.out.println("Receiving sensor data.");
        
        List<String> sensorData = new ArrayList<>(); 
        try {
            while((data = ServerComponent.br.readLine()) != null) {
                
                System.out.println("-> " + data);
                if(data.equals(OUTSIDE_GESTURE_MODE)) {
                
                    System.out.println("getting outside GestureComponent");
                    
                    break;
                }
                else if(data.equals(SENSOR_READING_FINISHED)) {
                    
                    String fileName;
                    if(isTraining) {
                    
                        fileName = newFolder + "\\" + createFileName();
                        FileWriter fw = new FileWriter(fileName);
                        BufferedWriter bw = new BufferedWriter(fw);
                     
                        if(!sensorData.isEmpty())
                            for(int i=0;i<sensorData.size();i++) {

                                bw.write(sensorData.get(i) + "\n");
                            }
                        bw.close();
                        fw.close();
                    }
                    else {
                    
                        List<Double> valX = new ArrayList<>();
                        List<Double> valY = new ArrayList<>();
                        List<Double> valZ = new ArrayList<>();

                        
//                        fileName = createFileName();
//                        FileWriter fw = new FileWriter(fileName);
//                        BufferedWriter bw = new BufferedWriter(fw);
                        for(int i=0;i<sensorData.size();i++) {

//                            bw.write(sensorData.get(i) + "\n");
                            StringTokenizer st = new StringTokenizer(sensorData.get(i));
                            valX.add(Double.parseDouble(st.nextToken()));
                            valY.add(Double.parseDouble(st.nextToken()));
                            valZ.add(Double.parseDouble(st.nextToken()));


                        }
//                        bw.close();
//                        fw.close();
                        
                        //process the test data that has come;
                        //try to check for errors
                        try {
                            
                            GenerateFeature gf = new GenerateFeature();
                            gf.testDataProcess(valX, valY, valZ);
                        
                        } catch(Exception e) {
                        
                            System.out.println(e.getMessage());
                        }
                        
                    }
                    
                    sensorData.clear();
                    
                    continue;
                }
                else if(data.equals(TRAINING_DATA_COMING)) {
                
                    isTraining = true;
                    
                    newFolder = "C:\\HCISystem\\" + createFolderName();
                    
                    createFolder = new File(newFolder);
                    
                    if(createFolder.mkdir()) {
                    
                        System.out.println("NEW TRAING DIRECTORY IS CREATED.");
                    }
                    else {
                    
                        System.out.println("FAILED TO CREATE NEW DIRECTORY.\nNOT READY FOR TRAINING DATA.");
                        
                    }
                    
                    System.out.println(TRAINING_DATA_COMING);
                    continue;
                }
                else if(data.equals(TRAINING_DATA_OVER)) {
                
                    isTraining = false;
                    System.out.println(TRAINING_DATA_OVER);
                    
                    FileWriter fw = new FileWriter(HCISystem.GESTUREFOLDERPATH + "//name_comm.act", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String intermediateReceiver;
                    //name
                    intermediateReceiver = ServerComponent.br.readLine();
                    TruncFolder.setCurrentName(intermediateReceiver);
                    bw.write(intermediateReceiver + "\n");
                    //action
                    intermediateReceiver = ServerComponent.br.readLine();
                    bw.write(intermediateReceiver + "\n");
                    bw.close();
                    fw.close();
                    //PlotFolder pf = new PlotFolder(newFolder);
                    
                    //just to check for errors.
                    try {
                        TruncFolder tf = new TruncFolder(newFolder);
                    } catch(Exception e) {
                    
                        System.out.println(e.getMessage());
                    }
                    
                    continue;
                }
                else if(data.equals(DISCARD_TRAINING_DATA)) {
                    
                    isTraining = false;
                    String[] allFiles = createFolder.list();
                    for(String eachFile : allFiles) {
                    
                        File currentFile = new File(createFolder.getPath(), eachFile);
                        currentFile.delete();
                    }
                    
                    if(createFolder.delete()) {
                    
                        System.out.println("Folder deleted");
                    }
                    else {
                    
                        System.out.println("Folder not deleted.");
                    }
                    System.out.println(DISCARD_TRAINING_DATA);
                    
                }
                
                sensorData.add(data);
                //System.out.println(data);
                //ServerComponent.sensorReading.setText(data);
                
                
            }
        } catch(IOException | NumberFormatException e) {
        
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
    private static String createFileName() {
    
        DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
        Date dateobj = new Date();  
        
        return "SENS_" + df.format(dateobj) + ".dat";
        
    }
    
    private static String createFolderName() {
    
        DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
        Date dateobj = new Date();  
        
        return "TRAIN_DATA_" + df.format(dateobj);
        
    }
}
