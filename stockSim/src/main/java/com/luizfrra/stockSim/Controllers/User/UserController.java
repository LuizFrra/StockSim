package com.luizfrra.stockSim.Controllers.User;

import com.luizfrra.stockSim.Controllers.Commons.BaseController;
import com.luizfrra.stockSim.DTOs.User.UserDTO;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.Services.User.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<User, UserDTO> {

    @Autowired
    public ModelMapper modelMapper;

    public UserController(UserService userService) {
        super(userService, UserController.class);
    }

    @Override
    public User convertFromDtoToMain(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDTO convertFromMainToDto(User data) {
        return modelMapper.map(data, UserDTO.class);
    }

}
