/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * @author arkadius
 */
public class Creature implements Comparable<Creature> {
    private Name type;
    private Name name;
    private int health = 100;
    private double power = 1.0;

    public Creature(Name type, Name name) {
        this.type = type;
        this.name = name;
    }

    public boolean decreaseHealth(int diff) {
        return (health -= diff) > 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPower(double power) {
        this.power = power;
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

    public double getPower() {
        return power;
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
                "type=" + getType() +
                ", name=" + getName() +
                ", health=" + health +
                ", power=" + power +
                '}';
    }
}
