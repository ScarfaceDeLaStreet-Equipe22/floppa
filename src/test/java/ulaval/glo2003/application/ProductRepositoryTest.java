package ulaval.glo2003.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.Utils.ItemNotFoundException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import ulaval.glo2003.domain.Seller;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();
    }

    @Test
    void save_shouldAddProductToRepository() {
        // Arrange
        Product product =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));
        // Act
        repository.save(product);

        // Assert
        assertTrue(repository.findAll().contains(product));
    }

    @Test
    void remove_shouldRemoveProductFromRepository() {
        // Arrange
        Product product =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));
        repository.save(product);

        // Act
        repository.remove(product);

        // Assert
        assertFalse(repository.findAll().contains(product));
    }

    @Test
    void remove_withNonExistingProduct_shouldThrowItemNotFoundException() {
        // Arrange
        Product product =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));

        // Act & Assert
        assertThrows(ItemNotFoundException.class, () -> repository.remove(product));
    }

    @Test
    void findById_withExistingProduct_shouldReturnProduct() {
        // Arrange
        Product product =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));
        repository.save(product);

        // Act
        Product foundProduct = repository.findById(product.getId());

        // Assert
        assertEquals(product, foundProduct);
    }

    @Test
    void findById_withNonExistingProduct_shouldThrowItemNotFoundException() {
        // Arrange & Act & Assert
        assertThrows(ItemNotFoundException.class, () -> repository.findById("123"));
    }

    @Test
    void update_withExistingProduct_shouldUpdateProduct() {
        // Arrange
        Product product =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));
        repository.save(product);

        Product updatedProduct =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("beauty"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));

        // Act
        repository.update(updatedProduct);

        // Assert
        assertEquals(updatedProduct, repository.findById(updatedProduct.getId()));
    }

    @Test
    void update_withNonExistingProduct_shouldThrowItemNotFoundException() {
        // Arrange
        Product product =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));

        // Act & Assert
        assertThrows(ItemNotFoundException.class, () -> repository.update(product));
    }

    @Test
    void deleteAll_shouldRemoveAllProducts() {
        // Arrange
        Product product1 =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));

        Product product2 = new Product(
                "ballon",
                "ballon de foot",
                new ProductCategory("Sport"),
                new Amount("15"),
                new Seller(
                        "ahmed",
                        "1968-05-12",
                        "ahmed@gmail.com",
                        "14185691931",
                        "je suis un vendeur sympa"));
        repository.save(product1);
        repository.save(product2);

        // Act
        repository.deleteAll();

        // Assert
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void findAll_shouldReturnAllProducts() {
        // Arrange
        Product product1 =
                new Product(
                        "ballon",
                        "ballon de foot",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        new Seller(
                                "ahmed",
                                "1968-05-12",
                                "ahmed@gmail.com",
                                "14185691931",
                                "je suis un vendeur sympa"));
        Product product2 = new Product(
                "ballon",
                "ballon de foot",
                new ProductCategory("Sport"),
                new Amount("15"),
                new Seller(
                        "ahmed",
                        "1968-05-12",
                        "ahmed@gmail.com",
                        "14185691931",
                        "je suis un vendeur sympa"));
        repository.save(product1);
        repository.save(product2);

        // Act
        ArrayList<Product> products = repository.findAll();

        // Assert
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }
}
