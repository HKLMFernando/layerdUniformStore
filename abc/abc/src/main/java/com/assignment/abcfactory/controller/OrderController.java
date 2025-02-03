package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.db.DBConnection;
import com.assignment.abcfactory.dto.*;
import com.assignment.abcfactory.dto.tm.CustomerTm;
import com.assignment.abcfactory.dto.tm.OrderTm;
import com.assignment.abcfactory.model.CustomerModel;
import com.assignment.abcfactory.model.ItemModel;
import com.assignment.abcfactory.model.OrderDetailsModel;
import com.assignment.abcfactory.model.OrderModel;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderController implements Initializable {

    public Button payment;

    public TextField txtSearchContact;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;


    @FXML
    private Button btnGetTotal;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnReset;
    @FXML
    private Button genarateReoprt;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemID;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colOrder;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private AnchorPane content;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblGetTotal;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private DatePicker pikDueDate;

    @FXML
    private TableView<OrderTm> tblOrder;

    @FXML
    private Label txtOrderID;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtqty;

    @FXML
    void loardpayment(ActionEvent event) {
        navigateTo("/view/paymentlayout.fxml");
    }


    @FXML
    void deletAction(ActionEvent event) {


    }
    @FXML
    void updateonAction(ActionEvent event) throws Exception {
        String orderId = txtOrderID.getText().trim();
        String customerId = cmbCustomerId.getValue();
        String itemId = cmbItemID.getValue();
        String orderDate = lblOrderDate.getText().trim();
        String dueDate = String.valueOf(pikDueDate.getValue());
        String qtyText = txtqty.getText().trim();
        String priceText = txtPrice.getText().trim();
        String totalText = lblGetTotal.getText().trim();

        if (!qtyText.matches("\\d+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity! (Must be a positive integer)").show();
            return;
        }

        if (!priceText.matches("\\d+(\\.\\d{1,2})?")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Price! (Must be a positive number, e.g., 10 or 10.50)").show();
            return;
        }

        if (!totalText.matches("\\d+(\\.\\d{1,2})?")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Total! (Must be a positive number, e.g., 10 or 10.50)").show();
            return;
        }

        int qty = Integer.parseInt(qtyText);
        double price = Double.parseDouble(priceText);
        double total = Double.parseDouble(totalText);

        OrderDto orderDto = new OrderDto(orderId, orderDate, dueDate, qty, price, customerId);
        OrderDetailsDto orderDetailDto = new OrderDetailsDto(itemId, orderId, total);

        boolean isUpdatedO = orderModel.updateOrders(orderDto);
        boolean isUpdatedOD = orderDetailsModel.updateOrders(orderDetailDto);

        if (isUpdatedO && isUpdatedOD) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Order Updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update the order. Please try again.").show();
        }
    }


    @FXML
    void clickedOrderdata(MouseEvent event) {
        OrderTm orderTm = tblOrder.getSelectionModel().getSelectedItem();
        if (orderTm != null) {

            txtOrderID.setText(orderTm.getOrder_id());
            cmbCustomerId.setValue(orderTm.getCust_id());
            cmbItemID.setValue(orderTm.getItem_id());
            lblOrderDate.setText(orderTm.getOrder_date());
            pikDueDate.setValue(LocalDate.parse(orderTm.getDue_date()));
            txtqty.setText(String.valueOf(orderTm.getQty()));
            txtPrice.setText(String.valueOf(orderTm.getPrice_per_unit()));
            lblGetTotal.setText(String.valueOf(orderTm.getTotal()));

            btnAdd.setDisable(true);

        }


    }

    OrderDetailsModel orderDetailsModel = new OrderDetailsModel();

    @FXML
    void addTable(ActionEvent event) throws Exception {
        String orderId = txtOrderID.getText().trim();
        String customerId = cmbCustomerId.getValue();
        String itemId = cmbItemID.getValue();
        String orderDate = lblOrderDate.getText().trim();
        String dueDate = String.valueOf(pikDueDate.getValue());
        String qtyText = txtqty.getText().trim();
        String priceText = txtPrice.getText().trim();
        String totalText = lblGetTotal.getText().trim();



        if (!qtyText.matches("\\d+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity! (Must be a positive integer)").show();
            return;
        }

        if (!priceText.matches("\\d+(\\.\\d{1,2})?")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Price! (Must be a positive number, e.g., 10 or 10.50)").show();
            return;
        }

        if (!totalText.matches("\\d+(\\.\\d{1,2})?")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Total! (Must be a positive number, e.g., 10 or 10.50)").show();
            return;
        }


        int qty = Integer.parseInt(qtyText);
        double price = Double.parseDouble(priceText);
        double total = Double.parseDouble(totalText);


        OrderDto orderDto = new OrderDto(orderId, orderDate, dueDate, qty, price, customerId);
        OrderDetailsDto orderDetailDto = new OrderDetailsDto(orderId, itemId, total);

        boolean isSavedOrder = orderModel.saveOrder(orderDto);
        boolean isSavedOrderDetail = orderDetailsModel.saveOrder(orderDetailDto);

        if (isSavedOrder && isSavedOrderDetail) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the order. Please try again.").show();
        }
    }


    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        CustomerDto customerDTO = CustomerModel.findById(selectedCustomerId);

        if (selectedCustomerId != null) {
            if (customerDTO != null) {
                lblCustomerName.setText(customerDTO.getCust_name());
            } else {
                lblCustomerName.setText("Not Found");
            }
        }
    }

    @FXML
    void homeButtonAction(ActionEvent event) {
        navigateTo("/view/mainlayout.fxml");
    }

    @FXML
    void reset(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void getTotalOnAction(ActionEvent event) {
        generateTotal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrder.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("due_date"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price_per_unit"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }

    private void generateTotal() {
        int qty = Integer.parseInt(txtqty.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double total = (double) qty * price;
        lblGetTotal.setText(String.valueOf(total));
    }

    private void refreshPage() throws Exception {
        loadNextOrderId();
        loadTableData();


        cmbCustomerId.setValue(null);
        cmbItemID.getSelectionModel().clearSelection();
        lblOrderDate.setText(LocalDate.now().toString());
        pikDueDate.setValue(LocalDate.now().plusDays(5));
        lblCustomerName.setText("");
        lblItemName.setText("");
        txtqty.setText("");
        txtPrice.setText("");
        lblGetTotal.setText("");
        loadItemId();

    }

    OrderModel orderModel = new OrderModel();

    public void loadNextOrderId() throws SQLException {
        String nextOrderId = orderModel.getNextOrderId();
        txtOrderID.setText(nextOrderId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<OrderAndDetailDto> orderAndDetailDtos = orderModel.getAllOrders();

        ObservableList<OrderTm> orderTms = FXCollections.observableArrayList();

        for (OrderAndDetailDto orderAndDetailDto : orderAndDetailDtos) {
            OrderTm orderTm = new OrderTm(
                    orderAndDetailDto.getOrder_id(),
                    orderAndDetailDto.getCust_id(),
                    orderAndDetailDto.getItem_id(),
                    orderAndDetailDto.getOrder_date(),
                    orderAndDetailDto.getDue_date(),
                    orderAndDetailDto.getQty(),
                    orderAndDetailDto.getPrice_per_unit(),
                    orderAndDetailDto.getTotal()
            );
            orderTms.add(orderTm);
        }

        tblOrder.setItems(orderTms);
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


    public void txtSearchContactOnAction(ActionEvent event) {
        String contact = txtSearchContact.getText();
        try {
            CustomerDto customerDto = OrderModel.findById(contact);


            if (customerDto != null) {
                lblCustomerName.setText(customerDto.getCust_name());
                cmbCustomerId.setValue(customerDto.getCust_id());
            } else {
                lblCustomerName.setText("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectedItemId = cmbItemID.getSelectionModel().getSelectedItem();
        ItemDto itemDTO = ItemModel.findById(selectedItemId);

        // If item found (itemDTO not null)
        if (itemDTO != null) {

            // FIll item related labels
            lblItemName.setText(itemDTO.getItem_name());

        }

    }

    private void loadItemId() throws SQLException {
        ArrayList<String> itemIds = ItemModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbItemID.setItems(observableList);
    }

    @FXML
    void genarateReportOnAction(ActionEvent event)   {
        System.out.println("HI");
        OrderTm orderTm = tblOrder.getSelectionModel().getSelectedItem();

        if (orderTm == null) {
            return;
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Report/Order1.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", ""); // or an actual date if needed
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load  Report").show();
            e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB eror").show();
        }

    }



}
