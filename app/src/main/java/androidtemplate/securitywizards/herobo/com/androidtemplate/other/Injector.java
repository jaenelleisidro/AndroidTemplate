package androidtemplate.securitywizards.herobo.com.androidtemplate.other;

import dagger.ObjectGraph;
//TODO : put this on a seperate library
public final class Injector {

    private static ObjectGraph objectGraph = null;

    public static void init(final Object rootModule) {

        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(rootModule);
        } else {
            objectGraph = objectGraph.plus(rootModule);
        }

        // Inject statics
        objectGraph.injectStatics();

    }

    public static void init(final Object rootModule, final Object target) {
        init(rootModule);
        inject(target);
    }

    public static void inject(final Object target) {
        objectGraph.inject(target);
    }

    public static <T> T resolve(Class<T> type) {
        return objectGraph.get(type);
    }
}
