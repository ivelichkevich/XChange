package org.knowm.xchange.coinsbank.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import java.util.TreeMap;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinsbank.CoinsbankAuthenticated;
import org.knowm.xchange.coinsbank.CoinsbankException;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.RestProxyFactory;

public class CoinsbankBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final String secretKey;
  protected final CoinsbankAuthenticated coinsbank;

  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * Constructor
   *
   * @param exchange
   */
  protected CoinsbankBaseService(Exchange exchange) {
    super(exchange);
    this.coinsbank =
        RestProxyFactory.createProxy(
            CoinsbankAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri(),
            getClientConfig());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.secretKey = exchange.getExchangeSpecification().getSecretKey();
  }

  /** Sign request JSON. */
  protected JsonNode formAndSignRequestJson(Map<String, String> params) {
    CoinsbankUtils.signParams(params);
    return toJson(params);
  }

  /** Return private API common params. */
  protected Map<String, String> getCommonParams() {
    Map<String, String> params = new TreeMap<>();
    params.put("apiid", apiKey);
    params.put("secret", secretKey);
    params.put("timestamp", String.valueOf(exchange.getNonceFactory().createValue()));
    return params;
  }

  public static <T extends CoinsbankResponse> T checkSuccess(T response) {
    if (response.isOk()) {
      return response;
    } else {
      throw new CoinsbankException(response.getErrorDescription());
    }
  }

  private JsonNode toJson(Map<String, String> params) {
    ObjectNode node = MAPPER.createObjectNode();
    params.forEach(node::put);
    return node;
  }
}
