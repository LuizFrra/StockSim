package com.luizfrra.stockSim.Controllers;

import com.luizfrra.stockSim.DTOs.UserDTO;
import com.luizfrra.stockSim.EntitiesDomain.UserDomain;
import com.luizfrra.stockSim.Responses.User.UserCreateResponse;
import com.luizfrra.stockSim.Responses.User.UserInvalidFieldsResponse;
import com.luizfrra.stockSim.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserDTO userDto) {
        if(!userDto.isValide())
            return new ResponseEntity(new UserInvalidFieldsResponse(userDto.getValidationErros()), HttpStatus.resolve(400));

        ModelMapper modelMapper = new ModelMapper();
        UserDomain user = modelMapper.map(userDto, UserDomain.class);
        userDto.cleanCredentials();

        if(userService.save(user) == null)
            return new ResponseEntity(new UserCreateResponse("User Already Exist", userDto), HttpStatus.resolve(409));

        return new ResponseEntity(new UserCreateResponse("User Created", userDto), HttpStatus.resolve(201));
    }
}
