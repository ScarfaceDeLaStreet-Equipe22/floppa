import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.application.repository.SellerMongoRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.Email;
import ulaval.glo2003.domain.utils.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellerMongoRepositoryTestIT {

    SellerMongoRepository sellerRepository;


    @AfterEach
    void Delete()
    {sellerRepository.deleteAll();}

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

    }



    @Test
    public void givenSeller_whenSaving_thenSellerIsSaved() {
        // arrange
        Seller seller =
                new Seller("testName", "1980-01-01", "seller@test.com", "15140109876", "bio");


        // act
        sellerRepository.save(seller);
        Seller sellerRepositorySellerByName = sellerRepository.getSellerById(seller.getId());

        // assert
        assertEquals(seller, sellerRepositorySellerByName);
    }

    @Test
    public void givenExistingSeller_whenUpdating_thenSellerIsUpdated() {
        // arrange
        String updatedName = "new name";
        Date updatedBirthDate = new Date("1980-02-02");
        Email updatedEmail = new Email("newSeller@test.com");
        PhoneNumber updatedPhoneNumber = new PhoneNumber("15140109877");
        String updatedBio = "NEW BIO";

        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        String sellerId = seller.getId();

        sellerRepository.save(seller);


        // act
        seller.setName(updatedName);
        seller.setBirthDate(updatedBirthDate);
        seller.setEmail(updatedEmail);
        seller.setPhoneNumber(updatedPhoneNumber);
        seller.setBio(updatedBio);
        sellerRepository.update(seller);

        Seller foundSeller = sellerRepository.getSellerById(seller.getId());
        // assert
        assertEquals(foundSeller.getName(), updatedName);
        assertEquals(foundSeller.getBirthdate(), updatedBirthDate);
        assertEquals(foundSeller.getEmail(), updatedEmail.getEmail());
        assertEquals(foundSeller.getPhoneNumber(), updatedPhoneNumber.getNumero());
        assertEquals(foundSeller.getBio(), updatedBio);
    }

    @Test
    public void givenExistingSeller_whenUpdating_thenOtherSellersAreNotUpdated() {
        // arrange
        String updatedName = "new name";
        Date updatedBirthDate = new Date("1980-02-02");
        Email updatedEmail = new Email("newSeller@test.com");
        PhoneNumber updatedPhoneNumber = new PhoneNumber("15140109877");
        String updatedBio = "NEW BIO";

        Seller firstSeller =
                new Seller(
                        "First Seller Name",
                        "1979-01-01",
                        "sellerfirst@test.com",
                        "15140009876",
                        "bio first");
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        Seller lastSeller =
                new Seller(
                        "Last Seller Name",
                        "1981-01-01",
                        "sellerlast@test.com",
                        "15140209876",
                        "bio last");

        String firstSellerId = firstSeller.getId();
        String lastSellerId = lastSeller.getId();
        sellerRepository.save(firstSeller);
        sellerRepository.save(seller);
        sellerRepository.save(lastSeller);

        // act
        seller.setName(updatedName);
        seller.setBirthDate(updatedBirthDate);
        seller.setEmail(updatedEmail);
        seller.setPhoneNumber(updatedPhoneNumber);
        seller.setBio(updatedBio);
        sellerRepository.update(seller);

        Seller foundFirstSeller = sellerRepository.getSellerById(firstSellerId);
        Seller foundLastSeller = sellerRepository.getSellerById(lastSellerId);

        // assert
        assertNotEquals(foundFirstSeller.getName(), updatedName, "First Seller name changed");
        assertNotEquals(
                foundFirstSeller.getBirthdate(),
                updatedBirthDate,
                "First Seller birthdate changed");
        assertNotEquals(
                foundFirstSeller.getEmail(), updatedEmail.getEmail(), "First Seller email changed");
        assertNotEquals(
                foundFirstSeller.getPhoneNumber(),
                updatedPhoneNumber.getNumero(),
                "First seller phone changed");
        assertNotEquals(foundLastSeller.getBio(), updatedBio, "Last seller bio changed");
        assertNotEquals(foundLastSeller.getName(), updatedName, "Last Seller name changed");
        assertNotEquals(
                foundLastSeller.getBirthdate(), updatedBirthDate, "Last Seller birthdate changed");
        assertNotEquals(
                foundLastSeller.getEmail(), updatedEmail.getEmail(), "Last Seller email changed");
        assertNotEquals(
                foundLastSeller.getPhoneNumber(),
                updatedPhoneNumber.getNumero(),
                "Last seller phone changed");
        assertNotEquals(foundLastSeller.getBio(), updatedBio, "Last seller bio changed");
    }

    @Test
    public void givenNewSeller_whenUpdating_thenThrowItemNotFoundException() {
        // arrange
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");

        // act
        Executable executable = () -> sellerRepository.update(seller);

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenNewSeller_whenRemoving_thenThrowsItemNotFoundException() {
        // arrange
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");

        // act
        Executable executable = () -> sellerRepository.remove(seller);

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void givenExistingSeller_whenRemoving_thenOnlyOneSellerIsRemoved() {
        // arrange
        Seller firstSeller =
                new Seller(
                        "First Seller Name",
                        "1979-01-01",
                        "sellerfirst@test.com",
                        "15140009876",
                        "bio first");
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        Seller lastSeller =
                new Seller(
                        "Last Seller Name",
                        "1981-01-01",
                        "sellerlast@test.com",
                        "15140209876",
                        "bio last");
        sellerRepository.save(firstSeller);
        sellerRepository.save(seller);
        sellerRepository.save(lastSeller);

        // act
        int countBeforeRemove = sellerRepository.getCount();
        sellerRepository.remove(seller);

        // assert
        assertEquals(countBeforeRemove - 1, sellerRepository.getCount());
    }

    @Test
    public void whenDeletingAll_thenAllSellersAreRemoved() {
        // arrange
        Seller firstSeller =
                new Seller(
                        "First Seller Name",
                        "1979-01-01",
                        "sellerfirst@test.com",
                        "15140009876",
                        "bio first");
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        Seller lastSeller =
                new Seller(
                        "Last Seller Name",
                        "1981-01-01",
                        "sellerlast@test.com",
                        "15140209876",
                        "bio last");
        sellerRepository.save(firstSeller);
        sellerRepository.save(seller);
        sellerRepository.save(lastSeller);

        // act
        sellerRepository.deleteAll();

        // assert
        assertEquals(0, sellerRepository.count());
    }

    @Test
    public void givenExistingId_whenFinding_thenSellerIsReturned() {
        // arrange
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        sellerRepository.save(seller);

        // act
        Seller foundSeller = sellerRepository.getSellerById(seller.getId());

        // assert
        assertEquals(seller, foundSeller);
    }

    @Test
    public void givenNonExistingId_whenFinding_thenThrowItemNotFoundException() {
        // arrange
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");

        // act
        Executable executable = () -> sellerRepository.getSellerById(seller.getId());

        // assert
        assertThrows(ItemNotFoundException.class, executable);
    }

    @Test
    public void whenFindingAll_thenAllSellersAreReturn() {
        // arrange
        Seller firstSeller =
                new Seller(
                        "First Seller Name",
                        "1979-01-01",
                        "sellerfirst@test.com",
                        "15140009876",
                        "bio first");
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        Seller lastSeller =
                new Seller(
                        "Last Seller Name",
                        "1981-01-01",
                        "sellerlast@test.com",
                        "15140209876",
                        "bio last");
        sellerRepository.save(firstSeller);
        sellerRepository.save(seller);
        sellerRepository.save(lastSeller);

        // act
        ArrayList<Seller> sellers = sellerRepository.findAll();

        // assert
        assertEquals(sellers.size(), sellerRepository.getCount());
    }
}
