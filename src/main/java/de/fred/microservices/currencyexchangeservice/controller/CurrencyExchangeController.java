package de.fred.microservices.currencyexchangeservice.controller;

import de.fred.microservices.currencyexchangeservice.Repository.CurrencyExchangeRepository;
import de.fred.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private Environment environment;
    private CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        Optional<CurrencyExchange> currencyExchangeOptional = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchangeOptional.isEmpty())
            throw new CurrencyExchangeException("Currency Exchange für " + from + " in " + to + " nicht gefunden.");

        CurrencyExchange currencyExchange = currencyExchangeOptional.get();
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
