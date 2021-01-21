/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

/**
 *
 * @author cyann
 */
public class OpcodeViewer {

    public static String classToOpcodeString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (int i = 0; i < cn.methods.size(); i++) {
            appendMethod(sb, (MethodNode) cn.methods.get(i));
        }

        return sb.toString();
    }

    private static void appendMethod(StringBuilder sb, MethodNode mn) {
        sb.append(mn.name);
        sb.append('\t');
        sb.append("// ( maxStack:");
        sb.append(mn.maxStack);
        sb.append(", maxLocals:");
        sb.append(mn.maxLocals);
        sb.append(')');
        sb.append('\n');
        

        InsnList il = mn.instructions;
        for (int i = 0; i < il.size(); i++) {
            appendInstructionNode(sb, il.get(i));
        }

    }

    private static void appendInstructionNode(StringBuilder sb, AbstractInsnNode in) {
        Printer printer = new Textifier();
        TraceMethodVisitor tmv = new TraceMethodVisitor(printer);
        in.accept(tmv);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        printer.print(writer);
        sb.append(stringWriter.toString());
    }

    public static void print(byte[] bytes) {
        System.out.println(classToOpcodeString(bytes));
    }

}
