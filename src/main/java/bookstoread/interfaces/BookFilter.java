package bookstoread.interfaces;

import bookstoread.entities.Book;

public interface BookFilter {
    boolean apply(Book b);
}
