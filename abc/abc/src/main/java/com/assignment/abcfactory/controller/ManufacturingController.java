package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.bo.BoFactory;
import com.assignment.abcfactory.bo.custom.FeedBackBo;
import com.assignment.abcfactory.bo.custom.ItemBO;
import com.assignment.abcfactory.bo.custom.ManuBo;
import com.assignment.abcfactory.bo.custom.OrderBo;
import com.assignment.abcfactory.bo.custom.impl.ManuBoImpl;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.model.ManuDto;
import com.assignment.abcfactory.view.tdm.ManuTm;
import com.assignment.abcfactory.dao.custom.impl.OrderDAOImpl;
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

public class ManufacturingController implements Initializable {

    @FXML
    private Button btmSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnhome;

    @FXML
    private Button btnupdate;

    @FXML
    private ComboBox<String> cmbOrderId;

    @FXML
    private ComboBox<String> cmbProssese;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colProsses;

    @FXML
    private TableColumn<?, ?> colmanuId;

    @FXML
    private AnchorPane content;

    @FXML
    private TableView<ManuTm> tblManu;

    @FXML
    private Label txtmanuId;

    @FXML
    void cmbOrderonAction(ActionEvent event) {

    }
    ManuBo manuBo= (ManuBo) BoFactory.getInstance().getBo(BoFactory.BOTYPE.MANU);
    OrderBo orderBo= (OrderBo) BoFactory.getInstance().getBo(BoFactory.BOTYPE.ORDER);
    @FXML
    void deleteonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String manuId = txtmanuId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = manuBo.delete(manuId);
            if (isDeleted){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "prosses deleted...!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to delete prosses...!").show();
            }
        }

    }

    @FXML
    void homeonAction(ActionEvent event) {
        navigateTo("/view/mainlayout.fxml");

    }

    @FXML
    void saveManuOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String manuId = txtmanuId.getText();
        String orderId = cmbOrderId.getValue();
        String prosses = cmbProssese.getValue();


        // Validate Order ID
        if (manuBo.isOrderalredyAdded(orderId)) {
            new Alert(Alert.AlertType.ERROR, "Manufacturing is already recorded for Order ID: " + orderId).show();
            return;
        }

        ManuDto manuDto = new ManuDto(
                manuId,
                orderId,
                prosses
        );
        boolean isSaved = manuBo.save(manuDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "prosses saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Prosses...!").show();
        }

    }

    @FXML
    void tblmanuAction(MouseEvent event) {
        ManuTm manuTm = (ManuTm) tblManu.getSelectionModel().getSelectedItem();
        if (manuTm != null) {
            txtmanuId.setText(manuTm.getManufacturing_id());
            cmbOrderId.setValue(manuTm.getOrder_id());
            cmbProssese.setValue(manuTm.getProsses_details());



            btmSave.setDisable(true);
            btnupdate.setDisable(false);
        }


    }

    @FXML
    void updateonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String manuId = txtmanuId.getText();
        String orderId = cmbOrderId.getValue();
        String prosses = cmbProssese.getValue();


        ManuDto manuDto = new ManuDto(
                manuId,
                prosses,
                orderId
        );
        boolean isSaved = manuBo.update(manuDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "prosses saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Prosses...!").show();
        }

    }
//    ManuBoImpl manuBo = (ManuBoImpl) BoFactory.getInstance().getBo(BoFactory.BOTYPE.MANU);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colmanuId.setCellValueFactory(new PropertyValueFactory<>("manufacturing_id"));
        colProsses.setCellValueFactory(new PropertyValueFactory<>("prosses_details"));
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("order_id"));





        ObservableList<String> prossesMethods = FXCollections.observableArrayList("done", "Prossesing", "yet to do","Order has canceled");
        cmbProssese.setItems(prossesMethods);

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Table data").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadNextManuId();
        loadOrderId();


        cmbProssese.setValue(null);
        cmbOrderId.setValue(null);

        btmSave.setDisable(false);
        btnupdate.setDisable(true);
        loadTableData();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<ManuDto> manuDtos = manuBo.getAll();
        ObservableList<ManuTm> manuTms = FXCollections.observableArrayList();

        for (ManuDto manuDto : manuDtos) {
            ManuTm manuTm = new ManuTm(
                    manuDto.getManufacturing_id(),
                    manuDto.getOrder_id(),
                    manuDto.getProsses_details()

            );
            manuTms.add(manuTm);
        }
        tblManu.setItems(manuTms);
    }
//    OrderDAOImpl orderModel = (OrderDAOImpl) BoFactory.getInstance().getBo(BoFactory.BOTYPE.ORDER);
    private void loadOrderId() throws SQLException {
        ArrayList<String> orderIds = orderBo.getAllOrderIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderIds);
        cmbOrderId.setItems(observableList);

    }
    private void loadNextManuId() throws SQLException, ClassNotFoundException {
        String nextmanuId = manuBo.getNextId();
        txtmanuId.setText(nextmanuId);
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
}
