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
public class LocalManager {

    private int next;

    public LocalManager(int initialIndex) {
        this.next = initialIndex;
    }

    public int getNext() {
        return next;
    }

}
