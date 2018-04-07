package io.jinxlabs.doctalk.data.businesslogic.interactor;

import javax.inject.Inject;

import io.jinxlabs.doctalk.data.DataManager;
import io.jinxlabs.doctalk.data.businesslogic.SearchViewState;
import io.reactivex.Observable;

public class SearchInteractor {
    private DataManager dataManager;

    @Inject
    public SearchInteractor(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Observable<SearchViewState> search(String searchString) {
        // Empty String, so no search
        if (searchString.isEmpty()) {
            return Observable.just(new SearchViewState.SearchNotStartedYet());
        }

        // search for product
        return dataManager.searchFor(searchString)
                .map(products -> {
                    if (products.isEmpty()) {
                        return new SearchViewState.EmptyResult(searchString);
                    } else {
                        return new SearchViewState.SearchResult(searchString, products);
                    }
                })
                .startWith(new SearchViewState.Loading())
                .onErrorReturn(error -> new SearchViewState.Error(searchString, error));
    }
}
