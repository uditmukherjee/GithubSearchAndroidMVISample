package io.jinxlabs.doctalk.ui.main;

import io.jinxlabs.doctalk.data.businesslogic.SearchViewState;
import io.jinxlabs.doctalk.ui.base.MviView;
import io.reactivex.Observable;

public interface SearchView extends MviView {
    Observable<String> searchIntent();
    void render(SearchViewState viewState);
}
