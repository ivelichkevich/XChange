package org.knowm.xchange.coinsbank.dto.trading;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankLimitOrder {
  private final String orderId;
  private final CoinsbankOrderStatus orderStatus;
  private final String symbol;
  private final String type;
  private final BigDecimal price;
  private final BigDecimal orderQuantity;
  private final BigDecimal filledQuantity;
  private final BigDecimal filledAmount;
  private final BigDecimal averagePrice;
  private final BigDecimal fees;
  private final Long createTime;
  private final Long lastModified;

  public CoinsbankLimitOrder(
      @JsonProperty("orderid") String orderId,
      @JsonProperty("orderstatus") CoinsbankOrderStatus orderStatus,
      @JsonProperty("symbol") String symbol,
      @JsonProperty("type") String type,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("orderquantity") BigDecimal orderQuantity,
      @JsonProperty("filledquantity") BigDecimal filledQuantity,
      @JsonProperty("filledamount") BigDecimal filledAmount,
      @JsonProperty("averageprice") BigDecimal averagePrice,
      @JsonProperty("fees") BigDecimal fees,
      @JsonProperty("createtime") Long createTime,
      @JsonProperty("lastmodified") Long lastModified) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
    this.symbol = symbol;
    this.type = type;
    this.price = price;
    this.orderQuantity = orderQuantity;
    this.filledQuantity = filledQuantity;
    this.filledAmount = filledAmount;
    this.averagePrice = averagePrice;
    this.fees = fees;
    this.createTime = createTime;
    this.lastModified = lastModified;
  }

  public String getOrderId() {
    return orderId;
  }

  public CoinsbankOrderStatus getOrderStatus() {
    return orderStatus;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getType() {
    return type;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getOrderQuantity() {
    return orderQuantity;
  }

  public BigDecimal getFilledQuantity() {
    return filledQuantity;
  }

  public BigDecimal getFilledAmount() {
    return filledAmount;
  }

  public BigDecimal getAveragePrice() {
    return averagePrice;
  }

  public BigDecimal getFees() {
    return fees;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public Long getLastModified() {
    return lastModified;
  }

  public static class Container extends CoinsbankResponse {
    private final CoinsbankLimitOrder order;

    public Container(@JsonProperty("order") CoinsbankLimitOrder order) {
      this.order = order;
    }

    public CoinsbankLimitOrder getOrder() {
      return order;
    }
  }
}
