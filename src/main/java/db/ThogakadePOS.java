package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class ThogakadePOS {
    private static ThogakadePOS instance;
    private List<Customer> connection;
    private ThogakadePOS(){
        connection = new ArrayList<>();
    }
    public List<Customer> getConnection(){
        return connection;
    }
    public static ThogakadePOS getInstance(){
        if(instance == null){
            return instance = new ThogakadePOS();
        }
        return instance;
    }


    public void updateCustomer(Customer customer) {
        for (int i = 0; i < connection.size(); i++) {
            if (connection.get(i).getId().equals(customer.getId())) {
                connection.set(i, customer);  // Replace the old customer object with the updated one
                break;
            }
        }
    }

    public void removeCustomer(Customer customer) {
        connection.remove(customer);
    }
}
