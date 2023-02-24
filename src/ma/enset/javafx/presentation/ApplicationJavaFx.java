package ma.enset.javafx.presentation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ApplicationJavaFx extends Application{
    public static void main(String[] args) {
        launch();
    }
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("views/ProductView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/myStyle.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion de produits");
       primaryStage.show();
    }
}
