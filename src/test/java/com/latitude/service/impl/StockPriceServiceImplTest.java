package com.latitude.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class StockPriceServiceImplTest {

    @InjectMocks
    StockPriceServiceImpl stockPriceService;

    @Test
    public void shouldReturnMaxProfit(){
        Integer[] stockPrices = {10, 7, 5, 8, 11, 9};
        Optional<Integer> maxPrice = stockPriceService.getMaxProfit(stockPrices);
        assertThat(maxPrice.get(), is(6));
    }

    @Test
    public void shouldNotReturnMaxProfitWhenStockPricesProvidedAreLessThan2Two(){
        Integer[] stockPrices = {10};
        Optional<Integer> maxPrice = stockPriceService.getMaxProfit(stockPrices);
        assertThat(maxPrice, is(Optional.empty()));
    }

}
