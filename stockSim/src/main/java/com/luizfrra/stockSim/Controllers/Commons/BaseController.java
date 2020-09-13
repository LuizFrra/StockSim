package com.luizfrra.stockSim.Controllers.Commons;

import com.luizfrra.stockSim.DTOs.Commons.CommonDTO;
import com.luizfrra.stockSim.Responses.InvalidFieldsResponse;
import com.luizfrra.stockSim.Responses.ObjectResponse;
import com.luizfrra.stockSim.Services.Commons.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public abstract class BaseController<C, R extends CommonDTO> {

    IBaseService baseService;

    private Logger logger;

    public BaseController(IBaseService baseService, Class c) {
        this.baseService = baseService;
        logger = LoggerFactory.getLogger(c);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody R dto) {
        if(!dto.isValide())
            return new ResponseEntity(new InvalidFieldsResponse(dto.getValidationErros()), HttpStatus.BAD_REQUEST);

        C data = (C) baseService.save(convertFromDtoToMain(dto));

        if(data == null)
            return new ResponseEntity(new ObjectResponse("Data Already Exist.", dto), HttpStatus.CONFLICT);

        dto = convertFromMainToDto(data);

        return new ResponseEntity(new ObjectResponse("Data Created", dto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity get(@PathVariable String id) {
        Optional<C> data = baseService.findById(id);

        if(data.isEmpty())
            return new ResponseEntity(new ObjectResponse("Data Not Found", id), HttpStatus.NOT_FOUND);

        return new ResponseEntity(new ObjectResponse("Data Found", data.get()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(new ObjectResponse("All Data", baseService.findAll()), HttpStatus.OK);
    }

    public abstract C convertFromDtoToMain(R dto);

    public abstract R convertFromMainToDto(C data);

}
