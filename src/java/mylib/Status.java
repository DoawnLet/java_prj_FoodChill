package mylib;

import java.util.stream.Stream;

public enum Status {
    
    PENDING(1, "Chờ xác nhận"),
    PROCESS(2, "Đang xử lý"),
    DRIVING(3, "Đang giao"),
    SUCCESS(4, "Giao hàng thành công"),
    CANCELED(5, "Đã hủy");

    private final int id;
    private final String name;

    // Constructor phải là private
    private Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Phương thức lấy trạng thái theo ID
    public static Status getStatus(int id) {
        return Stream.of(Status.values())
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
