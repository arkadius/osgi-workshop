/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

/**
 * @author arkadius
 */
public interface Behaviour {
    /**
     * Execute behaviour on game's state
     * @param game
     */
    void execute(Game game);
}
