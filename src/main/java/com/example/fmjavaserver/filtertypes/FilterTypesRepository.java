package com.example.fmjavaserver.filtertypes;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FilterTypesRepository extends JpaRepository<FilterTypes, Long> {

    Optional<FilterTypes> findById(Long _id);
    Optional<FilterTypes> findByType(String type);
}


