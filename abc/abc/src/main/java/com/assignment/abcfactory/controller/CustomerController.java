package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.bo.BoFactory;
import com.assignment.abcfactory.bo.custom.CustomerBO;
import com.assignment.abcfactory.model.CustomerDto;
import com.assignment.abcfactory.view.tdm.CustomerTm;
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

public class CustomerController implements Initializable {
    @FXML
    private AnchorPane content;
    @FXML
    private Button btnDelete;
    @FXML
    private Button GetOrder;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnHome;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContacts;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableView<CustomerTm> custTable;

    @FXML
    private TextField txtAddress;

    @FXML
    private Label txtCustId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtcontacts;

    @FXML
    private TextField txteMail;

    @FXML
    private Button btnsendMail;

    CustomerBO customerBo= (CustomerBO) BoFactory.getInstance().getBo(BoFactory.BOTYPE.CUSTOMER);

    @FXML
    void mainlayout(ActionEvent event) {
       {navigateTo("/view/mainlayout.fxml");}
    }
    @FXML
    void getOrder(ActionEvent event) {
        {navigateTo("/view/orderlayout.fxml");}
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
    void customerDelete(ActionEvent event) throws SQLException {
        String customerId = txtCustId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            if (customerBo.delete(customerId)) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "CustomerDto deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete customer!").show();
            }
        }

    }

    @FXML
    void customerSave(ActionEvent event) throws SQLException {
        String customerId = txtCustId.getText();
        String name = txtCustName.getText();
        String address = txtAddress.getText();
        String contacts = txtcontacts.getText();
        String nic = txtNic.getText();
        String email = txteMail.getText();

        // validate Regex
        String namePattern = "^[A-Za-z ]{3,50}$";
        String contactPattern = "^\\d{10}$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!name.matches(namePattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name. Must be 3-50 alphabetic characters.").show();
            return;
        }

        if (!contacts.matches(contactPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number. Must be 10 digits.").show();
            return;
        }
        if (!nic.matches(nicPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC. Format: 9 digits followed by V/X or 12 digits.").show();
            return;
        }
        if (!email.matches(emailPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email Address. Format: example@domain.com").show();
            return;
        }

        CustomerDto customerDTO = new CustomerDto(
                customerId,
                name,
                address,
                contacts,
                nic,
                email
        );

        boolean isSaved = customerBo.save(customerDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "CustomerDto saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save customer.").show();
        }
    }

    @FXML
    void customerUpdate(ActionEvent event) throws SQLException {
        String customerId = txtCustId.getText().trim();
        String name = txtCustName.getText().trim();
        String address = txtAddress.getText().trim();
        String contacts = txtcontacts.getText().trim();
        String nic = txtNic.getText().trim();
        String email = txteMail.getText().trim();

        // Validate inputs using regex


        if (!name.matches("[A-Za-z ]{3,50}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name! (Only letters, 3-50 characters)").show();
            return;
        }



        if (!contacts.matches("\\d{10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number! (Exactly 10 digits)").show();
            return;
        }

        if (!nic.matches("\\d{9}[Vv]")) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC! (e.g., 123456789V)").show();
            return;
        }

        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email! (e.g., example@mail.com)").show();
            return;
        }


        CustomerDto customerDTO = new CustomerDto(
                customerId,
                name,
                address,
                contacts,
                nic,
                email
        );

        if (customerBo.update(customerDTO)) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "CustomerDto updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update customer!").show();
        }
    }


    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTm customerTM = custTable.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            txtCustId.setText(customerTM.getCust_id());
            txtCustName.setText(customerTM.getCust_name());
            txtAddress.setText(customerTM.getAdress());
            txtcontacts.setText(customerTM.getContacts());
            txtNic.setText(customerTM.getNic());
            txteMail.setText(customerTM.getEMail());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    void resetCust(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("cust_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        colContacts.setCellValueFactory(new PropertyValueFactory<>("contacts"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("eMail"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ID").show();
        }

    }

    private void refreshPage() throws SQLException {
            loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtCustName.setText("");
        txtNic.setText("");
        txtAddress.setText("");
        txteMail.setText("");
        txtcontacts.setText("");
    }

    public void loadNextCustomerId() throws SQLException {
        String nextCustomerId = customerBo.getNextId();
        txtCustId.setText(nextCustomerId);
    }
//      CustomerBO customerBo= (CustomerBO) BoFactory.getInstance().getBo(BoFactory.BOTYPE.CUSTOMER);
//    CustomerBoImpl customerBo = new CustomerBoImpl();

    private void loadTableData() throws SQLException {
        ArrayList<CustomerDto> customerDTOS = customerBo.getAll();

        ObservableList<CustomerTm> customerTms = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDTOS) {
            CustomerTm customerTM = new CustomerTm(customerDto.getCust_id(), customerDto.getCust_name(), customerDto.getAdress(), customerDto.getContacts(), customerDto.getNic(), customerDto.getEMail());
            customerTms.add(customerTM);
        }

        custTable.setItems(customerTms);
    }


    @FXML
    void btnsendMailAction(ActionEvent event) {
        {navigateTo("/view/sendMail.fxml");}
    }
}
