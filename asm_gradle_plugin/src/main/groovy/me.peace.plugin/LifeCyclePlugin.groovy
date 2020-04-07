package me.peace.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        println("==>inside life cycle plugin")
        println("==>register auto track transform")
        //注册自定义的transform
        def android = project.extensions.getByType(AppExtension)
        LifeCycleTransform transform = new LifeCycleTransform()
        android.registerTransform(transform)
    }
}