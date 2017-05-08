package com.mymo.merger;

import com.mymo.merger.exception.*;
import org.junit.*;

import static org.junit.Assert.*;

public class TestMergeWithGlobalIgnore {

    private Merger merger;
    private PrimitivePojoWithGlobalIgnore source;
    private PrimitivePojoWithGlobalIgnore target;

    @Before
    public void setUp() {
        byte b = 0;
        short s = 4;
        source = new PrimitivePojoWithGlobalIgnore();
        source.setAnInt(4);
        source.setaByte(b);
        source.setaBoolean(true);
        source.setaDouble(1.1D);
        source.setaFloat(2.2F);
        source.setaLong(3L);
        source.setaShort(s);
        source.setaString("a string");

        merger = new Merger(new ClasspathScanningMergeContext(TestConfiguration.class));
    }

    @Test
    public void testMergePrimitive() throws MergeException {
        target = merger.merge(source).into(target);

        assertNull(target);
    }

}
