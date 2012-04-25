/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import pl.touk.osgiworkshop.game.base.BasePlace;
import pl.touk.osgiworkshop.game.console.ConsoleInterface;
import pl.touk.osgiworkshop.game.domain.*;

import java.util.Collections;

/**
 * @author arkadius
 */
public class GameApplication implements BundleActivator {

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

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("dupa");
        GameApplication.main(new String[0]);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
