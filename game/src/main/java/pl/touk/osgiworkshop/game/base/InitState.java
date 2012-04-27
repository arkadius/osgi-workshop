/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base;

import pl.touk.osgiworkshop.game.base.behave.DoNothing;
import pl.touk.osgiworkshop.game.base.behave.MoveToPlace;
import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.NotAllowedActionException;
import pl.touk.osgiworkshop.game.domain.State;

import java.util.Collections;
import java.util.List;

/**
 * @author arkadius
 */
public class InitState extends State {
    private String initPlaceName;

    public InitState(String initPlaceName) {
        super("init");
        this.initPlaceName = initPlaceName;
    }

    @Override
    public void introduce() {
        getGame().printOutLine("Witaj " + getGame().getPlayer().getName() + "!");
    }

    @Override
    public Behaviour execute(String action, List<String> parameters) throws NotAllowedActionException {
        return new MoveToPlace(initPlaceName);
    }

    @Override
    public List<String> getAvailableActions() {
        return Collections.emptyList();
    }
}
