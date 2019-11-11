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

package org.wahlzeit.model.clients;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.services.EmailAddress;


/**
 * An Administrator is a moderator with administration privileges.
 */
@Subclass(index = true)
public class Administrator extends Moderator
{
    public Administrator(PhotoManager photoManager, UserManager userManager, String userId, String emailAddress, String nickname, Client previousClient)
    {
        super(photoManager, userManager, userId, emailAddress, nickname, previousClient);
    }

    public Administrator(PhotoManager photoManager, UserManager userManager, String userId, String nickname, EmailAddress emailAddress, Client previousClient)
    {
        super(photoManager, userManager, userId, nickname, emailAddress, previousClient);
    }
}
