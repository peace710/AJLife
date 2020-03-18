package me.peace.annotation;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

// apt -factory me.peace.annotation.ExtractInterfaceProcessorFactory ClassAnnotationApt.java –s ../me/peace/annotation
public class ClassAnnotationApt {
}

class MultiplierProcessor implements AnnotationProcessor{
    AnnotationProcessorEnvironment env;
    List<MethodDeclaration> interfaceMethods = new ArrayList<>();

    public MultiplierProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
    }

    @Override
    public void process() {
        for (TypeDeclaration declaration : env.getSpecifiedTypeDeclarations()){
            ExtractInterface annotation = declaration.getAnnotation(ExtractInterface.class);
            if (annotation == null) break;
            for (MethodDeclaration methodDeclaration : declaration.getMethods()){
                Collection<Modifier> modifiers = methodDeclaration.getModifiers();
                if (modifiers.contains(Modifier.PUBLIC) && !modifiers.contains(Modifier.STATIC)){
                    interfaceMethods.add(methodDeclaration);
                }
                if (interfaceMethods.size() > 0){
                    createInterface(annotation,declaration);
                }
            }
        }
    }

    private void createInterface(ExtractInterface annotation,TypeDeclaration declaration){
        try {
            PrintWriter writer = env.getFiler().createSourceFile(annotation.value());
            writer.println("package " + declaration.getPackage().getQualifiedName() + ";");
            writer.println("public interface " + annotation.value() + "{");
            for (MethodDeclaration methodDeclaration:interfaceMethods){
                writer.print("public ");
                writer.print(methodDeclaration.getReturnType() + " ");
                writer.print(methodDeclaration.getSimpleName() + "(");
                int i = 0;
                for (ParameterDeclaration parameterDeclaration :
                    methodDeclaration.getParameters()){
                    writer.print(parameterDeclaration.getType() + " " + parameterDeclaration.getSimpleName());
                    if (++i < methodDeclaration.getParameters().size()){
                        writer.print(",");
                    }
                }
                writer.println(");");
            }
            writer.println("}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ExtractInterfaceProcessorFactory implements AnnotationProcessorFactory{

    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton(ExtractInterface.class.getCanonicalName());
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new MultiplierProcessor(annotationProcessorEnvironment);
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface ExtractInterface{
    String value();
}

@ExtractInterface("IMultiplier")
class Multiplier{
    private static final String TAG = Multiplier.class.getSimpleName();

    public int multiply(int x ,int y){
        int total = 0;
        for (int i = 0 ; i < x; i++){
            total = add(total,y);
        }
        return total;
    }

    private int add(int x,int y){
        return x + y;
    }

    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();
        LogUtils.i(TAG,"5 * 6 = " + multiplier.multiply(5 ,6));
    }
}




