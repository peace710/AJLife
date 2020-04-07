package me.peace.plugin


import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType
import me.peace.asm.LifeCycleClassVisitor
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

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

        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        if (outputProvider != null){
            outputProvider.deleteAll()
        }

        inputs.each {
            TransformInput input ->
                input.directoryInputs.each {
                    DirectoryInput dirInput ->
                        File dir = dirInput.file
                        if (dir){
                            dir.traverse(type: FileType.FILES,nameFilter:~/.*\.class/){
                                File file -> println("find class : " + file.name)
                                    //对class文件进行读取与解析
                                    ClassReader classReader = new ClassReader(file.bytes)
                                    //对class文件写入
                                    ClassWriter classWriter = new ClassWriter(classReader,
                                            ClassWriter.COMPUTE_MAXS)
                                    //访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor相应的方法
                                    ClassVisitor classVisitor = new LifeCycleClassVisitor(classWriter)
                                    //依次调用ClassVisitor接口的各个方法
                                    classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES)
                                    //toByteArray方法会将最终修改的字节码以byte数组形式返回
                                    byte[] bytes = classWriter.toByteArray()
                                    //通过文件流写入方式覆盖原先的内容，实现class文件的改写
                                    FileOutputStream outputStream = new FileOutputStream(file.path)
                                    outputStream.write(bytes)
                                    outputStream.close()
                            }
                        }
                        //处理完输入文件后把输出传给下一个文件
                        def dest = outputProvider.getContentLocation(dirInput.name,dirInput
                                .contentTypes,dirInput.scopes,Format.DIRECTORY)
                        FileUtils.copyDirectory(dirInput.file,dest)
                }
        }
    }
}

