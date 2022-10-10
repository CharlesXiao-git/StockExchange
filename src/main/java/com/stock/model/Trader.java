package com.stock.model;

import java.util.List;
import lombok.Data;

/**
 * no requirement for trader
 * Trader
 */
@Data
public class Trader {
  private String name;
  private String address;
  private List stockHoldList;
}
