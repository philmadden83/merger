package com.mymo.merger;

import java.util.Collections;
import java.util.Map;

import com.mymo.merger.configuration.PojoDefinition;

/**
 * 
 * @author Phil Madden
 *
 */
public class ClasspathScanningMergeContext extends AbstractMergeContext {

	public ClasspathScanningMergeContext(Class<?> configurationClass) {
		super(configurationClass);
	}

	@Override
	public Map<String, PojoDefinition> definePojos() {
		return Collections.EMPTY_MAP;
	}

}
