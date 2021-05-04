package iut.lp.dna.chat.service;

import java.util.List;

import iut.lp.dna.chat.model.Message;
import iut.lp.dna.chat.model.Position;
import iut.lp.dna.chat.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChatService {

    @GET("/iut/login")
    Call<User> login(@Query("name") String name);

    @GET("/iut/user")
    Call<List<User>> getUsers();

    @GET("/iut/message")
    Call<List<Message>> getMessages(@Query("since") Integer since);

    @POST("/iut/message")
    Call<Void> sendMessage(@Body Message message);

    @GET("/iut/logout/{userId}")
    Call<Void> logout(@Path("userId") String userId);

    @POST("/iut/position/{userId}")
    Call<Void> sendPosition(@Path("userId") String userId, @Body Position position);
}
