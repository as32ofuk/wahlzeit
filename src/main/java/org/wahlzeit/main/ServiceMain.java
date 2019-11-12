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

package org.wahlzeit.main;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.wahlzeit.handlers.*;
import org.wahlzeit.handlers.forms.*;
import org.wahlzeit.handlers.pages.*;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.languages.EnglishModelConfig;
import org.wahlzeit.model.languages.GermanModelConfig;
import org.wahlzeit.model.languages.LanguageConfigs;
import org.wahlzeit.services.ConfigDir;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.webparts.WebPartTemplateService;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

/**
 * A Main class that runs a Wahlzeit web server.
 */
public class ServiceMain extends ModelMain
{

    private static final Logger log = Logger.getLogger(ServiceMain.class.getName());

    private static final String PICTURES_PATH = "pictures";

    /**
     *
     */
    protected static ServiceMain instance = new ServiceMain();

    /**
     *
     */
    protected boolean isToStop = false;

    /**
     *
     */
    protected boolean isInProduction = false;

    /**
     *
     */
    public static ServiceMain getInstance()
    {
        return instance;
    }

    /**
     *
     */
    public void requestStop()
    {
        synchronized(instance)
        {
            instance.isToStop = true;
        }
    }

    /**
     *
     */
    public boolean isShuttingDown()
    {
        return instance.isToStop;
    }

    /**
     *
     */
    public boolean isInProduction()
    {
        return instance.isInProduction;
    }

    /**
     *
     */
    public void startUp(boolean inProduction, String rootDir) throws Exception
    {
        isInProduction = inProduction;

        log.config(LogBuilder.createSystemMessage().addAction("Start up ModelMain").toString());
        super.startUp(rootDir);

        log.config(LogBuilder.createSystemMessage().addAction("Configure WebPartTemplateService").toString());
        configureWebPartTemplateService();

        log.config(LogBuilder.createSystemMessage().addAction("Configure WebPartHandler").toString());
        configureWebPartHandlers();

        log.config(LogBuilder.createSystemMessage().addAction("Configure LanguageModels").toString());
        configureLanguageModels();

        log.config(LogBuilder.createSystemMessage().addAction("Add default user with pictures").toString());
        addDefaultUserWithPictures();

        log.config(LogBuilder.createSystemMessage().addMessage("StartUp complete.").toString());
    }

    /**
     *
     */
    public void configureWebPartTemplateService()
    {
        ConfigDir templatesDir = sysConfig.getTemplatesDir();
        WebPartTemplateService.getInstance().setTemplatesDir(templatesDir);
    }

