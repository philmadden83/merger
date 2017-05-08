package com.mymo.merger.configuration;

import java.lang.reflect.*;

public interface PojoFieldDefinitionFactory {

    PojoFieldDefinition getPojoFieldDefinition(Field field);

}
