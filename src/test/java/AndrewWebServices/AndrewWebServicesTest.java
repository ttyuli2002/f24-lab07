package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AndrewWebServicesTest {
    // Database database;
    FakeDatabase database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        // database = new Database(); // We probably don't want to access our real database...
        // recommender = new RecSys();
        // promoService = new PromoService();
        database = new FakeDatabase();
        recommender = Mockito.mock(RecSys.class);
        promoService = Mockito.mock(PromoService.class);

        andrewWebService = new AndrewWebServices(database, recommender, promoService);
        Mockito.when(recommender.getRecommendation("Scotty")).thenReturn("Animal House");
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Test sending a promotional email using the mock promo service
        String testEmail = "test@example.com";
        andrewWebService.sendPromoEmail(testEmail);

        // Verify that the mailTo method was called once with the test email
        verify(promoService, times(1)).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Log in to the service
        andrewWebService.logIn("Scotty", 17214);

        // Verify that the mailTo method of promoService was not called after login
        verify(promoService, times(0)).mailTo(Mockito.anyString());
    }
}
