package com.stock.service;

import com.stock.exception.InsufficientUnitsException;
import com.stock.exception.InvalidCodeException;
import com.stock.storage.StockStore;
import java.math.BigDecimal;
import java.util.Map;

/**
 * StockExchange interface
 */
public interface StockExchange {

  void buy(String code, Integer units) throws InsufficientUnitsException, InvalidCodeException;

  void sell(String code, Integer units) throws InvalidCodeException,InsufficientUnitsException;

  Map<String, Integer> getOrderBookTotalVolume();

  BigDecimal getTradingCosts();

  CalculateCharges getCalculateCharges();

  StockStore getStockStore();

}
