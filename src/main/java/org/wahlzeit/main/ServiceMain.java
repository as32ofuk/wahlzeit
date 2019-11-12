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
import org.wahlzeit.handlers.HandlerFactory;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.WebPartHandler;
import org.wahlzeit.handlers.WebPartHandlerManager;
import org.wahlzeit.handlers.forms.*;
import org.wahlzeit.handlers.pages.*;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.languages.EnglishModelConfig;
import org.wahlzeit.model.languages.GermanModelConfig;
import org.wahlzeit.model.languages.LanguageConfigs;
import org.wahlzeit.services.ConfigDir;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.LogBuilder;
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

    protected WebPartHandlerManager webPartHandlerManager;

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
        webPartHandlerManager = new WebPartHandlerManager();
        HandlerFactory handlerFactory = new HandlerFactory(photoManager, userManager, sysConfig);

        // NullInfo and NullForm
        webPartHandlerManager.addWebPartHandler(handlerFactory.createNullFormHandler(), PartUtil.NULL_FORM_NAME);

        // Note page
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowNotePageHandler(), PartUtil.SHOW_NOTE_PAGE_NAME);

        // ShowPhoto page
        webPartHandlerManager.addWebPartHandler(handlerFactory.createFilterPhotosFormHandler(), PartUtil.FILTER_PHOTOS_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createPraisePhotoFormHandler(), PartUtil.PRAISE_PHOTO_FORM_NAME);

        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPhotoPageHandler(), PartUtil.SHOW_PHOTO_PAGE_NAME, PartUtil.ENGAGE_GUEST_FORM_NAME);

        webPartHandlerManager.addWebPartHandler(handlerFactory.createFilterPhotosPageHandler(), PartUtil.FILTER_PHOTOS_PAGE_NAME);

        webPartHandlerManager.addWebPartHandler(handlerFactory.createResetSessionPageHandler(), PartUtil.RESET_SESSION_PAGE_NAME);

        // About and Terms pages
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowInfoPageHandler(AccessRights.GUEST, PartUtil.ABOUT_INFO_FILE), PartUtil.ABOUT_PAGE_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowInfoPageHandler(AccessRights.GUEST, PartUtil.CONTACT_INFO_FILE), PartUtil.CONTACT_PAGE_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowInfoPageHandler(AccessRights.GUEST, PartUtil.IMPRINT_INFO_FILE), PartUtil.IMPRINT_PAGE_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowInfoPageHandler(AccessRights.GUEST, PartUtil.TERMS_INFO_FILE), PartUtil.TERMS_PAGE_NAME);

        // Flag, Send, Tell, and Options pages
        WebPartHandler form;
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createFlagPhotoFormHandler(photoCaseManager), PartUtil.FLAG_PHOTO_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.GUEST, form), PartUtil.FLAG_PHOTO_PAGE_NAME);
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createSendEmailFormHandler(), PartUtil.SEND_EMAIL_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.GUEST, form), PartUtil.SEND_EMAIL_PAGE_NAME);
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createTellFriendFormHandler(), PartUtil.TELL_FRIEND_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.GUEST, form), PartUtil.TELL_FRIEND_PAGE_NAME);
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createSetOptionsFormHandler(), PartUtil.SET_OPTIONS_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.GUEST, form), PartUtil.SET_OPTIONS_PAGE_NAME);

        // Signup, Login, EmailUserName/Password, and Logout pages
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createLoginFormHandler(), PartUtil.LOGIN_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.GUEST, form), PartUtil.LOGIN_PAGE_NAME);

        webPartHandlerManager.addWebPartHandler(handlerFactory.createLogoutPageHandler(), PartUtil.LOGOUT_PAGE_NAME);

        // SetLanguage pages
        webPartHandlerManager.addWebPartHandler(handlerFactory.createSetLanguagePageHandler(), PartUtil.SET_ENGLISH_LANGUAGE_PAGE_NAME, PartUtil.SET_GERMAN_LANGUAGE_PAGE_NAME, PartUtil.SET_SPANISH_LANGUAGE_PAGE_NAME, PartUtil.SET_JAPANESE_LANGUAGE_PAGE_NAME);

        // SetPhotoSize pages
        webPartHandlerManager.addWebPartHandler(handlerFactory.createSetPhotoSizePageHandler(), PartUtil.SET_EXTRA_SMALL_PHOTO_SIZE_PAGE_NAME, PartUtil.SET_SMALL_PHOTO_SIZE_PAGE_NAME, PartUtil.SET_MEDIUM_PHOTO_SIZE_PAGE_NAME, PartUtil.SET_LARGE_PHOTO_SIZE_PAGE_NAME, PartUtil.SET_EXTRA_LARGE_PHOTO_SIZE_PAGE_NAME);


        // ShowHome page
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowUserProfileFormHandler(), PartUtil.SHOW_USER_PROFILE_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowUserPhotoFormHandler(), PartUtil.SHOW_USER_PHOTO_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowUserHomePageHandler(), PartUtil.SHOW_USER_HOME_PAGE_NAME);

        // EditProfile, ChangePassword, EditPhoto, and UploadPhoto pages
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createEditUserProfileFormHandler(), PartUtil.EDIT_USER_PROFILE_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.USER, form), PartUtil.EDIT_USER_PROFILE_PAGE_NAME);
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createEditUserPhotoFormHandler(), PartUtil.EDIT_USER_PHOTO_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.USER, form), PartUtil.EDIT_USER_PHOTO_PAGE_NAME);
        form = webPartHandlerManager.addWebPartHandler(handlerFactory.createUploadPhotoFormHandler(), PartUtil.UPLOAD_PHOTO_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPartPageHandler(AccessRights.USER, form), PartUtil.UPLOAD_PHOTO_PAGE_NAME);

        webPartHandlerManager.addWebPartHandler(handlerFactory.createEditPhotoCaseFormHandler(photoCaseManager), PartUtil.EDIT_PHOTO_CASE_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowPhotoCasesPageHandler(photoCaseManager), PartUtil.SHOW_PHOTO_CASES_PAGE_NAME);

        // Admin page incl. AdminUserProfile and AdminUserPhoto
        webPartHandlerManager.addWebPartHandler(handlerFactory.createShowAdminPageHandler(), PartUtil.SHOW_ADMIN_PAGE_NAME, PartUtil.SHOW_ADMIN_MENU_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createAdminUserProfileFormHandler(), PartUtil.ADMIN_USER_PROFILE_FORM_NAME);
        webPartHandlerManager.addWebPartHandler(handlerFactory.createAdminUserPhotoFormHandler(), PartUtil.ADMIN_USER_PHOTO_FORM_NAME);
    }

    /**
     *
     */
    public void configureLanguageModels()
    {
        LanguageConfigs.put(Language.ENGLISH, new EnglishModelConfig(sysConfig));
        LanguageConfigs.put(Language.GERMAN, new GermanModelConfig(sysConfig));
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
