package it.ElectricalColumnManager.service;

import it.ElectricalColumnManager.dto.UserDto;
import it.ElectricalColumnManager.entity.User;

public interface UserService {
    User saveNewUser(UserDto userDto);
    
}
