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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.LogManager;
import org.mz.copysoftware.App;

import org.mz.copysoftware.constant.Constant;
import org.mz.copysoftware.service.CopyDataService;
import org.mz.copysoftware.service.DetectUSBService;

/**
 * This class is the base class which detects the USB Drive and makes the tray 
 * icon.
 * @author Metazone
 */
public class TrayApplication {
        private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(TrayApplication.class.getName());


    public static String copyLocation;
    private SettingsFrame settingsFrame;

    public TrayApplication() {
        
        if (SystemTray.isSupported()) {
            initSystemTrayComponents();
            initDestination();
            settingsFrame = new SettingsFrame();
        }
        LOGGER.info("Tray application constructor performed");
    }

        /**
         * This method makes a system tray in which two options are present 
         * SETTINGS and EXIT. It gives the selected image to the tray icon.
         */
    private void initSystemTrayComponents() {
        Image trayImage = Toolkit.getDefaultToolkit().getImage(Constant.iconPath);
        PopupMenu popupMenu = new PopupMenu();
        MenuItem settingsMenuItem = new MenuItem("Settings");
        MenuItem exitMenuItem = new MenuItem("Exit");
        popupMenu.add(settingsMenuItem);
        popupMenu.add(exitMenuItem);
        settingsMenuItem.addActionListener((ActionEvent evt) -> {
            settingsMenuItemActionPerformed();
        });
        exitMenuItem.addActionListener((ActionEvent evt) -> {
            System.exit(0);
        });

        TrayIcon trayIcon = new TrayIcon(trayImage, "A Program by Metazone.", popupMenu);
        trayIcon.setImageAutoSize(true);

        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
            LOGGER.info("initSystemTrayComponents()");
        } catch (AWTException e) {
            //System.out.println("TrayIcon could not be added.");
            LOGGER.error(e.getMessage(),e);
        }
       
    }
    
    /**
     * This method reads the copied data destination from file and load in 
     * variable.
     */
    private void initDestination() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(Constant.filename));
            copyLocation = reader.readLine();
            LOGGER.info("initdestination");
        } catch (FileNotFoundException evt) {
            //e1.printStackTrace();
            LOGGER.error(evt.getMessage(),evt);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(),ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    LOGGER.info("Reader close");
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(),ex);
                }
            }
        }
    }
    
    /**
     * This method makes the setting frame visible and make it focusable.
     */
    private void settingsMenuItemActionPerformed() {
        settingsFrame.loadSettings();
        settingsFrame.setVisible(true);
        settingsFrame.setFocusableWindowState(true);
        LOGGER.info("settingsMenuItemActionPerformed()");
    }

    /**
     * This method is used to detect the USB Device and make the copy of USB 
     * data.
     */
    public void start() {
        new Thread(() -> {
            DetectUSBService detectUSBService = new DetectUSBService();
            while (true) {
                String drive = detectUSBService.detectUSB();
                //System.out.println(drive);
                new Thread(() -> {
                    try {
                        new CopyDataService(drive).copy();
                        LOGGER.info("Start() method");
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(),e);
                    }
                }).start();
            }
        }).start();

    }

}
