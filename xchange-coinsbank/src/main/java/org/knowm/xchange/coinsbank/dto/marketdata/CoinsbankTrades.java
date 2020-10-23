package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankTrades extends CoinsbankResponse {
  private final List<CoinsbankTrade> trades;

  public CoinsbankTrades(@JsonProperty("trades") List<CoinsbankTrade> trades) {
    this.trades = trades;
  }

  public List<CoinsbankTrade> getTrades() {
    return trades;
  }
}
