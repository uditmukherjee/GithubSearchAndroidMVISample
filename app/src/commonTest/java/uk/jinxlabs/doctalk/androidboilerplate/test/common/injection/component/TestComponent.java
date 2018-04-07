package uk.jinxlabs.doctalk.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import io.jinxlabs.doctalk.injection.component.ApplicationComponent;
import uk.jinxlabs.doctalk.androidboilerplate.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
