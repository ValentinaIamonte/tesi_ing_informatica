package it.ElectricalColumnManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.ElectricalColumnManager.dto.ElectricalColumnDto;
import it.ElectricalColumnManager.entity.ChargingModeType;
import it.ElectricalColumnManager.entity.ElectricalColumn;
import it.ElectricalColumnManager.entity.ElectricalColumnStateEnum;
import it.ElectricalColumnManager.entity.Location;
import it.ElectricalColumnManager.entity.Reservation;
import it.ElectricalColumnManager.entity.User;
import it.ElectricalColumnManager.repository.ChargingModeTypeRepository;
import it.ElectricalColumnManager.repository.ElectricalColumnRepository;
import it.ElectricalColumnManager.repository.LocationRepository;
import it.ElectricalColumnManager.repository.UserRepository;
import it.ElectricalColumnManager.utils.CSVUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ElectricalColumnServiceImpl implements ElectricalColumnService {

    @Autowired
    private ElectricalColumnRepository electricalColumnRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChargingModeTypeRepository chargingModeTypeRepository;

    public ElectricalColumn saveNewElectricalColumn(ElectricalColumnDto dto) {

        Optional<ElectricalColumn> electricalColumn = electricalColumnRepository
                .findBySerialNumber(dto.getSerialNumber());
        if (electricalColumn.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Electrical Column serial number already present");
        }

        Optional<ChargingModeType> chargingModeType = chargingModeTypeRepository.findById(dto.getIdChargingModeType());
        if (!chargingModeType.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ChargingModeType with id: " + dto.getIdChargingModeType() + "not exists");
        }
        ElectricalColumn columnToSave = ElectricalColumn.builder()
                .serialNumber(dto.getSerialNumber())
                .type(chargingModeType.get())
                .state(ElectricalColumnStateEnum.INITIALIZED)
                .build();
        columnToSave = electricalColumnRepository.save(columnToSave);

        Location location = Location.builder()
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .electricalColumn(columnToSave)
                .build();
        location = locationRepository.save(location);

        return columnToSave;

    }

    @Override
    public void populateDbWithChargingModeType() throws CsvValidationException, NumberFormatException, IOException {
        List<ChargingModeType> chargingModeTypes = CSVUtils.csvToChargingModeType();
        chargingModeTypeRepository.saveAll(chargingModeTypes);

    }

    @Override
    public Reservation bookElecticalColumn(Long idUser, Long idColumn) {

        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Optional<ElectricalColumn> column = electricalColumnRepository.findById(idColumn);
        if (column.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Electrical column not found");
        }
        if (!column.get().getState().equals(ElectricalColumnStateEnum.AVAILABLE)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Electrical column is not avaiable");
        }

        Reservation reservation = Reservation.builder()
                .user(user.get())
                .electricalColumn(column.get())
                .reservetionDate(new Date())
                .build();
        column.get().setState(ElectricalColumnStateEnum.RESERVED);
        electricalColumnRepository.save(column.get());
        return reservation;

    }

    @Override
    public void initElectricalColumn(Long idColumn) {

        Optional<ElectricalColumn> column = electricalColumnRepository.findById(idColumn);
        if (column.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Electrical column not found");
        }
        if (!column.get().getState().equals(ElectricalColumnStateEnum.INITIALIZED)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Electrical column is not initialized");
        }
        column.get().setState(ElectricalColumnStateEnum.AVAILABLE);
        electricalColumnRepository.save(column.get());
        return;

    }

    @Override
    public void setUnavaiableStateColumn(Long idColumn) {
    
        Optional<ElectricalColumn> column = electricalColumnRepository.findById(idColumn);
        if (column.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Electrical column not found");
        }
        column.get().setState(ElectricalColumnStateEnum.UNAVAIBLE);
        electricalColumnRepository.save(column.get());
        return;
    }

    @Override
    public void setBusyStateColumn(Long idColumn) {

        Optional<ElectricalColumn> column = electricalColumnRepository.findById(idColumn);
        if (column.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Electrical column not found");
        }
        if (!column.get().getState().equals(ElectricalColumnStateEnum.RESERVED)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Electrical column is not reserved");
        }
        column.get().setState(ElectricalColumnStateEnum.BUSY);
        electricalColumnRepository.save(column.get());
        return;
       
    }

    @Override
    public void setAvaiableStateColumn(Long idColumn) {
       Optional<ElectricalColumn> column = electricalColumnRepository.findById(idColumn);
        if (column.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Electrical column not found");
        }
        column.get().setState(ElectricalColumnStateEnum.AVAILABLE);
        electricalColumnRepository.save(column.get());
        return;

    }

}
