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
public abstract class BinaryOperation extends Expression {

    private final Expression left;
    private final Expression right;

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    
    @Override
    public void accept(Visitor visitor) {
        left.accept(visitor);
        right.accept(visitor);
        setVisitor(visitor);
    }

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

}
