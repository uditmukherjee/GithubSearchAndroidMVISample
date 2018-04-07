package io.jinxlabs.doctalk;

import android.app.Application;
import android.content.Context;

import io.jinxlabs.doctalk.injection.component.ApplicationComponent;
import uk.jinxlabs.doctalk.androidboilerplate.injection.component.DaggerApplicationComponent;
import io.jinxlabs.doctalk.injection.module.ApplicationModule;

public class DocTalkApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static DocTalkApplication get(Context context) {
        return (DocTalkApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
