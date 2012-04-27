/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import pl.touk.osgiworkshop.game.base.behave.DoNothing;
import pl.touk.osgiworkshop.game.base.behave.HurtPlayer;
import pl.touk.osgiworkshop.game.base.behave.PrintMessage;

import java.util.Random;

/**
 * @author arkadius
 */
public class Creature implements Comparable<Creature> {
    private Name type;
    private Name name;
    private int health = Config.getInstance().getInt("baseCreature.health");
    private double strength = Config.getInstance().getDouble("baseCreature.strength");
    private double accuracy = Config.getInstance().getDouble("baseCreature.accuracy");

    public Creature(Name type, Name name) {
        this.type = type;
        this.name = name;
    }

    public boolean decreaseHealth(int diff) {
        return (health -= diff) > 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Name getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public Behaviour attack(Player player, Place place) {
        // default implementation
        if (new Random().nextDouble() < accuracy) {
            return new HurtPlayer((int) strength);
        } else {
            return new PrintMessage("Przeciwnik spudłował.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Creature other = (Creature) obj;
        return Objects.equal(this.type, other.type) && Objects.equal(this.name, other.name);
    }

    public int compareTo(Creature that) {
        return ComparisonChain.start()
                .compare(this.getType(), that.getType())
                .compare(this.getName(), that.getName())
                .result();
    }

    @Override
    public String toString() {
        return "Creature{" +
                "health=" + health +
                ", name=" + name +
                ", type=" + type +
                '}';
    }
}
