package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.dto.ItemDto;
import com.assignment.abcfactory.dto.UserDto;
import com.assignment.abcfactory.dto.tm.ItemTm;
import com.assignment.abcfactory.dto.tm.UserTm;
import com.assignment.abcfactory.model.ItemModel;
import com.assignment.abcfactory.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserController {
    @FXML
    private AnchorPane content;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    public void initialize() {
        colname.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        try {
            refreshPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }

    private void refreshPage() throws SQLException {

        loadTableData();

        btnCreate.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtUserName.setText("");
        txtPassword.setText("");

    }

    private void loadTableData()throws SQLException {
        ArrayList<UserDto> userDTOS = UserModel.getAllItems();
        ObservableList<UserTm> userTMS = FXCollections.observableArrayList();

        for (UserDto userDTO : userDTOS) {
            UserTm userTm = new UserTm(userDTO.getUser_name(), userDTO.getPassword());
            userTMS.add(userTm);
        }

        tblUser.setItems(userTMS);
    }
    @FXML
    void DeleteOnAction(ActionEvent event)throws SQLException {
        String UserName = txtUserName.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = UserModel.deleteItem(UserName);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to item customer...!").show();
            }
        }



    }

    @FXML
    void createOnAction(ActionEvent event) throws SQLException {
        String UserName = txtUserName.getText();
        String Password = txtPassword.getText();

        UserDto userDTO = new UserDto(
                UserName,
                Password

        );

        boolean isSaved = UserModel.saveUser(userDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save User...!").show();
        }

    }

    @FXML
    void homeonAction(ActionEvent event) {
        {navigateTo("/view/mainlayout.fxml");}
    }

    private void navigateTo(String fxmlPath) {
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

    @FXML
    void onClickedAction(MouseEvent event) {
        UserTm userTM = tblUser.getSelectionModel().getSelectedItem();
        if (userTM != null) {
            txtUserName.setText(userTM.getUser_name());
            txtPassword.setText(userTM.getPassword());


            btnCreate.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    void updateobAction(ActionEvent event) throws SQLException {
        String UserName = txtUserName.getText();
        String Password = txtPassword.getText();
        UserDto userDTO = new UserDto(
                UserName,
                Password
        );
        boolean isUpdate = UserModel.updateUser(userDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User Profile update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update User Profile...!").show();
        }


    }

}
