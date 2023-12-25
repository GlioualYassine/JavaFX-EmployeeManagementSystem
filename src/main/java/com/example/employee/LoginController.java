package com.example.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private Button close;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    // GET DATABASE
    private Connection connect ;
    private PreparedStatement preparedStatement ;
    private ResultSet resultSet;
    double x , y ;
    public void loginAdmin(){
        String query = "SELECT * FROM admin WHERE username = ? AND password = ? ";
        connect = Databse.getConnect() ;
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1,username.getText());
            preparedStatement.setString(2,password.getText());
            resultSet = preparedStatement.executeQuery() ;
            Alert alert ;
            System.out.println("test");
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {

                if (resultSet.next()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Login");
//                    alert.showAndWait();
                    getData.username = username.getText() ;
                    loginbtn.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
                    Stage stage = new Stage(); // create new windows
                    Scene scene = new Scene(fxmlLoader.load()); // here load the content in the scene
                    stage.setTitle("Admin !"); // title of the window
                    stage.setScene(scene);// load the scene to the window
                    // MOUSE EVENT
                    stage.initStyle(StageStyle.TRANSPARENT); // to remove the window header (made ny windows 11)
                    scene.setOnMousePressed(this::onMousePressed); // set an event when the mouse is pressed
                    scene.setOnMouseDragged(this::onMouseDragged); // set an event when the mouse is dragged
                    stage.show();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Username / Password Incorrect");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void onMousePressed(MouseEvent event) {
        x = event.getSceneX(); // get the X coordinate of the mouse
        y = event.getSceneY(); // het the Y coordinate of the mouse
    }

    private void onMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ((Scene) event.getSource()).getWindow(); // get window (stage)
        stage.setX(event.getScreenX() - x  );
        stage.setY(event.getScreenY() - y );
    }

    public void close(){
        System.exit(0);
    }
}
