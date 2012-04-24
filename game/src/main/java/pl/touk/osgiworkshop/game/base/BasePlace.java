/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base;

import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.Name;
import pl.touk.osgiworkshop.game.domain.NotAllowedActionException;
import pl.touk.osgiworkshop.game.domain.Place;

import java.util.*;

/**
 * @author arkadius
 */
public class BasePlace extends Place {
    public BasePlace(final Name name, final String description) {
        super(name, description);
        setAssociatedState(new BaseState("wejście " + name.getLocomotive()) {
            @Override
            public void introduce() {
                getGame().printOutLine("Wszedłeś " + name.getLocomotive() + ".");
                getGame().printOutLine(description);
            }

            @Override
            public List<String> getAvailableActions() {
                List<String> list = new ArrayList<String>();
                list.addAll(super.getAvailableActions());
                list.addAll(getAvailableActionsInternal());
                return list;
            }

            @Override
            public Behaviour execute(String action, List<String> parameters) throws NotAllowedActionException {
                Behaviour behaviour = executeInternal(action, parameters);
                return behaviour != null ? behaviour : super.execute(action, parameters);
            }
        });
    }

    public List<String> getAvailableActionsInternal() {return Collections.emptyList();}

    public Behaviour executeInternal(String action, List<String> parameters) {return null;}

}
