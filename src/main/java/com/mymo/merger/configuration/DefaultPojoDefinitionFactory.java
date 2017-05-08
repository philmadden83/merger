package com.mymo.merger.configuration;

import com.mymo.merger.*;
import com.mymo.merger.annotation.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;

public class DefaultPojoDefinitionFactory implements PojoDefinitionFactory, MergeContextAware {
    private static final Logger LOGGER = Logger.getLogger(DefaultPojoDefinitionFactory.class.getCanonicalName());
    private PojoFieldDefinitionFactory pojoFieldDefinitionFactory;

    @Override
    public PojoDefinition getPojoDefinition(Class<?> clazz) {
        final PojoDefinition pojoDefinition = new PojoDefinition();

        for (Field field : getMergeableFields(clazz)) {
            if (!field.isAnnotationPresent(Ignore.class)) {
                pojoDefinition.addField(pojoFieldDefinitionFactory.getPojoFieldDefinition(field));
            }
        }

        return pojoDefinition;
    }

    private static Set<Field> getMergeableFields(Class<?> pojoClass) {
        final Set<Field> mergeableFields = new HashSet<>();
        for (Field field : pojoClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Ignore.class)) {
                continue;
            }
            mergeableFields.add(field);
        }

        return  mergeableFields;
    }

    @Override
    public void setContext(MergeContext context) {
        try {
            this.pojoFieldDefinitionFactory = context.newInstance(DefaultPojoFieldDefinitionFactory.class);
        } catch (com.mymo.merger.exception.InstantiationException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
    }
}


