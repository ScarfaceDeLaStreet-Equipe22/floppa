package ulaval.glo2003.application.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.utils.*;

class ProductRepositoryTest {

    ProductRepository productRepository;
    private static final Seller SELLER =
            new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
    private static final SellerMongoModel SELLER_MONGO =
            new SellerMongoModel(SELLER);

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    public void givenProduct_whenSaving_thenProductIsSaved() {
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        List<Product> singleProduct = List.of(product);

        productRepository.save(product);
        ArrayList<Product> repositorySellers = productRepository.findAll();

        assertIterableEquals(singleProduct, repositorySellers);
    }

    @Test
    public void givenExistingProduct_whenUpdating_thenProductIsUpdated() {
        ProductCategory updatedCategory = new ProductCategory("electronics");
        String updatedTitle = "title2";
        String updatedDescrption = "description2";
        Amount updatedSuggestedPrice = new Amount("10");
        Seller updatedSeller =
                new Seller("test", "01-02-1995", "tet@tst.com", "18191234567", "bio");
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        String productId = product.getId();
        productRepository.save(product);

        product.setCategory(updatedCategory);
        product.setDescription(updatedDescrption);
        product.setTitle(updatedTitle);
        product.setSeller(updatedSeller);
        product.setSuggestedPrice(updatedSuggestedPrice);
        productRepository.update(product);
        Product foundProduct = productRepository.findById(productId);

        assertEquals(foundProduct.getCategory(), updatedCategory.getCategory());
        assertEquals(foundProduct.getDescription(), updatedDescrption);
        assertEquals(foundProduct.getTitle(), updatedTitle);
        assertEquals(foundProduct.getSeller(), updatedSeller);
        assertEquals(foundProduct.getSuggestedPrice(), updatedSuggestedPrice);
    }

    @Test
    public void givenExistingProduct_whenUpdating_thenOtherProductsAreNotUpdated() {
        ProductCategory updatedCategory = new ProductCategory("electronics");
        String updatedTitle = "title2";
        String updatedDescrption = "description2";
        Amount updatedSuggestedPrice = new Amount("10");
        Seller updatedSeller =
                new Seller("test", "01-02-1995", "tet@tst.com", "18191234567", "bio");

        Product firstProduct =
                new Product(
                        "FirstProduct",
                        "firstdescription",
                        new ProductCategory("Sport"),
                        new Amount("2"),
                        SELLER_MONGO);
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        Product lastProduct =
                new Product(
                        "LastProduct",
                        "lastdescription",
                        new ProductCategory("Sport"),
                        new Amount("4"),
                        SELLER_MONGO);

        String firstProductId = firstProduct.getId();
        String lastProductId = lastProduct.getId();
        productRepository.save(firstProduct);
        productRepository.save(product);
        productRepository.save(lastProduct);


        product.setCategory(updatedCategory);
        product.setDescription(updatedDescrption);
        product.setTitle(updatedTitle);
        product.setSeller(updatedSeller);
        product.setSuggestedPrice(updatedSuggestedPrice);
        productRepository.update(product);

        Product foundFirstProduct = productRepository.findById(firstProductId);
        Product foundLastProduct = productRepository.findById(lastProductId);


        assertNotEquals(foundFirstProduct.getCategory(), updatedCategory.getCategory());
        assertNotEquals(foundFirstProduct.getDescription(), updatedDescrption);
        assertNotEquals(foundFirstProduct.getTitle(), updatedTitle);
        assertNotEquals(foundFirstProduct.getSeller(), updatedSeller);
        assertNotEquals(foundFirstProduct.getSuggestedPrice(), updatedSuggestedPrice);

        assertNotEquals(foundLastProduct.getCategory(), updatedCategory.getCategory());
        assertNotEquals(foundLastProduct.getDescription(), updatedDescrption);
        assertNotEquals(foundLastProduct.getTitle(), updatedTitle);
        assertNotEquals(foundLastProduct.getSeller(), updatedSeller);
        assertNotEquals(foundLastProduct.getSuggestedPrice(), updatedSuggestedPrice);
    }

    @Test
    public void givenNewProduct_whenUpdating_thenThrowItemNotFoundException() {
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        Executable executable = () -> productRepository.update(product);

        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenExistingProduct_whenRemoving_thenProductIsRemoved() {
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        productRepository.save(product);

        productRepository.remove(product);
        Executable executable = () -> productRepository.findById(product.getId());

        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenNewProduct_whenRemoving_thenThrowsItemNotFoundException() {
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        Executable executable = () -> productRepository.remove(product);

        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenExistingProduct_whenRemoving_thenOnlyOneProductIsRemoved() {
        Product firstProduct =
                new Product(
                        "FirstProduct",
                        "firstdescription",
                        new ProductCategory("Sport"),
                        new Amount("2"),
                        SELLER_MONGO);
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        Product lastProduct =
                new Product(
                        "LastProduct",
                        "lastdescription",
                        new ProductCategory("Sport"),
                        new Amount("4"),
                        SELLER_MONGO);
        productRepository.save(firstProduct);
        productRepository.save(product);
        productRepository.save(lastProduct);

        int countBeforeRemove = productRepository.count();
        productRepository.remove(product);

        assertEquals(countBeforeRemove - 1, productRepository.count());
    }

    @Test
    public void whenDeletingAll_thenAllProductsAreRemoved() {
        Product firstProduct =
                new Product(
                        "FirstProduct",
                        "firstdescription",
                        new ProductCategory("Sport"),
                        new Amount("2"),
                        SELLER_MONGO);
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        Product lastProduct =
                new Product(
                        "LastProduct",
                        "lastdescription",
                        new ProductCategory("Sport"),
                        new Amount("4"),
                        SELLER_MONGO);
        productRepository.save(firstProduct);
        productRepository.save(product);
        productRepository.save(lastProduct);

        productRepository.deleteAll();

        assertEquals(0, productRepository.count());
    }

    @Test
    public void givenExistingId_whenFinding_thenProductIsReturned() {
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        productRepository.save(product);

        Product foundProduct = productRepository.findById(product.getId());

        assertEquals(product, foundProduct);
    }

    @Test
    public void givenNonExistingId_whenFinding_thenThrowItemNotFoundException() {
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        Executable executable = () -> productRepository.findById(product.getId());

        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void whenFindingAll_thenAllProductsAreReturn() {
        Product firstProduct =
                new Product(
                        "FirstProduct",
                        "firstdescription",
                        new ProductCategory("Sport"),
                        new Amount("2"),
                        SELLER_MONGO);
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        Product lastProduct =
                new Product(
                        "LastProduct",
                        "lastdescription",
                        new ProductCategory("Sport"),
                        new Amount("4"),
                        SELLER_MONGO);
        productRepository.save(firstProduct);
        productRepository.save(product);
        productRepository.save(lastProduct);

        ArrayList<Product> products = productRepository.findAll();

        assertEquals(products.size(), productRepository.count());
    }
}
