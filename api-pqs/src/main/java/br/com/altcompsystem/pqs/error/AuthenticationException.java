package br.com.altcompsystem.pqs.error;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg){
        super(msg);
    }

    public AuthenticationException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
