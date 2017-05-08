package com.mymo.merger.configuration;

import java.util.*;

/**
 * 
 * @author Phil Madden
 *
 */
public class PojoDefinition {
	private final List<PojoFieldDefinition> pojoFieldDefinitions = new ArrayList<>();

	public void addField(PojoFieldDefinition pojoFieldDefinition) {
        pojoFieldDefinitions.add(pojoFieldDefinition);
	}
	
	public List<PojoFieldDefinition> getPojoFieldDefinitions() {
		return pojoFieldDefinitions;
	}

	public Set<Class<?>> getComplexPojoFieldDefinitions() {
	    Set<Class<?>> complexPojoFieldDefinitions = new HashSet<>();
        for(PojoFieldDefinition pojoFieldDefinition : pojoFieldDefinitions) {
            if (pojoFieldDefinition.isComplex()) {
                complexPojoFieldDefinitions.add(pojoFieldDefinition.getType());
            }
        }

        return complexPojoFieldDefinitions;
    }

}
