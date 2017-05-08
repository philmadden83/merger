package com.mymo.merger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mymo.merger.configuration.*;
import com.mymo.merger.handler.*;

/**
 * 
 * @author Phil Madden
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
	Class<? extends com.mymo.merger.handler.MergeHandler> defaultMergeHanger() default BasicMergeHandler.class;
	Class<? extends ObjectFactory> objectFactory() default DefaultObjectFactory.class;
	Class<? extends PojoNamingStrategy> pojoNamingStrategy() default DefaultPojoNamingStrategy.class;
	Class<? extends PojoDefinitionFactory> pojoDefinitionFactory() default DefaultPojoDefinitionFactory.class;
}
