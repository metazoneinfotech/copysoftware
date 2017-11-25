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
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mz.copysoftware;

import javax.swing.UIManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mz.copysoftware.gui.SettingsFrame;
import org.mz.copysoftware.gui.TrayApplication;

/**
 *
 * @author FILE
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                //LOGGER.info("Set the look and feel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                //LOGGER.error(ex.getMessage(),ex);
            }
            new TrayApplication().start();
            LOGGER.info("Application Started");
        });
    }

}
