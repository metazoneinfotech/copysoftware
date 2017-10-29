package org.mz.eit.copysoft.service;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;

public class DetectUSBService extends JFrame
{
   
	
	private static final long serialVersionUID = 4267000447096917461L;
	
  
	
    public String detectUSB(){
    	
   
    	System.out.println();
    	String[] letters = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "Z"};
        File[] drives = new File[letters.length];
        boolean[] isDrive = new boolean[letters.length];

        for ( int i = 0; i < letters.length; ++i )
            {
            drives[i] = new File(letters[i]+":/");

            isDrive[i] = drives[i].canRead();
            }

         while(true)
            {
        
            for ( int i = 0; i < letters.length; ++i )
                {
                boolean pluggedIn = drives[i].canRead();

                if ( pluggedIn != isDrive[i] )
                    {
                    if ( pluggedIn ) {
                      
                     return letters[i];
                    }
                    else
                  
                    isDrive[i] = pluggedIn;
                    System.out.println("unpulg"+letters[i]);
                    }
                }
    }

	
    	
    }
    

}