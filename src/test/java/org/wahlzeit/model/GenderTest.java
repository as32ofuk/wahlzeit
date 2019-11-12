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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the Gender class.
 */
public class GenderTest
{

    /**
     *
     */
    @Test
    public void testGetFromInt()
    {
        assertSame(Gender.getFromInt(Gender.UNDEFINED.asInt()), Gender.UNDEFINED);
        assertSame(Gender.getFromInt(Gender.MALE.asInt()), Gender.MALE);
        assertSame(Gender.getFromInt(Gender.FEMALE.asInt()), Gender.FEMALE);
        assertSame(Gender.getFromInt(Gender.OTHER.asInt()), Gender.OTHER);
    }

    /**
     *
     */
    @Test
    public void testGetFromString()
    {
        assertEquals(Gender.getFromString(Gender.UNDEFINED.asString()), Gender.UNDEFINED);
        assertEquals(Gender.getFromString(Gender.MALE.asString()), Gender.MALE);
        assertEquals(Gender.getFromString(Gender.FEMALE.asString()), Gender.FEMALE);
        assertEquals(Gender.getFromString(Gender.OTHER.asString()), Gender.OTHER);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativeValueShouldThrowException()
    {
        Gender.getFromInt(-1);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void tooBigValueShouldThrowException()
    {
        Gender.getFromInt(4);
    }
}
