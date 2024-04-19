package edu.miu.cs.cs489.lesson6.citylibraryapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dentistId;

    private String dentistName;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments;

    @Override
    public String toString() {
        return "Dentist{" +
                "dentistId=" + dentistId +
                ", dentistName='" + dentistName + '\'' +
                '}';
    }
}
