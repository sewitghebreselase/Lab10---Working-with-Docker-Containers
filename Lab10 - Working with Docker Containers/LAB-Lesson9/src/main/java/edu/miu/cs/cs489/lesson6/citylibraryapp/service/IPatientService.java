package edu.miu.cs.cs489.lesson6.citylibraryapp.service;

import edu.miu.cs.cs489.lesson6.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Patient;

import java.util.List;

public interface IPatientService {
    Patient save(Patient dentist);
    List<Patient> getAllPatients();
    Patient getPatientById(int id);
    void deletePatientById(int id);
    Patient updatePatient(int id, PatientRequest patient);
   List<Patient> getPatientsWithSearchString(String searchString);
}
