/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.asm;

import eu.ensg.compilers.ast.Addition;
import eu.ensg.compilers.ast.Ast;
import eu.ensg.compilers.ast.Division;
import eu.ensg.compilers.ast.Minus;
import eu.ensg.compilers.ast.Multiplication;
import eu.ensg.compilers.ast.Number;
import eu.ensg.compilers.ast.Print;
import eu.ensg.compilers.ast.Substration;
import eu.ensg.compilers.ast.Visitor;
import eu.ensg.compilers.utils.ByteClassLoader;
import eu.ensg.compilers.utils.LocalManager;
import eu.ensg.compilers.utils.OpcodeViewer;
import eu.ensg.compilers.utils.StackManager;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.Type;

/**
 *
 * @author cyann
 */
public class Calculator {

    private static class ASMVisitor implements Visitor {

        private final MethodVisitor mv;
        private final StackManager stack;
        private final LocalManager local;

        public int getMaxStack() {
            return stack.getMax();
        }

        public int getMaxLocalVariable() {
            return local.getNext();
        }

        public ASMVisitor(MethodVisitor mv, int initialStack, int initialLocal) {
            this.mv = mv;
            this.stack = new StackManager(initialStack);
            this.local = new LocalManager(initialLocal);
        }

        @Override
        public void visite(Addition ast) {
            ast.getRight().visite();
            ast.getLeft().visite();

            mv.visitInsn(IADD); // addition
            stack.decrement();
        }

        @Override
        public void visite(Substration ast) {
            ast.getRight().visite();
            ast.getLeft().visite();

            mv.visitInsn(ISUB); // soustraction
            stack.decrement();
        }

        @Override
        public void visite(Multiplication ast) {
            ast.getRight().visite();
            ast.getLeft().visite();

            mv.visitInsn(IMUL); // multiplication
            stack.decrement();
        }

        @Override
        public void visite(Division ast) {
            ast.getRight().visite();
            ast.getLeft().visite();

            mv.visitInsn(IDIV); // division
            stack.decrement();
        }

        @Override
        public void visite(Minus ast) {
            ast.getLeft().visite();

            mv.visitInsn(INEG); // négation
        }

        @Override
        public void visite(Number ast) {
            stack.increment();
            mv.visitIntInsn(BIPUSH, ast.getValue());
        }

        @Override
        public void visite(Print ast) {
            stack.increment();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            ast.getExpression().visite();
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            stack.decrement();
        }
    }

    public static void main(String[] args) {

        // build ast
        // print -8 + (7 - 3) = -4
        Ast ast = new Print(new Addition(new Minus(new Number(8)), new Substration(new Number(7), new Number(3))));
        //Ast ast = new Print(new Addition(new Number(2), new Number(2)));
        ASMVisitor astVisitor;

        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        cw.visit(V1_8, ACC_PUBLIC, "asm/Calculator", null, "java/lang/Object", null);
        {
            // constructor
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitMaxs(2, 1);
            mv.visitVarInsn(ALOAD, 0); // push `this` to the operand stack
            mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false); // call the constructor of super class
            mv.visitInsn(RETURN);
            mv.visitEnd();

        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);

            // compile through ast
            astVisitor = new ASMVisitor(mv, 0, 1);
            ast.accept(astVisitor);
            ast.visite();

            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("args", "[Ljava/lang/String;", null, l0, l1, 0);
            mv.visitMaxs(astVisitor.getMaxStack(), astVisitor.getMaxLocalVariable());
            mv.visitEnd();
        }
        cw.visitEnd();

        ByteClassLoader.ExecuteByteCode("asm.Calculator", cw.toByteArray());

        OpcodeViewer.print(cw.toByteArray());
    }

}
