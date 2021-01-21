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
public class Substration extends BinaryOperation {

    public Substration(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public void visite() {
        if (getVisitor() != null) {
            getVisitor().visite(this);
        }
    }

}
