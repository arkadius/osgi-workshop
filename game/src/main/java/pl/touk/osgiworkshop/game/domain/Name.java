/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.LinkedHashSet;
import static com.google.common.collect.Sets.*;
import static com.google.common.collect.Lists.*;

/**
 * @author arkadius
 */
public class Name implements Comparable<Name> {
    private LinkedHashSet<String> synonyms;
    private String locomotivePrefix;
    private String locomotiveForm;

    private Name() {}

    public static Name valueOf(String value, String... synonyms) {
        Name name = new Name();
        name.synonyms = newLinkedHashSet(asList(value, synonyms));
        return name;
    }

    public static Name valueWithLocomotiveFormOf(String value, String locomotivePrefix, String locomotiveForm, String... synonyms) {
        Name name = new Name();
        name.locomotivePrefix = locomotivePrefix;
        name.locomotiveForm   = locomotiveForm;
        name.synonyms = newLinkedHashSet(asList(value, synonyms));
        return name;
    }

    public boolean matches(String name) {
        name = name.toLowerCase();
        for (String synonym : synonyms) {
            if (name.startsWith(synonym.toLowerCase()))
                return true;
        }
        return false;
    }

    public String getLocomotive() {
        if (locomotiveForm != null) {
            return locomotivePrefix + " " + locomotiveForm;
        } else {
            return "do " + getValue();
        }
    }

    public String getValue() {
        return synonyms.iterator().next();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Name other = (Name) obj;
        return Objects.equal(this.getValue(), other.getValue());
    }

    public int compareTo(Name that) {
        return ComparisonChain.start()
                .compare(this.getValue(), that.getValue())
                .result();
    }

    @Override
    public String toString() {
        return getValue();
    }
}
