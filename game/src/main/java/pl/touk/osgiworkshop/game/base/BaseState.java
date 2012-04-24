/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import pl.touk.osgiworkshop.game.base.behave.DoNothing;
import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.NotAllowedActionException;
import pl.touk.osgiworkshop.game.domain.Place;
import pl.touk.osgiworkshop.game.domain.State;
import pl.touk.osgiworkshop.game.base.behave.MoveToPlace;

import java.util.List;
import java.util.SortedSet;

/**
 * @author arkadius
 */
public abstract class BaseState extends State {

    protected BaseState(String id) {
        super(id);
    }

    @Override
    public List<String> getAvailableActions() {
        return Lists.newArrayList("idź", "wyjdź", "pomoc");
    }

    public Behaviour execute(String action, List<String> parameters) {
        if (Sets.newHashSet("idź", "przejdź", "pójdź").contains(action)) {
            return executeGo(parameters);
        }
        throw new NotAllowedActionException(action, parameters);
    }

    private Behaviour executeGo(List<String> parameters) {
        String locatorPrefix = null;
        if (parameters.size() > 0 && Sets.newHashSet("do", "w", "na").contains(parameters.get(0))) {
            locatorPrefix = parameters.get(0);
            parameters = parameters.subList(1, parameters.size());
        }
        if (parameters.size() == 0) {
            getGame().printErrLine("Nie podałeś miejsca gdzie chcesz iść!");
            return new DoNothing();
        } else {
            String place = Joiner.on(" ").join(parameters);
            for (Place available : getAvailablePlacesToGo()) {
                if (available.getName().matches(place)) {
                    return new MoveToPlace(available);
                }
            }
            getGame().printErrLine("Nie mogę stąd iść " + (locatorPrefix != null ? locatorPrefix : "do") + " " + place + "!");
            getGame().printOutLine("Dostępne miejsca to: " + Joiner.on(", ").join(getAvailablePlacesToGo()) + ".");
            return new DoNothing();
        }
    }

    public List<Place> getAvailablePlacesToGo() {
        return getGame().getAvailablePlacesToGoFrom(getGame().getCurrentPlaceOfPlayer());
    }
}
