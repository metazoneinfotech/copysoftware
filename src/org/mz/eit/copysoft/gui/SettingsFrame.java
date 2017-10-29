package org.mz.eit.copysoft.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.mz.eit.copysoft.constant.Constant;


public class SettingsFrame extends javax.swing.JFrame {

	private   JButton directory;
    private   JPanel panel;
    private   JTextField location;
    private   JButton save;
    private   File file;
    private   JFileChooser fileChos; 
    private   String  fileChostitile;
    public SettingsFrame() throws IOException  {
    	
    	
        super("Error 404");
        setWindowLookAndFeel();
        setResizable(false);
        

        initComponents();
     
        
        location.setText(CopySoftware.copyLocation);
        
      
    }
    
    private void initComponents() {

        panel = new javax.swing.JPanel();
        directory = new javax.swing.JButton();
        location = new javax.swing.JTextField();
        save = new javax.swing.JButton();

         // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/2;
        y=(screenSize.height-frameSize.height)/2;
        setLocation(x, y);
        
        panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        directory.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        directory.setText("...");
        directory.setMaximumSize(new java.awt.Dimension(47, 25));
        directory.setMinimumSize(new java.awt.Dimension(47, 25));
        directory.setPreferredSize(new java.awt.Dimension(47, 25));

        location.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        location.setPreferredSize(new java.awt.Dimension(6, 25));

        save.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.interiorBackground"));
        save.setFont(new java.awt.Font("Kartika", 1, 12)); // NOI18N
        save.setText("Save");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Constant.iconPath));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(directory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(directory, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(save)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        
        
        
      
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        pack();
    
       
      

    directory.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			 locationChooser();  
		}
	});
    
    
 save.addActionListener(new ActionListener() {


		
		public void actionPerformed(ActionEvent arg0) {
			
			//overWrite ();
			 setLocation();
		        	 
		         
	        //  setVisible(true);
			 dispose();
	 
		}
	});

 
    }
    
    private String readPath(BufferedReader bufferedReader) throws IOException {
        boolean ok = false;
        do {
            System.out.println("Please enter a Path:");
            File f = new File(bufferedReader.readLine());
            if(f.exists() && f.isDirectory())
                ok = true;
            else
                System.err.println("Doesn't exist or is not a folder.");
        } while(!ok);
        return file.getAbsolutePath();
    }
    
    public void overWrite () {
    	
    	try{
         
    		
    		File ob=new File(Constant.filename);
    		
            FileWriter fw = new FileWriter(ob.getAbsolutePath(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.flush();
            bw.write(file.getPath());
            bw.close();

            System.out.println("Done");
        }catch(IOException e){
            e.printStackTrace();
        }
    	
    }
    public void locationChooser()
   {
        fileChos = new JFileChooser();
         fileChos.setCurrentDirectory(new java.io.File("."));
         
         
                    fileChos.setDialogTitle("Select Path ");
	             
	                fileChos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                
	                int value = fileChos.showOpenDialog(null);
	                
	                
	                if (value ==  JFileChooser.APPROVE_OPTION);
	                {
	                	
	                    file = fileChos.getSelectedFile();
	                   
	                    if (file.exists()){
	                    	 //file.listFiles();
	                    	 location.setText(file.getAbsolutePath());
	 	                     CopySoftware.copyLocation=file.getAbsolutePath();
	                    }
	                    else {
	                    JOptionPane.showMessageDialog(null,"Invalid Path");
	                       }
	                    
                        }
	              
   }
    
 
    public void setLocation() 
         {
      
    	
    	 
       	File filep= new File (location.getText());
 
    	 if (filep.exists()){
    		 
    		
			try {
				
				 clearNotepad();
				 FileWriter writer = new FileWriter(Constant.filename, true);
				
				 writer.write(filep.getAbsolutePath());
		         	
	             writer.close();	
	    		 
			} catch (IOException e) {
			
				e.printStackTrace();
			}
    		 
         }
         else {
         JOptionPane.showMessageDialog(null,"Invalid Path");
         }
       
       
      
}
    public void clearNotepad() throws FileNotFoundException 
    {
        File file=new File(Constant.filename);
         PrintWriter writer = new PrintWriter(file);
       writer.print("");
       writer.close();
    }
  
  
    public void setWindowLookAndFeel(){
    	
    	 try {
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                 if ("Nimbus".equals(info.getName())) {
                     javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                     break;
                 }
             }
         } catch (ClassNotFoundException ex) {
             java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
             java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
             java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }


         
    }
    
   
}