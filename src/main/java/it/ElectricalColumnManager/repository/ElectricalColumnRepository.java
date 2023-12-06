package it.ElectricalColumnManager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ElectricalColumnManager.entity.ElectricalColumn;


public interface ElectricalColumnRepository extends JpaRepository<ElectricalColumn,Long> {

    Optional<ElectricalColumn>findBySerialNumber(String serialNumber);
    
    
}
