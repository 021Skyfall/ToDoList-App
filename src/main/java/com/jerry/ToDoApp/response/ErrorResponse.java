package com.jerry.ToDoApp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jerry.ToDoApp.exception.BusinessLogicException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BusinessError businessError;

    public static ErrorResponse of(BusinessLogicException businessLogicException) {
        return new ErrorResponse(BusinessError.of(businessLogicException));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BusinessError {
        private int status;
        private String message;

        public static BusinessError of(BusinessLogicException businessLogicException) {
            return new BusinessError(businessLogicException.getExceptionCode().getStatus(),
                    businessLogicException.getExceptionCode().getMessage());
        }
    }
}
