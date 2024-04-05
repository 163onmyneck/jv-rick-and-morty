package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<CharacterResponseDto> fetchCharacters() {
        try {
            return fetchData().getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CharacterResponseDataDto fetchData() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
    }
}
