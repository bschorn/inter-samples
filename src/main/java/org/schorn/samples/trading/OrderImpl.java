/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples.trading;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author bschorn
 */
public class OrderImpl implements Trade.Order {

    private OrderImpl previous = null;
    private final Trade parentTrade;
    private final LocalDateTime timestamp;
    private final Identity id;
    private double qty;
    private double px;
    private boolean cancelled = false;
    private boolean paused = false;

    protected OrderImpl(Trade parentTrade, double qty, double px) {
        this.parentTrade = parentTrade;
        this.timestamp = LocalDateTime.now();
        this.id = Identity.create(Trade.Order.class);
    }

    @Override
    public Order modify(double qty, double px) {
        if (qty > this.qty && this.px == px) {
            this.qty = qty;
            return this;
        } else {
            this.cancel();
            OrderImpl orderImpl = new OrderImpl(this.parentTrade, qty, px);
            orderImpl.previous = this;
            return orderImpl;
        }
    }

    @Override
    public boolean cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean pause() {
        if (!this.paused) {
            this.paused = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean resume() {
        if (this.paused) {
            this.paused = false;
            return true;
        }
        return false;
    }

    @Override
    public double amt() {
        return this.qty * this.px;
    }

    @Override
    public double qty() {
        return this.qty;
    }

    @Override
    public double px() {
        return this.px;
    }

    @Override
    public Order previous() {
        return this.previous;
    }

    @Override
    public LocalDateTime timestamp() {
        return this.timestamp;
    }

    @Override
    public LocalDate date() {
        return this.parentTrade.date();
    }

    @Override
    public Identity id() {
        return this.id;
    }

    @Override
    public Identity.Account account() {
        return this.parentTrade.account();
    }

    @Override
    public Identity.Strategy strategy() {
        return this.parentTrade.strategy();
    }

    @Override
    public Identity.Broker broker() {
        return this.parentTrade.broker();
    }

    @Override
    public Identity.Instrument instrument() {
        return this.parentTrade.instrument();
    }

    @Override
    public Side side() {
        return this.parentTrade.side();
    }

    @Override
    public Identity tradeId() {
        return this.parentTrade.id();
    }

}
