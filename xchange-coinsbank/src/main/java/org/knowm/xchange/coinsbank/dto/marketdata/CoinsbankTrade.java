package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.knowm.xchange.dto.Order;

/**
 * [{"tid":75168699,"date":1549871835,"amount":77.7,"price":0.0332037,"direction":"ask"},
 */
public class CoinsbankTrade {

    private final BigDecimal price;
    private final BigDecimal quantity;
    private final Long timestamp;
    private final Order.OrderType type;
    private final String tradeId;

    public CoinsbankTrade(
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("amount") BigDecimal quantity,
            @JsonProperty("date") Long timestamp,
            @JsonProperty("direction") Order.OrderType type,
            @JsonProperty("tid") String tradeId) {
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.type = type;
        this.tradeId = tradeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Order.OrderType getType() {
        return type;
    }

    public String getTradeId() {
        return tradeId;
    }
}
