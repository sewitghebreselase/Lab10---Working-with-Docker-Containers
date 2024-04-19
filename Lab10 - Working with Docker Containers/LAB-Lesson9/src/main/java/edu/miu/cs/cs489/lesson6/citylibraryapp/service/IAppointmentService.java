package edu.miu.cs.cs489.lesson6.citylibraryapp.service;

import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Appointment;

import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    List<Appointment> findAll();
}