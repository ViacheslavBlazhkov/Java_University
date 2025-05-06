package domain.Exceptions.Advices;

import domain.Exceptions.InvalidTransferMoneyRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidTransferMoneyRequestAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidTransferMoneyRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidTransferMoneyRequest(InvalidTransferMoneyRequestException ex) {
        return ex.getMessage();
    }
}
