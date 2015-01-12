package com.boluogan.domain.domaintool.model;

import com.boluogan.domain.domaintool.constant.ResponseStatus;

/**
 * Created by boluogan.com on 12/31/14.
 */
public class ResponseData<T> {
    private ResponseStatus status;
    private T message;

    public ResponseData(ResponseStatus status, T message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
