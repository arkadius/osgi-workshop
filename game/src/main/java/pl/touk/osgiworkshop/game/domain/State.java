/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Objects;

import java.util.List;

/**
 * @author arkadius
 */
public abstract class State {
    private String id;
    private Game game;

    protected State(String id) {
        this.id = id;
    }

    /**
     * Introduce changing game to this state
     */
    public abstract void introduce();

    /**
     * Invoked on changing game from this state
     */
    public void bringOut() {}

    /**
     * Executes action with given parameters
     * @param action
     * @param parameters
     * @return behaviour(command) which can change the game!
     * @throws NotAllowedActionException if action is not allowed
     */
    public abstract Behaviour execute(String action, List<String> parameters) throws NotAllowedActionException;

    /**
     * Gets available actions
     * @return actions
     */
    public abstract List<String> getAvailableActions();

    public void setGame(Game game) {
        this.game = game;
    }

    public String getId() {
        return id;
    }

    protected Game getGame() {
        return game;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        return Objects.equal(this.id, other.id);
    }

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                '}';
    }
}
