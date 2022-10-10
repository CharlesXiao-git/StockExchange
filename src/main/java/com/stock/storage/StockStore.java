package com.stock.storage;

import com.stock.exception.InsufficientUnitsException;
import com.stock.exception.InvalidCodeException;
import com.stock.model.Stock;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * maintain stock data, can be replaced by db
 */
@Data
public class StockStore {
    //as buy method is synchronized. here use Hashmap
    Map<String,Stock>  stockStore= new HashMap<String,Stock> ();

    public StockStore(){
        initStore();
    }

    /**
     * initStore
     */
    private void initStore(){
        Stock stockcba = new Stock();
        stockcba.setTotal(100);
        stockcba.setUnits(100);
        stockcba.setCode("CBA");
        Stock stocknab = new Stock();
        stocknab.setTotal(80);
        stocknab.setUnits(80);
        stocknab.setCode("NAB");
        Stock stockqan = new Stock();
        stockqan.setTotal(50);
        stockqan.setUnits(50);
        stockqan.setCode("QAN");
        addStockToStore(stockcba);
        addStockToStore(stocknab);
        addStockToStore(stockqan);
    }

    /**
     * add stock To store
     * @param stock
     */
    public void addStockToStore(Stock stock) {
        String code = stock.getCode();
        stockStore.put(code,stock);
    }

    /**
     * reduceUnits
     * @param code
     * @param units
     * @throws InsufficientUnitsException
     * @throws InvalidCodeException
     */
    public void reduceUnits(String code, Integer units) throws InsufficientUnitsException, InvalidCodeException {
        if(this.stockStore.containsKey(code)){
            Stock stock = this.stockStore.get(code);
            Integer stockUnits = stock.getUnits();
            if(units <= stockUnits){
                stockUnits = stockUnits - units;
                stock.setUnits(stockUnits);
            }else{
                throw new InsufficientUnitsException("insufficient volume is available");
            }
        }else{
            throw new InvalidCodeException("invalid code");
        }
    }

    /**
     * addUnits
     * @param code
     * @param units
     * @throws InsufficientUnitsException
     * @throws InvalidCodeException
     */
    public void addUnits(String code, Integer units) throws InsufficientUnitsException, InvalidCodeException{
        if(this.stockStore.containsKey(code)){
            Stock stock = this.stockStore.get(code);
            Integer stockUnits = stock.getUnits();
            Integer totalUnits = stock.getTotal();
            if(stockUnits + units <=  totalUnits){
                stockUnits = stockUnits + units;
                stock.setUnits(stockUnits);
            }else{
                throw new InsufficientUnitsException("can not sell more than total");
            }
        }else{
            throw new InvalidCodeException("invalid code");
        }
    }
}
