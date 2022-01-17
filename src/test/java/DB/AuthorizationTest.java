package DB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationTest {
    @Test
    void login() {
        dataBase db = new dataBase();
        for(int i = 1; i <= 5; i++) {
            assertEquals(db.auth().login("user" + i, "pass" + i), dataBase.Error.GOOD); //gdy użytkownik poda dobre hasło
            assertEquals(db.auth().login("user" + i, "bruh"), dataBase.Error.BAD_PASSWORD); //gdy użytkownik poda złe hasło
            assertEquals(db.auth().login("bruh", "bruh"), dataBase.Error.USER_NULL); //gdy użytkownik nie istnieje
        }
    }
}