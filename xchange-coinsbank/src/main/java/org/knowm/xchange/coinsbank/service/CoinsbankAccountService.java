package org.knowm.xchange.coinsbank.service;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.dto.CoinsbankAdapters;
import org.knowm.xchange.coinsbank.dto.account.CoinsbankCoinBalances;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;

public class CoinsbankAccountService extends CoinsbankAccountServiceRaw implements AccountService {

  public static final String EXCHANGE_ACCOUNT = "exchange";

  public CoinsbankAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    // Account name by default
    return getAccountInfo(EXCHANGE_ACCOUNT);
  }

  public AccountInfo getAccountInfo(String account) throws IOException {
    CoinsbankCoinBalances balances = getCoinbeneBalances(account);

    return CoinsbankAdapters.adaptAccountInfo(balances);
  }
}
