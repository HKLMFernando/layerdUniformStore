package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.bo.BoFactory;
import com.assignment.abcfactory.bo.custom.CustomerBO;
import com.assignment.abcfactory.bo.custom.ItemBO;
import com.assignment.abcfactory.bo.custom.OrderBo;
import com.assignment.abcfactory.bo.custom.OrderDetailsBo;
import com.assignment.abcfactory.bo.custom.impl.OrderBoImpl;
import com.assignment.abcfactory.bo.custom.impl.OrderDetailsBoImpl;
import com.assignment.abcfactory.db.DBConnection;
import com.assignment.abcfactory.view.tdm.OrderTm;
import com.assignment.abcfactory.dao.custom.impl.CustomerDAOImpl;
import com.assignment.abcfactory.dao.custom.impl.ItemDAOImpl;
import com.assignment.abcfactory.model.*;
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

    OrderBo orderBo = (OrderBo) BoFactory.getInstance().getBo(BoFactory.BOTYPE.ORDER);
    CustomerBO customerBO= (CustomerBO) BoFactory.getInstance().getBo(BoFactory.BOTYPE.CUSTOMER);
    ItemBO itemBO = (ItemBO) BoFactory.getInstance().getBo(BoFactory.BOTYPE.ITEM);
    OrderDetailsBo orderDetailsBo = (OrderDetailsBo) BoFactory.getInstance().getBo(BoFactory.BOTYPE.ORDERDETAILS);

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

        boolean isUpdatedO = orderBo.update(orderDto);
        boolean isUpdatedOD = orderDetailsBo.update(orderDetailDto);

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

        boolean isSavedOrder = orderBo.save(orderDto);
        boolean isSavedOrderDetail = orderDetailsBo.save(orderDetailDto);

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
        CustomerDto customerDTO = customerBO.findById(selectedCustomerId);

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



    public void loadNextOrderId() throws SQLException, ClassNotFoundException {
        String nextOrderId = orderBo.getNextId();
        txtOrderID.setText(nextOrderId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<OrderAndDetailDto> orderAndDetailDtos = orderBo.getAllOrders();

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
            CustomerDto customerDto = customerBO.findByCusId(contact);


            if (customerDto != null) {
                lblCustomerName.setText(customerDto.getCust_name());
                cmbCustomerId.setValue(customerDto.getCust_id());
            } else {
                lblCustomerName.setText("CustomerDto not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//ItemDAOImpl itemModelDAO = new ItemDAOImpl();
    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectedItemId = cmbItemID.getSelectionModel().getSelectedItem();
        ItemDto itemDTO = itemBO.findById(selectedItemId);

        // If item found (itemDTO not null)
        if (itemDTO != null) {

            // FIll item related labels
            lblItemName.setText(itemDTO.getItem_name());

        }

    }

    private void loadItemId() throws SQLException {
        ArrayList<String> itemIds = itemBO.getAllIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbItemID.setItems(observableList);
    }

    @FXML
    void genarateReportOnAction(ActionEvent event)   {

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
