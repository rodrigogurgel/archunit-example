package br.com.rodrigogurgel.archunit_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArchunitExampleApplication

fun main(args: Array<String>) {
	runApplication<ArchunitExampleApplication>(*args)
}