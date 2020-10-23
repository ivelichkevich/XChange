package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankSymbol {

  private String ticker; // "CONIUSDT";
  private String baseAsset; // "CONI";
  private String quoteAsset; // "USDT";
  private BigDecimal takerFee; // "0.0010";
  private BigDecimal makerFee; // "0.0010";
  private int tickSize; // "5";
  private int lotStepSize; // "2";
  private BigDecimal minQuantity; // "1.00000000"

  public CoinsbankSymbol(
      @JsonProperty("ticker") String ticker,
      @JsonProperty("baseAsset") String baseAsset,
      @JsonProperty("quoteAsset") String quoteAsset,
      @JsonProperty("takerFee") BigDecimal takerFee,
      @JsonProperty("makerFee") BigDecimal makerFee,
      @JsonProperty("tickSize") int tickSize,
      @JsonProperty("lotStepSize") int lotStepSize,
      @JsonProperty("minQuantity") BigDecimal minQuantity) {
    this.ticker = ticker;
    this.baseAsset = baseAsset;
    this.quoteAsset = quoteAsset;
    this.takerFee = takerFee;
    this.makerFee = makerFee;
    this.tickSize = tickSize;
    this.lotStepSize = lotStepSize;
    this.minQuantity = minQuantity;
  }

  public String getTicker() {
    return ticker;
  }

  public String getBaseAsset() {
    return baseAsset;
  }

  public String getQuoteAsset() {
    return quoteAsset;
  }

  public BigDecimal getTakerFee() {
    return takerFee;
  }

  public BigDecimal getMakerFee() {
    return makerFee;
  }

  public int getTickSize() {
    return tickSize;
  }

  public int getLotStepSize() {
    return lotStepSize;
  }

  public BigDecimal getMinQuantity() {
    return minQuantity;
  }

  public static class Container extends CoinsbankResponse {

    private final List<CoinsbankSymbol> symbol;

    public Container(@JsonProperty("symbol") List<CoinsbankSymbol> symbol) {
      this.symbol = symbol;
    }

    public List<CoinsbankSymbol> getSymbol() {
      return symbol;
    }
  }
}
