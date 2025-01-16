package com.example.fmjavaserver.users;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long _id);

    Optional<Users> findByusername(String username);

    // This method will return all fllters records
    List<Users> findAll();
}
