package store;

import java.util.*;

/**
 * Class OrderSet represents a
 * collection of orders
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class OrderSet {
    
	//The set is maintained in an array list.
    private ArrayList<Order> set = null;
    
    /**
     * class OrderSet constructor
     */
    public OrderSet() {
    	set = new ArrayList<Order>();
    } 
    
    /**
     * class OrderSet constructor
     * @param inSet ArrayList array list of objects to initialize this set.
     */
    public OrderSet(ArrayList<Order> inSet) {
    	set = new ArrayList<Order>(inSet);
    }
    
    /**
     * getOrderAt returns the order at the specified location in the set.
     * @param index int index of Order to return
     * @return Order
     */
    public Order getOrderAt(int index) {
    	return (Order)set.get(index);
    }
    
    /**
     * getOrderCount returns the number of orders in the set.
     * @return int
     */
    public int getOrderCount() {
    	return set.size();
    }
    
    /**
     * addOrder adds an order to the set.
     * @param order Order order to be added to the set.
     */
    public void addOrder(Order order) {
    	set.add(order);
    }
    
    /**
     * removeOrderAt removes an order at the specified index and returns it.
     * @param index int index of order to remove.
     * @return Order
     */
    public Order removeOrderAt(int index) {
    	return (Order)set.remove(index);
    }
    
    /**
     * removeOrder removes the input order from the set.
     * @param order Order order to remove
     * @return boolean
     */
    public boolean removeOrder(Order order) {
    	return set.remove(order);
    }
    
}
