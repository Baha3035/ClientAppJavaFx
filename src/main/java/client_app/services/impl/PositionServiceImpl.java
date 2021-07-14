package client_app.services.impl;

import client_app.models.Position;
import client_app.services.PositionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PositionServiceImpl implements PositionService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Position> findAll() {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("http://localhost:8080/api/v1/position/all").build();

        Call call = okHttpClient.newCall(request);

        List<Position> positions = new ArrayList<>();
        try {
            Response response = call.execute();

            positions = objectMapper.readValue(response.body().string(), new TypeReference<List<Position>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }

    @Override
    public void save(Position position) {
        OkHttpClient okHttpClient = new OkHttpClient();

        try {
            RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsBytes(position),
                    MediaType.parse("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .post(requestBody)
                    .url("http://localhost:8080/api/v1/position/save")
                    .build();

            Call call = okHttpClient.newCall(request);
            Response response = call.execute();

            System.out.println(response.body().string());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

