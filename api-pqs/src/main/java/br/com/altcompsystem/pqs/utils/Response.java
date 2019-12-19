package br.com.altcompsystem.pqs.utils;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private T data;
    private List<String> errors;
    private int status;

    public Response() {
        this.status = 200;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if (this.errors == null)
            this.errors = new ArrayList<>();

        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setStatus(int httpStatusCode){ this.status = httpStatusCode; }

    public int getStatus(){ return this.status; }
}
