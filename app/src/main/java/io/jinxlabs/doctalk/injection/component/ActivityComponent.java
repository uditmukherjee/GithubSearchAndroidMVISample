package io.jinxlabs.doctalk.injection.component;

import dagger.Subcomponent;
import io.jinxlabs.doctalk.injection.PerActivity;
import io.jinxlabs.doctalk.injection.module.ActivityModule;
import io.jinxlabs.doctalk.ui.main.SearchActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SearchActivity searchActivity);
}
