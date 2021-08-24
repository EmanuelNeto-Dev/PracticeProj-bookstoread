package bookstoread.specifications;

import bookstoread.entities.Book;
import bookstoread.entities.BookPublishedYearFilter;
import bookstoread.interfaces.BookFilter;
import bookstoread.resolvers.BooksParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

@DisplayName("The book filter =>")
@ExtendWith(BooksParameterResolver.class)
public class BookFilterSpec {

    private Book cleanCode;
    private Book codeComplete;

    @BeforeEach
    void init(Map<String, Book> books) {
        cleanCode = books.get("Clean Code");
        codeComplete = books.get("Code Complete");
    }

    @Nested
    @DisplayName("when using a publisher filter...")
    class BookPublishedFilterSpec {

        @Test
        @DisplayName("should validate the asked year")
        public void validateBookPublishedPostOrBeforeToTheAskedYear() {
            BookFilter filter = BookPublishedYearFilter.after(2007);
            assertTrue(filter.apply(cleanCode));
            assertTrue(filter.apply(codeComplete));
        }
    }
}
