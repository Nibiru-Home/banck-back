package bank_back.bank_back.controller.webmodel.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull String login,
        @NotNull String password) {
}
