package com.epam.esm.dto.converter;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Component;

/**
 * User DTO/ENTITY converter class.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class UserConverter {

    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getCash()
        );
    }
}
