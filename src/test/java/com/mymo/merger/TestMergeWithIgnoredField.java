package com.mymo.merger;

import com.mymo.merger.exception.*;
import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TestMergeWithIgnoredField {

    private Merger merger;
    private PrimitivePojoWithIgnoredField source;
    private PrimitivePojoWithIgnoredField target;

    @Before
    public void setUp() {
        byte b = 0;
        short s = 4;
        source = new PrimitivePojoWithIgnoredField();
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

        assertThat(target.getaByte(),       is(source.getaByte()));
        assertThat(target.getaBoolean(),    is(source.getaBoolean()));
        assertThat(target.getaFloat(),      is(source.getaFloat()));
        assertThat(target.getaLong(),       is(source.getaLong()));
        assertThat(target.getAnInt(),       is(source.getAnInt()));
        assertThat(target.getaShort(),      is(source.getaShort()));

        assertNull(target.getaDouble());
        assertNull(target.getaString());
    }

}
