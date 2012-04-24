/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.console;

import pl.touk.osgiworkshop.game.domain.Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author arkadius
 */
public class ConsoleInterface implements Interface {
    private BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() {
        try {
            return rdr.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOut(String str) {
        System.out.print(str); System.out.flush();
    }

    public void printOutLine(String line) {
        System.out.println(line);
    }

    public void printErrLine(String line) {
        System.err.println(line);
        System.err.flush();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
