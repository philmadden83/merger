package com.mymo.merger.configuration;

/**
 * 
 * @author Phil Madden
 *
 */
public interface PojoDefinitionFactory {

	PojoDefinition getPojoDefinition(Class<?> entityClass);
	
}
