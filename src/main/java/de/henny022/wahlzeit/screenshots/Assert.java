package de.henny022.wahlzeit.screenshots;

public class Assert
{
    private Assert()
    {
    }

    public void assertFalse(boolean condition, String message)
    {
        if(condition)
        {
            throw new RuntimeException(message);
        }
    }

    public void assertTrue(boolean condition, String message)
    {
        assertFalse(!condition, message);
    }

    public void assertNotNull(Object object, String message)
    {
        assertFalse(object == null, message);
    }
}
