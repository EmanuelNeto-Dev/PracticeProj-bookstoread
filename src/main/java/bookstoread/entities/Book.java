package bookstoread.entities;

import java.time.LocalDate;

public class Book implements Comparable<Book>{

    private final String title;
    private final String author;
    private final LocalDate publishedOn;
    private LocalDate startedReadingOn;
    private LocalDate finishedToReadOn;

    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void startedReadingOn(LocalDate startedOn) {
        this.startedReadingOn = startedOn;
    }

    public void finishedToReadOn(LocalDate finishedOn) {
        this.finishedToReadOn = finishedOn;
    }

    public boolean isRead() {
        return startedReadingOn != null && finishedToReadOn != null;
    }

    @Override
    public String toString() {
        return "Book {" +
                "title='" + title + '\'' +
                "author='" + author + '\'' +
                "publishedOn='" + publishedOn + '}';
    }

    @Override
    public int compareTo(Book that) {
        return this.title.compareTo(that.title);
    }


}
