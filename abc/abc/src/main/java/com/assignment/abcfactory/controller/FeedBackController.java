package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.bo.BoFactory;
import com.assignment.abcfactory.bo.custom.CustomerBO;
import com.assignment.abcfactory.bo.custom.FeedBackBo;
import com.assignment.abcfactory.bo.custom.impl.CustomerBoImpl;
import com.assignment.abcfactory.model.CustomerDto;
import com.assignment.abcfactory.model.FeedBackDto;
import com.assignment.abcfactory.view.tdm.FeedBackTm;

import com.assignment.abcfactory.dao.custom.impl.FeedBackModelDAOImpl;
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

public class FeedBackController implements Initializable {
    @FXML
    private AnchorPane content;
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnrefresh;

    @FXML
    private Label lblCustomerName;

    @FXML
    private ComboBox<String> cmbCustomer;

    @FXML
    private TableColumn<FeedBackTm, String> colCustomerId;

    @FXML
    private TableColumn<FeedBackTm, String> colFeedBack;

    @FXML
    private TableColumn<FeedBackTm, String> colFeedbackId;

    @FXML
    private TableView<FeedBackTm> tblFeedBack;

    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TextField txtSearchContact;

    FeedBackBo feedBackBo =(FeedBackBo)BoFactory.getInstance().getBo(BoFactory.BOTYPE.FEEDBACK);
    CustomerBO customerBo = (CustomerBO) BoFactory.getInstance().getBo(BoFactory.BOTYPE.CUSTOMER);

    @FXML
    void txtSearchContactOnAction(ActionEvent event) {
        String contact = txtSearchContact.getText();
        try {
            CustomerDto customerDto = customerBo.findByCusId(contact);


            if (customerDto != null) {
                lblCustomerName.setText(customerDto.getCust_name());
                cmbCustomer.setValue(customerDto.getCust_id());
            } else {
                lblCustomerName.setText("CustomerDto not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private Label txtFeedbackId;
//    FeedBackModelDAOImpl feedBackModel = new FeedBackModelDAOImpl();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colFeedbackId.setCellValueFactory(new PropertyValueFactory<>("feed_back_id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colFeedBack.setCellValueFactory(new PropertyValueFactory<>("feed_back"));

        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }


    @FXML
    void DeleteFeedBack(ActionEvent event) throws SQLException {
        String feedbackID =txtFeedbackId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = feedBackBo.delete(feedbackID);
            if (isDeleted){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "FeedBackDto  deleted...!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to delete Feedback...!").show();
            }
        }


    }

    @FXML
    void SaveFeedBack(ActionEvent event) throws SQLException {
        String FeedbackId = txtFeedbackId.getText();
        String Feedback = txtFeedBack.getText();
        String CustomerId = cmbCustomer.getValue();

        FeedBackDto feedbackDTO = new FeedBackDto(
                FeedbackId,
                Feedback,
                CustomerId
        );
      //  FeedBackBoImpl feedBackBo = new FeedBackBoImpl();
        boolean isSaved = feedBackBo.save(feedbackDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "FeedBackDto saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save FeedBackDto...!").show();
        }

    }

    @FXML
    void mainlayout(ActionEvent event) {
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

    private void refreshPage() throws SQLException {
        loadNextFeedbackId();

        cmbCustomer.setValue(null);
        txtFeedBack.setText("");



        loadTableData();

    }

    private void loadTableData() throws SQLException {
        ArrayList<FeedBackDto> feedBackDtos = feedBackBo.getAll();

        ObservableList<FeedBackTm> feedBackTms = FXCollections.observableArrayList();

        for (FeedBackDto feedBackDto : feedBackDtos) {
            FeedBackTm feedBackTm = new FeedBackTm(
                    feedBackDto.getFeed_back_Id(),
                    feedBackDto.getFeed_back(),
                    feedBackDto.getCust_Id()

            );
            feedBackTms.add(feedBackTm);
        }

        tblFeedBack.setItems(feedBackTms);
    }

    private void loadNextFeedbackId() throws SQLException {
        String nextID = feedBackBo.getNextId();
        txtFeedbackId.setText(nextID);
    }




    @FXML
    void cmbCustomer(ActionEvent event) throws SQLException {
        String selectedCustomerId = cmbCustomer.getSelectionModel().getSelectedItem();
        CustomerDto customerDTO = customerBo.findById(selectedCustomerId);

        if (selectedCustomerId != null) {
            if(customerDTO != null) {
                lblCustomerName.setText(customerDTO.getCust_name());
            } else {
                lblCustomerName.setText("Not Found");
            }
        }

    }


    public void refresh(ActionEvent event) throws SQLException {
        refreshPage();
    }

    public void feedbacktblAction(MouseEvent mouseEvent) {
        FeedBackTm feedbackTM = tblFeedBack.getSelectionModel().getSelectedItem();
        if (feedbackTM != null) {
            txtFeedbackId.setText(feedbackTM.getFeed_back_id());
            cmbCustomer.setValue(feedbackTM.getCust_id());
            txtFeedBack.setText(feedbackTM.getFeed_back());


            btnSave.setDisable(true);
            btnrefresh.setDisable(false);
        }

    }
}
