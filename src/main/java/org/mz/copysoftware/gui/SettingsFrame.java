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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.mz.copysoftware.constant.Constant;

public class SettingsFrame extends javax.swing.JFrame {

    private static final Logger LOGGER = LogManager.getLogger(SettingsFrame.class.getName());

    public SettingsFrame() {
        super("CopySoftware");
        initComponents();
    }

    /**
     * This method makes the panel and add the necessary components to the
     * panel.
     */
    private void initComponents() {

        panel = new javax.swing.JPanel();
        copyDestinationField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        copyDestinationChooser = new JFileChooser();

        Dimension screenSize;
        Dimension frameSize;
        int x;
        int y;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameSize = getSize();
        x = (screenSize.width - frameSize.width) / 2;
        y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);

        browseBtn.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        browseBtn.setText("...");
        browseBtn.setMaximumSize(new java.awt.Dimension(47, 25));
        browseBtn.setMinimumSize(new java.awt.Dimension(47, 25));
        browseBtn.setPreferredSize(new java.awt.Dimension(47, 25));

        copyDestinationField.setPreferredSize(new java.awt.Dimension(6, 25));
        copyDestinationField.setEditable(false);

        copyDestinationChooser.setCurrentDirectory(new java.io.File("."));
        copyDestinationChooser.setDialogTitle("Select Copy Destination...");
        copyDestinationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        saveBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.interiorBackground"));
        saveBtn.setFont(new java.awt.Font("Kartika", 1, 12)); // NOI18N
        saveBtn.setText("Save");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Constant.ICON_PATH));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(copyDestinationField, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(copyDestinationField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(saveBtn)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        setResizable(false);
        pack();

        browseBtn.addActionListener((ActionEvent arg0) -> {
            browseBtnActionPerformed();
        });

        saveBtn.addActionListener((ActionEvent arg0) -> {
            saveBtnActionPerformed();
        });

    }

    /**
     * This method is used to open copy destination chooser and set the chooser
     * folder path to text field.
     */
    public void browseBtnActionPerformed() {
        if (copyDestinationChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION);
        {
            copyDestinationField.setText(copyDestinationChooser.getSelectedFile().getAbsolutePath());
        }
        LOGGER.info("Browse button clicked");
    }

    private void saveBtnActionPerformed() {
        saveCopyDestination();
        dispose();
        LOGGER.info("Save button clicked");
    }

    /**
     * This method saves the copy location of the data.
     */
    public void saveCopyDestination() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(Constant.FILE_NAME, true);
            writer.write(copyDestinationField.getText());
            TrayApplication.copyLocation = copyDestinationField.getText();
            LOGGER.info("Save the copy location of destination");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    LOGGER.info("Close the method");
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * This method set the latest copy destination to text field.
     */
    public void loadSettings() {
        copyDestinationField.setText(TrayApplication.copyLocation);
    }

    private JPanel panel;
    private JTextField copyDestinationField;
    private JButton browseBtn;
    private JButton saveBtn;
    private JFileChooser copyDestinationChooser;
}
