package com.stock.model;

import java.math.BigDecimal;
import lombok.Data;

/**
 * will use db to store in the future
 * TradeRecord
 */
@Data
public class TradeRecord {
  private Trader trader;
  private String code;
  private Integer units;
  private TradeType type;
  private ExchangeType xxchangeType;
  private BigDecimal charges;
}
