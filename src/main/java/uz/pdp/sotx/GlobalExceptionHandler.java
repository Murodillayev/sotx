package uz.pdp.sotx;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put("error", error.getDefaultMessage());
        });

        errors.put("path", request.getServletPath());
        return errors;
    }
}
