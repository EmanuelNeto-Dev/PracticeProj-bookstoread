package bookstoread.specifications;

import bookstoread.entities.Book;
import bookstoread.filters.BookPublishedYearFilter;
import bookstoread.filters.CompositeFilter;
import bookstoread.interfaces.BookFilter;
import bookstoread.resolvers.BooksParameterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Nested
    @DisplayName("when using multiple filters...")
    class BookMultipleFiltersSpec {

        @Test
        @DisplayName("should validate the criteria is based on multiple filters")
        public void shouldFilterOnMultipleCriteria() {
            CompositeFilter compositeFilter = new CompositeFilter();

            BookFilter mockedBookFilter = Mockito.mock(BookFilter.class);
            Mockito.when(mockedBookFilter.apply(cleanCode)).thenReturn(true);
            compositeFilter.addFilter(mockedBookFilter);

            assertTrue(compositeFilter.apply(cleanCode));
            Mockito.verify(mockedBookFilter).apply(cleanCode);
        }

        @Test
        @DisplayName("should validate criteria invoking all filters")
        public void shouldNotInvokeAfterFirstFailure() {
            CompositeFilter compositeFilter = new CompositeFilter();

            BookFilter invokedMockedFilter = Mockito.mock(BookFilter.class);
            Mockito.when(invokedMockedFilter.apply(cleanCode)).thenReturn(false);
            compositeFilter.addFilter(invokedMockedFilter);

            BookFilter nonInvokedMockedFilter = Mockito.mock(BookFilter.class);
            Mockito.when(nonInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
            compositeFilter.addFilter(nonInvokedMockedFilter);

            assertFalse(compositeFilter.apply(cleanCode));
            Mockito.verify(invokedMockedFilter).apply(cleanCode);
            Mockito.verify(nonInvokedMockedFilter).apply(cleanCode);

        }
    }

}
