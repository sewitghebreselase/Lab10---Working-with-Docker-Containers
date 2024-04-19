package edu.miu.cs.cs489.lesson6.citylibraryapp.dao;

import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByOrderByCityAsc();
}
