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
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surgeryNo;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments;


    @Override
    public String toString() {
        return "Surgery{" +
                "surgeryNo=" + surgeryNo +
                ", name='" + name + '\'' +
                '}';
    }
}
