package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Dailyhealthmodel;

public class DetailedModelController {

    Dailyhealthmodel selectedModel;
    Scene previousScene;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Button buttonBack;
    
    //this method draws inspiration from Dr.Billah's backButtonAction method
    @FXML
    void backButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if(previousScene != null) {
            stage.setScene(previousScene);
        }
    }
    //the following method draws inspiration from Dr.Billah's initData method
    public void initData(Dailyhealthmodel model) {
        
        selectedModel = model;
        
        try {
            String imageName = "/resource/images/happy.jpg";
            Image profile = new Image(getClass().getResourceAsStream(imageName));
            image.setImage(profile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"imageView\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'DetailedModelView.fxml'.";

    }
}