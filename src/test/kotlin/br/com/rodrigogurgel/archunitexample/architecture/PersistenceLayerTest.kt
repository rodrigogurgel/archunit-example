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
class PersistenceLayerTest {
    @ArchTest
    fun `Classes that resides in a package repository should be interface`(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..persistence.repository")
            .should()
            .beInterfaces()
            .check(importedClasses)
    }

    @ArchTest
    fun `Classes that resides in a package repository should have name ends with Repository`(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..persistence.repository")
            .should()
            .haveSimpleNameEndingWith("Repository")
            .check(importedClasses)
    }

    @ArchTest
    fun `Classes the resides in a package repository impl should implement repository interfaces`(
        importedClasses: JavaClasses
    ) {
        classes()
            .that()
            .resideInAPackage("..persistence.repository.impl")
            .and().areTopLevelClasses()
            .should()
            .implement(resideInAPackage("..persistence.repository"))
            .check(importedClasses)
    }
}