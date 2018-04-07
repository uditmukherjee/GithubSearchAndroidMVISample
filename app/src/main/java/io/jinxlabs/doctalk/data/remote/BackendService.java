package io.jinxlabs.doctalk.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.jinxlabs.doctalk.data.businesslogic.model.UserSearch;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BackendService {

    String ENDPOINT = "https://api.github.com/";

    //search/users?q=uditm&page=1&per_page=10&sort=repositories&type=user
    @GET("search/users")
    Observable<UserSearch> searchUsers(
            @Query("q") String username,
            @Query("page") int page,
            @Query("per_page") int perPageCount,
            @Query("sort") String sortByType,
            @Query("type") String typeOfUser
    );



    /******** Helper class that sets up a new services *******/
    class Creator {

        public static BackendService newBackendService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BackendService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(BackendService.class);
        }
    }
}
