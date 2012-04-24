/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import java.util.Collection;
import java.util.Collections;

/**
 * @author arkadius
 */
public class GameBuilder {
    private String playerName = "Gracz";
    private Collection<Place> places;
    private Collection<State> additionalStates = Collections.emptyList();
    private Collection<Creature> creatures = Collections.emptyList();
    private String initPlaceName;


    public Game createGame() {
        Game game = new Game();
        game.setPlayer(new Player(Name.valueOf(playerName, "gracz"), Config.getInstance().getInt("player.lifesCount")));
        for (Place place : places) {
            game.addState(place.getAssociatedState());
        }
        game.addStates(additionalStates);
        game.setInitPlaceName(initPlaceName);
        World world = new World();
        world.addPlaces(places);
        world.addCreatures(creatures);
        game.setWorld(world);
        return game;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public GameBuilder setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public Collection<Place> getPlaces() {
        return this.places;
    }

    public GameBuilder setPlaces(Collection<Place> places) {
        this.places = places;
        return this;
    }

    public Collection<State> getAdditionalStates() {
        return this.additionalStates;
    }

    public GameBuilder setAdditionalStates(Collection<State> additionalStates) {
        this.additionalStates = additionalStates;
        return this;
    }

    public Collection<Creature> getCreatures() {
        return this.creatures;
    }

    public GameBuilder setCreatures(Collection<Creature> creatures) {
        this.creatures = creatures;
        return this;
    }

    public String getInitPlaceName() {
        return this.initPlaceName;
    }

    public GameBuilder setInitPlaceName(String initPlaceName) {
        this.initPlaceName = initPlaceName;
        return this;
    }
}
