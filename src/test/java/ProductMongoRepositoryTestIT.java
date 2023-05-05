import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.application.repository.ProductMongoRepository;
import ulaval.glo2003.application.repository.ProductRepository;
import ulaval.glo2003.application.repository.SellerMongoRepository;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMongoRepositoryTestIT {


    ProductMongoRepository productRepository;
    SellerMongoRepository sellerRepository;
    private static final Seller SELLER =
            new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
    private static final SellerMongoModel SELLER_MONGO =
            new SellerMongoModel(SELLER);

    @AfterEach
    public void delete(){
        productRepository.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        Datastore datastore;
        String MONGO_CLUSTER_LINK = "mongodb://localhost:27017";
        String MONGO_NAME = "floppa-dev" ;

        MongoClient client = MongoClients.create(MONGO_CLUSTER_LINK);
        datastore = Morphia.createDatastore(client, MONGO_NAME);
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        sellerRepository = new SellerMongoRepository(datastore);

        productRepository = new ProductMongoRepository(datastore,sellerRepository);
    }

    @Test
    public void givenProduct_whenSaving_thenProductIsSaved() {
        // arrange
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        // act
        productRepository.save(product);
        Product productSaved = productRepository.findById(product.getId());

        // assert
        assertEquals(product, productSaved);
    }

    @Test
    public void givenExistingProduct_whenUpdating_thenProductIsUpdated() {
        // arrange
        ProductCategory updatedCategory = new ProductCategory("electronics");
        String updatedTitle = "title2";
        String updatedDescrption = "description2";
        Amount updatedSuggestedPrice = new Amount("10");

        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        String productId = product.getId();
        productRepository.save(product);

        // act
        product.setCategory(updatedCategory);
        product.setDescription(updatedDescrption);
        product.setTitle(updatedTitle);
        product.setSuggestedPrice(updatedSuggestedPrice);
        productRepository.update(product);
        Product foundProduct = productRepository.findById(productId);

        // assert
        assertEquals(foundProduct.getCategory(), updatedCategory.getCategory());
        assertEquals(foundProduct.getDescription(), updatedDescrption);
        assertEquals(foundProduct.getTitle(), updatedTitle);
        assertEquals(foundProduct.getSuggestedPrice(), updatedSuggestedPrice);
    }

    @Test
    public void givenExistingProduct_whenUpdating_thenOtherProductsAreNotUpdated() {
        // arrange
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

        // act
        product.setCategory(updatedCategory);
        product.setDescription(updatedDescrption);
        product.setTitle(updatedTitle);
        product.setSeller(updatedSeller);
        product.setSuggestedPrice(updatedSuggestedPrice);
        productRepository.update(product);

        Product foundFirstProduct = productRepository.findById(firstProductId);
        Product foundLastProduct = productRepository.findById(lastProductId);

        // assert
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
        // arrange
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        // act
        Executable executable = () -> productRepository.update(product);

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenExistingProduct_whenRemoving_thenProductIsRemoved() {
        // arrange
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        productRepository.save(product);

        // act
        productRepository.remove(product);
        Executable executable = () -> productRepository.findById(product.getId());

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenNewProduct_whenRemoving_thenThrowsItemNotFoundException() {
        // arrange
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        // act
        Executable executable = () -> productRepository.remove(product);

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenExistingProduct_whenRemoving_thenOnlyOneProductIsRemoved() {
        // arrange
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

        // act
        int countBeforeRemove = productRepository.count();
        productRepository.remove(product);

        // assert
        assertEquals(countBeforeRemove - 1, productRepository.count());
    }

    @Test
    public void whenDeletingAll_thenAllProductsAreRemoved() {
        // arrange
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

        // act
        productRepository.deleteAll();

        // assert
        assertEquals(0, productRepository.count());
    }

    @Test
    public void givenExistingId_whenFinding_thenProductIsReturned() {
        // arrange
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);
        productRepository.save(product);

        // act
        Product foundProduct = productRepository.findById(product.getId());

        // assert
        assertEquals(product, foundProduct);
    }

    @Test
    public void givenNonExistingId_whenFinding_thenThrowItemNotFoundException() {
        // arrange
        Product product =
                new Product(
                        "Valid Title",
                        "Valid Description",
                        new ProductCategory("Sport"),
                        new Amount("10.50"),
                        SELLER_MONGO);

        // act
        Executable executable = () -> productRepository.findById(product.getId());

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void whenFindingAll_thenAllProductsAreReturn() {
        // arrange
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

        // act
        List<Product> products = productRepository.getAllProducts();

        // assert
        assertEquals(products.size(), productRepository.count());
    }
}
