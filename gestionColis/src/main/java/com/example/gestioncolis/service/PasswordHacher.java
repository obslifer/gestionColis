package com.example.gestioncolis.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHacher {

    public static String session;
    public static int numeroColis;

    public static String getSession() {
        return session;
    }

    public static void setSession(String session) {
        PasswordHacher.session = session;
    }

    public static int getNumeroColis() {
        return numeroColis;
    }

    public static void setNumeroColis(int numeroColis) {
        PasswordHacher.numeroColis = numeroColis;
    }

    public static String hacher(String password) {
        String hashedPassword="";
        try {
            // Création de l'instance de MessageDigest avec l'algorithme SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Conversion du mot de passe en tableau de bytes
            byte[] passwordBytes = password.getBytes();

            // Calcul du hash du mot de passe
            byte[] hashedBytes = digest.digest(passwordBytes);

            // Conversion du tableau de bytes en une représentation hexadécimale
            hashedPassword = bytesToHex(hashedBytes);

            //System.out.println("Mot de passe haché : " + hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
