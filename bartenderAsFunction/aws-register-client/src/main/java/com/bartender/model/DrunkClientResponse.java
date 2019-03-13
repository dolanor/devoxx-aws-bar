package com.bartender.model;

import java.util.Objects;

public class DrunkClientResponse {
    private String idClient;
    private String certificateArn;
    private String privateKey;
    private String publicKey;
    private String certificatePem;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String idClient;
        private String certificateArn;
        private String privateKey;
        private String publicKey;
        private String certificatePem;

        public Builder setIdClient(String idClient) {
            this.idClient = idClient;
            return this;
        }

        public Builder setCertificateArn(String certificateArn) {
            this.certificateArn = certificateArn;
            return this;
        }

        public Builder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder setPublicKey(String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public Builder setCertificatePem(String certificatePem) {
            this.certificatePem = certificatePem;
            return this;
        }

        public DrunkClientResponse build() {
            final DrunkClientResponse response = new DrunkClientResponse();
            response.idClient = idClient;
            response.certificateArn = certificateArn;
            response.privateKey = privateKey;
            response.publicKey = publicKey;
            response.certificatePem = certificatePem;
            return response;
        }
    }

    public String getIdClient() {
        return idClient;
    }

    public String getCertificateArn() {
        return certificateArn;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getCertificatePem() {
        return certificatePem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrunkClientResponse)) return false;
        DrunkClientResponse that = (DrunkClientResponse) o;
        return Objects.equals(idClient, that.idClient) &&
                Objects.equals(certificateArn, that.certificateArn) &&
                Objects.equals(privateKey, that.privateKey) &&
                Objects.equals(publicKey, that.publicKey) &&
                Objects.equals(certificatePem, that.certificatePem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, certificateArn, privateKey, publicKey, certificatePem);
    }

    @Override
    public String toString() {
        return "DrunkClientResponse{" +
                "id='" + idClient + '\'' +
                ", certificateArn='" + certificateArn + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", certificatePem='" + certificatePem + '\'' +
                '}';
    }
}
