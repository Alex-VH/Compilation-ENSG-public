/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ByteClassLoader extends ClassLoader {

    public ByteClassLoader() {
        super(ClassLoader.getSystemClassLoader());
    }

    public Class<?> loadClass(String name, byte[] classContents) throws NoClassDefFoundError {
        Class<?> cls;

        try {
            cls = super.findSystemClass(name);
        } catch (final ClassNotFoundException ex) {
            cls = defineClass(name, classContents, 0, classContents.length);
        }

        resolveClass(cls);

        return cls;
    }
    
    public static void ExecuteByteCode(String name, byte[] bytes) {
        try {
            Class<?> cls = new ByteClassLoader().loadClass(name, bytes);
            cls.getMethod("main", String[].class).invoke(null, (Object) null);
        } catch (Exception ex) {
            Logger.getLogger(ByteClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
