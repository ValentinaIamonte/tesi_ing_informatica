package it.ElectricalColumnManager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.ElectricalColumnManager.dto.UserDto;
import it.ElectricalColumnManager.entity.User;
import it.ElectricalColumnManager.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveNewUser(UserDto userDto) {

        Optional<User> userCheckFiscalCode = userRepository.findByFiscalCode(userDto.getFiscalCode());
        if (userCheckFiscalCode.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Fiscal Code already present");
        }
        Optional<User> userCheckEmail = userRepository.findByEmail(userDto.getEmail());
        if (userCheckEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already present");
        }

        User userToSave = User.builder()
                .email(userDto.getEmail())
                .fiscalCode(userDto.getFiscalCode())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .build();

        return userRepository.save(userToSave);

    }
}
