package io.jinxlabs.doctalk.injection.component;

import dagger.Component;
import io.jinxlabs.doctalk.ui.base.BaseActivity;
import io.jinxlabs.doctalk.injection.ConfigPersistent;
import io.jinxlabs.doctalk.injection.module.ActivityModule;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}