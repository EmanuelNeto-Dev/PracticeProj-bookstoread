package bookstoread;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("the bookshelf =>")
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

        @Nested
        @DisplayName("Should be empty...")
        class IsEmpty {
            @Test
            @DisplayName("when no book was added to it")
            public void shelfEmptyWhenNoBookAdded() {
                List<Book> books = shelf.books();
                assertTrue(books.isEmpty(), "BookShelf should be empty.") ;
            }

            @Test
            @DisplayName("when was not passed any books in the method call")
            public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
                shelf.addBook();
                List<Book> books = shelf.books();
                assertTrue(books.isEmpty(), "BookShelf should be empty");
            }
        }

        @Nested
        @DisplayName("After adding some books...")
        class BooksAreAdded {
            @Test
            @DisplayName("should contain two books when was added two books to it")
            public void bookShelfContainsTwoBooksWhenTwoBooksAdded() {
                shelf.addBook(effectiveJava, codeComplete);
                List<Book> books = shelf.books();
                assertEquals(2, books.size(), "BookShelf should have two books.");
            }

            @Test
            @DisplayName("should be immutable for any client")
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
            @DisplayName("should be possible to print the book count and the titles")
            public void shelfToStringShouldPrintBookCountAndTitles() {
                shelf.addBook(effectiveJava, codeComplete);
                List<Book> books = shelf.books();
                assertAll(() -> assertTrue(books.contains(effectiveJava),
                                "1st title is missing"),
                        () -> assertTrue(books.contains(codeComplete),
                                "2st title is missing"),
                        () -> assertEquals(2, books.size(), "Book count missing"));
            }

        }

        @Nested
        @DisplayName("After be arranged...")
        class WasArranged {

            @Test
            @Disabled("Needs to implement Comparator")
            @DisplayName("should be possible to arrange by book title")
            public void bookshelfArrangeByBookTitle() {
                shelf.addBook(effectiveJava, codeComplete, mythicalManMonth);
                List<Book> books = shelf.arrange();
                assertEquals(asList(codeComplete, effectiveJava, mythicalManMonth)
                        , books,
                        "Books in a bookshelf should be arranged lexicographically by book title");
            }

            @Test
            @DisplayName("should be possible to stay in the same order that was inserted in the shelf")
            public void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
                shelf.addBook(effectiveJava, codeComplete, mythicalManMonth);
                shelf.arrange();
                List<Book> books = shelf.books();
                assertEquals(asList(effectiveJava, codeComplete, mythicalManMonth)
                        , books
                        , "Books in bookshelf are in insertion order");
            }

            @Test
            @DisplayName("should be possible to arrange in the reverse order that was inserted in the shelf")
            public void bookShelfArrangeByUserProvidedCriteria() {
                shelf.addBook(effectiveJava, codeComplete, mythicalManMonth);
                Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
                List<Book> books = shelf.arrange(reversed);
                //assertThat(books).isSortedAccordingTo(reversed);
            }
        }



}
