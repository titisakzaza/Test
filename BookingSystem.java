package application;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
	private List<User> users = new ArrayList<>();
    private List<Equipment> equipmentList = new ArrayList<>();

    public BookingSystem() {
        // เพิ่มผู้ใช้ตัวอย่าง
        users.add(new User("admin", "1234", "admin"));
        users.add(new User("user1", "pass1", "user"));

        // เพิ่มอุปกรณ์ตัวอย่าง
        equipmentList.add(new Equipment("ลูกฟุตบอล", 5));
        equipmentList.add(new Equipment("ไม้แบดมินตัน", 3));
    }

    // ตรวจสอบการล็อกอิน
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // แสดงรายการอุปกรณ์
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    // จองอุปกรณ์
    public boolean bookEquipment(String equipmentName) {
        for (Equipment e : equipmentList) {
            if (e.getName().equals(equipmentName) && e.getQuantity() > 0) {
                e.book();
                return true;
            }
        }
        return false;
    }

    // ยกเลิกการจอง
    public boolean cancelBooking(String equipmentName) {
        for (Equipment e : equipmentList) {
            if (e.getName().equals(equipmentName)) {
                e.cancel();
                return true;
            }
        }
        return false;
    }
}
