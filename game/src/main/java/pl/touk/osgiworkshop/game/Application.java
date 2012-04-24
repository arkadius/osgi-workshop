/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game;

import pl.touk.osgiworkshop.game.base.BasePlace;
import pl.touk.osgiworkshop.game.console.ConsoleInterface;
import pl.touk.osgiworkshop.game.domain.*;

import java.util.Collections;

/**
 * @author arkadius
 */
public class Application {

    public static void main(String[] args) {
        Place rozdroza = new BasePlace(Name.valueWithLocomotiveFormOf("Rozdroża", "na", "Rozdroża", "rozdroż"), "Jesteś na rozstaju dróg. Którędy podążysz?");
        rozdroza.setAvailablePlacesToGo("Rozdroża");
        Game game = new GameBuilder()
                .setPlayerName("Jan")
                .setPlaces(Collections.singleton(rozdroza))
                .setInitPlaceName("Rozdroża")
                .createGame();
        game.setIO(new ConsoleInterface());
        game.start();
    }
}
