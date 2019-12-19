/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples.manager;

import java.util.ArrayList;
import java.util.List;
import org.schorn.system.trading.Entity;

/**
 *
 * @author bschorn
 */
public enum Groupings {
    STRATEGY(Entity.Strategy.class),
    ACCOUNT(Entity.Account.class),
    BROKER(Entity.Broker.class),
    ACCOUNT_BROKER(Entity.Account.class, Entity.Broker.class);

    List<Class<? extends Entity>> groups = new ArrayList<>();

    Groupings(Class<? extends Entity>... classes) {
        for (Class<? extends Entity> classFor : classes) {
            this.groups.add(classFor);
        }
    }
}
