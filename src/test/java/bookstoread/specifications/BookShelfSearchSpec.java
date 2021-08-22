package bookstoread.specifications;

import bookstoread.entities.Book;
import bookstoread.entities.BookShelf;
import bookstoread.resolvers.BooksParameterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

@DisplayName("the bookshelf search => ")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfSearchSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book cleanCode;
    private Book refactoring;

    @BeforeEach
    void Init(Map<String, Book> books) {
        shelf = new BookShelf();
        effectiveJava = books.get("Effective Java");
        cleanCode = books.get("Clean Code");
        refactoring = books.get("Refactoring");
    }

    @BeforeEach
    void setup() {
        shelf.addBook(effectiveJava, cleanCode, refactoring);
    }

    @Test
    @DisplayName("should be able to find a book by a specific title")
    public void shouldFindBooksWithTitleContainingText() {
        List<Book> books = shelf.findBooksWithTheTitle("clean");
        assertEquals(1, books.size());
    }
}
