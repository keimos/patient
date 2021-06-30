package com.keimos.patient

import com.keimos.data.Patient
import com.keimos.data.PatientRepository
import com.keimos.requests.PatientRequest
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
class PatientController(private val patientsRepository: PatientRepository) {

    @GetMapping
    fun getAllPatients(): ResponseEntity<List<Patient>> {
        val patients = patientsRepository.findAll()
        return ResponseEntity.ok(patients)
    }

    @GetMapping
    fun getOnePatient(@PathVariable("id") id: String): ResponseEntity<Patient> {
        val patient = patientsRepository.findOneById(ObjectId(id))
        return ResponseEntity.ok(patient)
    }

    @PostMapping
    fun createPatient(@RequestBody request: PatientRequest):
    ResponseEntity<Patient> {
        val patient = patientsRepository.save(Patient(
            name = request.name,
            description = request.description
        ))
        return ResponseEntity(patient, HttpStatus.CREATED)
    }
}