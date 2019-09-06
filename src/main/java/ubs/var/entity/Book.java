package ubs.var.entity;

import java.util.ArrayList;
import java.util.List;

public class Book {
	List<Slide> slides = new ArrayList<Slide>();
	String bookName;
	int bookId;
	public Book(String bookName) {
		this.bookName = bookName;
		this.bookId = bookName.hashCode();
	}
	public void addSlides(Slide slide) {
		slides.add(slide);
	}
	public List<Slide> getSlides() {
		return slides;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}
