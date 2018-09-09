/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.voice_module;

import java.io.IOException;


/**
 *
 * @author omkarj
 */
public class StringProcessing {
    
    
    void computeLPSArray(String pat, int M, int lps[])
            {
                    // length of the previous longest prefix suffix
                    int len = 0;
                    int i = 1;
                    lps[0] = 0; // lps[0] is always 0

                    // the loop calculates lps[i] for i = 1 to M-1
                    while (i < M)
                    {
                            if (pat.charAt(i) == pat.charAt(len))
                            {
                                    len++;
                                    lps[i] = len;
                                    i++;
                            }
                            else // (pat[i] != pat[len])
                            {
                                    // This is tricky. Consider the example.
                                    // AAACAAAA and i = 7. The idea is similar 
                                    // to search step.
                                    if (len != 0)
                                    {
                                            len = lps[len-1];

                                            // Also, note that we do not increment
                                            // i here
                                    }
                                    else // if (len == 0)
                                    {
                                            lps[i] = len;
                                            i++;
                                    }
                            }
                    }
            }
    
    
    
    
    int KMPSearch(String pat, String txt)
                {
                    int M = pat.length();
                    int N = txt.length();
                    
                    

                    // create lps[] that will hold the longest
                    // prefix suffix values for pattern
                    int lps[] = new int[M];
                    int j = 0; // index for pat[]

                    // Preprocess the pattern (calculate lps[]
                    // array)
                    computeLPSArray(pat,M,lps);

                    int i = 0,m=0; // index for txt[]
                    while (i < N)
                    {
                            if (pat.charAt(j) == txt.charAt(i))
                            {
                                    j++;
                                    i++;
                            }
                            if (j == M)
                            {
                                    //System.out.println("C Command : "  );
                                     j = lps[j-1];
                                     m=1;
                            }

                            // mismatch after j matches
                            else if (i < N && pat.charAt(j) != txt.charAt(i))
                            {
                                    // Do not match lps[0..lps[j-1]] characters,
                                    // they will match anyway
                                    if (j != 0)
                                            j = lps[j-1];
                                    else
                                            i = i+1;
                            }
                    }
                    if(m == 1){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                     
                }
    
    public StringProcessing (String txt,String [] c) throws IOException{
    
        
        String [] con = c;
        String st = txt.toLowerCase();
        String[] token = st.split(" ");
        int t = 0,j = 0,k=0,p;
        for (p = 0 ; p < con.length ; p = p+2)
        {
            
            
            String E_Comm = con[p];
            String[] EC = E_Comm.split(" ");
            int r = EC.length;
            int s = token.length;
            int u,i;
            j=0;
            
            for(i = r-1; i>=0 ; i--){
                
                for(u = s-1; u>=0; u--){
                    
                    if(EC[i].equals(token[u])){
                        j++;   
                    }
                }
            }
            
            if(j == r){
                
                
                System.out.println("Corresponding CMD Command : " + con[p+1]);
                ActionControl ac=new ActionControl();
                ac.getString(con[p+1]);
                t = 1;
                break;
            }
            
        }
        if(t != 1)
        {
            
            for (p = 0 ; p < con.length ; p = p+2){
                
                
                j=0;
                String E_Command = con[p];
               
                
                String[] EC = E_Command.split(" ");
                int r = EC.length;
                int s = token.length;
                
                for(int i = r-1; i>=0 ; i--){
                    
                    int flag = 0;
                    
                    for(int u = s-1; u>=0; u--){
                        
                        if(token[u].length() < EC[i].length()){
                            
                            k = KMPSearch(token[u], EC[i]);
                            
                        }
                        else{
                            
                            k = KMPSearch( EC[i], token[u]);
                        }
                        if(k == 1){
                            j++;
                            flag = 1;
                            break;
                        }
                    }
                    if(flag != 1){
                        
                        break;
                    }                
                }
                if(j == r){
                    System.out.println("Corresponding CMD Command : " + con[p+1]);
                    ActionControl ac=new ActionControl();
                ac.getString(con[p+1]);
                    t=1;
                    break;
                }
            }
        }
        if(t != 1){
            System.out.println("Invalid Command by string processing");
        }
    }  
}