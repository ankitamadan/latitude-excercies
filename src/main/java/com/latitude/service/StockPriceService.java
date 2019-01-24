package com.latitude.service;

import java.util.Optional;

public interface StockPriceService {

    Optional<Integer> getMaxProfit(Integer[] stockPrices);

}
