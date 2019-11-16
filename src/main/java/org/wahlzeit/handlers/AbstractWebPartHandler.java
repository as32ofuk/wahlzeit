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

package org.wahlzeit.handlers;

import org.wahlzeit.handlers.forms.WebFormHandler;
import org.wahlzeit.model.*;
import org.wahlzeit.model.clients.Client;
import org.wahlzeit.model.clients.User;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.utils.HtmlUtil;
import org.wahlzeit.utils.StringUtil;
import org.wahlzeit.webparts.WebPart;
import org.wahlzeit.webparts.WebPartTemplate;
import org.wahlzeit.webparts.WebPartTemplateService;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A superclass for handling parts of web pages.
 */
public abstract class AbstractWebPartHandler implements WebPartHandler
{
    private static final Logger log = Logger.getLogger(AbstractWebPartHandler.class.getName());

    protected PhotoManager photoManager;
    protected UserManager userManager;
    protected SysConfig sysConfig;
    protected WebPartHandlerManager webPartHandlerManager;
    protected WebPartTemplateService webPartTemplateService;

    /**
     *
     */
    protected String templateName;
    /**
     *
     */
    protected AccessRights neededRights;

    public AbstractWebPartHandler(PhotoManager photoManager,
                                  UserManager userManager,
                                  SysConfig sysConfig,
                                  WebPartHandlerManager webPartHandlerManager,
                                  WebPartTemplateService webPartTemplateService,
                                  String templateName,
                                  AccessRights neededRights)
    {
        this.photoManager = photoManager;
        this.userManager = userManager;
        this.sysConfig = sysConfig;
        this.webPartHandlerManager = webPartHandlerManager;
        this.webPartTemplateService = webPartTemplateService;
        webPartTemplateService.setTemplatesDir(sysConfig.getTemplatesDir());
        this.templateName = templateName;
        this.neededRights = neededRights;
    }

    /**
     * @methodtype factory
     */
    protected final WebPart createWebPart(UserSession us)
    {
        return createWebPart(us, templateName);
    }

    /**
     * @methodtype factory
     */
    protected final WebPart createWebPart(UserSession us, String name)
    {
        WebPartTemplate template = webPartTemplateService.getTemplate(us.getClient().getLanguageConfiguration().getLanguageCode(), name);
        return new WebPart(template);
    }

    /**
     *
     */
    protected String getPhotoThumb(UserSession us, Photo photo)
    {
        String result = null;
        if(photo != null)
        {
            String imageLink = getPhotoAsRelativeResourcePathString(photo, PhotoSize.THUMB);
            result = HtmlUtil.asImg(HtmlUtil.asPath(imageLink), photo.getThumbWidth(), photo.getThumbHeight());
        }
        else
        {
            Language langValue = us.getClient().getLanguage();
            result = HtmlUtil.asImg(getEmptyImageAsRelativeResourcePathString(langValue));
        }
        return result;
    }

    /**
     *
     */
    protected String getPhotoAsRelativeResourcePathString(Photo photo, PhotoSize size)
    {
        return sysConfig.getPhotosDir().getRelativeDir() + "/?type=image&photoId=" + photo.getId().asString() +
                "&size=" + String.valueOf(size.asInt());
    }

    /**
     *
     */
    protected String getEmptyImageAsRelativeResourcePathString(Language lang)
    {
        String resName = lang.asIsoCode() + File.separator + "empty.png";
        return HtmlUtil.asPath(sysConfig.getStaticDir().getRelativeConfigFileName(resName));
    }

    /**
     *
     */
    protected String getPhotoSummary(UserSession us, Photo photo)
    {
        return photo.getSummary(us.getClient().getLanguageConfiguration());
    }

    /**
     *
     */
    protected String getPhotoCaption(UserSession us, Photo photo)
    {
        return photo.getCaption(us.getClient().getLanguageConfiguration());
    }

    /**
     *
     */
    protected final WebFormHandler getFormHandler(String name)
    {
        return webPartHandlerManager.getWebFormHandler(name);
    }

