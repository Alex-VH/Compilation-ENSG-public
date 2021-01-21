/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.paradigms;

/**
 *
 * @author cyann
 */
public class Clazz {

    public static class Character {

        private final String name;

        public Character(String name) {
            this.name = name;
        }

    }

    public static class Dispatch {

        public void method(String value) {
        }

        public void method(int value) {
        }

    }

    public static class Animal {

        public void eat() {
        }

    }

    public static class Recursion {

        public void loop10(int i) {
            if (i == 10) {
                return; // critère de sortie
            }
            this.loop10(i + 1);
        }

    }

    public static class Lion extends Animal {
    }

    public static void main(String[] args) {
        Character instance1 = new Character("Dupont");
        Character instance2 = new Character("Tintin");

        Animal animal; // sub class is not known
        animal = new Lion();
        animal.eat();

    }

}
