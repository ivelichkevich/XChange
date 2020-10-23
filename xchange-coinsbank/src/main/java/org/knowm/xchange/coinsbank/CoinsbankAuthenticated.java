package org.knowm.xchange.coinsbank;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.coinsbank.dto.account.CoinsbankCoinBalances;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankLimitOrder.Container;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankOrderResponse;
import org.knowm.xchange.coinsbank.dto.trading.CoinsbankOrders;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CoinsbankAuthenticated extends Coinsbank {
  /**
   * Retrieves account balance.
   *
   * @param jsonNode body JSON
   */
  @POST
  @Path("trade/balance")
  CoinsbankCoinBalances getBalance(JsonNode jsonNode) throws IOException, CoinsbankException;

  /**
   * Places on order.
   *
   * @param jsonNode body JSON
   */
  @POST
  @Path("trade/order/place")
  CoinsbankOrderResponse placeOrder(JsonNode jsonNode) throws IOException, CoinsbankException;

  /**
   * Retrieves order status info.
   *
   * @param jsonNode body JSON
   */
  @POST
  @Path("trade/order/info")
  Container getOrderStatus(JsonNode jsonNode) throws IOException, CoinsbankException;

  /**
   * Cancels an order.
   *
   * @param jsonNode body JSON
   */
  @POST
  @Path("trade/order/cancel")
  CoinsbankOrderResponse cancelOrder(JsonNode jsonNode) throws IOException, CoinsbankException;

  /**
   * Retrieves open orders.
   *
   * @param jsonNode body JSON
   */
  @POST
  @Path("trade/order/open-orders")
  CoinsbankOrders.Container getOpenOrders(JsonNode jsonNode) throws IOException, CoinsbankException;
}
