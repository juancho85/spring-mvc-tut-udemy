package guru.springframework.services.mapservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by juancho on 22/01/2017.
 */
@Service
@Profile("map")
public class RoleServiceMapImpl extends AbstractMapService {
    @Override
    protected void loadDomainObjects() {
        //
    }
}
