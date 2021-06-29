package com.keimos.patient

import com.keimos.data.Patient
import com.keimos.data.PatientRepository
import org.bson.types.ObjectId
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
}