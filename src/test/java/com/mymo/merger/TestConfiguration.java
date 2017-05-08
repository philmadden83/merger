package com.mymo.merger;

import com.mymo.merger.annotation.*;
import com.mymo.merger.fixtures.*;

@Configuration
public class TestConfiguration {

    @Mergeable
    private PrimitivePojo primitivePojo;
    @Mergeable
    private NonPrimitivePojo nonPrimitivePojo;
    @Mergeable
    private PrimitivePojoWithIgnoredField primitivePojoWithIgnoredField;
    @Mergeable
    private PrimitivePojoWithGlobalIgnore primitivePojoWithGlobalIgnore;

    @Mergeable
    private Order order;

}
