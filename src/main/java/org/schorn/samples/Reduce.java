/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;

/**
 *
 * @author bschorn
 */
public class Reduce {

    static public class TradeImpl {
        public final String key;
        public final double amount;
        public final double price;
        public final double quantity;

        public TradeImpl(String key, double amount, double quantity, double price) {
            this.key = key;
            this.amount = amount;
            this.quantity = quantity;
            this.price = price;
        }
    }

    static public class TradeSummarizer {

        private String key;
        private double totAmount = 0.0;
        private double totGrossAmount = 0.0;
        private double avgPrice = 0.0;
        private double totQuantity = 0;

        public TradeSummarizer add(TradeImpl trade) {
            this.key = trade.key;
            this.totAmount += trade.amount;
            this.totQuantity += trade.quantity;
            this.totGrossAmount += trade.price * trade.quantity;
            this.avgPrice = this.totGrossAmount / this.totQuantity;
            return this;
        }
        public TradeSummarizer merge(TradeSummarizer other) {
            this.totAmount += other.totAmount;
            this.totQuantity += other.totQuantity;
            this.totGrossAmount += other.avgPrice * other.totQuantity;
            this.avgPrice = this.totGrossAmount / this.totQuantity;
            return this;
        }

        public TradeReport tradeReport() {
            return new TradeReport(this.key, this.totAmount, this.totGrossAmount, this.totQuantity, this.avgPrice);
        }
    }

    static public class TradeReport {
        private final String key;
        private final double totAmount;
        private final double totGrossAmount;
        private final double avgPrice;
        private final double totQuantity;

        public TradeReport(String key, double totAmount, double totGrossAmount, double totQuantity, double avgPrice) {
            this.key = key;
            this.totAmount = totAmount;
            this.totGrossAmount = totGrossAmount;
            this.totQuantity = totQuantity;
            this.avgPrice = avgPrice;
        }

        public String key() {
            return this.key;
        }

        public double totAmount() {
            return this.totAmount;
        }

        public double totGrossAmount() {
            return this.totGrossAmount;
        }

        public double avgPrice() {
            return this.avgPrice;
        }

        public double totQuantity() {
            return this.totQuantity;
        }

        @Override
        public String toString() {
            return String.format("%s: %.2f %.2f %d %.2f",
                    this.key,
                    this.totAmount,
                    this.totGrossAmount,
                    (long) this.totQuantity,
                    this.avgPrice);
        }
    }

    static public class TradeCollector implements Collector<TradeImpl, TradeSummarizer, TradeReport> {
        public static TradeCollector myTradeCollector() {
            return new TradeCollector();
        }
        @Override
        public Supplier<TradeSummarizer> supplier() {
            return () -> new TradeSummarizer();
        }

        @Override
        public BiConsumer<TradeSummarizer, TradeImpl> accumulator() {
            return TradeSummarizer::add;
        }

        @Override
        public BinaryOperator<TradeSummarizer> combiner() {
            return TradeSummarizer::merge;
        }

        @Override
        public Function<TradeSummarizer, TradeReport> finisher() {
            return TradeSummarizer::tradeReport;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return new HashSet<>();
        }
    }

    static public void main(String[] args) {
        TradeImpl[] trades = new TradeImpl[]{
            new TradeImpl("AAPL", 1000, 100, 10.01),
            new TradeImpl("AAPL", 1002, 100, 10.03),
            new TradeImpl("AAPL", 1004, 100, 10.05),
            new TradeImpl("AAPL", 1006, 100, 10.07),
            new TradeImpl("AAPL", 1008, 100, 10.09),
            new TradeImpl("AAPL", 1010, 100, 10.11),
            new TradeImpl("AAPL", 1012, 100, 10.13)
        };
        TradeReport report = Arrays.asList(trades).stream().collect(new TradeCollector());
        System.out.println(report.toString());
    }

    static public class GroupByKey implements Function<TradeImpl, String> {

        @Override
        public String apply(TradeImpl trade) {
            return trade.key;
        }

    }

    public Map<String, List<TradeImpl>> groupBy(Stream<TradeImpl> trades, GroupByKey groupByKey) {
        return trades.collect(groupingBy(trade -> groupByKey.apply(trade)));
    }

}
