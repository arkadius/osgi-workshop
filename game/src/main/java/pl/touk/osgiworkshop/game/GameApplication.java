/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game;

import pl.touk.osgiworkshop.game.base.BasePlace;
import pl.touk.osgiworkshop.game.base.BaseWeapon;
import pl.touk.osgiworkshop.game.console.ConsoleInterface;
import pl.touk.osgiworkshop.game.domain.*;

import java.util.Collections;
import java.util.List;

/**
 * @author arkadius
 */
public class GameApplication {

//    private ServiceTracker<Plugin,Plugin> tracker;

    public static void main(String[] args) {
        Game game = createGame();
        game.start();
    }

    private static Game createGame() {
        final Game game = new GameBuilder()
                .setPlayerName("Jan")
                .setInitPlaceName("Rozdroża")
                .setPlayerWeaponsOnStart(Collections.singleton(new BaseWeapon(Name.valueOf("sztylet", "noz"), Config.getInstance().getDouble("weapons.sztylet.accuracy"), Config.getInstance().getInt("weapons.sztylet.strength"))))
                .createGame();

        game.setIO(new ConsoleInterface());

        Place rozdroza = new BasePlace(Name.valueWithLocomotiveFormOf("Rozdroża", "na", "Rozdroża", "rozdroz"), "Jesteś na rozstaju dróg. Którędy podążysz?") {
            @Override
            public List<String> getAvailablePlacesToGo() {
                return game.getPlacesNames();
            }
        };
        game.addPlaces(Collections.singleton(rozdroza));
        return game;
    }

//    public void start(BundleContext bundleContext) throws Exception {
//        final Game game = createGame();
//        tracker = new ServiceTracker<Plugin, Plugin>(bundleContext, Plugin.class, new PluginManager(game, bundleContext));
//        tracker.open();
//        new Thread() {
//            @Override
//            public void run() {
//                game.start();
//                System.exit(0);
//            }
//        }.start();
//    }

//    public void stop(BundleContext bundleContext) throws Exception {
//        tracker.close();
//    }

}
