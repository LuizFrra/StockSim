package com.luizfrra.stockSim.HGBrasil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HGAPIConsumer {

    private static Logger logger = LoggerFactory.getLogger(HGAPIConsumer.class);

    private static final String baseURI = "https://api.hgbrasil.com/finance/";
    private static final String key = "e9eac40c";

    public RestTemplate restTemplate;

    public Quote getQuote(String symbol) {
        symbol = symbol.toUpperCase();
        String uri = baseURI + "stock_price?key=" + key + "&symbol=" + symbol;
        String json = restTemplate.getForObject(uri, String.class);
        Map fullRequest = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            fullRequest = mapper.readValue(json, new TypeReference<HashMap>() {});
        } catch (JsonProcessingException e) {
            logger.info("Exception converting {} to map", json, e);
            return null;
        }

        LinkedHashMap quoteJSON = (LinkedHashMap) ((LinkedHashMap) fullRequest.get("results")).get(symbol);

        String name = (String)quoteJSON.get("name");
        double price = (double)quoteJSON.get("price");
        String date = (String) quoteJSON.get("updated_at");
        Date updatedAt = null;

        try {
            updatedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            logger.info("Exception Parsing {} to Date", date, e);
            return null;
        }

        return new Quote(symbol, name, price, updatedAt);
    }

}
