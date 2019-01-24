package com.latitude.controller;

import com.latitude.service.StockPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StockPriceController.class)
public class StockPriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StockPriceService stockPriceService;

    @Test
    public void shouldNoReturnMaximumProfitWhenInvalidStockPrices() throws Exception {
        mvc.perform(
                post("/maximumStockPrice")
                        .param("stockPrices", String.valueOf(8)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldNoReturnMaximumProfitWhenMaximumProfitCannotBeComputed() throws Exception {
        Integer[] stockPrices = {9, 7, 5};
        when(stockPriceService.getMaxProfit(stockPrices)).thenReturn(Optional.empty());
        mvc.perform(post("/maximumStockPrice/")
                .param("stockPrices", String.valueOf(9))
                .param("stockPrices", String.valueOf(7))
                .param("stockPrices", String.valueOf(5)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnMaximumProfit() throws Exception {
        Integer[] stockPrices = {10, 7, 5, 8, 11, 9};
        when(stockPriceService.getMaxProfit(stockPrices)).thenReturn(Optional.of(6));
        mvc.perform(post("/maximumStockPrice/")
                .param("stockPrices", String.valueOf(10))
                .param("stockPrices", String.valueOf(7))
                .param("stockPrices", String.valueOf(5))
                .param("stockPrices", String.valueOf(8))
                .param("stockPrices", String.valueOf(11))
                .param("stockPrices", String.valueOf(9)))
                .andExpect(status().isOk());
    }


}
