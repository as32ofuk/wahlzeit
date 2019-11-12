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
import org.wahlzeit.handlers.pages.WebPageHandler;
import org.wahlzeit.services.LogBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * A manager class for web parts.
 */
public class WebPartHandlerManager
{
    private static final Logger log = Logger.getLogger(WebPartHandler.class.getName());
    /**
     *
     */
    protected Map<String, WebPartHandler> handler;


    public WebPartHandlerManager()
    {
        handler = new HashMap<String, WebPartHandler>();
    }

    /**
     *
     */
    public WebPageHandler getWebPageHandler(String name)
    {
        return getWebPageHandlerFor(name);
    }

    /**
     *
     */
    public WebPageHandler getWebPageHandlerFor(String name)
    {
        WebPartHandler result = handler.get(name);
        if(result == null || !(result instanceof WebPageHandler))
        {
            result = getDefaultWebPageHandler();
        }

        return (WebPageHandler) result;
    }

    /**
     *
     */
    public WebPartHandler getDefaultWebPageHandler()
    {
        return handler.get("index");
    }

    /**
     *
     */
    public WebFormHandler getWebFormHandler(String name)
    {
        return getWebFormHandlerFor(name);
    }

    /**
     *
     */
    public WebFormHandler getWebFormHandlerFor(String name)
    {
        WebPartHandler result = handler.get(name);
        if(result == null || !(result instanceof WebFormHandler))
        {
            result = getDefaultWebPageHandler();
        }

        return (WebFormHandler) result;
    }

    public WebPartHandler addWebPartHandler(WebPartHandler handler, String... names)
    {
        for(String name : names)
        {
            addWebPartHandler(name, handler);
        }
        return handler;
    }

    public WebPartHandler addWebPartHandler(WebPartHandler handler, String name)
    {
        return addWebPartHandler(name, handler);
    }

    /**
     *
     */
    public WebPartHandler addWebPartHandler(String name, WebPartHandler myHandler)
    {
        handler.put(name, myHandler);
        log.config(LogBuilder.createSystemMessage().
                addAction("add WebPartHandler").
                addParameter("name", name).
                addParameter("handler", myHandler).toString());
        return myHandler;
    }

    /**
     *
     */
    public WebPartHandler getDefaultWebFormHandler()
    {
        return handler.get("null");
    }

}
