package org.mz.eit.copysoft.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.mz.eit.copysoft.constant.Constant;
import org.mz.eit.copysoft.gui.CopySoftware;

public class CopyDataService  {
	
    private String source;
    
    
	 public CopyDataService(String source) throws IOException{
		   this.source = source+":\\"+"\\";
		   
	 }

	

	   public  void copyFileUsingStream(File source, File dest) throws IOException {
	        InputStream is = null;
	        OutputStream os = null;
	        try {
	            is = new FileInputStream(source);
	            os = new FileOutputStream(dest);
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                os.write(buffer, 0, length);
	            }
	        } finally {
	            is.close();
	            os.close();
	        }
	    }
	   public  void  copy() throws IOException {

	    	 //  System.out.println(this.source);
	            try{
	            File source = new File(this.source);
	            File dest = new File(CopySoftware.copyLocation);
	            FileUtils.copyDirectory(source,dest);   
	            }catch (java.lang.IllegalArgumentException e){
	            	System.out.println("lol");
	            	JOptionPane.showMessageDialog(null,"System 32 bit error");
	            	
	            }
	            // copyFileUsingStream(source,dest);
	    }
	   
}