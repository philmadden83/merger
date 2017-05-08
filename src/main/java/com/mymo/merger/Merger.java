package com.mymo.merger;

import com.mymo.merger.annotation.*;
import com.mymo.merger.configuration.*;
import com.mymo.merger.exception.InstantiationException;
import com.mymo.merger.exception.MergeException;

/**
 *
 * @author Phil Madden
 *
 */
public class Merger {
	private final AbstractMergeContext context;
	private MergeBuilder mergeBuilder;

	public Merger(MergeContext context) {
		this.context = (AbstractMergeContext) context;
	}

	public <T> MergeBuilder merge(T t) {
		if (mergeBuilder == null) {
			mergeBuilder = new MergeBuilder(t);
		} else {
			//flyweight
			mergeBuilder.reset(t);
		}
		return mergeBuilder;
	}

	private <T> T merge(T source, T target) throws MergeException {
		if (source != null && !source.getClass().isAnnotationPresent(Ignore.class)) {
			if (target == null) {
				target = (T) context.newInstance(source.getClass());
			}
			for (PojoFieldDefinition pojoField : context.getPojoDefinition(source.getClass()).getPojoFieldDefinitions()) {
				merge(source, target, pojoField);
			}
		}
		return target;
	}

	private <T> void merge(T source, T target, PojoFieldDefinition pojoField) throws MergeException {
		if (source != null) {
			T sourceValue = pojoField.getValue(source);
			if (sourceValue != null) {
				try {
					T targetValue;
					if (pojoField.isComplex()) {
						if (pojoField.getValue(target) == null) {
							targetValue = pojoField.getValue(source);
						} else {
							targetValue = merge(pojoField.getValue(source), pojoField.getValue(target));
						}
					} else {
						targetValue = pojoField.getHandler().getMergeValue(pojoField.getValue(source), pojoField.getValue(target));
					}

					pojoField.setValue(target, targetValue);
				} catch (InstantiationException | IllegalArgumentException e) {
					throw new MergeException(e.getMessage(), e);
				}
			}
		}
	}

	public final class MergeBuilder {
		private Object t;

		public <T> MergeBuilder(T t) {
			this.t = t;
		}

		protected <T> void reset(T t) {
			this.t = t;
		}

		public final <T> T into(T t) throws MergeException  {
			return Merger.this.merge((T) this.t, t);
		}
	}

}
