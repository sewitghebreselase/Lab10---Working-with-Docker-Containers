package edu.miu.cs.cs489.lesson6.citylibraryapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "surgery_no")
    private Surgery surgery;

    @ManyToOne
    @JoinColumn(name = "patientNo")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentistId")
    private Dentist dentist;

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", appointmentDate=" + appointmentDate +
                ", surgery=" + surgery +
                ", patient=" + patient +
                ", dentist=" + dentist +
                '}';
    }
}
