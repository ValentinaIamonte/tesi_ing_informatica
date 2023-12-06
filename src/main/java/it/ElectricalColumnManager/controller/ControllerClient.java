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

import it.ElectricalColumnManager.dto.UserDto;
import it.ElectricalColumnManager.entity.User;
import it.ElectricalColumnManager.service.ElectricalColumnService;
import it.ElectricalColumnManager.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/client")
public class ControllerClient {

    @Autowired
    private UserService userService;

    @Autowired
    private ElectricalColumnService electricalColumnService;

    @PostMapping()
    public ResponseEntity<Object> insertNewClient(@RequestBody UserDto userDto) {

        User user = userService.saveNewUser(userDto);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/book_column/{idColumn}/{idUser}")
    public ResponseEntity<Object> bookElectricalColumn(@PathVariable Long idColumn, @PathVariable Long idUser) {

        electricalColumnService.bookElecticalColumn(idUser, idColumn);
        return ResponseEntity.ok().body("OK");
    }

    
    @GetMapping("/busy_column/{idColumn}/{idUser}")
    public ResponseEntity<Object> busyElectricalColumn(@PathVariable Long idColumn, @PathVariable Long idUser) {

        electricalColumnService.setBusyStateColumn(idColumn);
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("/free_column/{idColumn}/{idUser}")
    public ResponseEntity<Object> freeElectricalColumn(@PathVariable Long idColumn, @PathVariable Long idUser) {

        electricalColumnService.setAvaiableStateColumn(idColumn);
        return ResponseEntity.ok().body("OK");
    }

}