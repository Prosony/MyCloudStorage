package io.prsn.domain.services;


import javax.enterprise.context.Dependent;

@Dependent
public class ContractService {

    public String createToken(String phone, String lastCode){
        
//        final SecureRandom random = new SecureRandom();
//
//        final byte[] saltBytes = new byte[20];
//        random.nextBytes(saltBytes);
//
//		final String salt = Base64.encodeBase64String(saltBytes);
//        String nowDate = new ISO8601DateFormat().format(new Date());
//        String encryptesTicket = encryptorProvider.get().encrypt(salt  + "\0" + nowDate );
        return null;//encryptesTicket;
    }

    public long createContract(String phone, String code) {

//        ContractLogin contractLogin = new ContractLogin();
//        contractLogin.setPhone(phone);
//        contractLogin.setPhone(code); //encryptor.encryptPassword()
        return 0;//contractDAO.createContract(contractLogin);
    }

//    @Inject
//	@Named(AuthorizationService.ENCRYPTOR_NAME)
//	private Instance<PBEStringEncryptor> encryptorProvider;

//    @Inject
//    private PasswordEncryptor encryptor;

//    @Inject
//    private ContractDAO contractDAO;
}