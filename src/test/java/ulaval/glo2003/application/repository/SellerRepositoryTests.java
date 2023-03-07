package ulaval.glo2003.application.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.Email;
import ulaval.glo2003.domain.utils.PhoneNumber;

public class SellerRepositoryTests {

    SellerRepository sellerRepository;

    @BeforeEach
    public void setUp() {
        sellerRepository = new SellerRepository();
    }

    @Test
    public void givenSeller_whenSaving_thenSellerIsSaved() {
        // arrange
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        List<Seller> singleSeller = List.of(seller);

        // act
        sellerRepository.save(seller);
        ArrayList<Seller> repositorySellers = sellerRepository.findAll();

        // assert
        assertIterableEquals(singleSeller, repositorySellers);
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
        Seller foundSeller = sellerRepository.findById(sellerId);

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

        Seller foundFirstSeller = sellerRepository.findById(firstSellerId);
        Seller foundLastSeller = sellerRepository.findById(lastSellerId);

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
    public void givenExistingSeller_whenRemoving_thenSellerIsRemoved() {
        // arrange
        Seller seller =
                new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        sellerRepository.save(seller);

        // act
        sellerRepository.remove(seller);
        Executable executable = () -> sellerRepository.findById(seller.getId());

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
        int countBeforeRemove = sellerRepository.count();
        sellerRepository.remove(seller);

        // assert
        assertEquals(countBeforeRemove - 1, sellerRepository.count());
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
        Seller foundSeller = sellerRepository.findById(seller.getId());

        // assert
        assertEquals(seller, foundSeller);
    }

    @Test
    public void givenNonExistingId_whenFinding_thenThrowItemNotFoundException()
    {
        //arrange
        Seller seller = new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");

        // act
        Executable executable = () -> sellerRepository.findById(seller.getId());

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
        assertEquals(sellers.size(), sellerRepository.count());
    }
}
