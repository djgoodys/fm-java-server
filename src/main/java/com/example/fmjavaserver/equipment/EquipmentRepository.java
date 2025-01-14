package com.example.fmjavaserver.equipment;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findById(Long _id);

    Optional<Equipment> findByunitname(String unitname);

    // This method will return all equipment records
    List<Equipment> findAll();
}
