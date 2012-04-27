package pl.touk.osgiworkshop.testplugin;

import com.google.common.collect.Lists;
import pl.touk.osgiworkshop.game.base.BasePlace;
import pl.touk.osgiworkshop.game.domain.Creature;
import pl.touk.osgiworkshop.game.domain.Name;
import pl.touk.osgiworkshop.game.domain.Place;
import pl.touk.osgiworkshop.game.domain.State;
import pl.touk.osgiworkshop.game.plugin.Plugin;
import pl.touk.osgiworkshop.testplugin.states.OccupiedState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author arkadius
 */
public class TestPlugin implements Plugin {

    private List<Place> places = new ArrayList<Place>();
    private List<State> additionalStates = new ArrayList<State>();
    private List<Creature> creatures = new ArrayList<Creature>();

    public TestPlugin() {
        Place miasto = new BasePlace(Name.valueWithLocomotiveFormOf("Miasto", "do", "miasta"), "Witaj w Babilonie.");
        miasto.setAvailablePlacesToGo("Rozdroża");
        places.add(miasto);
        Place las = new BasePlace(Name.valueWithLocomotiveFormOf("Las", "do", "lasu"), "Dookoła ciebie zieleń. Słychać śpiew ptaków.");
        las.setAvailablePlacesToGo("Rozdroża");
        State lasState = las.getAssociatedState();
        additionalStates.add(lasState);
        Creature hordaRozbojnikow = new Creature(Name.valueWithLocomotiveFormOf("Horda rozbójników", "", "Hordę rozbójników"), Name.valueOf("horda1"));
        creatures.add(hordaRozbojnikow);
        las.setAssociatedState(new OccupiedState("Atak rozbójników", hordaRozbojnikow.getType(), hordaRozbojnikow.getName(), lasState.getId()));
        places.add(las);
    }

    public String getName() {
        return "test";
    }

    public Collection<Place> getPlaces() {
        return places;
    }

    public Collection<State> getAdditionalStates() {
        return additionalStates;
    }

    public Collection<Creature> getCreatures() {
        return creatures;
    }
}
