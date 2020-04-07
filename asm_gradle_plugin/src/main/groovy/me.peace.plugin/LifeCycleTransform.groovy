package me.peace.plugin


import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType

//检索遍历项目中所有的文件
public class LifeCycleTransform extends Transform{

    //task名称
    @Override
    String getName() {
        return "LifeCycleTransform"
    }

    //检索文件的类型.class还是标准java资源文件
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    //检索文件的范围
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    //是否支持增量编译
    @Override
    boolean isIncremental() {
        return false
    }


    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        //获取传入的输入流
        Collection<TransformInput> inputs = transformInvocation.inputs
        // 遍历directoryInputs(文件夹中的class文件) directoryInputs代表着以源码方式参与项目编译的所有目录结构及其目录下的源码文件
        // 比如我们手写的类以及R.class、BuildConfig.class以及MainActivity.class等
        inputs.each {
            TransformInput input ->
                input.directoryInputs.each {
                    DirectoryInput dirInput ->
                        File dir = dirInput.file
                        if (dir){
                            dir.traverse(type: FileType.FILES,nameFilter:~/.*\.class/){
                                File file -> println("find class : " + file.name)
                            }
                        }
                }
        }
    }
}

