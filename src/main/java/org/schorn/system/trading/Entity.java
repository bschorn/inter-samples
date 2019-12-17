/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.schorn.system.trading.Activity.Trade;
import org.schorn.system.trading.impl.AccountImpl;
import org.schorn.system.trading.impl.BrokerImpl;
import org.schorn.system.trading.impl.InstrumentImpl;
import org.schorn.system.trading.impl.StrategyImpl;

/**
 *
 * @author bschorn
 */
public interface Entity {

    public static Entity create(Class<? extends Trade> tradeClass) {
        return new Impl(tradeClass);
    }

    public int id();

    public String name();

    public interface Account extends Entity {
        static Account get(String name) {
            return AccountImpl.valueOf(name);
        }
    }

    public interface Strategy extends Entity {
        static Strategy get(String name) {
            return StrategyImpl.valueOf(name);
        }
    }

    public interface Broker extends Entity {
        static Broker get(String name) {
            return BrokerImpl.valueOf(name);
        }
    }

    public interface Instrument extends Entity {
        static Instrument get(String name) {
            return InstrumentImpl.valueOf(name);
        }

    }


    static class Impl implements Entity {

        static private final Map<Class<?>, AtomicInteger> IDS = new HashMap<>();

        static {
            IDS.put(Activity.Trade.class, new AtomicInteger(0));
            IDS.put(Activity.Order.class, new AtomicInteger(10000));
            IDS.put(Activity.Fill.class, new AtomicInteger(100000));
        }
        private final int id;
        private final String name;

        Impl(Class<?> className) {
            this.id = IDS.get(className).incrementAndGet();
            this.name = String.format("%s-%d", className.getSimpleName(), this.id);
        }

        @Override
        public int id() {
            return this.id;
        }

        @Override
        public String name() {
            return this.name;
        }
    }

}
