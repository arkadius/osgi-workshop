/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import java.util.*;

/**
 * @author arkadius
 */
class World {
    private SortedMap<String, Place> places = new TreeMap<String, Place>();
    private SortedSet<Creature> creatures = new TreeSet<Creature>();

    // PLACES

    public boolean hasPlace(String name) {
        return getPlace(name) != null;
    }

    public Place getPlace(String name) {
        for (Place place : places.values()) {
            if (place.getName().matches(name)) {
                return place;
            }
        }
        return null;
    }

    public List<String> getPlacesNames() {
        List<String> names = new ArrayList<String>();
        for (Place place : places.values()) {
            names.add(place.getName().getValue());
        }
        return names;
    }

    public List<Place> getPlacesOfNames(Collection<String> names) {
        List<Place> result = new ArrayList<Place>();
        for (String name : names) {
            Place place = places.get(name);
            if (place != null) {
                result.add(place);
            }
        }
        return result;
    }

    // CREATURES

    public boolean hasCreature(String type, String name) {
        return getCreature(type, name) != null;
    }

    public Creature getCreature(String type, String name) {
        for (Creature creature : creatures) {
            if (creature.getType().matches(type) && creature.getName().matches(name)) {
                return creature;
            }
        }
        return null;
    }

    // MUTATORS / ACCESSORS

    void addPlaces(Collection<? extends Place> places) {
        for (Place place : places) {
            this.places.put(place.getName().getValue(), place);
        }
    }

    void addCreatures(Collection<? extends Creature> creatures) {
        this.creatures.addAll(creatures);
    }

    // SPRING

    public void setPlaces(Collection<? extends Place> places) {
        addPlaces(places);
    }

    public void setCreatures(SortedSet<Creature> creatures) {
        this.creatures = creatures;
    }

    public Collection<? extends Place> getPlaces() {
        return places.values();
    }

    // TODO: add methods to remove
}
