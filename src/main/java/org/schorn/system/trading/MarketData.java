/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading;

import java.io.IOException;
import java.net.URI;
import org.schorn.system.trading.Entity.Instrument;
import org.schorn.system.trading.impl.MarketDataImpl;

/**
 *
 * @author bschorn
 */
public interface MarketData {

    static public MarketData instance() {
        return MarketDataImpl.instance;
    }

    public void connect(URI uri) throws IOException;

    public LevelOne register(Instrument instrument);

    public LevelOne getLevelOne(Instrument instrument);

    public void setBid(Instrument instrument, float px, float size);

    public void setAsk(Instrument instrument, float px, float size);

    public void setLast(Instrument instrument, float px, float qty);

    public interface LevelOne {

        public enum FIELDS {
            BID_PX,
            BID_SIZE,
            ASK_PX,
            ASK_SIZE,
            LAST_PX,
            LAST_QTY;
        }

        public int feedId();

        public Instrument instrument();

        public float bidPx();

        public float bidSize();

        public float askPx();

        public float askSize();

        public float lastPx();

        public float lastQty();
    }

}
