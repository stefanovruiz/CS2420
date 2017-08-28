package books;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookApp {
	
	public static void main(String[] args){
		List<Book> b = Book.getList("src/books.csv");
		
		
		System.out.println();
		
		System.out.println("Sorted List:");
		Collections.sort(b);
		for(Book book: b){
			System.out.println(book.toString());
		}
		
		Comparator c = Collections.reverseOrder();
		Collections.sort(b, c);
		System.out.println();
		
		System.out.println("Reverse Order:");
		for(Book book: b){
			System.out.println(book.toString());
		}
	}
		
		
}
