/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.schorn.system.trading.Activity.Trade;
import org.schorn.system.trading.Entity;
import org.schorn.system.trading.Entity.Account;
import org.schorn.system.trading.Entity.Broker;
import org.schorn.system.trading.Entity.Instrument;
import org.schorn.system.trading.Entity.Strategy;

/**
 *
 * @author bschorn
 */
public class TradeImpl implements Trade {

    private final LocalDateTime timestamp;
    private final LocalDate date;
    private final Entity id;
    private final Account account;
    private final Strategy strategy;
    private final Broker broker;
    private final Instrument instrument;
    private final Side side;

    private double amt;
    private double qty;
    private double px;

    public TradeImpl(LocalDate date, Account account, Strategy strategy, Broker broker, Instrument instrument, Side side) {
        this.timestamp = LocalDateTime.now();
        this.date = date;
        this.id = Entity.create(Trade.class);
        this.account = account;
        this.strategy = strategy;
        this.broker = broker;
        this.instrument = instrument;
        this.side = side;
    }

    @Override
    public LocalDateTime timestamp() {
        return this.timestamp;
    }

    @Override
    public LocalDate date() {
        return this.date;
    }

    @Override
    public Account account() {
        return this.account;
    }

    @Override
    public Strategy strategy() {
        return this.strategy;
    }

    @Override
    public Broker broker() {
        return this.broker;
    }

    @Override
    public Instrument instrument() {
        return this.instrument;
    }

    @Override
    public Side side() {
        return this.side;
    }

    @Override
    public Entity id() {
        return this.id;
    }

    @Override
    public double amt() {
        return this.amt;
    }

    @Override
    public double qty() {
        return this.qty;
    }

    @Override
    public double px() {
        return this.px;
    }
}
