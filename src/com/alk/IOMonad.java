package com.alk;

import java.util.function.Function;

public interface IOMonad {

    static <T> IO<T> of(final T value) {
        return () -> value;
    }

    static <A, B> IO<B> map(final Function<A, B> function, final A action) {
        return flatMap(function, of(action));
    }

    static <A, B> IO<B> flatMap(final Function<A, B> function, final IO<A> action) {
        return () -> function.apply(action.run());
    }

    static <T> IO<T> join(final IO<IO<T>> action) {
        return () -> action.run().run();
    }

    static <A, B> IO<B> bind(IO<A> action, Function<A, IO<B>> function) {
        return join(flatMap(function, action));
    }

    static IO<Unit> bind(final IO<Unit> a, final IO<Unit> b) {
        return bind(a, arg -> b);
    }
}