package br.com.tectoy.megadrivepublicity.config;

import com.amazonaws.auth.BasicAWSCredentials;

public class AwsCredentials {

    private static AwsCredentials instance;

    private final BasicAWSCredentials credentials;

    private AwsCredentials(String keyId, String secretKeyId){
        credentials = new BasicAWSCredentials(keyId, secretKeyId);
    }

    public static AwsCredentials getInstance(AwsProperties properties){
        if(instance == null){
            instance = new AwsCredentials(properties.getAccessKeyId(), properties.getSecretKeyId());
        }
        return instance;
    }

    public BasicAWSCredentials getCredentials(){
        return credentials;
    }

}
