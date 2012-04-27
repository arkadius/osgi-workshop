/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.plugin;

import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import pl.touk.osgiworkshop.game.domain.Game;

/**
 * @author arkadius
 */
public class PluginManager implements ServiceTrackerCustomizer<Plugin, Plugin> {

    private Game game;
    private BundleContext bundleContext;

    public PluginManager(Game game, BundleContext bundleContext) {
        this.game = game;
        this.bundleContext = bundleContext;
    }



    public Plugin addingService(ServiceReference<Plugin> pluginServiceReference) {
        Plugin plugin = bundleContext.getService(pluginServiceReference);
        addPlugin(plugin);
        return plugin;
    }

    public void modifiedService(ServiceReference<Plugin> pluginServiceReference, Plugin plugin) {
        // TODO ?
    }

    public void removedService(ServiceReference<Plugin> pluginServiceReference, Plugin plugin) {
        removePlugin(plugin);
        bundleContext.ungetService(pluginServiceReference);
    }

    public void addPlugin(Plugin plugin) {
        System.out.println("Plugin został zainstalowany: " + plugin.getName());
        game.addPlaces(plugin.getPlaces());
        game.addStates(plugin.getAdditionalStates());
        game.addCreatures(plugin.getCreatures());
    }


    private void removePlugin(Plugin plugin) {
        System.out.println("Plugin został odinstalowany: " + plugin.getName());
        // TODO
    }

}
