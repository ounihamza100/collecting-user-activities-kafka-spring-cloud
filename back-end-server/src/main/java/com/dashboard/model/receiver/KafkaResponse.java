package com.dashboard.model.receiver;

/**
 * Hamza.Ouni
 */

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offsets",
        "key_schema_id",
        "value_schema_id"
})
public class KafkaResponse {

    @JsonProperty("offsets")
    private List<Offset> offsets = null;
    @JsonProperty("key_schema_id")
    private Object keySchemaId;
    @JsonProperty("value_schema_id")
    private Object valueSchemaId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("offsets")
    public List<Offset> getOffsets() {
        return offsets;
    }

    @JsonProperty("offsets")
    public void setOffsets(List<Offset> offsets) {
        this.offsets = offsets;
    }

    @JsonProperty("key_schema_id")
    public Object getKeySchemaId() {
        return keySchemaId;
    }

    @JsonProperty("key_schema_id")
    public void setKeySchemaId(Object keySchemaId) {
        this.keySchemaId = keySchemaId;
    }

    @JsonProperty("value_schema_id")
    public Object getValueSchemaId() {
        return valueSchemaId;
    }

    @JsonProperty("value_schema_id")
    public void setValueSchemaId(Object valueSchemaId) {
        this.valueSchemaId = valueSchemaId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "KafkaResponse{" +
                "offsets=" + offsets +
                ", keySchemaId=" + keySchemaId +
                ", valueSchemaId=" + valueSchemaId +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
