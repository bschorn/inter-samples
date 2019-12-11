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
public class FillImpl implements Trade.Fill {

    private final LocalDateTime timestamp;
    private final Identity id;
    private final Order order;
    private final double amt;
    private final double qty;
    private final double px;

    FillImpl(Order order, double amt, double qty, double px) {
        this.timestamp = LocalDateTime.now();
        this.id = Identity.create(Trade.Fill.class);
        this.order = order;
        this.amt = amt;
        this.qty = qty;
        this.px = px;
    }

    @Override
    public Identity orderId() {
        return this.order.id();
    }

    @Override
    public double ordQty() {
        return this.order.qty();
    }

    @Override
    public double ordPx() {
        return this.order.px();
    }

    @Override
    public double cumAmt() {
        return 0.0;
    }

    @Override
    public double cumQty() {
        return 0.0;
    }

    @Override
    public double avgPx() {
        return 0.0;
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

    @Override
    public LocalDateTime timestamp() {
        return this.timestamp;
    }

    @Override
    public LocalDate date() {
        return this.order.date();
    }

    @Override
    public Identity id() {
        return this.id;
    }

    @Override
    public Account account() {
        return this.order.account();
    }

    @Override
    public Strategy strategy() {
        return this.order.strategy();
    }

    @Override
    public Broker broker() {
        return this.order.broker();
    }

    @Override
    public Instrument instrument() {
        return this.order.instrument();
    }

    @Override
    public Side side() {
        return this.order.side();
    }

}
