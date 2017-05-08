package com.mymo.merger.configuration;

import java.beans.Introspector;

public class DefaultPojoNamingStrategy implements PojoNamingStrategy {

    @Override
    public String getName(Class<?> pojoClass) {
        return Introspector.decapitalize(pojoClass.getSimpleName());
    }

}
