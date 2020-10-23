package org.knowm.xchange.coinsbank.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class CoinsbankOrderDeserializer extends JsonDeserializer<CoinsbankOrder> {
    @Override
    public CoinsbankOrder deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.readValueAsTree();
        return new CoinsbankOrder(node.get(0).decimalValue(), node.get(1).decimalValue(), node.get(2).longValue());
    }
}