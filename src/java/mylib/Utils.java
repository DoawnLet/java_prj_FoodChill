/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import dto.Food;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Utils {

    public static String decode(String encode) {
        if (encode == null || encode.isEmpty()) {
            return encode;
        }
        return new String(encode.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static double getTotalCost(Map<Food, Integer> cart) {
        return cart.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(0d, (acc, curr) -> acc + curr);
    }

    public static String getDeliverAddress(String city, String district, String ward, String street) {
        List<String> address = Arrays.asList(street, ward, district, city);
        return address.stream()
                .filter(part -> part != null)
                .reduce("", (acc, cur) -> acc + ", " + cur)
                .substring(2);
    }

}
