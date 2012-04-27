/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base.behave;

import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.Game;
import pl.touk.osgiworkshop.game.domain.Player;

/**
 * @author arkadius
 */
public class HurtPlayer implements Behaviour {

    private int healthDiff;

    public HurtPlayer(int healthDiff) {
        this.healthDiff = healthDiff;
    }

    public void execute(Game game) {
        if (!game.getCurrentCreature().isAlive())
            return;
        Player player = game.getPlayer();
        player.decreaseHealth(healthDiff);
        if (player.isAlive()) {
            game.printOutLine("Oberwałeś - straciłeś " + healthDiff + " pkt. życia. Pozostało ci " + player.getHealth() + " pkt. życia.");
        } else {
            if (player.getLifesCount() > 0) {
                game.printOutLine("Zginąłeś. Pozostały ci " + player.getLifesCount() + " życia.");
                game.respawn();
            } else {
                game.printOutLine("Zginąłeś. Spróbuj zagrać od nowa.");
                game.endTheGame();
            }
        }
    }
}
