package org.knowm.xchange.coinsbank.service.marketdata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.knowm.xchange.ExchangeFactory.INSTANCE;
import static org.knowm.xchange.currency.CurrencyPair.ETH_USD;

import java.util.List;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.CoinsbankExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class CoinsbankMarketDataServiceIntegration {

    private static final Exchange COINSBANK = INSTANCE.createExchange(CoinsbankExchange.class.getName());

    @Test
    public void testGetTicker() throws Exception {
        MarketDataService marketDataService = COINSBANK.getMarketDataService();
        Ticker ticker = marketDataService.getTicker(ETH_USD);
        assertThat(ticker.getAsk()).isGreaterThan(ticker.getBid());
        assertThat(ticker.getHigh()).isGreaterThan(ticker.getLow());
        assertThat(ticker.getCurrencyPair()).isEqualTo(ETH_USD);
    }

    @Test
    public void testGetOrderBook() throws Exception {
        OrderBook orderBookDefault = COINSBANK.getMarketDataService().getOrderBook(ETH_USD);
        assertThat(orderBookDefault).isNotNull();
        assertThat(orderBookDefault.getAsks().size()).isGreaterThan(0);
        assertThat(orderBookDefault.getBids().size()).isGreaterThan(0);

        OrderBook orderBookShort = COINSBANK.getMarketDataService().getOrderBook(ETH_USD, 1);
        assertThat(orderBookShort).isNotNull();
        assertThat(orderBookShort.getAsks().size()).isEqualTo(1);
        assertThat(orderBookShort.getBids().size()).isEqualTo(1);

        OrderBook orderBookLong = COINSBANK.getMarketDataService().getOrderBook(ETH_USD, 10);
        assertThat(orderBookLong).isNotNull();
        assertThat(orderBookLong.getAsks().size()).isEqualTo(10);
        assertThat(orderBookLong.getBids().size()).isEqualTo(10);
    }

    @Test
    public void testGetTrades() throws Exception {
        Trades tradesDefault = COINSBANK.getMarketDataService().getTrades(ETH_USD);
        assertThat(tradesDefault.getTrades().size()).isEqualTo(300);
        tradesDefault.getTrades().forEach(t -> assertThat(t.getCurrencyPair()).isEqualTo(ETH_USD));

        Trades tradesLimit20 = COINSBANK.getMarketDataService().getTrades(ETH_USD, 20);
        assertThat(tradesLimit20.getTrades().size()).isEqualTo(20);
        tradesLimit20.getTrades().forEach(t -> assertThat(t.getCurrencyPair()).isEqualTo(ETH_USD));
    }

    @Test
    public void testGetSymbol() {
        List<CurrencyPair> symbols = COINSBANK.getExchangeSymbols();
        assertThat(symbols).isNotNull();
        assertThat(symbols.contains(CurrencyPair.ETH_BTC)).isEqualTo(true);
    }
}
