/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

/**
 * @author arkadius
 */
public abstract class Weapon {
    private Name name;

    public Weapon(Name name) {
        this.name = name;
    }

    public abstract Behaviour use(Player player, Creature creature, Place place, String safeState);

    public Name getName() {
        return name;
    }
}
