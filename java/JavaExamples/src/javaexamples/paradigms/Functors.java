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
public class Functors {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jean", "Paul", "Claude", "Charles");

        Function<String, String> formater = (String t) -> "The name is " + t + ".";

        names.stream().map(formater).forEach(System.out::println);

    }

}
