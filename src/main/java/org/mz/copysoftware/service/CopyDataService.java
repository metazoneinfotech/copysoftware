/*
 * Copyright (C) 2017 Metazone Infotech Pvt Ltd.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.mz.copysoftware.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.mz.copysoftware.gui.CopySoftware;

public class CopyDataService  {
	
    private String source;
    
        
	 public CopyDataService(String source) throws IOException{
		   this.source = source+":\\"+"\\";
		   
	 }

	
            /**
             * Ye method asa karta hai
             * @param source
             * @param dest
             * @throws IOException 
             */
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