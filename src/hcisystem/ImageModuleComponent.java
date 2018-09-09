/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import hcisystem.image_module.EmotionProcessing;
import hcisystem.image_module.PerformEmotions;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pra
 */
public class ImageModuleComponent {
    
    private final ServerComponent serverComp;
    
    private BufferedImage image = null;
    
   
    
    private static final String OUTSIDE_IMAGE_MODE = "$$get_outside_image_mode_$$";
    private static final String PREPARE_TO_RECEIVE_IMAGE = "$$alpha_to_bravo_image_is_comming$$";
    
    
    public ImageModuleComponent(ServerComponent serverComp) {
    
        this.serverComp = serverComp;
        
    }
    
    public void startImageProcessing() {
    
        String data;
        System.out.println("inside startImageProcessing");
        try {
            while((data = ServerComponent.br.readLine()) != null) {
                
                if(data.equals(OUTSIDE_IMAGE_MODE)) {
                
                    System.out.println("getting outside imageComponent");
                    break;
                }
                else if(data.equals(PREPARE_TO_RECEIVE_IMAGE)) {
                
                    System.out.println("The emotion detected is : " + data);
                    
                    /*
                    try {
                    
                    
                        System.out.println("receiving an image");
                        System.out.println("creating byte array for image storage.");
                        //receive inage in a byte array
                        ObjectInputStream ois = new ObjectInputStream(ServerComponent.socket.getInputStream());
                        byte[] bytes;
                        bytes = (byte[]) ois.readObject();
                        //ois.close();
                        System.out.println("image stored in byte array.");

                        //create a new image file
                        System.out.println("Creating file path");
                        String fullQualifiedName = HCISystem.IMAGEFOLDERPATH + "\\" + createImageName();
                        System.out.println(fullQualifiedName);

                        //saving imside file.
                        System.out.println("Saving the image inside file");
                        File f = new File(fullQualifiedName);
                        FileOutputStream fos = null;
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        //fos.close();
                        System.out.println("Image stored in the file.");

                        System.out.println("image received.. ");
                        System.out.println("successfully saved in " + fullQualifiedName);
                        
                    }
                    catch (Exception e) {
                    
                        System.out.println(e.getMessage());
                    }
*/
                }
                else {
                
                    System.out.println("The emotion detected is : " + data);
                    EmotionProcessing ep = new EmotionProcessing(data);
                    
                    
                    

//System.out.println("Command not recognised");
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(VoiceModuleComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }   
    }
    
    private static String createImageName() {
    
        DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
        Date dateobj = new Date();  
        
        return "JPEG_" + df.format(dateobj) + ".jpg";
        //System.out.println(imageName);
    }
    
}
