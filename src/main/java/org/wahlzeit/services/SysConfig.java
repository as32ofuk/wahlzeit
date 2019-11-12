/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.services;

import java.io.File;
import java.util.logging.Logger;

/**
 * A basic set of system configuration data
 */
public class SysConfig extends AbstractConfig
{
    public static String DATA_PATH = "org-wahlzeit-dirkriehle";

    private static Logger log = Logger.getLogger(SysConfig.class.getName());

    /**
     *
     */
    protected String rootDir;

    /**
     *
     */
    protected ConfigDir scriptsDir;
    protected ConfigDir staticDir;
    protected ConfigDir templatesDir;

    /**
     *
     */
    protected Directory photosDir;
    protected Directory backupDir;
    protected Directory tempDir;

    /**
     *
     */
    public SysConfig()
    {
        this("");
    }

    /**
     *
     */
    public SysConfig(String myRootDir)
    {
        // Root directory
        rootDir = myRootDir;

        // Config directories
        scriptsDir = new ConfigDir(rootDir, "config" + File.separator + "scripts");
        staticDir = new ConfigDir(rootDir, "config" + File.separator + "static");
        templatesDir = new ConfigDir(rootDir, "config" + File.separator + "templates");

        // Data directories
        photosDir = new Directory(rootDir, DATA_PATH + File.separator + "photos");
        backupDir = new Directory(rootDir, DATA_PATH + File.separator + "backup");
        tempDir = new Directory(rootDir, DATA_PATH + File.separator + "temp");
    }

    /**
     *
     */
    public String getRootDirAsString()
    {
        return rootDir;
    }

    /**
     *
     */
    public ConfigDir getStaticDir()
    {
        return staticDir;
    }

    /**
     *
     */
    public ConfigDir getScriptsDir()
    {
        return scriptsDir;
    }

    /**
     *
     */
    public ConfigDir getTemplatesDir()
    {
        return templatesDir;
    }

    /**
     *
     */
    public Directory getPhotosDir()
    {
        return photosDir;
    }

    /**
     *
     */
    public Directory getBackupDir()
    {
        return backupDir;
    }

    /**
     *
     */
    public Directory getTempDir()
    {
        return tempDir;
    }

}
