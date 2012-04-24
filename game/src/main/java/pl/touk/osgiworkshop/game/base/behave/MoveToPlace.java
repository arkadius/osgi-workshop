/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base.behave;

import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.Game;
import pl.touk.osgiworkshop.game.domain.Name;
import pl.touk.osgiworkshop.game.domain.Place;

/**
 * @author arkadius
 */
public class MoveToPlace implements Behaviour {
    private String targetPlaceName;

    public MoveToPlace(String targetPlaceName) {
        this.targetPlaceName = targetPlaceName;
    }

    public MoveToPlace(Place targetPlace) {
        this.targetPlaceName = targetPlace.getName().getValue();
    }

    public void execute(Game game) {
        game.movePlayerToPlace(targetPlaceName);
    }
}
