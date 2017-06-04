package com.github.javabada.baccarat.game;

import java.math.BigDecimal;

public enum WagerType {

    PLAYER ("1"),
    BANKER ("0.95"),
    TIE    ("8");

    private final BigDecimal odds;

    WagerType(String odds) {
        this.odds = new BigDecimal(odds);
    }

    public BigDecimal getOdds() {
        return odds;
    }

}
