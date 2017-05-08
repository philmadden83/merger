package com.mymo.merger.configuration;

import com.mymo.merger.exception.*;
import com.mymo.merger.handler.*;

import java.lang.reflect.*;

public class PojoFieldDefinition {
    private final Field field;
    private final String fieldName;
    private final Method accessor;
    private final Method mutator;
    private final boolean complex;
    private final MergeHandler mergeHandler;

    public PojoFieldDefinition(Field field, Method accessor, Method mutator, MergeHandler mergeHandler, boolean complex) {
        this.field = field;
        this.accessor = accessor;
        this.mutator = mutator;
        this.mergeHandler = mergeHandler;
        this.complex = complex;
        this.fieldName = field.getName();
    }

    public String getFieldName() {
        return fieldName;
    }

    public MergeHandler getHandler() {
        return mergeHandler;
    }

    public <T> T getValue(T entity) throws MergeException {
        try {
            return (T) accessor.invoke(entity);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new MergeException(e.getMessage(), e);
        }
    }

    public <T> void setValue(T entity, Object... values) throws MergeException {
        try {
            mutator.invoke(entity, values);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new MergeException(e.getMessage(), e);
        }
    }

    public Class getType() {
        return field.getType();
    }

    public boolean isComplex() {
        return complex;
    }

}