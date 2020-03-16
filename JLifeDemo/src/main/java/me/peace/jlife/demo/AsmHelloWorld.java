package me.peace.jlife.demo;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;

import me.peace.classloader.ByteClassLoader;

public class AsmHelloWorld {
    public static void main(String[] args) throws Exception{
        byte[] bytes = byteX();
        ByteClassLoader classLoader = new ByteClassLoader();
        Class<?> clazz = classLoader.defineClass("me.peace.asm.HelloWorld",bytes);
        Method main = clazz.getMethod("main",String[].class);
        main.invoke(null,new Object[]{new String[]{}});
    }

    private static byte[] byteX(){
        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"me/peace/asm/HelloWorld",null,"java" +
            "/lang/Object",null);
        MethodVisitor methodVisitor =
            classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,"main","([Ljava/lang" +
                "/String;" +
                ")V",null,null);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System","out","Ljava/io" +
            "/PrintStream;");
        methodVisitor.visitLdcInsn("AsmHelloWorld");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/io/PrintStream","println",
            "(Ljava/lang/String;)V",false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(2,1);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}
