package bookstoread.specifications;

import bookstoread.entities.Book;
import bookstoread.entities.BookShelf;
import bookstoread.entities.Progress;
import bookstoread.resolvers.BooksParameterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The bookShelf progress")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfProgressSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book cleanCode;

    @BeforeEach
    void Init(Map<String, Book> books) {
        shelf = new BookShelf();
        this.effectiveJava = books.get("Effective Java");
        this.cleanCode = books.get("Clean Code");

    }

    @Nested
    @DisplayName("when no book read yet...")
    class WhenNoBookRead {

        @Test
        @DisplayName("should be 100% unread (0% Completed - 100% to read)")
        public void progress100PercentUnread() {
            Progress progress = shelf.progress();
            assertEquals(0,progress.percentualCompleted());
            assertEquals(100, progress.percentualToRead());
        }

    }

    @Nested
    @DisplayName("when some books have been read...")
    class WhenSomeBooksWasRead {

        @Test
        @DisplayName("should be possible to get the Completed and To Read percentages")
        public void getProgressWithCompletedAndToReadPercentages() {
            Progress progress = shelf.progress();

            effectiveJava.startedReadingOn(LocalDate.of(2016, Month.JULY, 1));
            cleanCode.startedReadingOn(LocalDate.of(2016, Month.JULY,31));
            cleanCode.finishedToReadOn(LocalDate.of(2016, Month.JULY, 31));

            assertEquals(40, progress.percentualCompleted());
            assertEquals(60, progress.percentualToRead());
        }
    }
}
