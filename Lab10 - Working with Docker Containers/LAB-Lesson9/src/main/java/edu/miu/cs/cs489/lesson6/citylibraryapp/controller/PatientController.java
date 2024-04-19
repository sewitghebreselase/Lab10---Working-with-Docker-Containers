package edu.miu.cs.cs489.lesson6.citylibraryapp.controller;


import edu.miu.cs.cs489.lesson6.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Patient;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.IPatientService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/adsweb/api/v1/patient")
public class PatientController {

    private final IPatientService patientService;
    private final ModelMapper modelMapper;

    public PatientController(IPatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.save(modelMapper.map(patientRequest, Patient.class)), HttpStatus.CREATED);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") int id, @Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.updatePatient(id, patientRequest), HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deletePatientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/search/{searchString}")
    public ResponseEntity<List<Patient>> getPatientsBySearchString(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.getPatientsWithSearchString(searchString));
    }



}
