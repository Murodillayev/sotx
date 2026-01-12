package uz.pdp.sotx.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.sotx.model.dto.ErrorDto;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(NotFoundException e, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

}
