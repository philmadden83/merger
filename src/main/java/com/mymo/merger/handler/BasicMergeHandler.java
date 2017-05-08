package com.mymo.merger.handler;

import com.mymo.merger.exception.MergeException;

/**
 * 
 * @author Phil Madden
 *
 */
public class BasicMergeHandler implements MergeHandler {

	@Override
	public <T> T getMergeValue(T newValue, T currentValue) throws MergeException {
		if (newValue != null) {
			return newValue;
		} else {
			return currentValue;
		}
	}

}