    /**
     *
     */
    protected boolean hasSavedPhotoId(UserSession us)
    {
        String id = us.getAsString(us.getSavedArgs(), Photo.ID);
        return !StringUtil.isNullOrEmptyString(id);
    }

    /**
     * https://youtu.be/-FRm3VPhseI
     */
    protected boolean isSavedPhotoVisible(UserSession us)
    {
        String id = us.getAsString(us.getSavedArgs(), Photo.ID);
        Photo photo = photoManager.getPhoto(id);
        return photo.isVisible();
    }

    /**
     *
     */
    protected boolean hasSavedMessage(UserSession us)
    {
        return !StringUtil.isNullOrEmptyString(us.getMessage());
    }

    /**
     *
     */
    public final String handleGet(UserSession us, String link, Map args)
    {
        if(!hasAccessRights(us, args))
        {
            log.warning(LogBuilder.createSystemMessage().
                    addMessage("insufficient rights for GET").toString());
            return getIllegalAccessErrorPage(us);
        }

        if(!isWellFormedGet(us, link, args))
        {
            log.warning(LogBuilder.createSystemMessage().
                    addMessage("received ill-formed GET").toString());
            return getIllegalArgumentErrorPage(us);
        }

        try
        {
            // may throw Exception
            return doHandleGet(us, link, args);
        }
        catch(Throwable t)
        {
            log.warning(LogBuilder.createSystemMessage().addException("Handle get failed", t).toString());
            return getInternalProcessingErrorPage(us);
        }
    }

    /**
     *
     */
    protected boolean hasAccessRights(UserSession us, Map args)
    {
        Client client = us.getClient();
        if(client == null)
        {
            return false;
        }

        return client.hasRights(getNeededRights());
    }

    /**
     *
     */
    protected String getIllegalAccessErrorPage(UserSession us)
    {
        ModelConfig config = us.getClient().getLanguageConfiguration();
        us.setHeading(config.getInformation());

        String msg1 = config.getIllegalAccessError();
        us.setMessage(msg1);

        return PartUtil.SHOW_NOTE_PAGE_NAME;
    }

    /**
     *
     */
    protected boolean isWellFormedGet(UserSession us, String link, Map args)
    {
        return true;
    }

    /**
     *
     */
    protected String getIllegalArgumentErrorPage(UserSession us)
    {
        ModelConfig config = us.getClient().getLanguageConfiguration();
        us.setHeading(config.getInformation());

        String msg1 = config.getIllegalArgumentError();
        String msg2 = config.getContinueWithShowPhoto();
        if(us.getClient() instanceof User)
        {
            msg2 = config.getContinueWithShowUserHome();
        }

        us.setTwoLineMessage(msg1, msg2);

        return PartUtil.SHOW_NOTE_PAGE_NAME;
    }

    /**
     * @param args TODO
     */
    protected String doHandleGet(UserSession us, String link, Map args)
    {
        return link;
    }

    /**
     *
     */
    protected String getInternalProcessingErrorPage(UserSession us)
    {
        ModelConfig config = us.getClient().getLanguageConfiguration();
        us.setHeading(config.getInformation());

        String msg1 = config.getInternalProcessingError();
        String msg2 = config.getContinueWithShowPhoto();
        if(us.getClient() instanceof User)
        {
            msg2 = config.getContinueWithShowUserHome();
        }

        us.setTwoLineMessage(msg1, msg2);

        return PartUtil.SHOW_NOTE_PAGE_NAME;
    }

    /**
     *
     */
    public final AccessRights getNeededRights()
    {
        return neededRights;
    }

    /**
     *
     */
    protected String getHeadingImageAsRelativeResourcePathString(Language lang)
    {
        String resName = lang.asIsoCode() + File.separator + "heading.png";
        return HtmlUtil.asPath(sysConfig.getStaticDir().getRelativeConfigFileName(resName));
    }

    /**
     *
     */
    protected String getResourceAsRelativeHtmlPathString(String resource)
    {
        return resource + ".html";
    }

}
