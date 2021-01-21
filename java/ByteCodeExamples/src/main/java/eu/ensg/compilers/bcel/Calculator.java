/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.bcel;

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
import eu.ensg.compilers.utils.OpcodeViewer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.bcel.Const;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.IADD;
import org.apache.bcel.generic.IDIV;
import org.apache.bcel.generic.IMUL;
import org.apache.bcel.generic.INEG;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.ISUB;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

/**
 *
 * @author cyann
 */
public class Calculator {

    private static class BCELVisitor implements Visitor {

        private final ConstantPoolGen cp;
        private final InstructionFactory f;
        private final InstructionList il;

        public BCELVisitor (ConstantPoolGen cp, InstructionFactory f, InstructionList il) {
            this.cp = cp;
            this.f = f;
            this.il = il;
        }

        @Override
        public void visite (Addition ast) {
            ast.getLeft().visite();
            ast.getRight().visite();
            il.append(new IADD());
        }

        @Override
        public void visite (Substration ast) {
            ast.getLeft().visite();
            ast.getRight().visite();
            il.append(new ISUB());
        }

        @Override
        public void visite (Multiplication ast) {
            ast.getLeft().visite();
            ast.getRight().visite();
            il.append(new IMUL());
        }

        @Override
        public void visite (Division ast) {
            ast.getLeft().visite();
            ast.getRight().visite();
            il.append(new IDIV());
        }

        @Override
        public void visite (Minus ast) {
            ast.getLeft().visite();
            il.append(new INEG());
        }

        @Override
        public void visite (Number ast) {
            il.append(new PUSH(cp, ast.getValue()));
        }

        @Override
        public void visite (Print ast) {
            il.append(new GETSTATIC(cp.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;")));
            ast.getExpression().visite();
            il.append(new INVOKEVIRTUAL(cp.addMethodref("java.io.PrintStream", "println", "(I)V")));
        }
    }
    
    /*
    BCEL Example: 
        il.append(new PUSH(cp, 8));
        il.append(new PUSH(cp, 7));
        il.append(new IADD());

        il.append(new GETSTATIC(cp.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;")));
        il.append(new ILOAD(0));
        il.append(new INVOKEVIRTUAL(cp.addMethodref("java.io.PrintStream", "println", "(I)V")));

    */

    public static void main (String[] args) throws IllegalArgumentException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ClassGen cg = new ClassGen("Main", "java.lang.Object", "",
                Const.ACC_PUBLIC | Const.ACC_SUPER, null);

        ConstantPoolGen cp = cg.getConstantPool();
        InstructionFactory f = new InstructionFactory(cg);
        InstructionList il = new InstructionList();

        MethodGen mg = new MethodGen(Const.ACC_STATIC | Const.ACC_PUBLIC, Type.VOID, new Type[]{new ArrayType(Type.STRING, 1)},
                new String[]{"argv"}, "main", "Main", il, cg.getConstantPool());

        // create visitor
        // build ast
        // print -8 + (7 - 3) = -4
        Ast ast = new Print(new Addition(new Minus(new Number(8)), new Substration(new Number(7), new Number(3))));
        BCELVisitor visitor = new BCELVisitor(cp, f, il);
        ast.accept(visitor);
        ast.visite();

        il.append(InstructionFactory.createReturn(Type.VOID));

        mg.setMaxLocals();
        mg.setMaxStack();
        cg.addMethod(mg.getMethod());
        il.dispose();

        System.out.println("---- opcode ----");

        OpcodeViewer.print(cg.getJavaClass().getBytes());

        // execute
        System.out.println("---- execute ----");

        byte[] bytes = cg.getJavaClass().getBytes();
        ByteClassLoader byteClassLoader = new ByteClassLoader();
        Class<?> loadClass = byteClassLoader.loadClass("Main", bytes);
        loadClass.getMethod("main", String[].class).invoke(null, new Object[]{new String[]{""}});

    }

}
