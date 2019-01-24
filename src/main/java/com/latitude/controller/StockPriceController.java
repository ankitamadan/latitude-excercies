package com.latitude.controller;

import com.latitude.exception.InvalidNumberOfStockPriceException;
import com.latitude.exception.InvalidStockPricesException;
import com.latitude.service.StockPriceService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Stock Price REST Endpoint", description = "Shows maximum stock price")
public class StockPriceController {

    private final StockPriceService stockPriceService;

    public StockPriceController(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
    }

    @ApiOperation(value = "Get maximum profit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully computed maximum stock price"),
            @ApiResponse(code = 404, message = "Error in finding maximum profit"),
            @ApiResponse(code = 422, message = "Getting a profit requires at least 2 prices")})
    @PostMapping("/maximumStockPrice")
    public ResponseEntity<Integer> getMaxStockPrice(@RequestParam(value="stockPrices") Integer[] stockPrices)
            throws InvalidNumberOfStockPriceException, InvalidStockPricesException {
        isValidNumberOfStockPrices(stockPrices);
        Integer maxPrice = stockPriceService.getMaxProfit(stockPrices)
                .orElseThrow(() -> new InvalidStockPricesException("Error in finding maximum profit"));
        return ResponseEntity.ok().body(maxPrice);
    }

    private void isValidNumberOfStockPrices(Integer[] stockPrices) throws InvalidNumberOfStockPriceException {
        if(stockPrices.length < 2 ){
            throw new InvalidNumberOfStockPriceException("Getting a profit requires at least 2 prices");
        }
    }
}
