package com.mymo.merger;

import com.mymo.merger.exception.*;
import org.junit.*;

import java.math.*;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TestMergeNonPrimitives {

    private Merger merger;
    private NonPrimitivePojo source;
    private NonPrimitivePojo target;

    @Before
    public void setUp() {
        byte b = 0;
        short s = 4;
        source = new NonPrimitivePojo();
        source.setaBigDecimal(BigDecimal.ONE);
        source.setaDate(new Date());

        merger = new Merger(new ClasspathScanningMergeContext(TestConfiguration.class));
    }

    @Test
    public void testMergePrimitive() throws MergeException {
        target = merger.merge(source).into(target);

        assertThat(target.getaBigDecimal(), is(source.getaBigDecimal()));
        assertThat(target.getaDate(),       is(source.getaDate()));
    }

}
