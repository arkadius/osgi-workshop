/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pl.touk.osgiworkshop.game.base.BasePlace;
import pl.touk.osgiworkshop.game.console.ConsoleInterface;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author arkadius
 */
public class IntegrationDomainTest {

    public static final Answer printingAnswer = new Answer() {
        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            if ("printOutLine".equals(invocationOnMock.getMethod().getName())) {
                System.out.println(invocationOnMock.getArguments()[0]);
            } else {
                System.err.println(invocationOnMock.getArguments()[0]);
            }
            return null;
        }
    };
    private Game game;
    private Interface io;

    @Before
    public void initGame() {
        Place rozdroza = new BasePlace(Name.valueWithLocomotiveFormOf("Rozdroża", "na", "Rozdroża", "rozdroż"), "Jesteś na rozstaju dróg. Którędy podążysz?");
        rozdroza.setAvailablePlacesToGo("Rozdroża");
        game = new GameBuilder()
                .setPlayerName("Jan")
                .setPlaces(Collections.singleton(rozdroza))
                .setInitPlaceName("Rozdroża")
                .createGame();
        io = mock(Interface.class);
        doAnswer(printingAnswer).when(io).printOutLine(anyString());
        doAnswer(printingAnswer).when(io).printErrLine(anyString());
        game.setIO(io);
    }


    @Test
    public void shouldWorkGameOver() {
        // given
        when(io.readLine()).thenReturn("wyjdź");

        // when
        game.start();

        // then
        verify(io, times(4)).printOutLine(anyString());
    }

    @Test
    public void shouldWorkGoToSomePlace() {
        // given
        when(io.readLine()).thenReturn("idź na rozdroża", "wyjdź");

        // when
        game.start();

        // then
        verify(io, times(6)).printOutLine(anyString());
    }
}
