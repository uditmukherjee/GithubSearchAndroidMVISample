package io.jinxlabs.doctalk.ui.main;

import javax.inject.Inject;

import io.jinxlabs.doctalk.data.businesslogic.SearchViewState;
import io.jinxlabs.doctalk.data.businesslogic.interactor.SearchInteractor;
import io.jinxlabs.doctalk.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchView> {

    private SearchInteractor searchInteractor;
    private Disposable disposable;

    @Inject
    public SearchPresenter(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    @Override
    public void attachView(SearchView mvpView) {
        super.attachView(mvpView);
        getMvpView().render(new SearchViewState.SearchNotStartedYet());
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) disposable.dispose();
    }

    public void listenForSearchEvents() {
        disposable = getMvpView().searchIntent()
                .startWith("")
                .subscribe(s -> {
                    searchInteractor.search(s)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(searchViewState -> {
                                getMvpView().render(searchViewState);
                            });
                }, throwable -> throwable.printStackTrace());
    }
}
