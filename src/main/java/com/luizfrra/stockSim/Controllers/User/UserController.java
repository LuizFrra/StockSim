package com.luizfrra.stockSim.Controllers.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizfrra.stockSim.Controllers.Commons.BaseController;
import com.luizfrra.stockSim.DTOs.User.UserDTO;
import com.luizfrra.stockSim.DTOs.UserQuotes.OperationType;
import com.luizfrra.stockSim.DTOs.UserQuotes.UserQuotesDTO;
import com.luizfrra.stockSim.EntitiesDomain.Message.Message;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotesKey;
import com.luizfrra.stockSim.Responses.InvalidFieldsResponse;
import com.luizfrra.stockSim.Services.Email.IEmailService;
import com.luizfrra.stockSim.Services.User.UserService;
import com.luizfrra.stockSim.Services.UserQuotes.UserQuotesService;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<User, UserDTO> {

    @Autowired
    public IEmailService emailService;

    @Autowired
    public UserQuotesService userQuotesService;

    @Autowired
    public ModelMapper modelMapper;

    public UserController(UserService userService) {
        super(userService, UserController.class);
    }

    @PostMapping("/quote")
    public ResponseEntity operateQuote(@RequestBody UserQuotesDTO userQuotesDTO) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        RefreshableKeycloakSecurityContext keycloakCtx = (RefreshableKeycloakSecurityContext) securityContext
                .getAuthentication().getCredentials();
        String email = keycloakCtx.getToken().getEmail();
        String userId = securityContext.getAuthentication().getName();
        userQuotesDTO.setUserId(userId);
        int numberOfQuotes = userQuotesDTO.getQuantity();

        if(!userQuotesDTO.isValide())
            return new ResponseEntity(new InvalidFieldsResponse(userQuotesDTO.getValidationErros()), HttpStatus.BAD_REQUEST);

        UserQuotes userQuotes = new UserQuotes();
        userQuotes.setId(new UserQuotesKey(userQuotesDTO.userId, userQuotesDTO.getSymbol()));
        userQuotes.setNumberOfQuotes(userQuotesDTO.quantity);

        String text = "", subject = "";

        if(userQuotesDTO.operationType == OperationType.BUY) {
            userQuotes = userQuotesService.save(userQuotes);
            text = "Compra de Ativo";
            subject = "Compra de " + numberOfQuotes + " Ativos " +
                    userQuotes.getQuote().getSymbol() + " Realizada";
        }
        else if(userQuotesDTO.operationType == OperationType.SELL) {
            userQuotes = userQuotesService.sellQuote(userQuotes);
            text = "Venda de Ativo";
            subject = "Venda de " + numberOfQuotes + " Ativos " +
                    userQuotes.getQuote().getSymbol() + " Realizada";
        }

        emailService.sendEmail(new Message(email, text, subject));
        return new ResponseEntity(userQuotes, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity save(UserDTO dto) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
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
