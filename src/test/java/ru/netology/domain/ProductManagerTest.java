package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "Name", 10, "NameAuthor1");
    private Book book2 = new Book(2, "Book about Smart", 20, "Author2");
    private Book book3 = new Book(3, "Book", 30, "Author3");
    private Smartphone smart1 = new Smartphone(4, "Smart-Book", 10, "SmartManufacturer1");
    private Smartphone smart2 = new Smartphone(5, "Smart", 20, "NameManufacturer2");
    private Smartphone smart3 = new Smartphone(6, "Phone", 30, "Manufacturer3");
    private Product product = new Product(7, "Name", 10);

    @Test
    void shouldSearchByName() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        Product[] expected = new Product[]{book1, smart2};
        Product[] actual = manager.searchBy("Name");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAuthorManufacturer() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        manager.add(product);
        Product[] expected = new Product[]{smart1, smart2, smart3};
        Product[] actual = manager.searchBy("Manufacturer");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmart() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        Product[] expected = new Product[]{book2, smart1, smart2};
        Product[] actual = manager.searchBy("Smart");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByText() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Text");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAuthor() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        Product[] expected = new Product[]{book1, book2, book3};
        Product[] actual = manager.searchBy("Author");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoteById() {
        repository.save(book1);
        repository.save(smart2);
        repository.remoteById(1);
        Product[] expected = new Product[]{smart2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}