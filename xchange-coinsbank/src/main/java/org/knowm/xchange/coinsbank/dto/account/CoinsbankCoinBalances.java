package org.knowm.xchange.coinsbank.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankCoinBalances extends CoinsbankResponse {
  private final List<CoinsbankCoinBalance> balances;
  private final String account;

  public CoinsbankCoinBalances(
      @JsonProperty("balance") List<CoinsbankCoinBalance> balances,
      @JsonProperty("account") String account) {
    this.balances = balances;
    this.account = account;
  }

  public List<CoinsbankCoinBalance> getBalances() {
    return balances;
  }

  public String getAccount() {
    return account;
  }
}
