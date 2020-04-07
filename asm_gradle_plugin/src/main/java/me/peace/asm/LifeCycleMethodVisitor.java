package me.peace.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.POP;

public class LifeCycleMethodVisitor extends MethodVisitor {
    private String className;
    private String methodName;

    public LifeCycleMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    //方法执行前插入代码
    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("MethodVisitor visit code");
        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn(className + " -> " + methodName);
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(POP);
    }
}
