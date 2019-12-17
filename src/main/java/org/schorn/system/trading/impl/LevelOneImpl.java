/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading.impl;

import org.schorn.system.trading.Entity.Instrument;
import org.schorn.system.trading.MarketData;

/**
 *
 * @author bschorn
 */
public class LevelOneImpl implements MarketData.LevelOne {

    private final MarketDataImpl marketData;
    private final Instrument instrument;
    private final int feedId;


    public LevelOneImpl(MarketDataImpl marketData, Instrument instrument, int feedId) {
        this.marketData = marketData;
        this.instrument = instrument;
        this.feedId = feedId;
    }

    @Override
    public int feedId() {
        return this.feedId;
    }

    @Override
    public Instrument instrument() {
        return this.instrument;
    }

    @Override
    public float bidPx() {
        return this.marketData.read(this.feedId + MarketData.LevelOne.FIELDS.BID_PX.ordinal() * 4);
    }

    @Override
    public float bidSize() {
        return this.marketData.read(this.feedId + MarketData.LevelOne.FIELDS.BID_SIZE.ordinal() * 4);
    }

    @Override
    public float askPx() {
        return this.marketData.read(this.feedId + MarketData.LevelOne.FIELDS.ASK_PX.ordinal() * 4);
    }

    @Override
    public float askSize() {
        return this.marketData.read(this.feedId + MarketData.LevelOne.FIELDS.ASK_SIZE.ordinal() * 4);
    }

    @Override
    public float lastPx() {
        return this.marketData.read(this.feedId + MarketData.LevelOne.FIELDS.LAST_PX.ordinal() * 4);
    }

    @Override
    public float lastQty() {
        return this.marketData.read(this.feedId + MarketData.LevelOne.FIELDS.LAST_QTY.ordinal() * 4);
    }

}
