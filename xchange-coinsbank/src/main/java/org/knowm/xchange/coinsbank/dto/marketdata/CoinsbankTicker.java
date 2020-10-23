package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

// {"last":3378.3,"high":3414.49,"low":3323.77,"volume":1411.4222,"buy":3350,"sell":3413.52}
public class CoinsbankTicker {
  private String symbol;
  private final BigDecimal dayHigh;
  private final BigDecimal dayLow;
  private final BigDecimal last;
  private final BigDecimal ask;
  private final BigDecimal bid;
  private final BigDecimal dayVolume;

  public CoinsbankTicker(
      @JsonProperty("name") String symbol,
      @JsonProperty("high") BigDecimal dayHigh,
      @JsonProperty("low") BigDecimal dayLow,
      @JsonProperty("last") BigDecimal last,
      @JsonProperty("sell") BigDecimal ask,
      @JsonProperty("buy") BigDecimal bid,
      @JsonProperty("volume") BigDecimal dayVolume) {
    this.symbol = symbol;
    this.dayHigh = dayHigh;
    this.dayLow = dayLow;
    this.last = last;
    this.ask = ask;
    this.bid = bid;
    this.dayVolume = dayVolume;
  }

  public String getSymbol() {
    return symbol;
  }

  public BigDecimal getDayHigh() {
    return dayHigh;
  }

  public BigDecimal getDayLow() {
    return dayLow;
  }

  public BigDecimal getLast() {
    return last;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getDayVolume() {
    return dayVolume;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return "CoinsbankTicker{"
        + "symbol='"
        + symbol
        + '\''
        + ", dayHigh="
        + dayHigh
        + ", dayLow="
        + dayLow
        + ", last="
        + last
        + ", ask="
        + ask
        + ", bid="
        + bid
        + ", dayVolume="
        + dayVolume
        + '}';
  }

  public static class Container extends CoinsbankResponse {
    private final List<CoinsbankTicker> tickers;
    private final long timestamp;

    public Container(
        @JsonProperty("ticker") List<CoinsbankTicker> tickers,
        @JsonProperty("timestamp") long timestamp) {
      this.tickers = tickers;
      this.timestamp = timestamp;
    }

    public CoinsbankTicker getTicker() {
      return tickers.get(0);
    }

    public long getTimestamp() {
      return timestamp;
    }
  }
}
