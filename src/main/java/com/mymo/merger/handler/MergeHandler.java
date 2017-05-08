package com.mymo.merger.handler;

import com.mymo.merger.exception.MergeException;

/**
 * 
 * @author Phil Madden
 *
 */
public interface MergeHandler {
	<T> T getMergeValue(T newValue, T currentValue) throws MergeException;
}
