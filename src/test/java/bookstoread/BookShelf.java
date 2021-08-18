package bookstoread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookShelf {

    List<String> books = new ArrayList<>();

    public List<String> books() {
        return books != null
                ? Collections.unmodifiableList(books) : Collections.emptyList();
    }

    public void addBook(String... bookTitlesToAdd) {
        books.addAll(Arrays.asList(bookTitlesToAdd));
    }

    public List<String> arrange() {
        return books.stream().sorted().collect(Collectors.toList());
    }
}