    /**
     *
     */
    public void configureWebPartHandlers()
    {
        WebPartHandler temp = null;
        WebPartHandlerManager manager = WebPartHandlerManager.getInstance();

        // NullInfo and NullForm
        manager.addWebPartHandler(PartUtil.NULL_FORM_NAME, new NullFormHandler(photoManager, userManager));

        // Note page
        manager.addWebPartHandler(PartUtil.SHOW_NOTE_PAGE_NAME, new ShowNotePageHandler(photoManager, userManager));

        // ShowPhoto page
        manager.addWebPartHandler(PartUtil.FILTER_PHOTOS_FORM_NAME, new FilterPhotosFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.PRAISE_PHOTO_FORM_NAME, new PraisePhotoFormHandler(photoManager, userManager));

        temp = new ShowPhotoPageHandler(photoManager, userManager);
        manager.addWebPartHandler(PartUtil.SHOW_PHOTO_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.ENGAGE_GUEST_FORM_NAME, temp);

        manager.addWebPartHandler(PartUtil.FILTER_PHOTOS_PAGE_NAME, new FilterPhotosPageHandler(photoManager, userManager));

        manager.addWebPartHandler(PartUtil.RESET_SESSION_PAGE_NAME, new ResetSessionPageHandler(photoManager, userManager));

        // About and Terms pages
        manager.addWebPartHandler(PartUtil.ABOUT_PAGE_NAME,
                new ShowInfoPageHandler(photoManager, userManager, AccessRights.GUEST, PartUtil.ABOUT_INFO_FILE));
        manager.addWebPartHandler(PartUtil.CONTACT_PAGE_NAME,
                new ShowInfoPageHandler(photoManager, userManager, AccessRights.GUEST, PartUtil.CONTACT_INFO_FILE));
        manager.addWebPartHandler(PartUtil.IMPRINT_PAGE_NAME,
                new ShowInfoPageHandler(photoManager, userManager, AccessRights.GUEST, PartUtil.IMPRINT_INFO_FILE));
        manager.addWebPartHandler(PartUtil.TERMS_PAGE_NAME,
                new ShowInfoPageHandler(photoManager, userManager, AccessRights.GUEST, PartUtil.TERMS_INFO_FILE));

        // Flag, Send, Tell, and Options pages
        temp = manager.addWebPartHandler(PartUtil.FLAG_PHOTO_FORM_NAME, new FlagPhotoFormHandler(photoManager, userManager, photoCaseManager));
        manager.addWebPartHandler(PartUtil.FLAG_PHOTO_PAGE_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.GUEST, temp));
        temp = manager.addWebPartHandler(PartUtil.SEND_EMAIL_FORM_NAME, new SendEmailFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.SEND_EMAIL_PAGE_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.GUEST, temp));
        temp = manager.addWebPartHandler(PartUtil.TELL_FRIEND_FORM_NAME, new TellFriendFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.TELL_FRIEND_PAGE_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.GUEST, temp));
        temp = manager.addWebPartHandler(PartUtil.SET_OPTIONS_FORM_NAME, new SetOptionsFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.SET_OPTIONS_PAGE_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.GUEST, temp));

        // Signup, Login, EmailUserName/Password, and Logout pages
        temp = manager.addWebPartHandler(PartUtil.LOGIN_FORM_NAME, new LoginFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.LOGIN_FORM_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.GUEST, temp));

        manager.addWebPartHandler(PartUtil.LOGOUT_PAGE_NAME, new LogoutPageHandler(photoManager, userManager));

        // SetLanguage pages
        temp = new SetLanguagePageHandler(photoManager, userManager);
        manager.addWebPartHandler(PartUtil.SET_ENGLISH_LANGUAGE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_GERMAN_LANGUAGE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_SPANISH_LANGUAGE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_JAPANESE_LANGUAGE_PAGE_NAME, temp);

        // SetPhotoSize pages
        temp = new SetPhotoSizePageHandler(photoManager, userManager);
        manager.addWebPartHandler(PartUtil.SET_EXTRA_SMALL_PHOTO_SIZE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_SMALL_PHOTO_SIZE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_MEDIUM_PHOTO_SIZE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_LARGE_PHOTO_SIZE_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SET_EXTRA_LARGE_PHOTO_SIZE_PAGE_NAME, temp);

        // ShowHome page
        manager.addWebPartHandler(PartUtil.SHOW_USER_PROFILE_FORM_NAME, new ShowUserProfileFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.SHOW_USER_PHOTO_FORM_NAME, new ShowUserPhotoFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.SHOW_USER_HOME_PAGE_NAME, new ShowUserHomePageHandler(photoManager, userManager));

        // EditProfile, ChangePassword, EditPhoto, and UploadPhoto pages
        temp = manager.addWebPartHandler(PartUtil.EDIT_USER_PROFILE_FORM_NAME, new EditUserProfileFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.EDIT_USER_PROFILE_PAGE_NAME,
                new ShowPartPageHandler(photoManager, userManager, AccessRights.USER, temp));
        temp = manager.addWebPartHandler(PartUtil.EDIT_USER_PHOTO_FORM_NAME, new EditUserPhotoFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.EDIT_USER_PHOTO_PAGE_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.USER, temp));
        temp = manager.addWebPartHandler(PartUtil.UPLOAD_PHOTO_FORM_NAME, new UploadPhotoFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.UPLOAD_PHOTO_PAGE_NAME, new ShowPartPageHandler(photoManager, userManager, AccessRights.USER, temp));

        manager.addWebPartHandler(PartUtil.EDIT_PHOTO_CASE_FORM_NAME, new EditPhotoCaseFormHandler(photoManager, userManager, photoCaseManager));
        manager.addWebPartHandler(PartUtil.SHOW_PHOTO_CASES_PAGE_NAME, new ShowPhotoCasesPageHandler(photoManager, userManager, photoCaseManager));

        // Admin page incl. AdminUserProfile and AdminUserPhoto
        temp = new ShowAdminPageHandler(photoManager, userManager);
        manager.addWebPartHandler(PartUtil.SHOW_ADMIN_PAGE_NAME, temp);
        manager.addWebPartHandler(PartUtil.SHOW_ADMIN_MENU_FORM_NAME, temp);
        manager.addWebPartHandler(PartUtil.ADMIN_USER_PROFILE_FORM_NAME, new AdminUserProfileFormHandler(photoManager, userManager));
        manager.addWebPartHandler(PartUtil.ADMIN_USER_PHOTO_FORM_NAME, new AdminUserPhotoFormHandler(photoManager, userManager));
    }

    /**
     *
     */
    public void configureLanguageModels()
    {
        LanguageConfigs.put(Language.ENGLISH, new EnglishModelConfig());
        LanguageConfigs.put(Language.GERMAN, new GermanModelConfig());
    }

    /**
     *
     */
    public void addDefaultUserWithPictures()
    {
        ObjectifyService.run(new VoidWork()
        {
            public void vrun()
            {
                try
                {
                    URL url = getClass().getClassLoader().getResource(PICTURES_PATH);
                    File file = new File(url.getPath());
                    createUser("robot", "Mr. Robot", "robot@ecorp.com", file.getAbsolutePath());
                }
                catch(NullPointerException e)
                {
                    log.warning("Unable to create default user");
                }
            }
        });
    }

    /**
     *
     */
    public void shutDown() throws Exception
    {
        //AgentManager am = AgentManager.getInstance();
        //am.stopAllThreads();

        super.shutDown();
    }

}
