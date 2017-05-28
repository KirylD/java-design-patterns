package by.developer.singleton;

/**
 * @author Kiryl.Drabysheuski
 * @since 1.0.0
 */
public final class Singleton {

    private static volatile Singleton INSTANCE = null;

    private Singleton () {};

    private synchronized Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class){
                if ((INSTANCE) == null) INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }

}
