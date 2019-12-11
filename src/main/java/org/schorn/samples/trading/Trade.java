/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples.trading;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.schorn.samples.trading.Identity.Account;
import org.schorn.samples.trading.Identity.Broker;
import org.schorn.samples.trading.Identity.Instrument;
import org.schorn.samples.trading.Identity.Strategy;

/**
 *
 * @author bschorn
 */
public interface Trade {

    public enum LongShort {
        LONG,
        SHORT;
    }

    public enum Direction {
        OPEN,
        CLOSE;
    }

    public enum Side {
        BUY(LongShort.LONG, Direction.OPEN),
        SELL(LongShort.LONG, Direction.CLOSE),
        COVER(LongShort.SHORT, Direction.CLOSE),
        SHORT(LongShort.SHORT, Direction.OPEN);

        private final LongShort longShort;
        private final Direction direction;

        Side(LongShort longShort, Direction direction) {
            this.longShort = longShort;
            this.direction = direction;
        }

        public LongShort longShort() {
            return this.longShort;
        }

        public Direction direction() {
            return this.direction;
        }
    }

    public LocalDateTime timestamp();

    public LocalDate date();

    public Identity id();

    public Account account();

    public Strategy strategy();

    public Broker broker();

    public Instrument instrument();

    public Side side();

    public double amt();

    public double qty();

    public double px();

    public interface Order extends Trade {
        public Identity tradeId();

        public Order modify(double qty, double px);

        public boolean cancel();

        public boolean pause();

        public boolean resume();

        public Order previous();
    }

    public interface Fill extends Trade {
        public Identity orderId();

        public double ordQty();

        public double ordPx();

        public double cumAmt();

        public double cumQty();

        public double avgPx();

    }
}
