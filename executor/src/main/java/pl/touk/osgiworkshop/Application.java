/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

import java.io.File;

/**
 * @author arkadius
 */
public class Application {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.start(false);
        Framework framework = main.getFramework();
        Thread.sleep(2000);
//        startBundle(framework, "pong/target/pong-1.0.jar");
//        startBundle(framework, "ping/target/ping-1.0.jar");

        startBundle(framework, "game/target/dependency/guava-osgi-11.0.1.jar");
        startBundle(framework, "game/target/game-1.0.jar");
//        Thread.sleep(2000);
        startBundle(framework, "plugin/target/plugin-1.0.jar");
//        System.exit(0);
    }

    private static void startBundle(Framework framework, String bundlePath) throws BundleException {
        File bundleFile = new File(bundlePath);
        Bundle bundle = framework.getBundleContext().installBundle("file://" + bundleFile.getAbsolutePath());
        bundle.start();
    }

}
