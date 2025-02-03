module com.assignment.abcfactory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires net.sf.jasperreports.core;
    requires java.mail;
    requires java.desktop;


    opens com.assignment.abcfactory.controller to javafx.fxml;
    exports com.assignment.abcfactory;
    opens com.assignment.abcfactory.view.tdm to javafx.base;
}