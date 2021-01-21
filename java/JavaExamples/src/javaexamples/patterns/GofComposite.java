/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyann
 */
public class GofComposite {

    public static abstract class Componant {

        private final String name;

        public Componant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract void operation();

    }

    public static class Composite extends Componant {

        private final List<Componant> children = new ArrayList<>();

        public Composite(String name) {
            super(name);
        }

        public boolean add(Componant e) {
            return children.add(e);
        }

        public boolean remove(Object o) {
            return children.remove(o);
        }

        // mandatory, herited from super class
        @Override
        public void operation() {
            System.out.println("Do from composite " + getName());

            for (Componant componant : children) {
                componant.operation();
            }
        }

    }

    public static class Leaf extends Componant {

        public Leaf(String name) {
            super(name);
        }

        @Override
        public void operation() {
            System.out.println("Do from leaf " + getName());
        }

    }

    public static void main(String[] args) {
        Composite root = new Composite("root");

        Leaf node1 = new Leaf("Level 1, node 1");
        root.add(node1);

        Composite node2 = new Composite("Level 1, node 2");
        root.add(node2);

        Leaf node3 = new Leaf("Level 2, node 1");
        node2.add(node3);

        Composite node4 = new Composite("Level 2, node 2");
        node2.add(node4);

        Leaf node5 = new Leaf("Level 3, node 1");
        node4.add(node5);

        Leaf node6 = new Leaf("Level 3, node 2");
        node4.add(node6);

        root.operation();
    }

}
