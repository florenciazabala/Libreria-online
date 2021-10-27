package com.egg.library.util;

import java.math.BigDecimal;

public enum Genre {
    NOVELA("Novela", "#A3D2CA", new BigDecimal(0.0)),
    CUENTO("Cuento","#CEE5D0",new BigDecimal(0.0)),
    CIENCIA_FICCION("Ciencia ficción","#FFB085",new BigDecimal(0.0)),
    POESIA("Poesia","#FFE699",new BigDecimal(0.0)),
    HISTORIA("Historia","#FCCBCB",new BigDecimal(0.0)),
    ARTE("Arte","#C3AED6",new BigDecimal(0.0));

    private String valueFormat;
    private  String color;
    private BigDecimal percent;

    Genre(String valueFormat, String color, BigDecimal percent) {
        this.valueFormat = valueFormat;
        this.color = color;
        this.percent = percent;
    }

    public String getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(String valueFormat) {
        this.valueFormat = valueFormat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
