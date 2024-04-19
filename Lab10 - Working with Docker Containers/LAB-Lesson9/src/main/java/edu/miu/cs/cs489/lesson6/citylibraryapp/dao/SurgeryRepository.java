package edu.miu.cs.cs489.lesson6.citylibraryapp.dao;

import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
