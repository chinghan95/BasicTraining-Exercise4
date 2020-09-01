package bookstore.javabeans;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * @Entity is a marker annotation which indicates that this class
 * is an entity. This annotation must be placed on the class name.
 */
@Entity
@Table(name = "book")
public class Book {

	/*
	 * @Id is placed on a specific field that holds the persistent identifying
	 * properties. This field is treated as a primary key in database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Book_Id")
	private int bookId;

	@Column(name = "Book_Name")
	private String bookName;

	@Column(name = "Category")
	private String category;

	@Column(name = "Price")
	private float price;
	
	@Column(name = "Store_Id", insertable=false, updatable=false)
	private int storeId;
	/*
	 * @ManToOne specifies the JPA Many to One mapping relationship between Book
	 * entity and Bookstore entity.
	 * "Multiple Book entities belong to One Bookstore entity."
	 * 
	 * FetchType.LAZY: load books on-demand(i.e. lazily), when you call the
	 * bookstore's getBooks() method
	 * 
	 * @JoinColumn specifies the name of column for foreign key which relates book
	 * to bookstore in the database
	 */
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Bookstore.class)
	@ManyToOne
	@JoinColumn(name = "Store_Id")
	private Bookstore bookstore;

	public Book() {

	}

	public Book(int bookId, String bookName, String category, float price, Bookstore bookstore) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.category = category;
		this.price = price;
		this.bookstore = bookstore;		
	}

	public Book(String bookName, String category, float price, Bookstore bookstore) {
		this.bookName = bookName;
		this.category = category;
		this.price = price;
		this.bookstore = bookstore;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Bookstore getBookstore() {
		return bookstore;
	}

	public void setBookstore(Bookstore bookstore) {
		this.bookstore = bookstore;
	}

}
