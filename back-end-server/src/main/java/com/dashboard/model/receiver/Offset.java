package com.dashboard.model.receiver;

/**
 * Hamza.Ouni
 */

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "partition",
        "offset",
        "error_code",
        "error"
})
public class Offset {

    @JsonProperty("partition")
    private Integer partition;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("error_code")
    private Object errorCode;
    @JsonProperty("error")
    private Object error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("partition")
    public Integer getPartition() {
        return partition;
    }

    @JsonProperty("partition")
    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("error_code")
    public Object getErrorCode() {
        return errorCode;
    }

    @JsonProperty("error_code")
    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("error")
    public Object getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Object error) {
        this.error = error;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
