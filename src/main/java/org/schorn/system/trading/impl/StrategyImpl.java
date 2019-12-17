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
public enum StrategyImpl implements Entity.Strategy {
    TRET,
    LCAP,
    SCAP,
    GROW,
    INCO,
    VALU,
    MNET;

    @Override
    public int id() {
        return this.ordinal();
    }

}
