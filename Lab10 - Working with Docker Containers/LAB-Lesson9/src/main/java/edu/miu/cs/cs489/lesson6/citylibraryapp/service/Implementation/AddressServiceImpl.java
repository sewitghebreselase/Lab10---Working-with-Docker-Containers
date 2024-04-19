package edu.miu.cs.cs489.lesson6.citylibraryapp.service.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.AddressRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.PatientRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Address;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Patient;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.IAddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;

    public AddressServiceImpl(AddressRepository addressRepository, PatientRepository patientRepository) {
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }



    @Override
    public String getAllAddressesWithPatientsInJSON() {
        // Retrieve all addresses sorted by city
        List<Address> sortedAddresses = addressRepository.findAllByOrderByCityAsc();

        // Convert addresses to JSON objects with associated patient data
        List<String> addressJSONList = sortedAddresses.stream()
                .map(this::convertAddressToJSON)
                .collect(Collectors.toList());

        // Join JSON strings with commas to form a JSON array
        String jsonArray = String.join(",\n", addressJSONList);

        // Wrap JSON array with square brackets to make it a valid JSON array
        return "[" + jsonArray + "]";
    }

    private String convertAddressToJSON(Address address) {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Fetch patient data associated with this address
            List<Patient> patients = patientRepository.findByAddress(address);

            // Create a map to represent the address along with patient data
            Map<String, Object> addressMap = new HashMap<>();
            addressMap.put("addressId", address.getAddressId());
            addressMap.put("street", address.getStreet());
            addressMap.put("city", address.getCity());
            addressMap.put("state", address.getState());
            addressMap.put("zipCode", address.getZipCode());

            // Include patient data if available
            if (!patients.isEmpty()) {
                List<Map<String, Object>> patientList = patients.stream()
                        .map(patient -> {
                            Map<String, Object> patientMap = new HashMap<>();
                            patientMap.put("patNo", patient.getPatNo());
                            patientMap.put("firstName", patient.getFirstName());
                            patientMap.put("lastName", patient.getLastName());
                            patientMap.put("email", patient.getEmail());
                            patientMap.put("phoneNumber", patient.getPhoneNumber());
                            return patientMap;
                        })
                        .collect(Collectors.toList());
                addressMap.put("patients", patientList);
            }

            // Convert the map to JSON string
            return objectMapper.writeValueAsString(addressMap);
        } catch (Exception e) {
            return "{}"; // Return empty JSON object in case of error
        }
    }
}