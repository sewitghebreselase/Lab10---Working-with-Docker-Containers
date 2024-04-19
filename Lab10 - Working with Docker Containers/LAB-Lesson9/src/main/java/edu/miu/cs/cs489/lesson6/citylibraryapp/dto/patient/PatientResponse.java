package edu.miu.cs.cs489.lesson6.citylibraryapp.dto.patient;

import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Address;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Appointment;


import java.util.List;

public record PatientResponse(
        Long patNo,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Address address,
        List<Appointment> appointments
) {
}
