package org.knowm.xchange.coinsbank.dto.trading;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.knowm.xchange.coinsbank.dto.CoinsbankResponse;

public class CoinsbankOrders {
  private final List<CoinsbankLimitOrder> orders;
  private final Integer totalCount;
  private final Integer pageSize;
  private final Integer page;

  public CoinsbankOrders(
      @JsonProperty("result") List<CoinsbankLimitOrder> orders,
      @JsonProperty("totalcount") Integer totalCount,
      @JsonProperty("pagesize") Integer pageSize,
      @JsonProperty("page") Integer page) {
    this.orders = orders;
    this.totalCount = totalCount;
    this.pageSize = pageSize;
    this.page = page;
  }

  public List<CoinsbankLimitOrder> getOrders() {
    return orders;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public Integer getPage() {
    return page;
  }

  public static class Container extends CoinsbankResponse {
    private final CoinsbankOrders orders;

    Container(@JsonProperty("orders") CoinsbankOrders orders) {
      this.orders = orders;
    }

    public CoinsbankOrders getOrders() {
      return orders;
    }
  }
}
