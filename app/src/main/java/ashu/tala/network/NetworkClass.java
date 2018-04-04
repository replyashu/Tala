package ashu.tala.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ashu.tala.utils.Constants;
import ashu.tala.model.ResultDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 04/04/18.
 */

public class NetworkClass implements Callback<ResultDTO> {
    static final String BASE_URL = Constants.baseUrl;

    public Retrofit start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;

    }

    @Override
    public void onResponse(Call<ResultDTO> call, Response<ResultDTO> response) {
        if(response.isSuccessful()) {
            ResultDTO eventList = response.body();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ResultDTO> call, Throwable t) {
        t.printStackTrace();
    }
}
