/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.paradigms;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author cyann
 */
public class Tuples {

    public static class Unit<T1> {

        private final T1 value1;

        public T1 getValue1() {
            return value1;
        }

        public Unit(T1 value1) {
            this.value1 = value1;
        }

        @Override
        public String toString() {
            return "Unit{" + "value1=" + value1 + '}';
        }

    }

    public static class Pair<T1, T2> extends Unit<T1> {

        private final T2 value2;

        public T2 getValue2() {
            return value2;
        }

        public Pair(T1 value1, T2 value2) {
            super(value1);
            this.value2 = value2;
        }

        @Override
        public String toString() {
            return "Pair{" + "value1=" + getValue1() + ", value2=" + value2 + '}';
        }

    }

    public static void main(String[] args) {

        Pair<Integer, Integer> point = new Pair<>(10, 20);
        System.out.println(point);

    }

}
