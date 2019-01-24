package com.latitude.service.impl;

import com.latitude.service.StockPriceService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    @Override
    public Optional<Integer> getMaxProfit(Integer[] stockPrices) {
        return !isValidNumberOfStockPrices(stockPrices) ? Optional.empty() : getMaximumProfitFromStockPrices(stockPrices);
    }

    private Optional<Integer> getMaximumProfitFromStockPrices(Integer[] stockPrices) {
        Integer minimumPrice = stockPrices[0];
        Integer maxProfit = stockPrices[1] - stockPrices[0];

        //We need to start at second index because we can't sell at the first time since we need to buy first
        //Hence, we can't buy and sell at the same time
        for (int i = 1; i < stockPrices.length; i++) {
            Integer currentPrice = stockPrices[i];

            //potential profit if we bought at minimum price and sold at max
            Integer potentialProfit = currentPrice - minimumPrice;

            //Updating maximum profit
            maxProfit = Arrays.asList(maxProfit, potentialProfit).stream().max(Integer::compare).get();

            //Updating minimum price so its always the lowest price
            minimumPrice = Arrays.asList( minimumPrice, currentPrice).stream().min(Integer::compare).get();
        }
        return Optional.of(maxProfit);
    }


    private Boolean isValidNumberOfStockPrices(Integer[] stockPrices){
        return stockPrices.length > 2 ? true : false;
    }


}
