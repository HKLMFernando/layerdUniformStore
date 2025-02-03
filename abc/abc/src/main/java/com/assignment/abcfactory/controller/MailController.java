package com.assignment.abcfactory.controller;

import com.assignment.abcfactory.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;




public class MailController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadid();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private AnchorPane content;


    @FXML
    private TextArea description;

    @FXML
    private Button homw;

    @FXML
    private Button send;

    @FXML
    private TextField subject;

    @FXML
    private TextField txtCustMail;

    @FXML
    private ComboBox<String> combo;

    @FXML
    void homeAction(ActionEvent event) {
        {navigateTo("/view/customerlayout.fxml");}

    }

    @FXML
    void sendAction(ActionEvent event) {
        String CustomerEmail = combo.getValue();
            if (CustomerEmail == null) {
                return;
            }
            final String From = "matheeshafernando61@gmail.com";

            String subject1 = subject.getText();
            String body = description.getText();

            if (subject1.isEmpty() || body.isEmpty()) {
                new Alert(Alert.AlertType.WARNING,"Subject and body are required").show();
                return;
            }

            sendEmailWithGmail(From, String.valueOf(CustomerEmail), subject1,body);
        }
        private void sendEmailWithGmail(String From, String CustomerEmail, String subject, String body) {
            String password = "hcmg avsz zayg eoat";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(From, password);
                }
            });
            try {

                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(From));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CustomerEmail));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);

                new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
            } catch (MessagingException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
            }
        }
    private void loadid() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        ResultSet rs = connection.createStatement().executeQuery("SELECT eMail FROM customer");
        ObservableList<String> data = FXCollections.observableArrayList();

        while (rs.next()) {
            data.add(rs.getString("eMail"));
        }
        combo.setItems(data);
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

