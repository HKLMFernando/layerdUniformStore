package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.bo.custom.impl.UserBoImpl;
import com.assignment.abcfactory.model.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogLayoutController {

    @FXML
    private Button btnLog;

    @FXML
    private AnchorPane content;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    UserBoImpl userBo = new UserBoImpl();
    @FXML
    public void btnLogInAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDto user = userBo.authenticateUser(username, password);

        if (user != null) {
            navigateTo("/view/mainlayout.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }

    private void navigateTo(String fxmlPath) throws IOException {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Home page!").show();
        }

    }
}

