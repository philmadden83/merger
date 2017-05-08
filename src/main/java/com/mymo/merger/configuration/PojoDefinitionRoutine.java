package com.mymo.merger.configuration;

import java.util.Map;

/**
 * 
 * @author Phil Madden
 *
 */
public interface PojoDefinitionRoutine {

	Map<String, PojoDefinition> run(Class<?> configuration);
	void setEntityDefinitionFactory(Class<? extends PojoDefinitionFactory> entityDefinitionFactory);
	void setEntityNamingStrategy(Class<? extends PojoNamingStrategy> entityNamingStrategy);
}
