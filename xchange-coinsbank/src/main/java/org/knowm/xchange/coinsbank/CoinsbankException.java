package org.knowm.xchange.coinsbank;

import si.mazi.rescu.HttpStatusExceptionSupport;

public class CoinsbankException extends HttpStatusExceptionSupport {
  public CoinsbankException(String description) {
    super(description);
  }
}
