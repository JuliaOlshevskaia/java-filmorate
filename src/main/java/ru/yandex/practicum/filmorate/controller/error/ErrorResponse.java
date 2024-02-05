package ru.yandex.practicum.filmorate.controller.error;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



public class ErrorResponse {
    private final String error;

    public ErrorResponse(String error) {
        this.error=error;
    }

    public String getError() {
        return error;
    }
}
