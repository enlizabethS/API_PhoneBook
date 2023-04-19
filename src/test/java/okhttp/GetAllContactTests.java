package okhttp;

import com.google.gson.Gson;
import dto.AllContactDto;
import dto.ContactDto;
import dto.ContactResponseDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContactTests {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZWxpc2F2ZXRhLnN0ZXB1cmFAbWFpbC5ydSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjgyNTI0Njc0LCJpYXQiOjE2ODE5MjQ2NzR9.q4MPRms4lcqkK-fQVkU5ouhCBIost18dC-EjS320a2o";

    @Test
    public void getAllContactSuccessTest() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);
        AllContactDto contactDto = gson.fromJson(response.body().string(), AllContactDto.class);
        List<ContactDto> contacts = contactDto.getContacts();
        for(ContactDto contact: contacts){
            System.out.println(contact.getId());
            System.out.println("------------");
        }
        //21a6c06b-1b27-41a1-97cb-31fe12f4b753
    }
}
