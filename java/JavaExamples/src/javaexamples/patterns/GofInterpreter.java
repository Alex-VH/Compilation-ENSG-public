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
public class GofInterpreter {

    public static class Context {

        StringBuilder formula = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
    }

    public static abstract class Expression {

        public abstract void interpret(Context context);

    }

    // non terminal
    public static class Addition extends Expression {

        private final Expression left;
        private final Expression right;

        public Addition(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void interpret(Context context) {
            left.interpret(context);

            context.formula.append(" + ");

            right.interpret(context);

            int op2 = context.stack.pop();
            int op1 = context.stack.pop();
            context.stack.push(op1 + op2);
        }

    }

    // terminal one
    public static class Number extends Expression {

        private final int value;

        public Number(int value) {
            this.value = value;
        }

        @Override
        public void interpret(Context context) {
            context.formula.append(value);
            context.stack.push(value);
        }

    }

    public static void main(String[] args) {
        Context context = new Context();

        Expression formula = new Addition(new Number(7), new Addition(new Number(8), new Number(2)));
        
        formula.interpret(context);
        
        System.out.println(context.formula + " = " + context.stack);
    }
    
}
