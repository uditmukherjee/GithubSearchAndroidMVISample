package io.jinxlabs.doctalk.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.jinxlabs.doctalk.data.DataManager;
import io.jinxlabs.doctalk.data.remote.BackendService;
import io.jinxlabs.doctalk.injection.ApplicationContext;
import io.jinxlabs.doctalk.injection.module.ApplicationModule;
import io.jinxlabs.doctalk.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext Context context();
    Application application();
    BackendService ribotsService();
    DataManager dataManager();
    RxEventBus eventBus();
}
