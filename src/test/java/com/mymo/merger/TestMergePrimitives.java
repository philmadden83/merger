package com.mymo.merger;

import com.mymo.merger.exception.*;
import org.junit.*;

import java.math.*;
import java.sql.*;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestMergePrimitives {

    private Merger merger;
    private PrimitivePojo source;
    private PrimitivePojo target;

    @Before
    public void setUp() {
        byte b = 0;
        short s = 4;
        source = new PrimitivePojo();
        source.setAnInt(4);
        source.setaByte(b);
        source.setaBoolean(true);
        source.setaDouble(1.1D);
        source.setaFloat(2.2F);
        source.setaLong(3L);
        source.setaShort(s);
        source.setaString("a string");
        source.setaBigDecimal(BigDecimal.ONE);
        source.setaBigInteger(BigInteger.TEN);
        source.setaTimestamp(new Timestamp(System.currentTimeMillis()));
        source.setaTime(new Time(System.currentTimeMillis()));
        source.setaSqlDate(new java.sql.Date(System.currentTimeMillis()));
        source.setaDate(new Date(System.currentTimeMillis()));

        merger = new Merger(new ClasspathScanningMergeContext(TestConfiguration.class));
    }

    @Test
    public void testMergePrimitive() throws MergeException {
        target = merger.merge(source).into(target);

        assertThat(target.getaByte(),       is(source.getaByte()));
        assertThat(target.getaBoolean(),    is(source.getaBoolean()));
        assertThat(target.getaDouble(),     is(source.getaDouble()));
        assertThat(target.getaFloat(),      is(source.getaFloat()));
        assertThat(target.getaLong(),       is(source.getaLong()));
        assertThat(target.getAnInt(),       is(source.getAnInt()));
        assertThat(target.getaShort(),      is(source.getaShort()));
        assertThat(target.getaString(),     is(source.getaString()));
        assertThat(target.getaTime(),       is(source.getaTime()));
        assertThat(target.getaTimestamp(),  is(source.getaTimestamp()));
        assertThat(target.getaSqlDate(),    is(source.getaSqlDate()));
        assertThat(target.getaDate(),       is(source.getaDate()));
        assertThat(target.getaBigDecimal(), is(source.getaBigDecimal()));
        assertThat(target.getaBigInteger(), is(source.getaBigInteger()));
    }

    @Test
    public void testMergePrimitiveWithSomeNullValues() throws MergeException {
        source.setaString(null);
        source.setaDouble(null);
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
