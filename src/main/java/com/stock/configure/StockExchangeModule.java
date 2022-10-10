package com.stock.configure;

import com.google.inject.AbstractModule;
import com.stock.service.CalculateCharges;
import com.stock.service.StockExchange;
import com.stock.service.impl.CalculateChargesImpl;
import com.stock.service.impl.StockExchangeImpl;
import com.stock.service.impl.StockExchangeLockImpl;

/**
 * StockExchangeModule
 */
public class StockExchangeModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(CalculateCharges.class).to(CalculateChargesImpl.class);
    //support 2 implements for stockExchange
    bind(StockExchange.class).to(StockExchangeImpl.class);
    //bind(StockExchange.class).to(StockExchangeLockImpl.class);
  }

}
