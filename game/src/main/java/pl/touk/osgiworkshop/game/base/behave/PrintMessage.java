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
public class PrintMessage implements Behaviour {
    private String msg;

    public PrintMessage(String msg) {
        this.msg = msg;
    }

    public void execute(Game game) {
        game.printOutLine(msg);
    }
}
