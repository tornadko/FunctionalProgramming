package com.alk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public interface ConsoleIO {

    static IO<Unit> putString(final String string) {
        return () -> {
            System.out.println(string);
            return Unit.VALUE;
        };
    }

    Function<String, IO<Unit>> putString = ConsoleIO::putString;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    IO<String> getString = () -> {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

}