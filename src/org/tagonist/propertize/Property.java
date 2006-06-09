/*
 * 
 */
package org.tagonist.propertize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation that will be recognized by the PropertizeTask.  When
 * placed on a field, a public getter and setter will be created by
 * bytecode manipulation.  If either method already exists, it is
 * left intact.
 * 
 * @author Jeff Schnitzer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property
{
	/** Create a get method if one does not exist */
	boolean get() default true;
	
	/** Create a set method if one doesnot exist */
	boolean set() default true;
}
