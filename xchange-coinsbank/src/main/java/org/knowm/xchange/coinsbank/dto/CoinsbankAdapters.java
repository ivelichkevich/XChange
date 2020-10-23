package org.knowm.xchange.coinsbank.dto;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.knowm.xchange.coinsbank.dto.account.CoinsbankCoinBalances;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankOrder;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankOrderBook;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankSymbol;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTicker;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTrade;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankLimitOrder;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankOrders;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;

public class CoinsbankAdapters {

    public static String adaptCurrencyPair(CurrencyPair pair) {
        return (pair.base.getCurrencyCode() + pair.counter.getCurrencyCode()).toLowerCase();
    }

    public static CurrencyPair adaptSymbol(String symbol) {
        try {
            return new CurrencyPair(symbol.substring(0, 3), symbol.substring(3));
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Not supported Coinsbank symbol: " + symbol, e);
        }
    }

    public static String adaptOrderType(OrderType type) {
        switch (type) {
            case BID:
                return "buy-limit";
            case ASK:
                return "sell-limit";
            default:
                throw new IllegalArgumentException("Unsupported order type: " + type);
        }
    }

    public static Ticker adaptTicker(CoinsbankTicker ticker) {
        return new Ticker.Builder()
                .currencyPair(adaptSymbol(ticker.getSymbol()))
                .bid(ticker.getBid())
                .ask(ticker.getAsk())
                .high(ticker.getDayHigh())
                .low(ticker.getDayLow())
                .last(ticker.getLast())
                .volume(ticker.getDayVolume())
                .timestamp(new Date())
                .build();
    }

    public static OrderBook adaptOrderBook(CoinsbankOrderBook.Container response, CurrencyPair currencyPair) {
        CoinsbankOrderBook orders = response.getOrderBook();
        List<LimitOrder> asks = new LinkedList<>();
        orders.getAsks().forEach(order -> asks.add(adaptOrder(currencyPair, OrderType.ASK, order)));
        List<LimitOrder> bids = new LinkedList<>();
        orders.getBids().forEach(order -> bids.add(adaptOrder(currencyPair, OrderType.BID, order)));
        return new OrderBook(null, asks, bids);
    }

    public static Trade adaptTrade(CoinsbankTrade trade, CurrencyPair pair) {
        return new Trade.Builder()
                .price(trade.getPrice())
                .originalAmount(trade.getQuantity())
                .currencyPair(pair)
                .type(trade.getType())
                .timestamp(new Date(trade.getTimestamp()))
                .id(trade.getTradeId())
                .build();
    }

    public static OpenOrders adaptOpenOrders(CoinsbankOrders orders) {
        if (orders == null) {
            return new OpenOrders(Collections.EMPTY_LIST);
        }
        return new OpenOrders(orders.getOrders().stream().map(CoinsbankAdapters::adaptLimitOrder).collect(toList()));
    }

    public static AccountInfo adaptAccountInfo(CoinsbankCoinBalances balances) {
        Wallet wallet =
                new Wallet(
                        null,
                        balances
                                .getBalances()
                                .stream()
                                .map(
                                        balance ->
                                                new Balance(
                                                        new Currency(balance.getAsset()),
                                                        balance.getTotal(),
                                                        balance.getAvailable(),
                                                        balance.getReserved()))
                                .collect(toList()));

        return new AccountInfo(wallet);
    }

    private static LimitOrder adaptOrder(CurrencyPair currencyPair, OrderType orderType,
            CoinsbankOrder coinsbankOrder) {

        return new LimitOrder(
                orderType,
                coinsbankOrder.getQuantity(),
                currencyPair,
                null,
                null,
                coinsbankOrder.getRate());
    }

    public static LimitOrder adaptLimitOrder(CoinsbankLimitOrder order) {
        return new LimitOrder.Builder(null, adaptSymbol(order.getSymbol()))
                .id(order.getOrderId())
                .timestamp(new Date(order.getCreateTime()))
                .orderStatus(order.getOrderStatus().getStatus())
                .limitPrice(order.getPrice())
                .cumulativeAmount(order.getFilledQuantity())
                .originalAmount(order.getOrderQuantity())
                .build();
    }

    public static ExchangeMetaData adaptMetadata(List<CoinsbankSymbol> markets) {
        Map<CurrencyPair, CurrencyPairMetaData> pairMeta = new HashMap<>();
        for (CoinsbankSymbol ticker : markets) {
            pairMeta.put(
                    new CurrencyPair(ticker.getBaseAsset(), ticker.getQuoteAsset()),
                    new CurrencyPairMetaData(null, ticker.getMinQuantity(), null, null, null));
        }
        return new ExchangeMetaData(pairMeta, null, null, null, null);
    }
}
