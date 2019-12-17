/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.schorn.system.trading.Activity.Order;
import org.schorn.system.trading.Activity.Trade;
import org.schorn.system.trading.Entity.Account;
import org.schorn.system.trading.Entity.Broker;
import org.schorn.system.trading.Entity.Instrument;
import org.schorn.system.trading.Entity.Strategy;
import org.schorn.system.trading.MarketData.LevelOne;

/**
 *
 * @author bschorn
 */
public class TradingSystem {

    //private final List<Trade> trades = new ArrayList<>();
    //private final List<Order> orders = new ArrayList<>();
    //private final List<Fill> fills = new ArrayList<>();

    private final LocalDate tradeDate;
    private final List<Account> accounts;
    private final List<Broker> brokers;
    private final List<Strategy> strategies;
    private final List<Instrument> instruments;

    public TradingSystem(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
        this.accounts = Arrays.asList(new String[]{"A000000", "A000001", "A000002"}).stream()
                .map(str -> Account.get(str))
                .collect(Collectors.toList());
        this.brokers = Arrays.asList(new String[]{"BAML", "GSCO", "JPMC", "MSCO"}).stream()
                .map(str -> Broker.get(str))
                .collect(Collectors.toList());
        this.strategies = Arrays.asList(new String[]{"TRET", "LCAP", "SCAP", "GROW", "INCO", "VALU", "MNET"}).stream()
                .map(str -> Strategy.get(str))
                .collect(Collectors.toList());
        this.instruments = Arrays.asList(new String[]{"A", "B", "C"}).stream()
                .map(str -> Instrument.get(str))
                .collect(Collectors.toList());
        Path marketDataFile = Path.of("C:/MarketData/", tradeDate.format(DateTimeFormatter.BASIC_ISO_DATE), "/taq.bin");

        try {
            for (Instrument instrument : this.instruments) {
                MarketData.instance().register(instrument);
            }
            MarketData.instance().connect(marketDataFile.toUri());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public Trade createTrade() {
        return Trade.create(this.tradeDate,
                getRandomAccount(),
                getRandomStrategy(),
                getRandomBroker(),
                getRandomInstrument(),
                getRandomSide());
    }

    public Order createOrder(Trade parentTrade) {
        return Order.create(parentTrade,
                getRandomQty(parentTrade),
                getRandomPx(parentTrade));
    }

    public Stream<Order> orders() {
        return null;
    }

    private Account getRandomAccount() {
        int idx = ThreadLocalRandom.current().nextInt(0, this.accounts.size() - 1);
        return this.accounts.get(idx);
    }

    private Broker getRandomBroker() {
        int idx = ThreadLocalRandom.current().nextInt(0, this.brokers.size() - 1);
        return this.brokers.get(idx);
    }

    private Strategy getRandomStrategy() {
        int idx = ThreadLocalRandom.current().nextInt(0, this.strategies.size() - 1);
        return this.strategies.get(idx);
    }

    private Instrument getRandomInstrument() {
        int idx = ThreadLocalRandom.current().nextInt(0, this.instruments.size() - 1);
        return this.instruments.get(idx);
    }

    private Trade.Side getRandomSide() {
        int idx = ThreadLocalRandom.current().nextInt(0, 1);
        return Trade.Side.values()[idx];
    }

    private double getRandomQty(Trade trade) {
        int mult = ThreadLocalRandom.current().nextInt(1, 10);
        return 100.0 * mult;
    }

    private double getRandomPx(Trade trade) {
        double mult = ThreadLocalRandom.current().nextDouble(0.01, 0.05);
        LevelOne md = MarketData.instance().getLevelOne(trade.instrument());
        switch (trade.side()) {
            case SELL:
            case SHORT:
                return md.bidPx();
            case BUY:
            case COVER:
            default:
                return md.askPx();
        }
    }
}
