package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewFormController implements Initializable {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtPhoneNumber;
    public JFXComboBox<String> cmbTitle;
    public DatePicker dateDOB;
    public TextField txtSearch;
    @FXML
    private TableView<Customer> tblCustomer;
    @FXML
    private TableColumn<Customer, String> colAddress;
    @FXML
    private TableColumn<Customer, String> colDob;
    @FXML
    private TableColumn<Customer, String> colId;
    @FXML
    private TableColumn<Customer, String> colName;
    @FXML
    private TableColumn<Customer, String> colPhoneNumber;

    private Customer selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setEditable(false);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                selectedCustomer = newValue;
                setTextToValue(newValue);
            }
        }));

        getData();
    }

    public void setTextToValue(Customer newValue) {
        if (newValue != null) {
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtPhoneNumber.setText(newValue.getPhoneNumber());
            dateDOB.setValue(newValue.getDob());
        }
    }

    //reload btn
    @FXML
    void btnReloadOnAction(ActionEvent event) {
        getData();
    }

    public void getData() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

        List<Customer> customerList = ThogakadePOS.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(customerList);
        tblCustomer.setItems(customerObservableList);
    }

    //updateCustomer method
    public void updateCustomer() {
        if (selectedCustomer != null) {
            selectedCustomer.setName(txtName.getText());
            selectedCustomer.setAddress(txtAddress.getText());
            selectedCustomer.setPhoneNumber(txtPhoneNumber.getText());
            selectedCustomer.setDob(dateDOB.getValue());

            ThogakadePOS.getInstance().updateCustomer(selectedCustomer);

            tblCustomer.refresh();
        } else {
            System.out.println("No customer selected for update.");
        }
    }

    //update btn
    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        updateCustomer();
    }

    //delete btn
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (selectedCustomer != null) {

                ThogakadePOS.getInstance().getConnection().remove(selectedCustomer);

                getData();
                txtId.setText(null);
                txtPhoneNumber.setText(null);
                txtName.setText(null);
                txtAddress.setText(null);
                
        } else {
            System.out.println("No customer selected for deletion.");
        }
    }

    //Search btn
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchText = txtSearch.getText().toLowerCase();
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList(ThogakadePOS.getInstance().getConnection());
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();

        int i = 0;
        while (i < allCustomers.size()) {
            Customer customer = allCustomers.get(i);
            if (customer.getName().toLowerCase().contains(searchText) ||
                    customer.getAddress().toLowerCase().contains(searchText) ||
                    customer.getId().toLowerCase().contains(searchText)) {
                filteredList.add(customer);
            }
            i++;
        }

        tblCustomer.setItems(filteredList);
    }
}


