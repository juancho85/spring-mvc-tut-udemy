package guru.springframework.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by juancho on 22/01/2017.
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor strongEcryptor;

    @Autowired
    public void setStrongEcryptor(StrongPasswordEncryptor strongEcryptor) {
        this.strongEcryptor = strongEcryptor;
    }

    @Override
    public String encryptString(String input) {
        return this.strongEcryptor.encryptPassword(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return false;
    }
}
