/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import static java.util.stream.Collectors.groupingBy;
import org.schorn.system.trading.Activity;
import org.schorn.system.trading.Aggregate;
import org.schorn.system.trading.Aggregate.ActivityGroup;

/**
 *
 * @author bschorn
 */
public class TradeManager implements Consumer<Activity.Trade> {

    private final Map<Groupings, Aggregate.ActivityGroupKeyExtractor<Activity.Trade, String>> keyExtractors;
    private final List<ActivityGroup> strategyTrades = new ArrayList<>();
    private Map<String, List<ActivityGroup>> tradesByStrategy;


    TradeManager() {
        this.keyExtractors = new HashMap<>();
        this.keyExtractors.put(Groupings.STRATEGY, t -> t.strategy().toString());
        this.keyExtractors.put(Groupings.ACCOUNT, t -> t.account().toString());
        this.keyExtractors.put(Groupings.BROKER, t -> t.broker().toString());
        this.keyExtractors.put(Groupings.ACCOUNT_BROKER, t -> String.format("%s:%s", t.account().toString(), t.broker().toString()));
    }

    @Override
    public void accept(Activity.Trade trade) {
        ActivityGroup strategyTrade = ActivityGroup.create(trade, this.keyExtractors.get(Groupings.STRATEGY));
        strategyTrades.add(strategyTrade);
        this.tradesByStrategy = strategyTrades.stream()
                .collect(groupingBy(ActivityGroup::getKey));
    }


}
