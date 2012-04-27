/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author arkadius
 */
public class Place implements Comparable<Place> {
    private Name name;
    private String description;
    private State associatedState;
    private List<String> availablePlacesToGo = Collections.emptyList();

    public Place(Name name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setAssociatedState(State associatedState) {
        this.associatedState = associatedState;
    }

    public void setAvailablePlacesToGo(String... placesToGo) {
        this.availablePlacesToGo = Arrays.asList(placesToGo);
    }

    public Name getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public State getAssociatedState() {
        return associatedState;
    }

    public List<String> getAvailablePlacesToGo() {
        return availablePlacesToGo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Place other = (Place) obj;
        return Objects.equal(this.name, other.name);
    }


    public int compareTo(Place that) {
        return ComparisonChain.start().
                compare(this.getName(), that.getName())
                .result();
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
