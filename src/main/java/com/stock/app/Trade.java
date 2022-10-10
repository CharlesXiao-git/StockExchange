package com.stock.app;

import com.google.inject.Inject;
import com.stock.exception.InsufficientUnitsException;
import com.stock.exception.InvalidCodeException;
import com.stock.service.StockExchange;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import lombok.Data;

@Data
public class Trade implements Runnable{
  StockExchange stockExchange;

  @Inject
  public Trade(StockExchange stockExchange) {
    this.stockExchange = stockExchange;
  }

  @Override
  public void run() {
    // get stocks current state in store
    Map result = this.getStockExchange().getOrderBookTotalVolume();
    System.out.println("Before run,get the state in store is : " + result);
    System.out.println("exchangeType is: " + this.getStockExchange().getCalculateCharges().getExchangeType());
    Random random = new Random();
    // run buy
    int times = random.nextInt(10);
    System.out.println(Thread.currentThread().getName() + " run buy " + times);
    for(int i=0; i< times; i++){
      try{
        int units = random.nextInt(10);
        stockExchange.buy("NAB",units);
      }catch (InvalidCodeException e){
        System.out.println(e.getMessage());
      }catch (InsufficientUnitsException e) {
        System.out.println(e.getMessage());
      }
    }
    // run sell
    times = random.nextInt(10);
    System.out.println(Thread.currentThread().getName() + " run sell " + times);
    for(int i=0; i< times; i++){
      try{
        int units = random.nextInt(10);
        stockExchange.sell("NAB",units);
      }catch (InvalidCodeException e){
        System.out.println(e.getMessage());
      }catch (InsufficientUnitsException e) {
        System.out.println(e.getMessage());
      }
    }
    // get stock exchange cost
    BigDecimal totalcost = this.getStockExchange().getTradingCosts();
    System.out.println("Total stock exchange charges is : " + totalcost + " $");

    result = this.getStockExchange().getOrderBookTotalVolume();
    System.out.println("After run, get the state in store is : " + result);
  }

}
