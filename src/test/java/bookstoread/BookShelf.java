package bookstoread;

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
}
