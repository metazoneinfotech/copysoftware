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
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.mz.copysoftware.gui.TrayApplication;

public class CopyDataService {
            private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(CopyDataService.class.getName());


    private final String source;

        /**
         * This is a constructor initializes the source of copy.
         * @param source 
         */
    public CopyDataService(String source) {
        this.source = source + ":\\" + "\\";
        LOGGER.info("CopyDataService()");
    }

    /**
     *This method copies the data of source and destination.
     * @throws IOException
     */
    public void copy() throws IOException {
        File sourceFile = new File(source);
        File dest = new File(TrayApplication.copyLocation);
        FileUtils.copyDirectory(sourceFile, dest);
        LOGGER.info("Copy()");
    }

}
