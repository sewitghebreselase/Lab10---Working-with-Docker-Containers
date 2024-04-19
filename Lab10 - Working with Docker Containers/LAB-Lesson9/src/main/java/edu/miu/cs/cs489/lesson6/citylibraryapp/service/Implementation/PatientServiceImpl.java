package edu.miu.cs.cs489.lesson6.citylibraryapp.service.Implementation;

import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.AddressRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.PatientRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Address;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Patient;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.IPatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;

    public PatientServiceImpl(PatientRepository patientRepository, AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }

    public Patient save(Patient patient) {
        addressRepository.save(patient.getAddress());
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePatientById(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(int id, PatientRequest patientRequest) {
        var updatePatient =  patientRepository.findById(id).orElse(null);
        if (updatePatient != null) {
            updatePatient.setFirstName(patientRequest.getFirstName());
            updatePatient.setLastName(patientRequest.getLastName());
            updatePatient.setEmail(patientRequest.getEmail());
            updatePatient.setPhoneNumber(patientRequest.getPhoneNumber());
            if (updatePatient.getAddress() != null) {
                updatePatient.getAddress().setCity(patientRequest.getAddress().getCity());
                updatePatient.getAddress().setState(patientRequest.getAddress().getState());
                updatePatient.getAddress().setStreet(patientRequest.getAddress().getStreet());
                updatePatient.getAddress().setZipCode(patientRequest.getAddress().getZipCode());
            }else {
                var address = new Address();
                address.setCity(patientRequest.getAddress().getCity());
                address.setState(patientRequest.getAddress().getState());
                address.setStreet(patientRequest.getAddress().getStreet());
                address.setZipCode(patientRequest.getAddress().getZipCode());
                updatePatient.setAddress(address);
                addressRepository.save(address);
            }
        }
        assert updatePatient != null;
        return patientRepository.save(updatePatient);
    }


    @Override
    public List<Patient> getPatientsWithSearchString(String searchString) {
        return patientRepository.searchPatients(searchString);
    }
}
