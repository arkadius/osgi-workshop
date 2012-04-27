/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base.behave;

import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.Game;

/**
 * @author arkadius
 */
public class AggregateBehaviour implements Behaviour {
    private Behaviour[] behaviours;

    public AggregateBehaviour(Behaviour... behaviours) {

        this.behaviours = behaviours;
    }

    public void execute(Game game) {
        for (Behaviour behaviour : behaviours) {
            behaviour.execute(game);
        }
    }
}
