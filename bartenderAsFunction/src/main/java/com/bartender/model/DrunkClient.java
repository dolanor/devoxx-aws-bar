package com.bartender.model;

import java.util.Objects;

public class DrunkClient {
    private String id;
    private String certificateArn;
    private String privateKey;
    private String publicKey;
    private String certificatePem;

    public String getId() {
        return id;
    }

    public DrunkClient setId(String id) {
        this.id = id;
        return this;
    }

    public String getCertificateArn() {
        return certificateArn;
    }

    public DrunkClient setCertificateArn(String certificateArn) {
        this.certificateArn = certificateArn;
        return this;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public DrunkClient setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public DrunkClient setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public String getCertificatePem() {
        return certificatePem;
    }

    public DrunkClient setCertificatePem(String certificatePem) {
        this.certificatePem = certificatePem;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrunkClient)) return false;
        DrunkClient that = (DrunkClient) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(certificateArn, that.certificateArn) &&
                Objects.equals(privateKey, that.privateKey) &&
                Objects.equals(publicKey, that.publicKey) &&
                Objects.equals(certificatePem, that.certificatePem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, certificateArn, privateKey, publicKey, certificatePem);
    }

    @Override
    public String toString() {
        return "DrunkClient{" +
                "id='" + id + '\'' +
                ", certificateArn='" + certificateArn + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", certificatePem='" + certificatePem + '\'' +
                '}';
    }
}
