package io.jinxlabs.doctalk.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.jinxlabs.doctalk.data.businesslogic.model.User;
import io.jinxlabs.doctalk.data.remote.BackendService;
import io.reactivex.Observable;

@Singleton
public class DataManager {

    private final BackendService mBackendService;

    @Inject
    public DataManager(BackendService backendService) {
        mBackendService = backendService;
    }

    public Observable<List<User>> searchFor(@NonNull String searchQueryText) {

        if (searchQueryText == null) {
            return Observable.error(new NullPointerException("SearchQueryText == null"));
        }

        if (searchQueryText.length() == 0) {
            return Observable.error(new IllegalArgumentException("SearchQueryTest is blank"));
        }

        return mBackendService.searchUsers(searchQueryText, 1, 50, "repositories", "user")
                .flatMap(userSearch -> Observable.just(userSearch.getItems()));
    }

}
