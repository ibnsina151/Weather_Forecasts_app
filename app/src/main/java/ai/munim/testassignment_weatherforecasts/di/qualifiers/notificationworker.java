package ai.munim.testassignment_weatherforecasts.di.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface notificationworker {
}
