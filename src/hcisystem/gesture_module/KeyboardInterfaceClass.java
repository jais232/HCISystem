/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.gesture_module;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pra
 */
public class KeyboardInterfaceClass {
 
    public void executeKeyboardCommand(String command) {
    
        try {
            Robot robot = new Robot();
            
            switch(command) {
            
                case "A":
                    robot.keyPress(KeyEvent.VK_A);
                    break;
                case "B":
                    robot.keyPress(KeyEvent.VK_B);
                    break;
                case "C":
                    robot.keyPress(KeyEvent.VK_C);
                    break;
                case "D":
                    robot.keyPress(KeyEvent.VK_D);
                    break;
                case "E":
                    robot.keyPress(KeyEvent.VK_E);
                    break;
                case "F":
                    robot.keyPress(KeyEvent.VK_F);
                    break;
                case "G":
                    robot.keyPress(KeyEvent.VK_G);
                    break;
                case "H":
                    robot.keyPress(KeyEvent.VK_I);
                    break;
                case "J":
                    robot.keyPress(KeyEvent.VK_K);
                    break;
                case "L":
                    robot.keyPress(KeyEvent.VK_L);
                    break;
                case "M":
                    robot.keyPress(KeyEvent.VK_M);
                    break;
                case "N":
                    robot.keyPress(KeyEvent.VK_N);
                    break;
                case "O":
                    robot.keyPress(KeyEvent.VK_O);
                    break;
                case "P":
                    robot.keyPress(KeyEvent.VK_P);
                    break;
                case "Q":
                    robot.keyPress(KeyEvent.VK_Q);
                    break;
                case "R":
                    robot.keyPress(KeyEvent.VK_R);
                    break;
                case "S":
                    robot.keyPress(KeyEvent.VK_S);
                    break;
                case "T":
                    robot.keyPress(KeyEvent.VK_T);
                    break;
                case "U":
                    robot.keyPress(KeyEvent.VK_U);
                    break;
                case "V":
                    robot.keyPress(KeyEvent.VK_V);
                    break;
                case "W":
                    robot.keyPress(KeyEvent.VK_W);
                    break;
                case "X":
                    robot.keyPress(KeyEvent.VK_X);
                    break;
                case "Y":
                    robot.keyPress(KeyEvent.VK_Y);
                    break;
                case "Z":
                    robot.keyPress(KeyEvent.VK_Z);
                    break;
                case "0":
                    robot.keyPress(KeyEvent.VK_0);
                    break;
                case "1":
                    robot.keyPress(KeyEvent.VK_1);
                    break;
                case "2":
                    robot.keyPress(KeyEvent.VK_2);
                    break;
                case "3":
                    robot.keyPress(KeyEvent.VK_3);
                    break;
                case "4":
                    robot.keyPress(KeyEvent.VK_4);
                    break;
                case "5":
                    robot.keyPress(KeyEvent.VK_5);
                    break;
                case "6":
                    robot.keyPress(KeyEvent.VK_6);
                    break;
                case "7":
                    robot.keyPress(KeyEvent.VK_7);
                    break;
                case "8":
                    robot.keyPress(KeyEvent.VK_8);
                    break;
                case "9":
                    robot.keyPress(KeyEvent.VK_9);
                    break;
                case "UP":
                    robot.keyPress(KeyEvent.VK_UP);
                    break;
                case "DOWN":
                    robot.keyPress(KeyEvent.VK_DOWN);
                    break;
                case "LEFT":
                    robot.keyPress(KeyEvent.VK_LEFT);
                    break;
                case "RIGHT":
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    break;
                case "ENTER":
                    robot.keyPress(KeyEvent.VK_ENTER);
                    break;
                case "SPACE":
                    robot.keyPress(KeyEvent.VK_SPACE);
                    break;
                case "MINUS":
                    robot.keyPress(KeyEvent.VK_MINUS);
                    break;
                case "ASTERISK":
                    robot.keyPress(KeyEvent.VK_ASTERISK);
                    break;
                case "PLUS":
                    robot.keyPress(KeyEvent.VK_PLUS);
                    break;
                case "TAB":
                    robot.keyPress(KeyEvent.VK_TAB);
                    break;
                case "PERIOD":
                    robot.keyPress(KeyEvent.VK_PERIOD);
                    break;
                case "ESCAPE":
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    break;
                case "WINDOWS":
                    robot.keyPress(KeyEvent.VK_WINDOWS);
                    break;
                case "PAGE_UP":
                    robot.keyPress(KeyEvent.VK_PAGE_UP);
                    break;
                case "PAGE_DOWN":
                    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                    break;

                
                    
            }
            
            
        } catch (AWTException ex) {
            Logger.getLogger(KeyboardInterfaceClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e) {
                    
            System.out.println(e.getMessage());
        }
    }
    
}
