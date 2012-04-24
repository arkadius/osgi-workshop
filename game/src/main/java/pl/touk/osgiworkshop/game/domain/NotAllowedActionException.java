/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import com.google.common.base.Joiner;

import java.util.List;

/**
 * @author arkadius
 */
public class NotAllowedActionException extends RuntimeException {

    public NotAllowedActionException(String action, List<String> parameters) {
        super("Nie mogę wykonać: " + action + " " + Joiner.on(" ").join(parameters));
    }

}
