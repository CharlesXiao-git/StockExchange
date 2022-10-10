package com.stock.model;

import lombok.Data;

/**
 * Stock
 */
@Data
public class Stock {
  private String name;
  private String code;
  private Integer units;
  private Integer total;
}
