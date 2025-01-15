package com.example.fmjavaserver.equipment;

import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findById(Long _id);

    Optional<Equipment> findByunitname(String unitname);

    List<Equipment> findByassignedto(String assignedto);

    List<Equipment> findAll();

    @Query("SELECT e FROM Equipment e WHERE LOWER(e.unitname) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.location) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.areaserved) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.filtersize) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.belts) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.notes) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.filtertype) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.assignedto) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
           "OR LOWER(e.image) LIKE LOWER(CONCAT('%', :searchwords, '%'))")
    List<Equipment> searchByAllFields(@Param("searchwords") String searchwords);

     @Query("SELECT e FROM Equipment e WHERE e.filtersdue = :today")
    List<Equipment> findAllByFiltersDueToday(@Param("today") LocalDate today);
}
