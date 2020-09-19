package com.luizfrra.stockSim.Controllers.User;

import com.luizfrra.stockSim.Controllers.Commons.BaseController;
import com.luizfrra.stockSim.DTOs.User.UserDTO;
import com.luizfrra.stockSim.DTOs.UserQuotes.UserQuotesDTO;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotesKey;
import com.luizfrra.stockSim.Responses.InvalidFieldsResponse;
import com.luizfrra.stockSim.Services.User.UserService;
import com.luizfrra.stockSim.Services.UserQuotes.UserQuotesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<User, UserDTO> {

    @Autowired
    public UserQuotesService userQuotesService;

    @Autowired
    public ModelMapper modelMapper;

    public UserController(UserService userService) {
        super(userService, UserController.class);
    }

    @PostMapping("/{id}/buyquote")
    public ResponseEntity buyQuote(UserQuotesDTO userQuotesDTO) {

        if(!userQuotesDTO.isValide())
            return new ResponseEntity(new InvalidFieldsResponse(userQuotesDTO.getValidationErros()), HttpStatus.BAD_REQUEST);

        UserQuotes userQuotes = new UserQuotes();
        userQuotes.setId(new UserQuotesKey(userQuotesDTO.userId, userQuotesDTO.getSymbol()));
        userQuotes.setNumberOfQuotes(userQuotesDTO.quantity);

        return new ResponseEntity(userQuotesService.save(userQuotes), HttpStatus.OK);
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
