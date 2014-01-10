package com.gwtplatform.carstore.shared.dispatch;

import java.util.List;

public class GetResults<T> extends Response {
    private List<T> payload;
    private Long totalElements;

    public GetResults() {
    }

    public GetResults(List<T> payload) {
        this.payload = payload;
    }

    public GetResults(List<T> payload, Long totalElements) {
        this.payload = payload;
        this.totalElements = totalElements;
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
