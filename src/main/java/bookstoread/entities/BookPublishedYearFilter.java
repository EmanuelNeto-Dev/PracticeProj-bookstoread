package bookstoread.entities;

import bookstoread.interfaces.BookFilter;

import java.time.LocalDate;

public class BookPublishedYearFilter implements BookFilter {

    private LocalDate startDate;

    public static BookPublishedYearFilter after(int year) {
        BookPublishedYearFilter filter = new BookPublishedYearFilter();
        filter.startDate = LocalDate.of(year, 12, 31);
        return filter;
    }

    @Override
    public boolean apply(Book b) {
        return b.getPublishedOn().isAfter(startDate)
                || b.getPublishedOn().isBefore(startDate);
    }
}
