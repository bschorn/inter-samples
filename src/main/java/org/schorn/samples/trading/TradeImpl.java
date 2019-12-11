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
public class TradeImpl implements Trade {

    private final LocalDateTime timestamp;
    private final LocalDate date;
    private final Identity id;
    private final Account account;
    private final Strategy strategy;
    private final Broker broker;
    private final Instrument instrument;
    private final Side side;
    private double amt;
    private double qty;
    private double px;

    protected TradeImpl(LocalDate date, Identity id, Account account, Strategy strategy, Broker broker, Instrument instrument, Side side) {
        this.timestamp = LocalDateTime.now();
        this.date = date;
        this.id = id;
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
    public Identity id() {
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
