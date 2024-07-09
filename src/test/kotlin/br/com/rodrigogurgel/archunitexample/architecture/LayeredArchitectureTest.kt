package br.com.rodrigogurgel.archunitexample.architecture

import com.tngtech.archunit.base.DescribedPredicate.alwaysTrue
import com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage
import com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures.layeredArchitecture

@AnalyzeClasses(
    packages = ["br.com.rodrigogurgel.archunitexample"],
    importOptions = [DoNotIncludeTests::class, DoNotIncludeJars::class],
)
class LayeredArchitectureTest {
    @ArchTest
    fun `Layered architecture packages rules`(importedClasses: JavaClasses) {
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("Presentation Layer").definedBy("..presentation..")
            .layer("Business Layer").definedBy("..business..")
            .layer("Persistence Layer").definedBy("..persistence..")
            .whereLayer("Presentation Layer").mayOnlyAccessLayers("Business Layer")
            .whereLayer("Business Layer").mayOnlyAccessLayers("Persistence Layer")
            .whereLayer("Persistence Layer").mayNotAccessAnyLayer()
            .ignoreDependency(
                alwaysTrue(),
                resideOutsideOfPackages(
                    "br.com.rodrigogurgel.archunitexample..",
                )
            )
            .check(importedClasses)
    }
}
