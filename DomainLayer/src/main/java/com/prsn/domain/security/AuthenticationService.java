package com.prsn.domain.security;

import com.prsn.domain.dao.ContractDAO;
import com.prsn.domain.models.Contract;
import com.prsn.toolkits.config.ConfigurationProducer;
import com.prsn.toolkits.security.PasswordEncryptor;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.time.DateUtils;
import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;


/**
 * @author プロソニーPRSN
 */
@Dependent
public class AuthenticationService {



    @PostConstruct
    public void init() {
        secretKey = producer.getConfiguration().getString("JWT.SECRET_KEY");
        serverId = producer.getConfiguration().getString("SERVER_ID");
        ttl = Integer.parseInt(producer.getConfiguration().getString("JWT.EXPIRATION"));
    }


    //Sample method to construct a JWT
    public Map<String, String>  createJWT(Contract contract) {


        long nowMillis = System.currentTimeMillis(); //the JWT signature algorithm we will be using to sign the token

        Date dateExpiration = DateUtils.addMinutes(Calendar.getInstance().getTime(), ttl);
        String key = Base64.encodeBytes(secretKey.getBytes()); //TODO get key from config.

        JwtBuilder builder = Jwts.builder() //set the JWT Claims
                .setId(String.valueOf(UUID.randomUUID()))
                .setIssuedAt(new Date(nowMillis))
                .setIssuer(String.valueOf(contract.get_id()))
                .setSubject(producer.getConfiguration().getString("SERVER_ID"))
                .signWith(SignatureAlgorithm.HS512,key);
//                .setPayload(PasswordEncryptor.getNextSalt()); //Both 'payload' and 'claims' cannot both be specified. Choose either one.

        if (ttl >= 0) { //if it has been specified, let's add the expiration
            builder.setExpiration(dateExpiration);
        }
        Map<String, String> resutl = new HashMap<String, String>();
        resutl.put("token", builder.compact()); //Builds the JWT and serializes it to a compact, URL-safe string
        return resutl;
    }

    public boolean verificationToken(String token) {

        Jws<Claims> parser = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        Claims claims = parser.getBody();

        log.info(parser.getSignature());

        Date ttl = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());

        if (!now.after(ttl)) {
            return false;
        }

        boolean isExist = contractDAO.checkContract(UUID.fromString(claims.getIssuer()));

        if (!isExist) return false;
        if (!serverId.equals(claims.getSubject())) return false;

        return true;
    }

    @Inject
    private ConfigurationProducer producer;

    @Inject
    private ContractDAO contractDAO;

    private String secretKey;
    private String serverId;
    private int ttl;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

}
