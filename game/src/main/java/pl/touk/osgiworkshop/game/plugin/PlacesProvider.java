/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.plugin;

import pl.touk.osgiworkshop.game.domain.Place;

import java.util.Collection;

/**
 * @author arkadius
 */
public interface PlacesProvider {
    Collection<Place> getPlacesAvailableFromStart();
}
