package org.knowm.xchange.coinsbank.dto.trading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankOrderResponse extends CoinsbankResponse {

  private final String orderId;

  public CoinsbankOrderResponse(@JsonProperty("orderid") String orderId) {
    this.orderId = orderId;
  }

  public String getOrderId() {
    return orderId;
  }
}
