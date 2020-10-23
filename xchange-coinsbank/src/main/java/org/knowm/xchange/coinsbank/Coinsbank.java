package org.knowm.xchange.coinsbank;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankOrderBook;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTicker;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTrade;
import org.knowm.xchange.coinsbank.dto.marketdata.CoinsbankTrades;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface Coinsbank {

    /**
     * Retrieves a ticker.
     *
     * @param symbol the currency pair
     */
    @GET
    @Path("api/bitcoincharts/ticker/{symbol}")
    CoinsbankTicker ticker(@PathParam("symbol") String symbol) throws IOException, CoinsbankException;

    /**
     * The call for order book
     *
     * @param symbol the currency pair
     * @param limit order book length limit
     */
    @GET
    @Path("api/bitcoincharts/orderbook/{symbol}")
    CoinsbankOrderBook orderBook(@PathParam("symbol") String symbol,
            @QueryParam("limit") Integer limit) throws IOException, CoinsbankException;

    /**
     * Retrieves a ticker.
     */
    @GET
    @Path("api/bitcoincharts/ticker")
    List<CoinsbankTicker> tickers() throws IOException, CoinsbankException;

    /**
     * The call for recent trades
     *
     * @param symbol the currency pair
     * @param limit trades length limit
     */
    @GET
    @Path("api/bitcoincharts/trades/{symbol}")
    List<CoinsbankTrade> trades(@PathParam("symbol") String symbol, @QueryParam("limit") Integer limit)
            throws IOException, CoinsbankException;
}
