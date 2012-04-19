/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.ping;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import pl.touk.osgiworkshop.pong.Pong;

/**
 * @author arkadius
 */
public class Ping implements BundleActivator {
    public void go() {
        System.out.println("ping");
        new Pong().go();
    }

    public void start(BundleContext bundleContext) throws Exception {
        go();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("bye");
    }
}
