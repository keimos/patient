package com.keimos.patient

import com.keimos.data.Patient
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatientApplicationTests @Autowired constructor(
	private val patientRepository: PatientRepository,
	private val restTemplate: TestRestTemplate
) {
	private val defaultPatientId = ObjectId.get()

	@LocalServerPort
	protected var port: Int = 0

	@BeforeEach
	fun setUp() {
		patientRepository.deleteAll()
	}

	private fun getRootUrl(): String? = "http://localhost:$port/patients"

	private fun saveOnePatient() =
		patientRepository.save(Patient(defaultPatientId, "Name",
			"Description"))

	@Test
	fun `should return all patients`() {
		saveOnePatient()

		val response = restTemplate.getForEntity(
			getRootUrl(),
			List::class.java
		)

		Assertions.assertEquals(200, response.statusCode.value())
		Assertions.assertNotNull(response.body)
		Assertions.assertEquals(defaultPatientId, response.body?.id)
	}

}
