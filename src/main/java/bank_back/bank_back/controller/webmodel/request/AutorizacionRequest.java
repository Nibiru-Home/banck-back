package bank_back.bank_back.controller.webmodel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutorizacionRequest(
        String login,
        @JsonProperty("api_token") String apiToken) {
}
