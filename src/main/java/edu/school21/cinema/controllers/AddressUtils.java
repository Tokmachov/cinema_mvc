package edu.school21.cinema.controllers;

public class AddressUtils {
    public static String toIpV4(String address) {
        if (address.equals("0:0:0:0:0:0:0:1")) {
            return "127.0.0.1";
        }
        return address;
    }
}
