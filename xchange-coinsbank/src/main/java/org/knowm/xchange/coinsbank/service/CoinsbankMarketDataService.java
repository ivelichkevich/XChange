package org.knowm.xchange.coinsbank.service;

import static java.util.stream.Collectors.toList;
import static org.knowm.xchange.coinsbank.dto.CoinsbankAdapters.adaptOrderBook;
import static org.knowm.xchange.coinsbank.dto.CoinsbankAdapters.adaptSymbol;
import static org.knowm.xchange.coinsbank.dto.CoinsbankAdapters.adaptTicker;
import static org.knowm.xchange.coinsbank.dto.CoinsbankAdapters.adaptTrade;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.dto.CoinsbankAdapters;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankSymbol;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTicker;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

public class CoinsbankMarketDataService extends CoinsbankMarketDataServiceRaw implements MarketDataService {

    public CoinsbankMarketDataService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
        return adaptTicker(getCoinbeneTicker(currencyPair));
    }

    @Override
    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        Integer limit = null;
        if (args != null && args.length > 0) {
            if (args[0] instanceof Integer && (Integer) args[0] > 0) {
                limit = (Integer) args[0];
            }
        }
        return adaptOrderBook(getCoinbeneOrderBook(currencyPair, limit), currencyPair);
    }

    @Override
    public List<Ticker> getTickers(Params params) throws IOException {
        return getCoinbeneTickers().stream().map(CoinsbankAdapters::adaptTicker).collect(toList());
    }

    @Override
    public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
        Integer limit = null;
        if (args != null && args.length > 0) {
            if (args[0] instanceof Integer && (Integer) args[0] > 0) {
                limit = (Integer) args[0];
            }
        }
        List<Trade> trades = getCoinbeneTrades(currencyPair, limit).stream()
                .map(trade -> adaptTrade(trade, currencyPair)).collect(toList());
        return new Trades(trades, Trades.TradeSortType.SortByTimestamp);
    }

    public ExchangeMetaData getMetadata() throws IOException {
        List<CoinsbankSymbol> symbols = getCoinbeneTickers().stream().map(t -> buildSymbol(t)).collect(toList());
        return CoinsbankAdapters.adaptMetadata(symbols);
        //return CoinsbankAdapters.adaptMetadata(Collections.EMPTY_LIST);
    }

    private CoinsbankSymbol buildSymbol(CoinsbankTicker t) {
        CurrencyPair pair = adaptSymbol(t.getSymbol());
        return new CoinsbankSymbol(t.getSymbol(), pair.base.toString(), pair.counter.toString(), null, null, 0, 0,
                null);
    }
}
