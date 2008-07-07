/*
 */
package org.tagonist.beanutils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanArrayConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.ByteArrayConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.CharacterArrayConverter;
import org.apache.commons.beanutils.converters.CharacterConverter;
import org.apache.commons.beanutils.converters.ClassConverter;
import org.apache.commons.beanutils.converters.DoubleArrayConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FileConverter;
import org.apache.commons.beanutils.converters.FloatArrayConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongArrayConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortArrayConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.converters.StringArrayConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.beanutils.converters.URLConverter;
import org.tagonist.ActionContext;

/**
 * A much smarter BeanUtilsBean that traps errors instead of ignoring
 * them or throwing exceptions.
 * 
 * @author Jeff Schnitzer
 * @author Jon Stevens
 */
public class ErrorTrappingBeanUtilsBean extends BeanUtilsBean
{
	/** */
	public ErrorTrappingBeanUtilsBean()
	{
		// Must reregister everything so that we get exceptions
		
		ConvertUtilsBean cu = this.getConvertUtils();
		
        cu.register(new BigDecimalConverter(), BigDecimal.class);
        cu.register(new BigIntegerConverter(), BigInteger.class);
		cu.register(new BooleanConverter(), Boolean.TYPE);
		cu.register(new BooleanConverter(), Boolean.class);
		cu.register(new BooleanArrayConverter(), boolean[].class);
		cu.register(new ByteConverter(), Byte.TYPE);
		cu.register(new ByteConverter(), Byte.class);
		cu.register(new ByteArrayConverter(), byte[].class);
		cu.register(new CharacterConverter(), Character.TYPE);
		cu.register(new CharacterConverter(), Character.class);
		cu.register(new CharacterArrayConverter(), char[].class);
		cu.register(new ClassConverter(), Class.class);
		cu.register(new DoubleConverter(), Double.TYPE);
		cu.register(new DoubleConverter(), Double.class);
		cu.register(new DoubleArrayConverter(), double[].class);
		cu.register(new FloatConverter(), Float.TYPE);
		cu.register(new FloatConverter(), Float.class);
		cu.register(new FloatArrayConverter(), float[].class);
		cu.register(new IntegerConverter(), Integer.TYPE);
		cu.register(new IntegerConverter(), Integer.class);
		cu.register(new IntegerArrayConverter(), int[].class);
		cu.register(new LongConverter(), Long.TYPE);
		cu.register(new LongConverter(), Long.class);
		cu.register(new LongArrayConverter(), long[].class);
		cu.register(new ShortConverter(), Short.TYPE);
		cu.register(new ShortConverter(), Short.class);
		cu.register(new ShortArrayConverter(), short[].class);
		cu.register(new StringConverter(), String.class);
		cu.register(new StringArrayConverter(), String[].class);
		cu.register(new SqlDateConverter(), Date.class);
		cu.register(new SqlTimeConverter(), Time.class);
		cu.register(new SqlTimestampConverter(), Timestamp.class);
		cu.register(new FileConverter(), File.class);
		cu.register(new URLConverter(), URL.class);
	}
	
	/**
	 * Populates the bean from the properties Map, storing any errors
	 * in the errorSink.
	 */
    public void populate(Object bean, Map properties, ActionContext errorSink)
	{
		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null))
		{
			return;
		}
		
		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext())
		{

			// Identify the property name and value(s) to be assigned
			String name = (String)names.next();
			if (name == null)
				continue;

			Object value = properties.get(name);

			// Perform the assignment for this property
			try
			{
				this.setProperty(bean, name, value);
			}
			catch (ConversionException ex)
			{
				errorSink.setError(name, ex.getMessage());
			}
			catch (IllegalAccessException ex)
			{
				errorSink.setError(name, ex.getMessage());
			}
			catch (InvocationTargetException ex)
			{
				errorSink.setError(name, ex.getMessage());
			}
			catch (IllegalArgumentException ex)
			{
				errorSink.setError(name, ex.getMessage());
			}
			catch (StringIndexOutOfBoundsException ex)
			{
				errorSink.setError(name, ex.getMessage());
			}
		}
	}
}
