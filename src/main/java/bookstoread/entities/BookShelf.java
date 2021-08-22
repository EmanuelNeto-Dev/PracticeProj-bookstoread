package bookstoread.entities;

import bookstoread.interfaces.BookFilter;

import java.util.*;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void addBook(Book... bookTitlesToAdd) {
        books.addAll(Arrays.asList(bookTitlesToAdd));
    }

    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Progress progress() {
        int booksRead = Long.valueOf(books.stream().filter(Book::isRead).count()).intValue();
        int booksToRead = books.size() - booksRead;
        int percentageCompleted = booksRead * 100 / books.size();
        int percentageToRead = booksToRead * 100 / books.size();

        return new Progress(percentageCompleted, percentageToRead, 0);
    }

    public List<Book> findBooksWithTheTitle(String title) {
        return findBooksWithTheTitle(title, b -> true);
    }

    private List<Book> findBooksWithTheTitle(String title, BookFilter filter) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title))
                .filter(b -> filter.apply(b))
                .collect(Collectors.toList());
    }

}
