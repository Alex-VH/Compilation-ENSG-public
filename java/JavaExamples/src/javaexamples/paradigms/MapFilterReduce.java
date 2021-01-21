/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.paradigms;

import java.util.stream.Stream;
import javaexamples.utils.StreamUtils;

/**
 *
 * @author cyann
 */
public class MapFilterReduce {

    public static void mapExample() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
          .map((item) -> "The number is : " + item)
          .forEach(System.out::println);

        // The number is : 1
        // The number is : 2
        // The number is : 3
        // The number is : 4
        // The number is : 5
        // The number is : 6
        // The number is : 7
        // The number is : 8
    }

    public static void filterExample() {
Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
  .filter((item) -> item % 2 == 0)
  .forEach(System.out::println);

        // 2
        // 4
        // 6
        // 8
    }

    private static void reduceExample() {
        System.out.println(
Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
.reduce((a, b) -> a + b).get()
        );

        // 36
    }

    private static void zipExample() {
        StreamUtils.zip(
          Stream.of(1, 2, 3, 4),
          Stream.of(4, 3, 2, 1), (a, b) -> a + b)
          .forEach(System.out::println);

        // 5
        // 5
        // 5
        // 5
    }

    public static void main(String[] args) {

        System.out.println("# Map");
        mapExample();
        System.out.println("");

        System.out.println("# Filter");
        filterExample();
        System.out.println("");

        System.out.println("# Reduce");
        reduceExample();
        System.out.println("");

        System.out.println("# Zip");
        zipExample();
        System.out.println("");

    }

}
