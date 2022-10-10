package com.stock.service.impl;

import com.google.inject.Inject;
import com.stock.exception.InsufficientUnitsException;
import com.stock.exception.InvalidCodeException;
import com.stock.model.Stock;
import com.stock.model.TradeRecord;
import com.stock.model.TradeType;
import com.stock.model.Trader;
import com.stock.service.CalculateCharges;
import com.stock.service.StockExchange;
import com.stock.storage.StockStore;
import com.stock.storage.TradeRecordStore;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * all business related to stock exchanges will be implemented here.
 * It use ReentrantLock to make the application thread safe
 */
public class StockExchangeLockImpl  implements StockExchange {

  private StockStore stockStore = new StockStore();
  private TradeRecordStore tradeRecordStore = new TradeRecordStore();
  private ReentrantLock lock = new ReentrantLock();
  private CalculateCharges calculateCharges;
  private Trader trader;

  @Inject
  public StockExchangeLockImpl(CalculateCharges calculateCharges) {
    this.calculateCharges = calculateCharges;
  }

  /**
   * buy stock
   * @param code
   * @param units
   * @throws InsufficientUnitsException
   * @throws InvalidCodeException
   */
  public void buy(String code, Integer units)
      throws InsufficientUnitsException, InvalidCodeException {
    try{
      if (lock.tryLock(2, TimeUnit.SECONDS)) {
        System.out.println(Thread.currentThread().getName() +" get lock" );
        System.out.println(Thread.currentThread().getName() +" buy " + code +",units :" + units);
        stockStore.reduceUnits(code,units );
        int remain = stockStore.getStockStore().get(code).getUnits();
        System.out.println(Thread.currentThread().getName() + " buy remain:  " + remain);
        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setCode(code);
        tradeRecord.setUnits(units);
        tradeRecord.setType(TradeType.Buy);
        tradeRecord.setTrader(trader);
        tradeRecord.setCharges(calculateCharges.calculateCharges());
        tradeRecordStore.addTradeRecord(tradeRecord);
        Thread.sleep(1000);
        lock.unlock();
      }else{
        System.out.println(Thread.currentThread().getName() + " can not get lock");
      }
    }catch (InterruptedException e){
      e.printStackTrace();
    } finally {
      if (lock.isHeldByCurrentThread()){
        lock.unlock();
      }
    }
  }

  /**
   * sell stock
   * @param code
   * @param units
   * @throws InvalidCodeException
   * @throws InsufficientUnitsException
   */
  public void sell(String code, Integer units) throws InvalidCodeException,InsufficientUnitsException {
    try{
      if (lock.tryLock(2, TimeUnit.SECONDS)) {
        System.out.println(Thread.currentThread().getName() +" get lock" );
        System.out.println(Thread.currentThread().getName() +" sell " + code +",units :" + units);
        stockStore.addUnits(code,units );
        int remain = stockStore.getStockStore().get(code).getUnits();
        System.out.println(Thread.currentThread().getName() + " sell remain:  " + remain);
        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setCode(code);
        tradeRecord.setUnits(units);
        tradeRecord.setType(TradeType.Sell);
        tradeRecord.setTrader(trader);
        tradeRecord.setCharges(calculateCharges.calculateCharges());
        tradeRecordStore.addTradeRecord(tradeRecord);
        Thread.sleep(1000);
        lock.unlock();
      }else{
        System.out.println(Thread.currentThread().getName() + " can not get lock");
      }
    }catch (InterruptedException e){
      e.printStackTrace();
    } finally {
      if (lock.isHeldByCurrentThread()){
        lock.unlock();
      }
    }
  }

  /**
   * Report aggregate volume available for each code
   * @return
   */
  public Map<String, Integer> getOrderBookTotalVolume() {
    Map result = new HashMap<String,Integer>();
    Map store = stockStore.getStockStore();
    store.forEach((k,v)->{
      Stock stock = (Stock) v ;
      result.put(k, stock.getUnits());
    });
    return result;
  }

  /**
   * getTradingCosts
   * @return
   */
  public BigDecimal getTradingCosts() {
    BigDecimal result = new BigDecimal(0);
    for (TradeRecord tradeRecord : tradeRecordStore.getTradeRecordStore()){
      result = result.add(tradeRecord.getCharges());
    }
    return result;
  }

  @Override
  public CalculateCharges getCalculateCharges(){
    return this.calculateCharges;
  }

  @Override
  public StockStore getStockStore() {
    return this.stockStore;
  }
}
