package guru.springframework.services.security;

/**
 * Created by juancho on 22/01/2017.
 */
public interface EncryptionService {

    String encryptString(String input);

    boolean checkPassword(String plainPassword, String encryptedPassword);
}
