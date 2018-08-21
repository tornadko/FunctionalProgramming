package com.alk;

@FunctionalInterface
public interface IO<R> {

   R run();
}