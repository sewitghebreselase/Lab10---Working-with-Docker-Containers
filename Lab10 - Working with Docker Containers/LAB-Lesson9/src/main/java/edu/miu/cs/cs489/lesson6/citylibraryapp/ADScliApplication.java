package edu.miu.cs.cs489.lesson6.citylibraryapp;

import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.RoleRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.UserRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.*;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ADScliApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ADScliApplication.class, args);
    }

    private final IDentistService dentistService;
    private final IPatientService patientService;
    private final ISurgeryService surgeryService;
    private final IAppointmentService appointmentService;
    private final IAddressService addressService;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public ADScliApplication(IDentistService dentistService, IPatientService patientService,
                             ISurgeryService surgeryService, IAppointmentService appointmentService, IAddressService addressService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.dentistService = dentistService;
        this.patientService = patientService;
        this.surgeryService = surgeryService;
        this.appointmentService = appointmentService;
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create addresses
        Address address1 = new Address(null, "123 Main St", "New York", "NY", "10001");
        Address address2 = new Address(null, "456 Elm St", "Los Angeles", "CA", "90001");
        Address address3 = new Address(null, "789 Oak St", "Chicago", "IL", "60601");
        Address address4 = new Address(null, "1000 N 4th St", "Fairfield", "IA", "52557");

        // Save addresses
        addressService.save(address1);
        addressService.save(address2);
        addressService.save(address3);
        addressService.save(address4);
        // Create surgeries
        Surgery surgery1 = new Surgery(null, "Surgery 1", address1, new ArrayList<>());
        Surgery surgery2 = new Surgery(null, "Surgery 2", address2, new ArrayList<>());

        // Save surgeries
        surgeryService.save(surgery1);
        surgeryService.save(surgery2);

        // Create dentists
        Dentist dentist1 = new Dentist(null, "Tony Smith", new ArrayList<>());
        Dentist dentist2 = new Dentist(null, "Helen Pearson", new ArrayList<>());

        // Save dentists
        dentistService.save(dentist1);
        dentistService.save(dentist2);

        // Create patients

        Patient patient1 = new Patient(null, "Jill", "Bell", "jill@example.com", "9876543210", address2, new ArrayList<>());


        // Save patients
        patientService.save(patient1);


        // Create appointments
        Appointment appointment1 = new Appointment(null, LocalDate.of(2013, 9, 12), surgery1, modelMapper().map(patient1, Patient.class), dentist1);


        // Save appointments
        appointmentService.save(appointment1);

        // List all appointments
        List<Appointment> appointmentList = appointmentService.findAll();
        System.out.println(appointmentList);

        var adminUser = userRepository.findByUsername("admin");
        if(adminUser.isEmpty()) {
            List<Role> listAdminRoles = new ArrayList<>();
            var adminRole = roleRepository.findByName("ROLE_ADMIN");
            if(adminRole.isEmpty()) {
                var newAdminRole =  new Role(null, "ROLE_ADMIN");
                listAdminRoles.add(newAdminRole);
            } else {
                listAdminRoles.add(adminRole.get());
            }
            User newAdminUser = new User(null, "Admin", "Admin", "Admin", "admin",
                    passwordEncoder.encode("test123"), "admin@exmaple.com",
                    true,true, true, true);
            newAdminUser.setRoles(listAdminRoles);
            userRepository.save(newAdminUser);
        }

    }

}
