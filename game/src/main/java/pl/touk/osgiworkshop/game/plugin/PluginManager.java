/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.osgi.service.importer.OsgiServiceLifecycleListener;
import pl.touk.osgiworkshop.game.domain.Game;

import java.util.Map;
import java.util.Set;

/**
 * @author arkadius
 */
public class PluginManager implements OsgiServiceLifecycleListener {
    private static final Logger log = LoggerFactory.getLogger(PluginManager.class);
    private Game game;

    public PluginManager(Game game) {
        this.game = game;
    }

    @Override
    public void bind(Object service, Map properties) throws Exception {
        addPlugin((Plugin) service);
    }

    @Override
    public void unbind(Object service, Map properties) throws Exception {
        removePlugin((Plugin) service);
    }

    public void addPlugin(Plugin plugin) {
        log.info("Plugin został zainstalowany: " + plugin.getName());
        game.addPlaces(plugin.getPlaces());
        game.addStates(plugin.getAdditionalStates());
        game.addCreatures(plugin.getCreatures());
    }


    private void removePlugin(Plugin plugin) {
        log.info("Plugin został odinstalowany: " + plugin.getName());
        // TODO
    }
}
