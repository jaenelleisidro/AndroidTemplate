package securitywizards.herobo.com.androidtemplate.other.dagger;

import dagger.Module;

/**
 * Add all the other modules to this one.
 */
@Module(
        includes = {
                AndroidModule.class,
                MainModule.class
        }
)
public class RootModule {
}
