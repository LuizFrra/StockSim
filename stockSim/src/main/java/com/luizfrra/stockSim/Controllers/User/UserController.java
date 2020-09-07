package com.luizfrra.stockSim.Controllers.User;

import com.luizfrra.stockSim.DTOs.User.UserDTO;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.Responses.Commons.InvalidFieldsResponse;
import com.luizfrra.stockSim.Responses.User.UserResponse;
import com.luizfrra.stockSim.Services.User.UserService;
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

    @Autowired
    public ModelMapper modelMapper;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserDTO userDto) {
        if(!userDto.isValide())
            return new ResponseEntity(new InvalidFieldsResponse(userDto.getValidationErros()), HttpStatus.BAD_REQUEST);

        User user = modelMapper.map(userDto, User.class);
        userDto.cleanCredentials();

        if(userService.save(user) == null)
            return new ResponseEntity(new UserResponse("User Already Exist", userDto), HttpStatus.CONFLICT);

        return new ResponseEntity(new UserResponse("User Created", userDto), HttpStatus.CREATED);
    }
}
