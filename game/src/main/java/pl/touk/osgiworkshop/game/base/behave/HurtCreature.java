/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base.behave;

import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.Creature;
import pl.touk.osgiworkshop.game.domain.Game;

/**
 * @author arkadius
 */
public class HurtCreature implements Behaviour {
    private Creature creature;
    private int healthDiff;
    private String safeState;

    public HurtCreature(Creature creature, int healthDiff, String safeState) {
        this.creature = creature;
        this.healthDiff = healthDiff;
        this.safeState = safeState;
    }

    public void execute(Game game) {
        creature.decreaseHealth(healthDiff);
        if (creature.isAlive()) {
            game.printOutLine("Zabrałeś " + healthDiff + " pkt. życia wrogowi. Pozostało mu " + creature.getHealth() + " pkt. życia.");
        } else {
            game.printOutLine("Zabiłeś wroga.");
            game.changeState(safeState);
        }
    }
}
