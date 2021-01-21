/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.ast;

/**
 *
 * @author cyann
 */
public class Number extends Expression {

    private final int value;

    public int getValue() {
        return value;
    }

    public Number(int value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        setVisitor(visitor);
    }
    
    @Override
    public void visite() {
        if (getVisitor() != null) {
            getVisitor().visite(this);
        }
    }

}
