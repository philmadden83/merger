package com.mymo.merger.configuration;

import com.mymo.merger.exception.InstantiationException;

public interface ObjectFactory {

    <T> T getInstance(Class<T> tClass) throws InstantiationException;

}
