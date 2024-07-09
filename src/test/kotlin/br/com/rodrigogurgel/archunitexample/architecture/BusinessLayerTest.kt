package br.com.rodrigogurgel.archunitexample.architecture

import com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes

@AnalyzeClasses(
    packages = ["br.com.rodrigogurgel.archunitexample"],
    importOptions = [DoNotIncludeTests::class, DoNotIncludeJars::class],
)
class BusinessLayerTest {
    @ArchTest
    fun `Classes that resides in a package service should be interface`(importedClasses: JavaClasses) {
        classes().that().resideInAPackage("..business.service")
            .should().beInterfaces()
            .check(importedClasses)
    }

    @ArchTest
    fun `Classes that resides in a package service should have name ends with Service`(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..business.service")
            .should()
            .haveSimpleNameEndingWith("Service")
            .check(importedClasses)
    }

    @ArchTest
    fun `Classes the resides in a package service impl should implement service interfaces`(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..business.service.impl")
            .should()
            .implement(resideInAPackage("..business.service.."))
            .check(importedClasses)
    }
}