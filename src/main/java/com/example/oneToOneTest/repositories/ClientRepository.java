package com.example.oneToOneTest.repositories;

import com.example.oneToOneTest.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
