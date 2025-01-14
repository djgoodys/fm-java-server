package com.example.fmjavaserver.filters;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FiltersRepository extends JpaRepository<Filters, Long> {

    Optional<Filters> findById(Long _id);

    Optional<Filters> findByfiltersize(String filtersize);

    // This method will return all fllters records
    List<Filters> findAll();
}
