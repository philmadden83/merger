package com.mymo.merger.configuration;

import com.mymo.merger.*;
import com.mymo.merger.handler.MergeHandler;
import com.mymo.merger.util.*;

import java.lang.reflect.*;
import java.util.logging.*;

public class DefaultPojoFieldDefinitionFactory implements PojoFieldDefinitionFactory, MergeContextAware {
    private static final Logger LOGGER = Logger.getLogger(DefaultPojoFieldDefinitionFactory.class.getCanonicalName());
    private MergeContext context;

    @Override
    public PojoFieldDefinition getPojoFieldDefinition(Field field) {
        try {
            boolean isComplexType = PojoFieldUtil.isComplexType(field.getType());
            Method accessor = field.getDeclaringClass().getDeclaredMethod(PojoFieldUtil.getAccessorMethodName(field));
            Method mutator = field.getDeclaringClass().getDeclaredMethod(PojoFieldUtil.getMutatorMethodName(field), field.getType());
            MergeHandler mergeHandler = getMergeHandler(field);

            return new PojoFieldDefinition(field, accessor, mutator, mergeHandler, isComplexType);

        } catch (NoSuchMethodException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }

        return null;
    }

    private MergeHandler getMergeHandler(Field field) {
        if(field.getDeclaringClass().isAnnotationPresent(com.mymo.merger.annotation.MergeHandler.class)) {
            return getMergeHandlerInstance(field.getDeclaringClass().getAnnotation(com.mymo.merger.annotation.MergeHandler.class));
        } else if (field.isAnnotationPresent(com.mymo.merger.annotation.MergeHandler.class)) {
            return getMergeHandlerInstance(field.getAnnotation(com.mymo.merger.annotation.MergeHandler.class));
        } else {
            return context.getDefaultMergeHandler();
        }
    }

    @Override
    public void setContext(MergeContext context) {
        this.context = context;
    }

    private MergeHandler getMergeHandlerInstance(com.mymo.merger.annotation.MergeHandler mergeHandler) {
        try {
            context.newInstance(mergeHandler.getClass());
        } catch (com.mymo.merger.exception.InstantiationException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
        return null;
    }
}
