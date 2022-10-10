package com.stock.service;

import com.stock.model.ExchangeType;
import java.math.BigDecimal;

/**
 * CalculateCharges interface
 */
public interface CalculateCharges {

  /**
   * calculateCharges
   * @return BigDecimal
   */
  public BigDecimal calculateCharges();

  /**
   * can subject to change
   */
  public void setExchangeType(ExchangeType exchangeType);

  public ExchangeType getExchangeType();

}
