package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;

@JsonDeserialize(using = CoinsbankOrderDeserializer.class)
public class CoinsbankOrder {
  private final BigDecimal rate;
  private final BigDecimal quantity;
  private final Long orderId;

  @JsonCreator
  public CoinsbankOrder(
      @JsonProperty("rate") BigDecimal rate,
      @JsonProperty("amount") BigDecimal quantity,
      @JsonProperty("orderId") Long orderId) {
    this.rate = rate;
    this.quantity = quantity;
    this.orderId = orderId;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public Long getOrderId() {
    return orderId;
  }

  @Override
  public String toString() {
    return "CoinsbankOrder{" + "rate=" + rate + ", quantity=" + quantity + '}';
  }
}
