package com.stock;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stock.app.Trade;
import com.stock.configure.StockExchangeModule;
import com.stock.model.ExchangeType;
import com.stock.model.Stock;


public class application{

  public static void main(String[] args) {

    Injector injector = Guice.createInjector(new StockExchangeModule());
    Trade trade = injector.getInstance(Trade.class);
    trade.getStockExchange().getCalculateCharges().setExchangeType(ExchangeType.ASX);
    Stock stock = new Stock();
    stock.setTotal(30);
    stock.setUnits(30);
    stock.setCode("ANZ");
    trade.getStockExchange().getStockStore().addStockToStore(stock);

    Thread thread1 = new Thread(trade);
    thread1.setName("trade1");
    thread1.start();

    Thread thread2 = new Thread(trade);
    thread2.setName("trade2");
    thread2.start();

    String exchangeType = args[0];
    if (exchangeType.equalsIgnoreCase("CXA")){
      trade.getStockExchange().getCalculateCharges().setExchangeType(ExchangeType.CXA);
    }else if (exchangeType.equalsIgnoreCase("ASX")){
      trade.getStockExchange().getCalculateCharges().setExchangeType(ExchangeType.ASX);
    }

  }
}
