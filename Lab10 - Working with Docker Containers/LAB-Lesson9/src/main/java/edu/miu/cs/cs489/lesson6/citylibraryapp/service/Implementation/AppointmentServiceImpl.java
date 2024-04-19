package edu.miu.cs.cs489.lesson6.citylibraryapp.service.Implementation;

import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.AppointementRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Appointment;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.IAppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointementRepository appointmentRepository;

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll(){return appointmentRepository.findAll();}

}