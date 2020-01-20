package de.henny022.wahlzeit.screenshots;

public class Assert
{
    private Assert()
    {
    }

    public static void assertFalse(boolean condition, String message)
    {
        if(condition)
        {
            throw new RuntimeException(message);
        }
    }

    public static void assertTrue(boolean condition, String message)
    {
        assertFalse(!condition, message);
    }

    public static void assertNotNull(Object object, String message)
    {
        assertFalse(object == null, message);
    }

    public static void assertNull(Object object, String message)
    {
        assertTrue(object == null, message);
    }
}
