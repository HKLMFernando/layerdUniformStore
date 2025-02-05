package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.bo.BoFactory;
import com.assignment.abcfactory.bo.custom.ItemBO;
import com.assignment.abcfactory.bo.custom.impl.ItemBoImpl;
import com.assignment.abcfactory.model.ItemDto;
import com.assignment.abcfactory.view.tdm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private AnchorPane content;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnrefresh;


    @FXML
    private Button btnHome;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    ItemBO itemBo = (ItemBO) BoFactory.getInstance().getBo(BoFactory.BOTYPE.ITEM);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        try {
            refreshPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }



    private void refreshPage() throws SQLException {

        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtItemId.setText("");
        txtItemName.setText("");

    }
    @FXML
    void mainlayout(ActionEvent event) {
        {navigateTo("/view/mainlayout.fxml");}
    }

    @FXML
    void tableClickAction(MouseEvent event) {
        ItemTm itemTM = tblItem.getSelectionModel().getSelectedItem();
        if (itemTM != null) {
            txtItemId.setText(itemTM.getItemId());
            txtItemName.setText(itemTM.getItemName());


            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }


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



    private void loadTableData() throws SQLException {
        ArrayList<ItemDto> itemDTOS = itemBo.getAll();
        ObservableList<ItemTm> itemTMS = FXCollections.observableArrayList();

        for (ItemDto itemDTO : itemDTOS) {
            ItemTm itemTm = new ItemTm(itemDTO.getItem_id(), itemDTO.getItem_name());
            itemTMS.add(itemTm);
        }

        tblItem.setItems(itemTMS);
    }

    @FXML
    void deleteItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemId = txtItemId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = itemBo.delete(itemId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "ItemDto deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to item customer...!").show();
            }
        }
    }

    @FXML
    void saveItem(ActionEvent event) throws SQLException {
        String itemName = txtItemName.getText();
        String itemId = txtItemId.getText();

        ItemDto itemDTO = new ItemDto(
                itemId,
                itemName

        );

        boolean isSaved = itemBo.save(itemDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "ItemDto saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save ItemDto...!").show();
        }
    }

    @FXML
    void updateItem(ActionEvent event) throws SQLException {
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        ItemDto itemDTO = new ItemDto(
                itemId,
                itemName
        );
        boolean isUpdate = itemBo.update(itemDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "ItemDto update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update ItemDto...!").show();
        }

    }
    @FXML
    void refreshAction(ActionEvent event) throws SQLException {
        refreshPage();
    }


}
