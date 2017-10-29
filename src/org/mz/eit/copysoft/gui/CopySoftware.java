package org.mz.eit.copysoft.gui;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.mz.eit.copysoft.constant.Constant;
import org.mz.eit.copysoft.service.CopyDataService;
import org.mz.eit.copysoft.service.DetectUSBService;

import sun.awt.RequestFocusController;

import com.sun.scenario.Settings;




public class CopySoftware   {


	private SystemTray tray;
	private TrayIcon trayIcon;
	private PopupMenu popup;
	public static String copyLocation;
	private SettingsFrame settingsFrame=null;
	private String drive;


	public static void main(String[] args) throws IOException {
		new CopySoftware().start();
	}


	public CopySoftware(){
		initSystemTrayComponents();
		initDestination();
	}

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

	void initDestination()


	{
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(Constant.filename));
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