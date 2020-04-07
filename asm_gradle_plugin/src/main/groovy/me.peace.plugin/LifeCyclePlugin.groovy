package me.peace.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        println("==>inside life cycle plugin")
    }
}