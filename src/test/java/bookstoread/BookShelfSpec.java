package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf =>")
public class BookShelfSpec {

        private BookShelf shelf;
        private Book effectiveJava;
        private Book codeComplete;
        private Book mythicalManMonth;

        @BeforeEach
        void init() {
            shelf = new BookShelf();
            effectiveJava =
                    new Book("Effective Java","Joshua Bloch"
                            , LocalDate.of(2004, Month.MAY, 8));
            codeComplete =
                    new Book("Code Complete", "Steve McConnell",
                            LocalDate.of(2004, Month.JUNE, 9));
            mythicalManMonth =
                    new Book("The Mythical Man-Month", "Frederick Phillips Brooks"
                            , LocalDate.of(1975, Month.JANUARY, 1));
        }

        private BookShelfSpec(TestInfo testInfo) {
            System.out.println("Working on test "
                    + testInfo.getDisplayName());
        }

        @Test
        @DisplayName("is empty when no book was added to it")
        public void shelfEmptyWhenNoBookAdded(TestInfo testInfo) {
            System.out.println("Working on test case verifying if " + testInfo.getDisplayName());
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), "BookShelf should be empty.") ;
        }

        @Test
        public void bookShelfContainsTwoBooksWhenTwoBooksAdded() {
            shelf.addBook(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            assertEquals(2, books.size(), "BookShelf should have two books.");
        }

        @Test
        public void shelfToStringShouldPrintBookCountAndTitles() {
            shelf.addBook(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            assertAll(() -> assertTrue(books.contains(effectiveJava),
                            "1st title is missing"),
                    () -> assertTrue(books.contains(codeComplete),
                            "2st title is missing"),
                    () -> assertEquals(2, books.size(), "Book count missing"));

        }

        @Test
        public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
            shelf.addBook();
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), "BookShelf should be empty");
        }

        @Test
        public void booksReturnedFromShelfIsImmutableForClient() {
            shelf.addBook(effectiveJava, codeComplete);
            List<Book> books = shelf.books();

            try {
                books.add(mythicalManMonth);
                fail("Should not be able to add book to books");
            }
            catch(Exception e) {
                assertTrue(e instanceof UnsupportedOperationException,
                        "Should throw UnsupportedOperationException.");
            }
        }

        @Test
        public void bookshelfArrangeByBookTitle() {
            shelf.addBook(effectiveJava, codeComplete, mythicalManMonth);
            List<Book> books = shelf.arrange();
            assertEquals(asList(codeComplete, effectiveJava, mythicalManMonth)
                    , books,
                    "Books in a bookshelf should be arranged lexicographically by book title");
        }

        @Test
        public void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
            shelf.addBook(effectiveJava, codeComplete, mythicalManMonth);
            shelf.arrange();
            List<Book> books = shelf.books();
            assertEquals(asList(effectiveJava, codeComplete, mythicalManMonth)
                    , books
                    , "Books in bookshelf are in insertion order");
        }

        @Test
        public void bookShelfArrangeByUserProvidedCriteria() {
            shelf.addBook(effectiveJava, codeComplete, mythicalManMonth);
            List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
            assertEquals(
                    asList(mythicalManMonth, effectiveJava, codeComplete)
                    ,books
                    , "Books in a bookshelf are arranged in descending order of book title"
            );
        }
}
