package SplashScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;
import static javafx.util.Duration.seconds;

public class FadeTransition {

    public static void applyFadeTransition(Node node, Duration duration, EventHandler<ActionEvent> event) {
        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(duration, node);
        fadeIn.setCycleCount(1);
        fadeIn.setFromValue(0.2);
        fadeIn.setToValue(1);
        fadeIn.setAutoReverse(true);
        fadeIn.setOnFinished(event);

        javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(Duration.seconds(0.5), node);
        fadeOut.setCycleCount(1);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(0.0);
        fadeOut.setAutoReverse(true);

        fadeOut.play();
        fadeOut.setOnFinished((e) -> {
            fadeIn.play();
        });
    }

}
