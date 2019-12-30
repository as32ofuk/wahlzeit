package de.henny022.wahlzeit.screenshots.doc;

import java.lang.annotation.Repeatable;

@Repeatable(DesignPatterns.class)
public @interface DesignPattern
{
    String name();
    String[] participants();
}
