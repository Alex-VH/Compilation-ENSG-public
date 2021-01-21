/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.patterns;

import java.util.Stack;

/**
 *
 * @author cyann
 */
public class GofVisitor {

    public static abstract class Visitor {

        public abstract void visite(Addition expression);

        public abstract void visite(Number expression);

    }

    public static class EvaluationVisitor extends Visitor {

        Stack<Integer> stack = new Stack<>();

        @Override
        public void visite(Addition expression) {
            expression.left.visite();
            expression.right.visite();

            int p2 = stack.pop();
            int p1 = stack.pop();

            stack.push(p1 + p2);
        }

        @Override
        public void visite(Number expression) {
            stack.push(expression.value);
        }

        @Override
        public String toString() {
            return "Result = " + stack.get(0);
        }

    }

    public static class ToStringVisitor extends Visitor {

        StringBuilder sb = new StringBuilder();

        @Override
        public void visite(Addition expression) {
            expression.left.visite();

            sb.append(" + ");

            expression.right.visite();
        }

        @Override
        public void visite(Number expression) {
            sb.append(String.valueOf(expression.value));
        }

        @Override
        public String toString() {
            return sb.toString();
        }

    }

    public static class ToInvertedPolishNotation extends Visitor {

        StringBuilder sb = new StringBuilder();

        @Override
        public void visite(Addition expression) {
            sb.append("(+ ");
            expression.left.visite();

            sb.append(" ");

            expression.right.visite();
            sb.append(')');
        }

        @Override
        public void visite(Number expression) {
            sb.append(String.valueOf(expression.value));
        }

        @Override
        public String toString() {
            return sb.toString();
        }

    }

    public static abstract class Expression {

        public abstract void accept(Visitor visitor);

        public abstract void visite();

    }

    // non terminal
    public static class Addition extends Expression {

        private Visitor visitor;

        private final Expression left;
        private final Expression right;

        public Addition(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void accept(Visitor visitor) {
            this.visitor = visitor;
            this.left.accept(visitor);
            this.right.accept(visitor);
        }

        @Override
        public void visite() {
            visitor.visite(this);
        }

    }

    // terminal one
    public static class Number extends Expression {

        private Visitor visitor;
        private final int value;

        public Number(int value) {
            this.value = value;
        }

        @Override
        public void accept(Visitor visitor) {
            this.visitor = visitor;
        }

        @Override
        public void visite() {
            visitor.visite(this);
        }

    }

    public static void main(String[] args) {
        Expression formula = new Addition(new Number(7), new Addition(new Number(8), new Number(2)));

        Visitor visitor = new ToStringVisitor();
        formula.accept(visitor);
        formula.visite();

        System.out.println("Expression is: [" + visitor + ']');

        visitor = new EvaluationVisitor();
        formula.accept(visitor);
        formula.visite();

        System.out.println("Result is: [" + visitor + ']');

        visitor = new ToInvertedPolishNotation();
        formula.accept(visitor);
        formula.visite();

        System.out.println("InvertedPolishNotation is: " + visitor);

        //formula.interpret(context);
        //System.out.println(context.formula + " = " + context.stack);
    }

}
