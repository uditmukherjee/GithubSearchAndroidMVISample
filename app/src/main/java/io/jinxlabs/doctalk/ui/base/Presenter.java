package io.jinxlabs.doctalk.ui.base;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MviView type that wants to be attached with.
 */
public interface Presenter<V extends MviView> {

    void attachView(V mvpView);

    void detachView();
}
