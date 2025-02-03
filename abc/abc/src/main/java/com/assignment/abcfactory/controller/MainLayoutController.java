package com.assignment.abcfactory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainLayoutController {
    public AnchorPane roodnode;
    @FXML
    private AnchorPane content;

  

    @FXML
    void lordCustomerPage(ActionEvent event) {
        {navigateTo("/view/customerlayout.fxml");}

    }
    @FXML
    void lordFeedBackPage(ActionEvent event) {
        {navigateTo("/view/feedbacklayout.fxml");}
    }

    @FXML
    void lordItemPage(ActionEvent event) {
        {navigateTo("/view/itemlayout.fxml");}
    }

    @FXML
    void lordOrderPage(ActionEvent event) {
        {navigateTo("/view/orderlayout.fxml");}
    }

    @FXML
    void lordPaymentPage(ActionEvent event) {
        {navigateTo("/view/manufacturingdetails.fxml");}
    }

    @FXML
    void lordUserPage(ActionEvent event) {{navigateTo("/view/userlayout.fxml");}

    }
    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));


            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}
