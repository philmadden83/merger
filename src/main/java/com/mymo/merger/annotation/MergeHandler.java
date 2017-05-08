package com.mymo.merger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mymo.merger.handler.*;

/**
 * 
 * @author Phil Madden
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MergeHandler {

	Class<? extends com.mymo.merger.handler.MergeHandler> value() default BasicMergeHandler.class;

}
