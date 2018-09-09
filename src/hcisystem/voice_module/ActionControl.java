/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.voice_module;
import java.io.*;
/**
 *
 * @author Administrator
 */
public class ActionControl  {
    
    public String command;
     
   
    public void getString(String command) throws IOException{

        System.out.println("GOT THIS IN ACTION CONTROL : " + command);
        
        
        try {
            File file = new File("action.bat");

              // creates the file
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(command);
                writer.flush();
            }

            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("action.bat");
        }
        catch(Exception e) {
        
            System.out.println("GOT ERROR IN ACTION CONTROL : " + e.getMessage());
        }
    }
}

