/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.voice_module;

import hcisystem.HCISystem;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omkarj
 */
public class FileControl {
    
    
    private final String path;

    public FileControl() {
        this.path = HCISystem.VOICEFOLDERPATH + "\\database.dat";
    }

    int readLines() throws IOException{

        System.out.println("inside readlines method");
        System.out.println("creating file reader object");
        FileReader fi_read = new FileReader(path);
        System.out.println("done");
        System.out.println("calculating number of lines.");
        int no_lines = 0;
        try (BufferedReader bf = new BufferedReader(fi_read)) {
            String al;
            no_lines = 0;
            while((al = bf.readLine()) != null){
                no_lines++;
            }
        }
        catch (Exception e) {
        
            System.out.println(e.getMessage());
        }
        System.out.println("number of lines --> " + no_lines);
        return no_lines;
    }

    
    public String[] openFile() {
    
        String textData[] = null;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            int no_lines = readLines();
            
            textData = new String[no_lines];
            int i;
            for(i=0; i< no_lines; i++){
                textData[i] = br.readLine();
            }
            
            br.close();
            fr.close();
        } catch (Exception ex) {
            
            System.out.println(ex.getMessage());
        }
        return textData;
    }
}
