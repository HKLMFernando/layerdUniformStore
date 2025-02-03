package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.dto.ItemDto;
import com.assignment.abcfactory.dto.OrderDto;
import com.assignment.abcfactory.dto.PaymentDto;
import com.assignment.abcfactory.dto.tm.CustomerTm;
import com.assignment.abcfactory.dto.tm.PaymentTm;
import com.assignment.abcfactory.model.ItemModel;
import com.assignment.abcfactory.model.OrderModel;
import com.assignment.abcfactory.model.PaymentModel;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private ComboBox<String> CmbPayMetod;

    @FXML
    private Button btnAddPayment;

    @FXML
    private Button btnDeletepayment;

    @FXML
    private Button btnHome;
    @FXML
    private Button btnrefresh;

    @FXML
    private ComboBox<String> cmbOrderId;

    @FXML
    private ComboBox<String> cmdPayMethod;

    @FXML
    private TableColumn<PaymentTm, String> colDate;

    @FXML
    private TableColumn<PaymentTm, String> colOrderID;

    @FXML
    private TableColumn<PaymentTm, String> colPayMetod;

    @FXML
    private TableColumn<PaymentTm,Double> colPayment;

    @FXML
    private TableColumn<PaymentTm, String> colPaymentId;

    @FXML
    private AnchorPane content;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<PaymentTm> tblPayment;


    @FXML
    private Label payAmount;

    @FXML
    private Label txtPaymentId;
    @FXML
    void addPayment(ActionEvent event) throws SQLException {
        String paymentId = txtPaymentId.getText().trim();
        String orderId = cmbOrderId.getValue();
        String payMethod = cmdPayMethod.getValue();
        String date = lblDate.getText().trim();
        String paymentText = payAmount.getText().trim();



        if (payMethod == null || payMethod.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a valid Payment Method!").show();
            return;
        }

        if (!paymentText.matches("\\d+(\\.\\d{1,2})?")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Payment Amount! (Must be a positive number, e.g., 100 or 100.50)").show();
            return;
        }
        double payment = Double.parseDouble(paymentText);

        if (paymentModel.isOrderAlreadyPaid(orderId)) {
            new Alert(Alert.AlertType.ERROR, "Payment is already recorded for Order ID: " + orderId).show();
            return;
        }

        PaymentDto paymentDTO = new PaymentDto(paymentId, payMethod, date, payment, orderId);

        boolean isSaved = paymentModel.savePayment(paymentDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Payment...!").show();
        }
    }



    @FXML
    void deletePayment(ActionEvent event) throws SQLException {
        String payID = txtPaymentId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = paymentModel.deletePayment(payID);
            if (isDeleted){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment deleted...!").show();
            }else{
                    new Alert(Alert.AlertType.ERROR, "Fail to delete Payment...!").show();
                }
        }

    }

    @FXML
    void mainlayout(ActionEvent event) {
        {
            navigateTo("/view/mainlayout.fxml");
        }
    }
    @FXML
    void reFreshAction(ActionEvent event) throws SQLException {
        loadNextPaymentId();
        loadOrderId();


        cmdPayMethod.setValue(null);
        cmbOrderId.setValue(null);
        lblDate.setText(LocalDate.now().toString());
        btnAddPayment.setDisable(false);

    }


    PaymentModel paymentModel = new PaymentModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colPayMetod.setCellValueFactory(new PropertyValueFactory<>("payment_methord"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Bank Card", "Bank Transfer");
        cmdPayMethod.setItems(paymentMethods);

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Table data").show();
        }

    }

    private void refreshPage() throws SQLException {
        loadNextPaymentId();
        loadOrderId();


        cmdPayMethod.setValue(null);
        cmbOrderId.setValue(null);
        lblDate.setText(LocalDate.now().toString());

        loadTableData();

    }

    private void loadTableData() throws SQLException {
        ArrayList<PaymentDto> paymentDtos = paymentModel.getAllPayments();

        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDto.getPayment_id(),
                    paymentDto.getPayment_methord(),
                    paymentDto.getDate(),
                    paymentDto.getPayment(),
                    paymentDto.getOrder_id()

            );
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    private void loadNextPaymentId() throws SQLException {
        String nextPaymentId = paymentModel.getNextPayId();
        txtPaymentId.setText(nextPaymentId);

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

    private void loadOrderId() throws SQLException {
        ArrayList<String> orderIds = paymentModel.getAllOrderIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderIds);
        cmbOrderId.setItems(observableList);
    }

    @FXML
    void orderIdComboboxAction(ActionEvent event) throws SQLException {

        try {
            String selectedOrderId = cmbOrderId.getSelectionModel().getSelectedItem();
            if (selectedOrderId != null) {
                OrderDto orderDto = paymentModel.findByOrderId(selectedOrderId);

                if (orderDto != null) {
                    lblDate.setText(orderDto.getOrder_date().toString());
                    double paymentAmount = paymentModel.getPaymont(selectedOrderId);
                    payAmount.setText(String.valueOf(paymentAmount));
                } else {
                    new Alert(Alert.AlertType.WARNING, "Order not found for ID: " + selectedOrderId).show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "select an order id: " ).show();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            new SQLIntegrityConstraintViolationException(e);

            new Alert(Alert.AlertType.ERROR,"Payment is already done!").show();
        }
    }
    @FXML
    void PaymentTableOnAction(MouseEvent event) {
        PaymentTm paymentTm = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTm != null) {
            txtPaymentId.setText(paymentTm.getPayment_id());
            cmdPayMethod.setValue(paymentTm.getPayment_methord());
            lblDate.setText(paymentTm.getDate());
            payAmount.setText(String.valueOf(paymentTm.getPayment()));
            cmbOrderId.setValue(paymentTm.getOrder_id());

            btnAddPayment.setDisable(true);
            btnrefresh.setDisable(false);
        }

    }

}





