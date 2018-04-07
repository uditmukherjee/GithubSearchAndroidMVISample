package io.jinxlabs.doctalk.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.jinxlabs.doctalk.data.remote.BackendService;

@Singleton
public class DataManager {

    private final BackendService mBackendService;

    @Inject
    public DataManager(BackendService ribotsService) {
        mBackendService = ribotsService;
    }

}
