/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.testplugin;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import pl.touk.osgiworkshop.game.plugin.Plugin;

/**
 * @author arkadius
 */
public class TestPluginActivator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(Plugin.class, new TestPlugin(), null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
    }
}
