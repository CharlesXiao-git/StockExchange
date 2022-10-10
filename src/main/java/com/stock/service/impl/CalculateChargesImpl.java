package com.stock.service.impl;

import com.stock.model.ExchangeType;
import com.stock.service.CalculateCharges;
import java.math.BigDecimal;
import lombok.Data;

/**
 * all calculate charges business, will be implemented here
 */
@Data
public class CalculateChargesImpl implements CalculateCharges {

  /**
   * get exchangeType from outside and subject to change
   */
  private ExchangeType exchangeType;
  /**
   * At present it is simple, but it could be complex to calculate the charges in the future
   * calculateCharges
   * @return
   */
  public BigDecimal calculateCharges(){
    if(exchangeType.name().equalsIgnoreCase("CXA")){
      return new BigDecimal("0.05");
    }else if(exchangeType.name().equalsIgnoreCase("ASX")){
      return new BigDecimal("0.07");
    }
    return new BigDecimal(0d);
  }
}
