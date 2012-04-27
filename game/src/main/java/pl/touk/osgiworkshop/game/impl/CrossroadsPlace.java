/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.impl;

import pl.touk.osgiworkshop.game.base.BasePlace;
import pl.touk.osgiworkshop.game.domain.Game;
import pl.touk.osgiworkshop.game.domain.Name;

import java.util.List;

/**
 * @author arkadius
 */
public class CrossroadsPlace extends BasePlace {
    private Game game;

    public CrossroadsPlace(final Name name, final String description) {
        super(name, description);
    }

    @Override
    public List<String> getAvailablePlacesToGo() {
        return game.getPlacesNames();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
