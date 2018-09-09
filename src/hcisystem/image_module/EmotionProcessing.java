/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.image_module;

import hcisystem.Keyboard;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author omkarj
 */
public class EmotionProcessing {
    
   public EmotionProcessing(String e) {
        //public static void main(String args[]){
        String emotion = e;
       //String emotion = "surprise";
       emotion = "Happy";
        
            try {
                //Process p;
                Runtime q = Runtime.getRuntime();
                Keyboard keyboard = new Keyboard();
                JFrame frame=new JFrame();
                Robot robot = new Robot();
            switch(emotion) {
            
                case "Happy":
                    //p = new ProcessBuilder("mspaint","happy.jpg").start();

                    q.exec("notepad.exe");
                    keyboard.type("Hey u seem to be happy today...let's play a game");
                    q.exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Owner\\Desktop\\omkr\\C-Programming-Ebook.pdf");
                    break;
                    
                    
                    
                case "Sad":
                    q.exec("notepad.exe");
                    keyboard.type("Hey u look a bit sad...let's read a book");
                    q.exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Owner\\Desktop\\omkr\\C-Programming-Ebook.pdf");
                    break;
                
                    
                    
                case "Angry":
                    
                    q.exec("start www.rainymood.com");
                    break;
                    
                    
                    
                case "Surprised":
                    
                    int xSet = 350;
                    int ySet = 350;
                    int x = 0;
                    int y = 0;
                    double alpha = 0;
                 
                    q.exec("mspaint.exe");
                    robot.delay(2000);
                    
                    robot.mouseMove(xSet,ySet);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
 
                for(int i = 0; i < 360; i++){
        
                    robot.mouseMove(x+xSet,y+ySet);
                    y = (int)Math.round(100*Math.sin(alpha));
                    x++;
                    alpha += 2*Math.PI/360*1;
                    robot.delay(15);
                }
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    break;    
        
                
                    
                case "Scared":
                    BufferedImage img = ImageIO.read(new File("C:\\Users\\Owner\\Desktop\\fear.jpg"));
                    ImageIcon icon = new ImageIcon(img);
                    JLabel label = new JLabel(icon);
                    frame.add(label);  
                    frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
                    frame.setVisible(true);
                    break;
                    
                case "contempt":
                    
                    BufferedImage img1 = ImageIO.read(new File("C:\\Users\\Owner\\Desktop\\.jpg"));
                    ImageIcon icon1 = new ImageIcon(img1);
                    JLabel label1 = new JLabel(icon1);
                    frame.add(label1);  
                    frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
                    frame.setVisible(true);
                    break;
                    
                case "disgust":
                    BufferedImage img2 = ImageIO.read(new File("C:\\Users\\Owner\\Desktop\\.jpg"));
                    ImageIcon icon2 = new ImageIcon(img2);
                    JLabel label2 = new JLabel(icon2);
                    frame.add(label2);  
                    frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
                    frame.setVisible(true);
                    break;
                    
                case "Neutral":
                    BufferedImage img3 = ImageIO.read(new File("C:\\Users\\Owner\\Desktop\\.jpg"));
                    ImageIcon icon3 = new ImageIcon(img3);
                    JLabel label3 = new JLabel(icon3);
                    frame.add(label3);  
                    frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
                    frame.setVisible(true);
                    break;
                    
                    
                default:
                    BufferedImage img4 = ImageIO.read(new File("C:\\Users\\Owner\\Desktop\\.jpg"));
                    ImageIcon icon4 = new ImageIcon(img4);
                    JLabel label4 = new JLabel(icon4);
                    frame.add(label4);  
                    frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
                    frame.setVisible(true);
                    break;
            }
            
            
        }   catch (Exception ex) {
                System.out.println("Some error in emotion processing code");
            }
        }
        
        
        
}
    

