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
package org.mz.copysoftware.gui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.mz.copysoftware.constant.Constant;
import org.mz.copysoftware.service.CopyDataService;
import org.mz.copysoftware.service.DetectUSBService;

/**
 * This class is the base class which detects the USB Drive 
 * @author Metazone
 */
public class CopySoftware   {

	private SystemTray tray;
	private TrayIcon trayIcon;
	private PopupMenu popup;
	public static String copyLocation;
	private SettingsFrame settingsFrame=null;
	private String drive;
	
        
	public CopySoftware(){
		initSystemTrayComponents();
		initDestination();
	}
        
        /**
         * This method is used to detect the USB Device
         * @throws IOException 
         */
	public void start() throws IOException{

		
		new Thread(new Runnable() {

			public void run() {

				while(true){
				drive = new DetectUSBService().detectUSB();
				//System.out.println(drive);
				
				new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						try {
							new CopyDataService(drive).copy();
						} catch (IOException e) {
							//e.printStackTrace();
                                                }
					}
				}).start();
				}
			}
		}).start();
		
	}
        /**
         * 
         */
	public void initSystemTrayComponents(){

		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}


		popup=new PopupMenu();
		tray = SystemTray.getSystemTray();

		MenuItem settings = new MenuItem("Settings");
		MenuItem exit = new MenuItem("Exit");

		Image image = Toolkit.getDefaultToolkit().getImage(Constant.iconPath);
		trayIcon = new TrayIcon(image, "A Program by Error 404",popup); 
		trayIcon.setImageAutoSize(true);

		popup.add(settings);
		popup.add(exit);

		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (settingsFrame==null){

					try {

						settingsFrame=new SettingsFrame();

					} catch (IOException e) {

						e.printStackTrace();
					}
				}
				else {
					settingsFrame.setVisible(true);
				}
				settingsFrame.setVisible(true);
				settingsFrame.setFocusableWindowState(true);
			}
		});
		exit.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);


			}
		});

		// trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}

	}
        /**
         * 
         */
	void initDestination()


	{
		BufferedReader reader=null;
		try {
                    System.err.println(getClass().getClassLoader().getResource("files/copy-location.txt").getPath());
			reader = new BufferedReader(new FileReader("D:/Git Projects/copysoftware/target/classes/files/copy-location.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = null;
		try{
			while((line = reader.readLine()) != null){
				copyLocation = line;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}