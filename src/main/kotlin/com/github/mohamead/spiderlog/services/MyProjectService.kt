package com.github.mohamead.spiderlog.services

import com.intellij.openapi.project.Project
import com.github.mohamead.spiderlog.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
