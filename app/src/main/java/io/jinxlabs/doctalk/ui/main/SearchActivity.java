package io.jinxlabs.doctalk.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.jinxlabs.doctalk.R;
import io.jinxlabs.doctalk.data.businesslogic.SearchViewState;
import io.jinxlabs.doctalk.data.businesslogic.model.User;
import io.jinxlabs.doctalk.ui.base.BaseActivity;
import io.reactivex.Observable;

public class SearchActivity extends BaseActivity implements SearchView {

    @BindView(R.id.searchView) android.widget.SearchView searchView;
    @BindView(R.id.user_container) RecyclerView userContainer;
    @BindView(R.id.progress_container) LinearLayout progressContainer;
    @BindView(R.id.message_text) TextView messageText;
    @BindView(R.id.error_text) TextView errorText;

    @Inject SearchPresenter presenter;

    private Unbinder bind;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_search);
        bind = ButterKnife.bind(this);

        initRecyclerView();
        presenter.attachView(this);
        presenter.listenForSearchEvents();
    }

    private void initRecyclerView() {
        userContainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter();
        userContainer.setAdapter(adapter);
    }

    @Override
    public Observable<String> searchIntent() {
        return RxSearchView.queryTextChanges(searchView)
                .skip(2)
                .filter(queryString -> queryString.length() > 3 || queryString.length() == 0)
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map(CharSequence::toString);
    }

    @Override
    public void render(SearchViewState viewState) {
        if (viewState instanceof SearchViewState.SearchNotStartedYet)
            showSearchNotStarted();

        if (viewState instanceof SearchViewState.SearchResult)
            showSearchResult(((SearchViewState.SearchResult) viewState).getResult());

        if (viewState instanceof SearchViewState.EmptyResult)
            showEmptyView();

        if (viewState instanceof SearchViewState.Error)
            showError(((SearchViewState.Error) viewState).getError());

        if (viewState instanceof SearchViewState.Loading)
            showLoading();
    }

    private void showLoading() {
        hideViews(userContainer, errorText, messageText);
        progressContainer.setVisibility(View.VISIBLE);
    }

    private void showError(Throwable error) {
        hideViews(userContainer, messageText, progressContainer);
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(error.getMessage());
    }

    private void showSearchNotStarted() {
        hideViews(progressContainer, errorText, userContainer);

        messageText.setVisibility(View.VISIBLE);
        messageText.setText("Search For Github Users");
    }

    private void showSearchResult(List<User> result) {
        hideViews(progressContainer, messageText, errorText);
        userContainer.setVisibility(View.VISIBLE);

        adapter.clear();
        adapter.addUsers(result);
    }

    private void showEmptyView() {
        hideViews(progressContainer, errorText, userContainer);

        messageText.setVisibility(View.VISIBLE);
        messageText.setText("No Results Found");
    }

    void hideViews(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
