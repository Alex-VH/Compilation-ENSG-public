/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.utils;

/**
 *
 * @author cyann
 */
public class StackManager {

    private int size;
    private int max;

    public StackManager(int initialSize) {
        this.size = initialSize;
        this.max = initialSize;
    }

    public StackManager increment() {
        size++;
        if (max < size) {
            max = size;
        }
        return this;
    }

    public StackManager decrement() {
        size--;
        return this;
    }

    public int getSize() {
        return size;
    }

    public int getMax() {
        return max;
    }

}
