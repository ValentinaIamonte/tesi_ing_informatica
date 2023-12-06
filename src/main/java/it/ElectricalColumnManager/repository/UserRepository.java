package it.ElectricalColumnManager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ElectricalColumnManager.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>findByFiscalCode(String fiscalCode);
    Optional<User>findByEmail(String email);
    
}
