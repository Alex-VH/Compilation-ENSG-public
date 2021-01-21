package eu.ensg.compilers.bcel;

import eu.ensg.compilers.utils.ByteClassLoader;
import eu.ensg.compilers.utils.OpcodeViewer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;

public class HelloWorld {

    public static void printCode (Method[] methods) {
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);

            Code code = methods[i].getCode();
            if (code != null) // Non-abstract method
            {
                System.out.println(code);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws IllegalArgumentException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ClassGen cg = new ClassGen("Hello", "java.lang.Object", "",
                Const.ACC_PUBLIC | Const.ACC_SUPER, null);

        InstructionFactory f = new InstructionFactory(cg);
        InstructionList il = new InstructionList();

        MethodGen mg = new MethodGen(Const.ACC_STATIC | Const.ACC_PUBLIC, Type.VOID, new Type[]{new ArrayType(Type.STRING, 1)},
                new String[]{"argv"}, "main", "Hello", il, cg.getConstantPool());

        il.append(f.createPrintln("Hello World"));
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
        Class<?> loadClass = byteClassLoader.loadClass("Hello", bytes);
        loadClass.getMethod("main", String[].class).invoke(null, new Object[]{new String[]{""}});

    }
}
