/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base.behave;

import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.Game;
import pl.touk.osgiworkshop.game.domain.Weapon;

/**
 * @author arkadius
 */
public class UseWeapon implements Behaviour {
    private Weapon weapon;

    public UseWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void execute(Game game) {
        game.getPlayer().setCurrentWeapon(weapon);
    }
}
