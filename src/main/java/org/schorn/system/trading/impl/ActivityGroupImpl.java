/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading.impl;

import org.schorn.system.trading.Activity;
import org.schorn.system.trading.Aggregate.ActivityGroup;
import org.schorn.system.trading.Aggregate.ActivityGroupKeyExtractor;

/**
 *
 * @author bschorn
 */
public class ActivityGroupImpl implements ActivityGroup {

    private final ActivityGroupKeyExtractor<Activity, String> keyExtractor;
    private final Activity activity;

    public ActivityGroupImpl(Activity activity, ActivityGroupKeyExtractor keyExtractor) {
        this.activity = activity;
        this.keyExtractor = keyExtractor;
    }

    @Override
    public String getKey() {
        return this.keyExtractor.apply(this.activity);
    }

}
