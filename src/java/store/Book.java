package store;

/**
 * A class representing a book
 * 
 * @author Sergey Kim
 * @version 1.0
 */
public class Book {
     
    private String isbn;	// book's ISBN
    private String title;	// book's title
    private String description;	// book's description
    private String publisher;	// book's publisher
    private String publicationDate; // book's publication date
    private String bookType; // book's type
    private float price; // book's price
    
    private int quantity; // book's quantity (for cart and orders)
    
    /**
     * class Book constructor
     */
    public Book() {} 
    
    /**
     * class Book constructor
     * @param inIsbn String book's ISBN
     * @param inTitle String book's title
     * @param inDescription String book's description
     * @param inPublisher String book's publisher
     * @param inPublicationDate String book's publication date
     * @param inBookType String book's type
     * @param inPrice float book's price
     */
    public Book(String inIsbn, String inTitle, String inDescription, 
            String inPublisher, String inPublicationDate, 
            String inBookType, Float inPrice) {
            
        
        isbn = inIsbn;
        title = inTitle;
        description = inDescription;
        publisher = inPublisher;
        publicationDate = inPublicationDate;
        bookType = inBookType;
        price = inPrice;
        
        quantity = 1;
    }
    
    /**
     * Accessor for ISBN
     * @return String
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * Mutator for ISBN
     * @param inIsbn String input ISBN
     * @return void
     */
    public void setIsbn(String inIsbn) {
        isbn = inIsbn;
    }
    
    /**
     * Accessor for title
     * @return String
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Mutator for title
     * @param inTitle String input title
     * @return void
     */
    public void setTitle(String inTitle) {
        title = inTitle;
    }
    
    /**
     * Accessor for description
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Mutator for description
     * @param inDescription String input description
     * @return void
     */
    public void setDescription(String inDescription) {
        description = inDescription;
    }
    
    /**
     * Accessor for publisher
     * @return String
     */
    public String getPublisher() {
        return publisher;
    }
    
    /**
     * Mutator for publisher
     * @param inPublisher String input publisher
     * @return void
     */
    public void setPublisher(String inPublisher) {
        publisher = inPublisher;
    }
    
    /**
     * Accessor for publication date
     * @return String
     */
    public String getPublicationDate() {
        return publicationDate;
    }
    
    /**
     * Mutator for publication date
     * @param inPublicationDate input publication date
     * @return void
     */
    public void setPublicationDate(String inPublicationDate) {
        publicationDate = inPublicationDate;
    }
    
    /**
     * Accessor for book type
     * @return String
     */
    public String getBookType() {
        return bookType;
    }
    
    /**
     * Mutator for book type
     * @param inBookType input book type
     * @return void
     */
    public void setBookType(String inBookType) {
        bookType = inBookType;
    }
    
    /**
     * Accessor for price
     * @return float
     */
    public float getPrice() {
        return price;
    }
    
    /**
     * Mutator for price
     * @param inPrice input price
     * @return void
     */
    public void setPrice(float inPrice) {
        price = inPrice;
    }
    
    /**
     * Accessor for quantity
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Mutator for quantity
     * @param inQuantity input quantity
     * @return void
     */
    public void setQuantity(int inQuantity) {
        quantity = inQuantity;
    }
}
