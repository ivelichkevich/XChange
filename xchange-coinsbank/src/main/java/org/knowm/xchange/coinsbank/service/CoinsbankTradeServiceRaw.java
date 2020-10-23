package org.knowm.xchange.coinsbank.service;

import java.io.IOException;
import java.util.Map;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.CoinsbankException;
import org.knowm.xchange.coinsbank.dto.CoinsbankAdapters;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankLimitOrder.Container;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankOrderResponse;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankOrders;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;

public class CoinsbankTradeServiceRaw extends CoinsbankBaseService {

  protected CoinsbankTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  protected CoinsbankOrderResponse placeCoinbeneLimitOrder(LimitOrder order)
      throws IOException, CoinsbankException {
    Map<String, String> params = getCommonParams();
    params.put("price", order.getLimitPrice().toString());
    params.put("quantity", order.getOriginalAmount().toString());
    params.put("symbol", CoinsbankAdapters.adaptCurrencyPair(order.getCurrencyPair()));
    params.put("type", CoinsbankAdapters.adaptOrderType(order.getType()));

    return checkSuccess(coinsbank.placeOrder(formAndSignRequestJson(params)));
  }

  protected CoinsbankResponse cancelCoinbeneOrder(String orderId)
      throws IOException, CoinsbankException {
    Map<String, String> params = getCommonParams();
    params.put("orderid", orderId);

    return checkSuccess(coinsbank.cancelOrder(formAndSignRequestJson(params)));
  }

  protected Container getCoinbeneOrder(String orderId) throws IOException, CoinsbankException {
    Map<String, String> params = getCommonParams();
    params.put("orderid", orderId);

    return checkSuccess(coinsbank.getOrderStatus(formAndSignRequestJson(params)));
  }

  protected CoinsbankOrders.Container getCoinbeneOpenOrders(CurrencyPair currencyPair)
      throws IOException, CoinsbankException {
    Map<String, String> params = getCommonParams();
    params.put("symbol", CoinsbankAdapters.adaptCurrencyPair(currencyPair));

    return checkSuccess(coinsbank.getOpenOrders(formAndSignRequestJson(params)));
  }
}
