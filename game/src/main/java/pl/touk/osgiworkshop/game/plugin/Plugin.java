/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.plugin;

import pl.touk.osgiworkshop.game.domain.Creature;
import pl.touk.osgiworkshop.game.domain.Place;
import pl.touk.osgiworkshop.game.domain.State;

import java.util.Collection;

/**
 * @author arkadius
 */
public interface Plugin {
    String getName();
    Collection<Place> getPlaces();
    Collection<State> getAdditionalStates();
    Collection<Creature> getCreatures();

}
