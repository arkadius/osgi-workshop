/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base;

import pl.touk.osgiworkshop.game.base.behave.HurtCreature;
import pl.touk.osgiworkshop.game.base.behave.PrintMessage;
import pl.touk.osgiworkshop.game.domain.*;

import java.util.Random;

/**
 * @author arkadius
 */
public class BaseWeapon extends Weapon {

    private double accuracy;
    private int hitStrength;

    public BaseWeapon(Name name, double accuracy, int hitStrength) {
        super(name);
        this.accuracy = accuracy;
        this.hitStrength = hitStrength;
    }

    @Override
    public Behaviour use(Player player, Creature creature, Place place, String safeState) {
        double computedAccuracy = player.getAccuracy() * accuracy;
        int computedHitStrength = ((int) Math.ceil(player.getStrength() * hitStrength)) / 5 * 5;
        if (new Random().nextDouble() < computedAccuracy) {
            return new HurtCreature(creature, computedHitStrength, safeState);
        } else {
            return new PrintMessage("Nie trafiłeś przeciwnika.");
        }
    }
}
