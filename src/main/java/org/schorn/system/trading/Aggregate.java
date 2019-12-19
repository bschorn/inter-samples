/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading;

import java.util.function.Function;
import org.schorn.system.trading.impl.ActivityGroupImpl;

/**
 *
 * @author bschorn
 */
public interface Aggregate {

    interface GroupBy<K> {

        K getKey();
    }

    interface KeyExtractor<T, R> extends Function<T, R> {
        @Override
        R apply(T t);
    }

    interface ActivityGroupKeyExtractor<T extends Activity, K> extends KeyExtractor<T, K> {
        @Override
        K apply(T activity);
    }

    interface ActivityGroup extends GroupBy<String> {
        static public ActivityGroup create(Activity activity, ActivityGroupKeyExtractor keyExtractor) {
            return new ActivityGroupImpl(activity, keyExtractor);
        }
    }
}
