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
    private Collection<? extends Place> places = Collections.emptyList();
    private Collection<? extends State> additionalStates = Collections.emptyList();
    private Collection<? extends Creature> creatures = Collections.emptyList();
    private Collection<? extends Weapon> playerWeaponsOnStart = Collections.emptyList();
    private String initPlaceName;


    public Game createGame() {
        Game game = new Game();
        Player player = new Player(Name.valueOf(playerName, "gracz"), Config.getInstance().getInt("player.lifesCount"));
        for (Weapon weapon : playerWeaponsOnStart) {
            player.addWeapon(weapon);
        }
        game.setPlayer(player);
        game.addPlaces(places);
        game.addStates(additionalStates);
        game.addCreatures(creatures);
        game.setInitPlaceName(initPlaceName);
        return game;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public GameBuilder setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public Collection<? extends Place> getPlaces() {
        return this.places;
    }

    public GameBuilder setPlaces(Collection<? extends Place> places) {
        this.places = places;
        return this;
    }

    public Collection<? extends State> getAdditionalStates() {
        return this.additionalStates;
    }

    public GameBuilder setAdditionalStates(Collection<? extends State> additionalStates) {
        this.additionalStates = additionalStates;
        return this;
    }

    public Collection<? extends Creature> getCreatures() {
        return this.creatures;
    }

    public GameBuilder setCreatures(Collection<? extends Creature> creatures) {
        this.creatures = creatures;
        return this;
    }

    public Collection<? extends Weapon> getPlayerWeaponsOnStart() {
        return this.playerWeaponsOnStart;
    }

    public GameBuilder setPlayerWeaponsOnStart(Collection<? extends Weapon> playerWeaponsOnStart) {
        this.playerWeaponsOnStart = playerWeaponsOnStart;
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
