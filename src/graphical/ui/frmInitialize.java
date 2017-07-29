/*
 * 
 *  * (c) kiboy5
 *  * 3rd Year BS in Computer Science @ Holy Angel University
 */
package graphical.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class frmInitialize extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/graphical/ui/frmMain.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Root Finder");
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
