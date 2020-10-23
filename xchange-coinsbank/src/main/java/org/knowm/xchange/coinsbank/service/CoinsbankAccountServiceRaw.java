package org.knowm.xchange.coinsbank.service;

import java.io.IOException;
import java.util.Map;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.dto.account.CoinsbankCoinBalances;

public class CoinsbankAccountServiceRaw extends CoinsbankBaseService {

  protected CoinsbankAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public CoinsbankCoinBalances getCoinbeneBalances(String account) throws IOException {
    Map<String, String> params = getCommonParams();
    params.put("account", account);

    return checkSuccess(coinsbank.getBalance(formAndSignRequestJson(params)));
  }

  public CoinsbankCoinBalances getCoinbeneBalances() throws IOException {
    return getCoinbeneBalances("exchange");
  }
}
