package org.knowm.xchange.coinsbank.service;

import static org.knowm.xchange.coinsbank.dto.CoinsbankAdapters.adaptCurrencyPair;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.CoinsbankException;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankOrderBook;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTicker;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTrade;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTrades;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

public class CoinsbankMarketDataServiceRaw extends CoinsbankBaseService {

    protected CoinsbankMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public CoinsbankTicker getCoinbeneTicker(CurrencyPair currencyPair) throws IOException {
        try {
            CoinsbankTicker ticker = coinsbank.ticker(adaptCurrencyPair(currencyPair));
            if (ticker.getSymbol() == null) {
                ticker.setSymbol(adaptCurrencyPair(currencyPair));
            }
            return ticker;
        } catch (CoinsbankException e) {
            throw new ExchangeException(e.getMessage(), e);
        }
    }

    public List<CoinsbankTicker> getCoinbeneTickers() throws IOException {
        try {
            return coinsbank.tickers();
        } catch (CoinsbankException e) {
            throw new ExchangeException(e.getMessage(), e);
        }
    }

    public CoinsbankOrderBook.Container getCoinbeneOrderBook(CurrencyPair currencyPair, Integer size)
            throws IOException {
        try {
            return new CoinsbankOrderBook.Container(
                    coinsbank.orderBook(adaptCurrencyPair(currencyPair), size),
                    adaptCurrencyPair(currencyPair));
        } catch (CoinsbankException e) {
            throw new ExchangeException(e.getMessage(), e);
        }
    }

    //  public CoinsbankSymbol.Container getSymbol() throws IOException {
    //    try {
    //      return checkSuccess(new CoinsbankSymbol.Container(coinsbank.tickers().stream().map(t ->
    // t.getTicker().getSymbol()).collect(
    //              Collectors.toList()));
    //      //return checkSuccess(coinsbank.tickers().stream().map(t -> t.getTicker().getSymbol().));
    //    } catch (CoinsbankException e) {
    //      throw new ExchangeException(e.getMessage(), e);
    //    }
    //  }

    public List<CoinsbankTrade> getCoinbeneTrades(CurrencyPair currencyPair) throws IOException {
        return getCoinbeneTrades(currencyPair, null);
    }

    public List<CoinsbankTrade> getCoinbeneTrades(CurrencyPair currencyPair, Integer size) throws IOException {
        try {
            return coinsbank.trades(adaptCurrencyPair(currencyPair), size);
        } catch (CoinsbankException e) {
            throw new ExchangeException(e.getMessage(), e);
        }
    }
}
