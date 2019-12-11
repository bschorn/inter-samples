/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples.trading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author bschorn
 */
public interface Identity {

    public static Identity create(Class<? extends Trade> tradeClass) {
        return new Impl(tradeClass);
    }

    public int id();

    public String name();

    public interface Account extends Identity {

    }

    public interface Strategy extends Identity {

    }

    public interface Broker extends Identity {

    }

    public interface Instrument extends Identity {

    }

    static class Impl implements Identity {

        static private final Map<Class<?>, AtomicInteger> IDS = new HashMap<>();

        static {
            IDS.put(Trade.class, new AtomicInteger(0));
            IDS.put(Trade.Order.class, new AtomicInteger(10000));
            IDS.put(Trade.Fill.class, new AtomicInteger(100000));
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
