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

import javax.swing.*;

import java.io.File;

/**
 *
 * @author METAZONE
 */
public class DetectUSBService extends JFrame {

    private static final long serialVersionUID = 4267000447096917461L;

    /**
     * This method detects the USB drives name.
     * @return 
     */
    public String detectUSB() {

        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "Z"};
        File[] drives = new File[letters.length];
        boolean[] isDrive = new boolean[letters.length];

        for (int i = 0; i < letters.length; ++i) {
            drives[i] = new File(letters[i] + ":/");

            isDrive[i] = drives[i].canRead();
        }

        while (true) {

            for (int i = 0; i < letters.length; ++i) {
                boolean pluggedIn = drives[i].canRead();

                if (pluggedIn != isDrive[i]) {
                    if (pluggedIn) {

                        return letters[i];
                    } else {
                        isDrive[i] = pluggedIn;
                    }
                }
            }
        }

    }

}
