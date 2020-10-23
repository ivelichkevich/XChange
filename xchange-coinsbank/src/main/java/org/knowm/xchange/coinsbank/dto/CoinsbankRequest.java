package org.knowm.xchange.coinsbank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CoinsbankRequest {
  @JsonProperty private final String apiId;

  @JsonProperty private final long timestamp;

  @JsonProperty private final String sign;

  protected CoinsbankRequest(String apiId, long timestamp, String sign) {
    this.apiId = apiId;
    this.timestamp = timestamp;
    this.sign = sign;
  }

  public String getApiId() {
    return apiId;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getSign() {
    return sign;
  }
}
