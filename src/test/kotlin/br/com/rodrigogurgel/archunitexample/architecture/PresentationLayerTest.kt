package br.com.rodrigogurgel.archunitexample.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.springframework.web.bind.annotation.RestController

@AnalyzeClasses(
    packages = ["br.com.rodrigogurgel.archunitexample"],
    importOptions = [DoNotIncludeTests::class, DoNotIncludeJars::class],
)
class PresentationLayerTest {
    @ArchTest
    fun `The package controller should only have classes that the name ends with Controller`(importedClasses: JavaClasses) {
        classes().that().resideInAPackage("..presentation.controller..")
            .should()
            .haveSimpleNameEndingWith("Controller")
            .check(importedClasses)
    }

    @ArchTest
    fun `Classes resides in package controller should be annotated with RestController`(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..presentation.controller..")
            .should()
            .beAnnotatedWith(RestController::class.java)
            .check(importedClasses)
    }

    @ArchTest
    fun `The package dto should only have classes that the name ends with DTO`(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..presentation.dto..")
            .should()
            .haveSimpleNameEndingWith("DTO")
            .check(importedClasses)
    }
}