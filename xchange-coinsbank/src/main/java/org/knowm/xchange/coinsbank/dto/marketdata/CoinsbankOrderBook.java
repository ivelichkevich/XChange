package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankOrderBook {
  private final List<CoinsbankOrder> asks;
  private final List<CoinsbankOrder> bids;

  public CoinsbankOrderBook(
      @JsonProperty("asks") List<CoinsbankOrder> asks,
      @JsonProperty("bids") List<CoinsbankOrder> bids) {
    this.asks = asks;
    this.bids = bids;
  }

  public List<CoinsbankOrder> getAsks() {
    return asks;
  }

  public List<CoinsbankOrder> getBids() {
    return bids;
  }

  @Override
  public String toString() {
    return "CoinsbankOrderBook{" + "asks=" + asks + ", bids=" + bids + '}';
  }

  public static class Container extends CoinsbankResponse {
    private final CoinsbankOrderBook orderBook;
    private final String symbol;

    public Container(@JsonProperty("orderbook") CoinsbankOrderBook orderBook, @JsonProperty("symbol") String symbol) {
      this.orderBook = orderBook;
      this.symbol = symbol;
    }

    public CoinsbankOrderBook getOrderBook() {
      return orderBook;
    }

    public String getSymbol() {
      return symbol;
    }
  }
}
