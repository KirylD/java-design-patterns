package by.developer.singleton.example;

/**
 * @author Kiryl.Drabysheuski
 * @since 1.0.0
 */
public class Launcher {

    public static void main(String[] args) {
        String projectName = Configuration.getInstance().getProperty("kiryl.love");
        System.out.println(projectName);
    }

}
