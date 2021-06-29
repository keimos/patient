package com.keimos.patient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScans
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
class PatientApplication

fun main(args: Array<String>) {
	runApplication<PatientApplication>(*args)
}
