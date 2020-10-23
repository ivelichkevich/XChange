package org.knowm.xchange.coinsbank.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.dto.CoinsbankAdapters;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankLimitOrder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

public class CoinsbankTradeService extends CoinsbankTradeServiceRaw implements TradeService {
  public CoinsbankTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    return placeCoinbeneLimitOrder(limitOrder).getOrderId();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    return cancelCoinbeneOrder(orderId).isOk();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {
    CoinsbankLimitOrder order = getCoinbeneOrder(orderIds[0]).getOrder();
    return Collections.singletonList(CoinsbankAdapters.adaptLimitOrder(order));
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws IOException {
    return getOpenOrders(((DefaultOpenOrdersParamCurrencyPair) params).getCurrencyPair());
  }

  public OpenOrders getOpenOrders(CurrencyPair pair) throws IOException {
    return CoinsbankAdapters.adaptOpenOrders(getCoinbeneOpenOrders(pair).getOrders());
  }
}
