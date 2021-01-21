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
public abstract class UnaryOperation extends Expression {

    private final Expression left;

    public Expression getLeft() {
        return left;
    }

    @Override
    public void accept(Visitor visitor) {
        left.accept(visitor);
        setVisitor(visitor);
    }

    public UnaryOperation(Expression left) {
        this.left = left;
    }

}
