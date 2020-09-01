package bookstore.javabeans;

//import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bookstore")
public class Bookstore {
	
	@Id
	@Column(name="Store_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int storeId;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Tel")
	private String tel;
	
//	@OneToMany(fetch=FetchType.LAZY, targetEntity=Book.class, mappedBy="bookstore")
	@OneToMany(mappedBy="bookstore", cascade = CascadeType.REMOVE)
	private Set<Book> books;
	// private List<Book> books = new ArrayList<Book>();
	
	public Bookstore() {
		
	}
	
	public Bookstore(int storeId, String name, String address, String tel) {
		this.storeId = storeId;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}
	public Bookstore(String name, String address, String tel) {
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
