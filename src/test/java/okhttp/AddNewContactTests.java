package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.ContactResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class AddNewContactTests {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZWxpc2F2ZXRhLnN0ZXB1cmFAbWFpbC5ydSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjgyNTI0Njc0LCJpYXQiOjE2ODE5MjQ2NzR9.q4MPRms4lcqkK-fQVkU5ouhCBIost18dC-EjS320a2o";

    @Test
    public void addNewContactSuccessTest() throws IOException {
        int i = new Random().nextInt(1000) + 1000;
        ContactDto contactDto = ContactDto.builder()
                .name("Varya")
                .lastName("Pupok")
                .email("kan"+ i + "@gmail.com")
                .phone("1234512345"+i)
                .address("Vienna")
                .description("goalkeeper")
                .build();
        RequestBody body = RequestBody.create(gson.toJson(contactDto),JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);
        ContactResponseDto responseDto = gson.fromJson(response.body().string(),ContactResponseDto.class);
//        System.out.println(responseDto.getMessage());
        Assert.assertTrue(responseDto.getMessage().contains("Contact was added!"));
    }
}
