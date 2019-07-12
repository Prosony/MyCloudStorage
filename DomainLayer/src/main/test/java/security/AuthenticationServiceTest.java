package security;

import com.prsn.toolkits.config.ConfigurationProducer;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * @author プロソニーPRSN
 */

public class AuthenticationServiceTest extends Assert {

    @Inject
    private ConfigurationProducer configurationProducer;

    @Test
    public void createJWTTest() {


        assertNull(configurationProducer);
//        assertTrue(configurationProducer.getConfiguration() == null);
//        Contract contract = new Contract();
//
//        contract.set_id(UUID.randomUUID());
//        contract.setDate(Calendar.getInstance().getTime());
//        contract.setDateLastConnect(Calendar.getInstance().getTime());
//        contract.setLastCode("12345");
//        contract.setPhone("9150333401");
//
//        String token = service.createJWT(contract);
//
//        boolean isValid = service.verificationToken(token);
//
//        assertTrue(isValid);
    }
}
