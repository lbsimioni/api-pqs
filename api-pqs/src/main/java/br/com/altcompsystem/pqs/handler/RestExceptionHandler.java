package br.com.altcompsystem.pqs.handler;


import br.com.altcompsystem.pqs.error.AuthenticationException;
import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@CrossOrigin(allowedHeaders = "*")
public class RestExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<Object>> businessExceptionHanlder(BusinessException ex){
        Response<Object> response = new Response<>();
        response.getErrors().add(ex.getMessage());
        response.setStatus(200);
        LOG.info("Bussiness Exception: {} at {}", ex.getMessage(), ex.getStackTrace()[0]);

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response<Object>> authenticationExceptionHandler(AuthenticationException ex){
        Response<Object> response = new Response<>();
        response.getErrors().add(ex.getMessage());
        response.setStatus(401);
        LOG.info("Authentication Exception: {} at {}", ex.getMessage(), ex.getStackTrace()[0]);

        return ResponseEntity.status(401).body(response);
    }


}
