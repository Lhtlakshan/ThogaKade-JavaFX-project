package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import db.ThogakadePOS;
import model.Customer;

public class AddCustomerFormController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private DatePicker dateDOB;

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        ObservableList<String> title = FXCollections.observableArrayList();
        title.add("Mr");
        title.add("Miss");
        cmbTitle.setItems(title);

    }
    public void btnAddOnAction(ActionEvent actionEvent) {

        Customer customer = new Customer(txtId.getText(),txtAddress.getText(),txtName.getText(),txtPhoneNumber.getText(),dateDOB.getValue(),cmbTitle.getValue());
        List<Customer> customerList = ThogakadePOS.getInstance().getConnection();
        customerList.add(customer);
        System.out.println(customerList);
        clear();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear(){
        txtId.setText(null);
        txtAddress.setText(null);
        txtName.setText(null);
        txtPhoneNumber.setText(null);
        dateDOB.setValue(null);
        cmbTitle.setValue(null);
    }
}

