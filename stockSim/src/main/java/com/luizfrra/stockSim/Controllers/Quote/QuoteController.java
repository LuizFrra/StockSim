package com.luizfrra.stockSim.Controllers.Quote;

import com.luizfrra.stockSim.Controllers.Commons.BaseController;
import com.luizfrra.stockSim.DTOs.Quote.QuoteDTO;
import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.Services.Quote.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quote")
public class QuoteController extends BaseController<Quote, QuoteDTO> {

    @Autowired
    public ModelMapper modelMapper;

    public QuoteController(QuoteService quoteService) {
        super(quoteService, QuoteController.class);
    }

    @Override
    public Quote convertFromDtoToMain(QuoteDTO dto) {
        return modelMapper.map(dto, Quote.class);
    }

    @Override
    public QuoteDTO convertFromMainToDto(Quote data) {
        return modelMapper.map(data, QuoteDTO.class);
    }

}
