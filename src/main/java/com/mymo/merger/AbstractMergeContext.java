package com.mymo.merger;

import java.lang.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.*;

import com.mymo.merger.annotation.Configuration;
import com.mymo.merger.annotation.Mergeable;
import com.mymo.merger.configuration.*;
import com.mymo.merger.exception.InstantiationException;
import com.mymo.merger.handler.*;

/**
 * 
 * @author Phil Madden
 *
 */
public abstract class AbstractMergeContext implements MergeContext {
    private static final Logger LOGGER = Logger.getLogger(AbstractMergeContext.class.getCanonicalName());
	protected static final Map<String, PojoDefinition> pojoDefinitionMap = new HashMap<>();
	protected static ObjectFactory objectFactory;
	protected static PojoNamingStrategy pojoNamingStrategy;
	protected static PojoDefinitionFactory pojoDefinitionFactory;

	private static Class<?> configuration;
	private static Configuration configurationAnnotation;
	private static MergeHandler defaultMergeHandler;

	public abstract Map<String, PojoDefinition> definePojos();

	protected AbstractMergeContext(Class<?> configurationClass) {
		if (!configurationClass.isAnnotationPresent(Configuration.class)) {
			throw new RuntimeException("Specified class must be annotated with @MergerConfiguration.");
		}

		init(configurationClass);
		defineExplicitlyDefinedMergeablePojo();
	}

	private void init(Class<?> configurationClass) {
		try {
			configuration = configurationClass;
			configurationAnnotation = configuration.getAnnotation(Configuration.class);
			objectFactory = configurationAnnotation.objectFactory().newInstance();

			defaultMergeHandler = newInstance(configurationAnnotation.defaultMergeHanger());
			pojoNamingStrategy = newInstance(configurationAnnotation.pojoNamingStrategy());
			pojoDefinitionFactory = newInstance(configurationAnnotation.pojoDefinitionFactory());
		} catch (com.mymo.merger.exception.InstantiationException | java.lang.InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
		}
	}

	private void defineExplicitlyDefinedMergeablePojo() {
		defineExplicitlyDefinedMergeablePojo(getExplicitEntities());
		pojoDefinitionMap.putAll(definePojos());
	}

	private void defineExplicitlyDefinedMergeablePojo(Set<Class<?>> entities) {
		for (Class<?> pojoClass : entities) {
			if (pojoDefinitionMap.containsKey(pojoNamingStrategy.getName(pojoClass))) {
				continue;
			}
			PojoDefinition pojoDefinition = pojoDefinitionFactory.getPojoDefinition(pojoClass);
			pojoDefinitionMap.put(pojoNamingStrategy.getName(pojoClass), pojoDefinition);
			defineExplicitlyDefinedMergeablePojo(pojoDefinition.getComplexPojoFieldDefinitions());
		}
	}

	private static Set<Class<?>> getExplicitEntities() {
		Set<Class<?>> pojoClasses = new HashSet<>();
		for (Field field : configuration.getDeclaredFields()) {
			if (field.isAnnotationPresent(Mergeable.class)) {
				pojoClasses.add(field.getType());
			}
		}

		return pojoClasses;
	}

	@Override
	public MergeHandler getDefaultMergeHandler() {
		return defaultMergeHandler;
	}

	@Override
	public final PojoDefinition getPojoDefinition(Class tClass) {
		PojoDefinition pojoDefinition = pojoDefinitionMap.get(pojoNamingStrategy.getName(tClass));

		if (pojoDefinition == null) {
			throw new RuntimeException("No Pojo Definition found for type: " + tClass.getCanonicalName());
		}

		return pojoDefinition;
	}

	@Override
	public final <T> T newInstance(Class<T> tClass) throws InstantiationException {
		T t = objectFactory.getInstance(tClass);
		if (MergeContextAware.class.isAssignableFrom(tClass)) {
			((MergeContextAware) t).setContext(this);
		}
		return t;
	}
}
