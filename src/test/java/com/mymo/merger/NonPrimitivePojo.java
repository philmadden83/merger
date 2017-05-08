package com.mymo.merger;

import java.math.*;
import java.sql.*;
import java.util.Date;

public class NonPrimitivePojo {
    private Date aDate;
    private Timestamp aTimestamp;
    private BigDecimal aBigDecimal;

    public Date getaDate() {
        return aDate;
    }

    public void setaDate(Date aDate) {
        this.aDate = aDate;
    }

    public Timestamp getaTimestamp() {
        return aTimestamp;
    }

    public void setaTimestamp(Timestamp aTimestamp) {
        this.aTimestamp = aTimestamp;
    }

    public BigDecimal getaBigDecimal() {
        return aBigDecimal;
    }

    public void setaBigDecimal(BigDecimal aBigDecimal) {
        this.aBigDecimal = aBigDecimal;
    }
}
