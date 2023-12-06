package it.ElectricalColumnManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ElectricalColumnManager.dto.ElectricalColumnDto;
import it.ElectricalColumnManager.entity.ElectricalColumn;
import it.ElectricalColumnManager.service.ElectricalColumnService;


@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ControllerManager {

    @Autowired
    private ElectricalColumnService electricalColumnService;

    @PostMapping("/insert_column")
    public ResponseEntity<Object> insertNewElectricalColumn(@RequestBody ElectricalColumnDto dto) {
     
        ElectricalColumn electricalColumn = electricalColumnService.saveNewElectricalColumn(dto);
        return ResponseEntity.ok().body(electricalColumn);
    }

    @GetMapping("/disable_column/{id}")
    public ResponseEntity<Object> disableElectricalColumn(@PathVariable Long id) {
     
        electricalColumnService.setUnavaiableStateColumn(id);
        return ResponseEntity.ok().body("OK");
    }
    @GetMapping("/init_column/{id}")
    public ResponseEntity<Object> initElectricalColumn(@PathVariable Long id) {
     
        electricalColumnService.initElectricalColumn(id);
        return ResponseEntity.ok().body("OK");
    }

   

}