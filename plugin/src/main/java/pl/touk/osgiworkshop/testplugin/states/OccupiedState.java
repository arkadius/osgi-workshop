/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.testplugin.states;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import pl.touk.osgiworkshop.game.base.behave.AggregateBehaviour;
import pl.touk.osgiworkshop.game.base.behave.DoNothing;
import pl.touk.osgiworkshop.game.base.behave.UseWeapon;
import pl.touk.osgiworkshop.game.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author arkadius
 */
public class OccupiedState extends State {

    private final Name creatureType;
    private final Name creaturesName;
    private String safeState;

    public OccupiedState(String id, Name creatureType, Name creaturesName, String safeState) {
        super(id);
        this.creatureType = creatureType;
        this.creaturesName = creaturesName;
        this.safeState = safeState;
    }

    @Override
    public void introduce() {
        Creature creature = getGame().getCreature(creatureType.getValue(), creaturesName.getValue());
        if (creature.isAlive()) {
            getGame().setCurrentCreature(creature);
            getGame().printOutLine("Zostałeś napadnięty przez " + creatureType.getLocomotive() + ". Musisz zacząć działać, albo umrzesz.");
        } else {
            getGame().changeState(safeState);
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return newArrayList("broń się", "uciekaj", "dostępne bronie", "wybierz broń");
    }

    @Override
    public Behaviour execute(String action, List<String> parameters) throws NotAllowedActionException {
        if (Sets.newHashSet("bron", "obron", "atakuj", "zaatakuj").contains(action)) {
            return executeAttack(parameters);
        } else if (Sets.newHashSet("uciekaj", "ucieknij").contains(action)) {
            return executeLive(parameters);
        } else if (Sets.newHashSet("dostepna", "dostepne", "jakie", "jaka").contains(action)) {
            return executeShowWeapons(parameters);
        } else if (Sets.newHashSet("wybierz", "wybieram", "uzyj", "uzywam").contains(action)) {
            return executeChooseWeapon(parameters);
        }
        throw new NotAllowedActionException(action, parameters);
    }

    private Behaviour executeAttack(List<String> parameters) {
        Weapon weapon = getGame().getPlayer().getCurrentWeapon();
        if (weapon == null) {
            getGame().printErrLine("Nie wybrałeś broni!");
            return new DoNothing();
        } else {
            Creature creature = getGame().getCurrentCreature();
            Behaviour attackAction = weapon.use(getGame().getPlayer(), creature, getGame().getCurrentPlaceOfPlayer(), safeState);
            // TODO: action should be simulated then decision if execute next
            Behaviour contraAttackAction = creature.attack(getGame().getPlayer(), getGame().getCurrentPlaceOfPlayer());
            return new AggregateBehaviour(attackAction, contraAttackAction);
        }
    }

    private Behaviour executeLive(List<String> parameters) {
        return new DoNothing();
    }

    private Behaviour executeShowWeapons(List<String> parameters) {
        if (parameters.size() > 0 && Sets.newHashSet("sa", "jest").contains(parameters.get(0))) {
            parameters = parameters.subList(1, parameters.size());
        }
        if (parameters.size() == 0) {
            return missingWords();
        } else if (Sets.newHashSet("dostepne", "dostepna").contains(parameters.get(0))) {
            parameters = parameters.subList(1, parameters.size());
        }
        if (parameters.size() == 0) {
            return missingWords();
        } else if (!Sets.newHashSet("bron", "bronie").contains(parameters.get(0))) {
            return notUnderdtandVerb(parameters);
        }
        showAvailableWeapons();
        return new DoNothing();
    }

    private Behaviour executeChooseWeapon(List<String> parameters) {
        if (parameters.size() > 0 && Sets.newHashSet("bron", "broni").contains(parameters.get(0))) {
            parameters = parameters.subList(1, parameters.size());
        }
        if (parameters.size() == 0) {
            return missingWords();
        }
        String weaponName = Joiner.on(" ").join(parameters);
        for (Weapon weapon : getGame().getPlayer().getWeapons()) {
            if (weapon.getName().matches(weaponName)) {
                getGame().printOutLine("Używasz broni: " + weapon.getName() + ".");
                return new UseWeapon(weapon);
            }
        }
        getGame().printErrLine("Nie mam przy sobie broni: " + weaponName);
        showAvailableWeapons();
        return new DoNothing();
    }


    private void showAvailableWeapons() {
        List<String> weaponNames = new ArrayList<String>();
        for (Weapon weapon : getGame().getPlayer().getWeapons()) {
            weaponNames.add(weapon.getName().getValue());
        }
        getGame().printOutLine("Dostępne bronie to: " + Joiner.on(", ").join(weaponNames) + ".");
    }

    private Behaviour missingWords() {
        getGame().printErrLine("O co pytasz pytasz wylewny człowieku?");
        return new DoNothing();
    }

    private Behaviour notUnderdtandVerb(List<String> parameters) {
        getGame().printErrLine("Nie rozumiem sformułowania: " + parameters.get(0) + ".");
        return new DoNothing();
    }
}
