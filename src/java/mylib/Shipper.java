/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.util.stream.Stream;

/**
 *
 * @author Asus
 */
public enum Shipper {
    
    GIAO_HANG_MOT("S1", "Tuan Kha", "123456789"),
    GIAO_HANG_HAI("S2", "Thanh Tam", "987654321"),
    GIAO_HANG_BA("S3", "Nguyen Khai", "683571010"); 

    private final String id;
    private final String name;
    private final String phoneNumber;

    private Shipper(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public static Shipper getShipper(String id) {
        return Stream.of(Shipper.values())
                .filter(shipper -> shipper.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
        
}

class ShipperTest {
    public static void main(String[] args) {
        // Kiểm tra với ID hợp lệ
        Shipper shipper1 = Shipper.getShipper("S1");
        System.out.println(shipper1 != null ? "✅ Shipper: " + shipper1.getName() : "❌ Không tìm thấy shipper với ID S1");

        Shipper shipper2 = Shipper.getShipper("S2");
        System.out.println(shipper2 != null ? "✅ Shipper: " + shipper2.getName() : "❌ Không tìm thấy shipper với ID S2");

        // Kiểm tra với ID không hợp lệ
        Shipper shipper3 = Shipper.getShipper("S99");
        System.out.println(shipper3 != null ? "✅ Shipper: " + shipper3.getName() : "❌ Không tìm thấy shipper với ID S99");
    }
}
