package store;

import java.util.*;

/**
 * Class BookSet represents a
 * collection of books.
 * 
 * @author Sergey Kim
 * @version 1.0
 */
public class BookSet {
	
	// The set is maintained in an array list.
    private ArrayList<Book> set = null;
    
    /**
     * class BookSet constructor
     */
    public BookSet() {
    	set = new ArrayList<Book>();
    } 
    
    /**
     * class BookSet constructor	
     * @param inSet ArrayList array list of objects to initialize this set.
     */
    public BookSet(ArrayList<Book> inSet) {
    	set = new ArrayList<Book>(inSet);
    }
    
    /**
     * getBookAt returns the book at the specified location in the set.
     * @param index int index of Book to return
     * @return Book
     */
    public Book getBookAt(int index) {
    	return (Book)set.get(index);
    }
    
    /**
     * getBookCount returns the number of books in the set.
     * @return int
     */
    public int getBookCount() {
    	return set.size();
    }
    
    /**
     * addBook adds a book to the set.
     * @param book Book book to be added to the set
     */
    public void addBook(Book book) {
    	set.add(book);
    }
    
    /**
     * removeBookAt removes a book at the specified index and returns it.
     * @param index int index of book to remove and return.
     * @return Book
     */
    public Book removeBookAt(int index) {
    	return (Book)set.remove(index);
    }

    /**
     * removeBook removes the input book from the set.
     * @param book Book book to be removed
     * @return boolean
     */
    public boolean removeBook(Book book) {
    	return set.remove(book);
    }
    
}
