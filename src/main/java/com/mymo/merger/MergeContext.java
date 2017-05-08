package com.mymo.merger;

import com.mymo.merger.configuration.*;
import com.mymo.merger.exception.InstantiationException;
import com.mymo.merger.handler.*;

public interface MergeContext {
    MergeHandler getDefaultMergeHandler();
    <T> PojoDefinition getPojoDefinition(Class<T> tClass);
    <T> T newInstance(Class<T> tClass) throws InstantiationException;
}
