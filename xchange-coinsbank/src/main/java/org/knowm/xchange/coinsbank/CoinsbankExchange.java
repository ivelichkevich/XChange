package org.knowm.xchange.coinsbank;

import java.io.IOException;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.coinsbank.service.CoinsbankAccountService;
import org.knowm.xchange.coinsbank.service.CoinsbankMarketDataService;
import org.knowm.xchange.coinsbank.service.CoinsbankTradeService;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class CoinsbankExchange extends BaseExchange implements Exchange {

    private static final Logger LOG = LoggerFactory.getLogger(CoinsbankExchange.class);

    private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

    @Override
    protected void initServices() {
        this.marketDataService = new CoinsbankMarketDataService(this);
        this.accountService = new CoinsbankAccountService(this);
        this.tradeService = new CoinsbankTradeService(this);
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
        exchangeSpecification.setSslUri("https://coinsbank.com/");
        exchangeSpecification.setHost("coinsbank.com");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Coinsbank");
        exchangeSpecification.setExchangeDescription("CoinsBank - the bank of Blockchain future");
        //exchangeSpecification.setShouldLoadRemoteMetaData(false); // TODO
        return exchangeSpecification;
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return nonceFactory;
    }

    @Override
    public void remoteInit() throws IOException, ExchangeException {
        exchangeMetaData = ((CoinsbankMarketDataService) marketDataService).getMetadata();
    }
}
