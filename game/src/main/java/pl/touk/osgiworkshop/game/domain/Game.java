/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import pl.touk.osgiworkshop.game.base.InitState;
import pl.touk.osgiworkshop.game.base.behave.DoNothing;
import pl.touk.osgiworkshop.game.base.behave.EndTheGame;
import pl.touk.osgiworkshop.game.base.behave.MoveToPlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author arkadius
 */
public class Game {
    private Map<String, State> states = new HashMap<String, State>();
    private State currentState;
    private World world;
    private Player player;
    private Creature currentCreature;
    private Interface io;
    private boolean gameEnd;

    public void start() {
        currentState.introduce();
        Behaviour behaviour = currentState.execute(null, null); // what is first step in game?
        behaviour.execute(this);
        while (!gameEnd) {
            behaviour = readAction();
            behaviour.execute(this);
        }
        printOutLine("KONIEC GRY");
    }

    private Behaviour readAction() {
        io.printOut("> ");
        String line = readLine();
        String[] arr = line.toLowerCase().split("\\s+");
        if (arr.length == 0 || arr.length == 1 && arr[0].isEmpty()) {
            printErrLine("Nie podałeś akcji!");
            showAvailableActions();
        } else if (arr[0].startsWith("pomoc")) {
            printErrLine("Wołasz o pomoc głupcze?!");
            showAvailableActions();
        } else if (Sets.newHashSet("wyjdź", "wyjście", "koniec").contains(arr[0])) {
            return new EndTheGame();
        } else {
            try {
                return currentState.execute(arr[0], Arrays.asList(arr).subList(1, arr.length));
            } catch (NotAllowedActionException ex) {
                printOutLine(ex.getMessage());
                showAvailableActions();
            }
        }
        return new DoNothing();
    }

    private void showAvailableActions() {
        printOutLine("Dostępne akcje to: " + Joiner.on(", ").join(currentState.getAvailableActions()) + ".");
    }

    public Name getPlayersName() {
        return player.getName();
    }

    public void endTheGame() {
        this.gameEnd = true;
    }

    // PLACES

    public void movePlayerToPlace(String name) {
        Place place = world.getPlace(name);
        player.moveToPlace(place);
        changeState(place.getAssociatedState());
    }

    public Place getCurrentPlaceOfPlayer() {
        return player.getCurrentPlace();
    }

    public List<Place> getAvailablePlacesToGoFrom(Place place) {
        return getPlacesOfNames(place.getAvailablePlacesToGo());
    }

    public List<Place> getPlacesOfNames(Collection<String> names) {
        return world.getPlacesOfNames(names);
    }

    // STATES

    public void changeState(String id) {
        changeState(states.get(id));
    }

    private void changeState(State state) {
        currentState.bringOut();
        currentState = state;
        currentState.introduce();
    }

    // I/O

    private String readLine() {
        return io.readLine();
    }

    public void printOutLine(String line) {
        io.printOutLine(line);
    }

    public void printErrLine(String line) {
        io.printErrLine(line);
    }


    // MUTATORS / ACCESSORS

    void setWorld(World world) {
        this.world = world;
    }

    void setPlayer(Player player) {
        this.player = player;
    }

    public void setInitPlaceName(String initPlaceName) {
        State initial = new InitState(initPlaceName);
        addState(initial);
        currentState = initial;
    }

    void addStates(Collection<State> states) {
        for (State state : states) {
            addState(state);
        }
    }

    public void addState(State state) {
        state.setGame(this);
        states.put(state.getId(), state);
    }

    public void setIO(Interface io) {
        this.io = io;
    }

    // TODO: add methods to remove
}
