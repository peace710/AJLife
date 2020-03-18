package me.peace.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import me.peace.annotation.Call;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"me.peace.annotation.Call"})
public class CallProcessor extends AbstractProcessor {
    private static final String TAG = CallProcessor.class.getSimpleName();

    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getRootElements()){
            String packageStr = element.getEnclosingElement().toString();
            String classStr = element.getSimpleName().toString();
            for (Element enclosedElement : element.getEnclosedElements()){
                if (enclosedElement.getKind() == ElementKind.METHOD){
                    Call call = enclosedElement.getAnnotation(Call.class);
                    if (call != null){
                        String method = enclosedElement.getSimpleName().toString();
                        LogUtils.i(TAG,"package = " + packageStr + ",class = " + classStr + ", " +
                            "method = " + method);
                        TypeSpec autoClass = TypeSpec.classBuilder(method).build();
                        JavaFile javaFile = JavaFile.builder(packageStr, autoClass).build();
                        try {
                            javaFile.writeTo(filer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Call.class.getCanonicalName());
    }
}
