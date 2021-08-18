package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

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
            effectiveJava = new Book("Effective Java","Joshua Bloch", LocalDate.of(2004, Month.MAY, 8));
        }

        private BookShelfSpec(TestInfo testInfo) {
            System.out.println("Working on test "
                    + testInfo.getDisplayName());
        }

        @Test
        @DisplayName("is empty when no book was added to it")
        public void shelfEmptyWhenNoBookAdded(TestInfo testInfo) {
            System.out.println("Working on test case verifying if " + testInfo.getDisplayName());
            List<String> books = shelf.books();
            assertTrue(books.isEmpty(), "BookShelf should be empty.") ;
        }

        @Test
        public void bookShelfContainsTwoBooksWhenTwoBooksAdded() {
            shelf.addBook("Effective Java","Code Complete");
            List<String> books = shelf.books();
            assertEquals(2, books.size(), "BookShelf should have two books.");
        }

        @Test
        public void shelfToStringShouldPrintBookCountAndTitles() {
            shelf.addBook("The Phoenix Project","Java 8 in Action");
            List<String> books = shelf.books();
            assertAll(() -> assertTrue(books.contains("The Phoenix Project"),
                            "1st title is missing"),
                    () -> assertTrue(books.contains("Java 8 in Action"),
                            "2st title is missing"),
                    () -> assertEquals(2, books.size(), "Book count missing"));

        }

        @Test
        public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
            shelf.addBook();
            List<String> books = shelf.books();
            assertTrue(books.isEmpty(), "BookShelf should be empty");
        }

        @Test
        public void booksReturnedFromShelfIsImmutableForClient() {
            shelf.addBook("Effective Java", "Code Complete");
            List<String> books = shelf.books();

            try {
                books.add("The Mythical Man-Month");
                fail("Should not be able to add book to books");
            }
            catch(Exception e) {
                assertTrue(e instanceof UnsupportedOperationException,
                        "Should throw UnsupportedOperationException.");
            }
        }

        @Test
        public void bookshelfArrangeByBookTitle() {
            shelf.addBook("Effective Java", "Code Complete", "The Mythical Man-Month");
            List<String> books = shelf.arrange();
            assertEquals(Arrays.asList("Code Complete", "Effective Java", "The Mythical Man-Month")
                    , books,
                    () -> "Books in a bookshelf should be arranged lexicographically by book title");
        }

        @Test
        public void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
            shelf.addBook("Effective Java", "Code Complete", "The Mythical Man-Month");
            shelf.arrange();
            List<String> books = shelf.books();
            assertEquals(Arrays.asList("Effective Java", "Code Complete", "The Mythical Man-Month")
                    , books
                    , () -> "Books in bookshelf are in insertion order");
        }
}
