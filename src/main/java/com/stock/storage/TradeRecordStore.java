package com.stock.storage;

import com.stock.model.TradeRecord;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * maintain trade record data, can be replaced by db
 */
@Data
public class TradeRecordStore {

  List<TradeRecord> tradeRecordStore = new ArrayList<TradeRecord>();

  /**
   * addTradeRecord
   * @param tradeRecord
   */
  public void addTradeRecord(TradeRecord tradeRecord){
    tradeRecordStore.add(tradeRecord);
  }

}
