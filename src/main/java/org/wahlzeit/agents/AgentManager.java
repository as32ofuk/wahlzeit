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

package org.wahlzeit.agents;

import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.services.LogBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The AgentManager singleton manages all Agent instances.
 */
public class AgentManager
{
    private static Logger log = Logger.getLogger(AgentManager.class.getName());

    protected PhotoManager photoManager;

    /**
     *
     */
    protected Map<String, Agent> agents;

    public AgentManager(PhotoManager photoManager, UserManager userManager)
    {
        this.photoManager = photoManager;
        this.agents = new HashMap<>();
        addAgent(new NotifyUsersAboutPraiseAgent(photoManager, userManager));
    }

    /**
     * @methodtype get
     */
    public Agent getAgent(String name)
    {
        return agents.get(name);
    }

    /**
     * @methodtype set
     */
    public void addAgent(Agent agent)
    {
        String name;
        synchronized(agent)
        {
            name = agent.getName();
            agents.put(name, agent);
        }
        log.config(LogBuilder.createSystemMessage().addMessage("agent added").addParameter("name", name).toString());
    }

    /**
     * @methodtype command
     */
    public void startAgent(String agentName) throws IllegalArgumentException
    {
        Agent agent = getAgent(agentName);
        if(agent != null)
        {
            agent.run();
        }
        else
        {
            throw new IllegalArgumentException("Unknown agent name: " + agentName);
        }
    }

}
