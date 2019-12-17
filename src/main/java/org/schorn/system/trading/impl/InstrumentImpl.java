/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading.impl;

import org.schorn.system.trading.Entity;

/**
 *
 * @author bschorn
 */
public enum InstrumentImpl implements Entity.Instrument {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    I,
    J,
    K,
    L,
    M,
    N,
    O,
    P,
    Q,
    U,
    V,
    W,
    X,
    Y,
    Z;

    @Override
    public int id() {
        return this.ordinal();
    }

}
