package ulaval.glo2003.application.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Seller;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SellerRepositoryTests
{

    SellerRepository sellerRepository;

    @BeforeEach
    public void setUp()
    {
        sellerRepository = new SellerRepository();
    }

    @Test
    public void givenSeller_whenSaving_thenSellerIsSaved()
    {
        //arrange
        Seller seller = new Seller("Seller Name", "1980-01-01", "seller@test.com", "15140109876", "bio");
        List<Seller> singleSeller = List.of(seller);

        //act
        sellerRepository.save(seller);
        ArrayList<Seller> repositorySellers = sellerRepository.findAll();

        //assert
        assertIterableEquals(singleSeller, repositorySellers);
    }

}
