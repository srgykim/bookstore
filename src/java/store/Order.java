package store;

/**
 * Class Order represents an order.
 * 
 * @author Sergey Kim
 * @version 1.0
 */
public class Order {

    private int orderId; // order number
    private String email; // email of the customer who made the order
    private String firstName; // first name of the customer
    private String lastName; // last name of the customer
    private String address; // address of the customer
    private String phone; // phone number of the customer
    private String deliveryMethod; // delivery method chosen by the customer
    private String orderDate; // date of the order made by the customer
    private float orderCost; // total cost of the order including delivery
    
    /**
     * class Order constructor
     */
    public Order() {}
    
    /**
     * class Order constructor
     * @param inOrderId int order number
     * @param inEmail String customer's email
     * @param inFirstName String customer's first name
     * @param inLastName String customer's last name
     * @param inAddress String customer's address
     * @param inPhone String customer's phone number
     * @param inDeliveryMethod String chosen delivery method
     * @param inOrderDate String date of the order
     * @param inOrderCost float total cost of the order
     */
    public Order(int inOrderId, String inEmail, String inFirstName, 
            String inLastName, String inAddress, String inPhone, 
            String inDeliveryMethod, String inOrderDate, Float inOrderCost) {
        
        orderId = inOrderId;
        email = inEmail;
        firstName = inFirstName;
        lastName = inLastName;
        address = inAddress;
        phone = inPhone;
        deliveryMethod = inDeliveryMethod;
        orderDate = inOrderDate;
        orderCost = inOrderCost;
    }
    
    /**
     * Accessor for order number
     * @return String
     */
    public int getOrderId() {
        return orderId;
    }
    
    /**
     * Mutator for order number
     * @param inOrderId int input order number
     * @retrn void
     */
    public void setOrderId(int inOrderId) {
        orderId = inOrderId;
    }
    
    /**
     * Accessor for customer's email
     * @return String
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Mutator for customer's email
     * @param inEmail String input customer's email
     * @return void
     */
    public void setEmail(String inEmail) {
        email = inEmail;
    }
    
    /**
     * Accessor for customer's first name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Mutator for customer's first name
     * @param inFirstName String input customer's first name
     * @return void
     */
    public void setFirstName(String inFirstName) {
        firstName = inFirstName;
    }
    
    /**
     * Accessor for customer's last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Mutator for customer's last name
     * @param inLastName String input customer's last name
     * @return void
     */
    public void setLastName(String inLastName) {
        lastName = inLastName;
    }
    
    /**
     * Accessor for customer's address
     * @return String
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Mutator for customer's address
     * @param inAddress String input customer's address
     * @return void
     */
    public void setAddress(String inAddress) {
        address = inAddress;
    }
    
    /**
     * Accessor for customer's phone number
     * @return String
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Mutator for customer's phone number
     * @param inPhone String input customer's phone number
     * @return void
     */
    public void setPhone(String inPhone) {
        phone = inPhone;
    }
    
    /**
     * Accessor for chosen delivery method
     * @return String
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }
    
    /**
     * Mutator for chosen delivery method
     * @param inDeliveryMethod String input chosen delivery method
     * @return void
     */
    public void setDeliveryMethod(String inDeliveryMethod) {
        deliveryMethod = inDeliveryMethod;
    }
    
    /**
     * Accessor for order date.
     * @return String
     */
    public String getOrderDate() {
        return orderDate;
    }
    
    /**
     * Mutator for order date.
     * @param inOrderDate String input order date
     * @return void
     */
    public void setOrderDate(String inOrderDate) {
        orderDate = inOrderDate;
    }
    
    /**
     * Accessor for total cost of the order
     * @return float
     */
    public float getOrderCost() {
        return orderCost;
    }
    
    /**
     * Mutator for total cost of the order
     * @param inOrderCost float input total cost of the order
     * @return void
     */
    public void setOrderCost(float inOrderCost) {
        orderCost = inOrderCost;
    }
    
}
