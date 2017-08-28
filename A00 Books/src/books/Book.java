package books;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Book implements Comparable<Book> {

	private String title;
	private String author;
	private int year;
	
	public Book(String title, String author, int year){
		this.title=title;
		this.author=author;
		this.year=year;
	}
	
	public String getTitle(){
		return title;
		
	}
	public String getAuthor(){
		return author;
	}
	public int getYear(){
		return year;
	}
	@Override
	public String toString(){
		return title+" by "+author+'('+year+')';
	}
	
	public static List<Book> getList(String f){
		List<Book> books = new ArrayList<>();
		int bookNo = 0;
		File file = new File(f);
		
		Scanner input;
		try {
			input = new Scanner(file);
			
			while(input.hasNext()){
				String[] parts = input.nextLine().split(",");
				if(parts.length==3){
					Book b = new Book(parts[0],parts[1],Integer.parseInt(parts[2]));
					books.add(b);
					bookNo++;
				}else{
					System.out.println("Problem reading: "+Arrays.toString(parts).replace("[","").replace("]", ""));
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Number of books: "+bookNo);
	
		return books;
		
	}

	@Override
	public int compareTo(Book o) {
		 return this.getTitle().compareTo(o.getTitle());
	}
}
