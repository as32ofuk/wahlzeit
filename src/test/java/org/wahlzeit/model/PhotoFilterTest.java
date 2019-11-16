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
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

public class PhotoFilterTest
{
    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    private PhotoFilter photoFilter;

    @Before
    public void initPhotoFilter()
    {
        UserManager userManager = new UserManager();
        PhotoFactory photoFactory = new PhotoFactory(userManager);
        GlobalsManager globalsManager = new GlobalsManager(userManager);
        ImageStorage imageStorage = new DatastoreAdapter();
        PhotoManager photoManager = new PhotoManager(photoFactory, userManager, globalsManager, imageStorage);
        photoFilter = new PhotoFilter(photoManager);
    }

    /**
     *
     */
    @Test
    public void testConstructor()
    {
        assertNotNull(photoFilter);

        // Check properties after creation
        assertEquals("", photoFilter.userName);
        assertEquals(Tags.EMPTY_TAGS, photoFilter.tags);

        assertEquals(0, photoFilter.processedPhotoIds.size());
        assertEquals(0, photoFilter.displayablePhotoIds.size());
    }

    /**
     *
     */
    @Test
    public void testStaticProperties()
    {
        assertEquals("userName", PhotoFilter.USER_NAME);
        assertEquals("tags", PhotoFilter.TAGS);
    }

    /**
     *
     */
    @Test
    public void testClear()
    {
        photoFilter.setUserName("test");
        assertEquals("test", photoFilter.getUserName());

        photoFilter.setTags(new Tags("test"));
        assertEquals("test", photoFilter.getTags().asString());

        photoFilter.displayablePhotoIds.add(new PhotoId(3));
        assertTrue(photoFilter.displayablePhotoIds.size() == 1);

        photoFilter.processedPhotoIds.add(new PhotoId(2));
        assertTrue(photoFilter.processedPhotoIds.size() == 1);

        photoFilter.clear();

        assertEquals("", photoFilter.getUserName());
        assertEquals(Tags.EMPTY_TAGS, photoFilter.getTags());
        assertTrue(photoFilter.displayablePhotoIds.isEmpty());
        assertTrue(photoFilter.processedPhotoIds.isEmpty());
    }

}
