/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.*;

/**
 * @author arkadius
 */
public class Player extends Creature {
    private int lifes = 1;
    private Set<Weapon> weapons = new HashSet<Weapon>();
    private Place currentPlace;

    public Player(Name name, int lifes) {
        super(Name.valueOf("człowiek", "ludz", "ludź"), name);
        this.lifes = lifes;
    }

    /**
     * Takes one life
     * @return if player still is alive
     */
    public boolean takeOneLife() {
        return --lifes > 0 ;
    }

    public void moveToPlace(Place newPlace) {
        currentPlace = newPlace;
    }

    public int getLifesCount() {
        return lifes;
    }

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }


}