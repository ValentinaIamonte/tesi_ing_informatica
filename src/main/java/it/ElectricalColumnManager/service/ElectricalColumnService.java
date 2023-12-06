package it.ElectricalColumnManager.service;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

import it.ElectricalColumnManager.dto.ElectricalColumnDto;
import it.ElectricalColumnManager.entity.ElectricalColumn;
import it.ElectricalColumnManager.entity.Reservation;


public interface ElectricalColumnService {
    ElectricalColumn saveNewElectricalColumn(ElectricalColumnDto dto);
    void populateDbWithChargingModeType() throws CsvValidationException, NumberFormatException, IOException;
    Reservation bookElecticalColumn(Long idUser, Long idColumn);
    void initElectricalColumn(Long idColumn);
    void setUnavaiableStateColumn(Long idColumn);
    void setBusyStateColumn(Long idColumn);
    void setAvaiableStateColumn(Long idColumn);

    
}
