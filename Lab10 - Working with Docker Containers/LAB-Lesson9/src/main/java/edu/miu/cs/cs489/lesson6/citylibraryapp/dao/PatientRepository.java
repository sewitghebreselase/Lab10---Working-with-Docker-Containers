package edu.miu.cs.cs489.lesson6.citylibraryapp.dao;

import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Address;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(p.firstName) LIKE %:searchString% OR " +
            "LOWER(p.lastName) LIKE %:searchString% OR " +
            "LOWER(p.email) LIKE %:searchString% OR " +
            "LOWER(p.phoneNumber) LIKE %:searchString% OR " +
            "LOWER(CONCAT(p.address.street, ' ', p.address.city, ' ', p.address.state, ' ', p.address.zipCode)) LIKE %:searchString%")
    List<Patient> searchPatients(@Param("searchString") String searchString);

    List<Patient> findByAddress(Address address);
}