package pl.nutriplan.mealdiaryservice.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class FoodServiceClient {

    private final RestClient restClient;

    public FoodServiceClient(RestClient.Builder restClientBuilder,
                             @Value("${food-service.base-url}") String baseUrl
    ) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    }


    public FoodPortionResponse calculatePortion(UUID productId, BigDecimal grams) {

        try {
            FoodPortionResponse response = restClient
                    .get()
                    .uri(uriBuilder ->
                            uriBuilder.path("/api/foods/{id}/portion")
                                    .queryParam("grams", grams.toPlainString())
                                    .build(productId))
                    .retrieve()
                    .body(FoodPortionResponse.class);

            if (response == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "Food portion response is null");
            }
            return response;
        } catch (RestClientResponseException exception) {
            if (exception.getStatusCode().value() == 404) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Food portion not found",
                        exception);
            }
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Food portion response not found",
                    exception);
        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Lack of call to server",
                    exception);
        }
    }
}
