package com.alk;

import static com.alk.ConsoleIO.getString;
import static com.alk.ConsoleIO.putString;
import static com.alk.IOMonad.bind;

public class Main {


    public static void main(String[] args) {
        IO<Unit> hello = putString("Hello");
        IO<Unit> greet = IOMonad.bind(
                ConsoleIO.putString("Enter your name:"),
                bind(getString, arg -> ConsoleIO.putString("Hello, " + arg + "!")));
        IO<Unit> repeatMessage = bind(getString, putString);
        IO<Unit> main = IOMonad.bind(
                hello,
                IOMonad.bind(
                        greet,
                        IOMonad.bind(
                                ConsoleIO.putString("Enter a message you want to be repeated"),
                                repeatMessage)));
        main.run();
    }
}