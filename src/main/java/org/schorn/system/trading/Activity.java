/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.schorn.system.trading.impl.FillImpl;
import org.schorn.system.trading.impl.OrderImpl;
import org.schorn.system.trading.impl.TradeImpl;

/**
 *
 * @author bschorn
 */
public interface Activity {

    public interface Trade extends Activity {

        static public Trade create(LocalDate date, Entity.Account account, Entity.Strategy strategy, Entity.Broker broker, Entity.Instrument instrument, Side side) {
            return new TradeImpl(date, account, strategy, broker, instrument, side);
        }

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

        public Entity id();

        public Entity.Account account();

        public Entity.Strategy strategy();

        public Entity.Broker broker();

        public Entity.Instrument instrument();

        public Side side();

        public double amt();

        public double qty();

        public double px();
    }

    public interface Order extends Trade {

        static public Order create(Trade parentTrade, double qty, double px) {
            return new OrderImpl(parentTrade, qty, px);
        }

        public Entity tradeId();

        public Order modify(double qty, double px);

        public boolean cancel();

        public boolean pause();

        public boolean resume();

        public Order previous();
    }

    public interface Fill extends Trade {

        static public Fill create(Order order, double amt, double qty, double px) {
            return new FillImpl(order, amt, qty, px);
        }

        public Entity orderId();

        public double ordQty();

        public double ordPx();

        public double cumAmt();

        public double cumQty();

        public double avgPx();

    }

}
