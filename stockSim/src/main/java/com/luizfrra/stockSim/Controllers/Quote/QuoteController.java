package com.luizfrra.stockSim.Controllers.Quote;

import com.luizfrra.stockSim.DTOs.Quote.QuoteDTO;
import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.Responses.Commons.InvalidFieldsResponse;
import com.luizfrra.stockSim.Responses.Quote.QuoteResponse;
import com.luizfrra.stockSim.Services.Quote.QuoteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quote")
public class QuoteController {

    @Autowired
    public QuoteService quoteService;

    @Autowired
    public ModelMapper modelMapper;

    public final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @PostMapping
    public ResponseEntity save(@RequestBody QuoteDTO quoteDto) {
        if(!quoteDto.isValide())
            return new ResponseEntity(new InvalidFieldsResponse(quoteDto.getValidationErros()), HttpStatus.BAD_REQUEST);

        Quote quote = quoteService.save(quoteDto.symbol);

        if(quote == null) {
            return new ResponseEntity(new QuoteResponse("Quote Already Exist.", quoteDto), HttpStatus.CONFLICT);
        }
        quoteDto = modelMapper.map(quote, QuoteDTO.class);
        return new ResponseEntity(new QuoteResponse("Quote Created", quoteDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{symbol}")
    public ResponseEntity get(@PathVariable String symbol) {
        Optional<Quote> quote = quoteService.findBySymbol(symbol);

        if(quote.isEmpty())
            return new ResponseEntity(new QuoteResponse("Quote Not Found", symbol), HttpStatus.NOT_FOUND);

        return new ResponseEntity(new QuoteResponse("Quote Found", quote.get()), HttpStatus.OK);
    }
}
