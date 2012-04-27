/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.base;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.w3c.dom.stylesheets.LinkStyle;
import pl.touk.osgiworkshop.game.base.behave.DoNothing;
import pl.touk.osgiworkshop.game.domain.Behaviour;
import pl.touk.osgiworkshop.game.domain.NotAllowedActionException;
import pl.touk.osgiworkshop.game.domain.Place;
import pl.touk.osgiworkshop.game.domain.State;
import pl.touk.osgiworkshop.game.base.behave.MoveToPlace;

import java.util.ArrayList;
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
        return Lists.newArrayList("idź", "gdzie iść");
    }

    public Behaviour execute(String action, List<String> parameters) {
        if (Sets.newHashSet("idz", "przejdz", "pojdz").contains(action)) {
            return executeGo(parameters);
        } else if (Sets.newHashSet("gdzie", "dokad").contains(action)) {
            return executeWhere(parameters);
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
        } else {
            String place = Joiner.on(" ").join(parameters);
            for (Place available : getAvailablePlacesToGo()) {
                if (available.getName().matches(place)) {
                    return new MoveToPlace(available);
                }
            }
            getGame().printErrLine("Nie mogę stąd iść " + (locatorPrefix != null ? locatorPrefix : "do") + " " + place + "!");
            getGame().printOutLine("Dostępne miejsca to: " + Joiner.on(", ").join(getAvailablePlacesToGo()) + ".");
        }
        return new DoNothing();
    }

    private Behaviour executeWhere(List<String> parameters) {
        if (parameters.size() > 0 && Sets.newHashSet("moge", "moglbym", "moglabym").contains(parameters.get(0))) {
            parameters = parameters.subList(1, parameters.size());
        }        
        if (parameters.size() == 0) {
            getGame().printErrLine("O co pytasz pytasz wylewny człowieku?");
        } else {
            String verb = parameters.get(0);
            if (!Sets.newHashSet("isc", "przejsc", "pojsc").contains(verb)) {
                getGame().printErrLine("Nie rozumiem sformułowania: " + verb + ".");
            } else {
                List<Place> places = getGame().getAvailablePlacesToGoFrom(getGame().getCurrentPlaceOfPlayer());
                List<String> names = new ArrayList<String>(places.size());
                for (Place place : places) {
                    names.add(place.getName().getValue());
                }
                getGame().printOutLine("Dostępne miejsca to: " + Joiner.on(", ").join(names) + ".");
            }
        }
        return new DoNothing();
    }

    private List<Place> getAvailablePlacesToGo() {
        return getGame().getAvailablePlacesToGoFrom(getGame().getCurrentPlaceOfPlayer());
    }
}
