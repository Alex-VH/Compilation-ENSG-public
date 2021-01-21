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
public class Print extends Expression {

    private final Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(Visitor visitor) {
        expression.accept(visitor);
        setVisitor(visitor);
    }

    @Override
    public void visite() {
        if (getVisitor() != null) {
            getVisitor().visite(this);
        }
    }

}
