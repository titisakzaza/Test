package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private BookingSystem bookingSystem = new BookingSystem();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ระบบจองอุปกรณ์กีฬา");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("เข้าสู่ระบบ");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = bookingSystem.login(username, password);
            if (user != null) {
                showMainScreen(primaryStage, user);
            } else {
                showAlert("ล็อกอินไม่สำเร็จ", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
            }
        });

        VBox loginLayout = new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField, loginButton);
        primaryStage.setScene(new Scene(loginLayout, 300, 200));
        primaryStage.show();
    }

    private void showMainScreen(Stage stage, User user) {
        ListView<String> equipmentListView = new ListView<>();
        for (Equipment e : bookingSystem.getEquipmentList()) {
            equipmentListView.getItems().add(e.getName() + " (" + e.getQuantity() + " ชิ้น)");
        }

        Button bookButton = new Button("จอง");
        Button cancelButton = new Button("ยกเลิกการจอง");

        bookButton.setOnAction(e -> {
            String selected = equipmentListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String equipmentName = selected.split(" ")[0];
                if (bookingSystem.bookEquipment(equipmentName)) {
                    showAlert("สำเร็จ", "จอง " + equipmentName + " สำเร็จ");
                    refreshEquipmentList(equipmentListView);
                } else {
                    showAlert("ผิดพลาด", "อุปกรณ์ไม่เพียงพอ");
                }
            }
        });

        cancelButton.setOnAction(e -> {
            String selected = equipmentListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String equipmentName = selected.split(" ")[0];
                if (bookingSystem.cancelBooking(equipmentName)) {
                    showAlert("สำเร็จ", "ยกเลิกการจอง " + equipmentName + " สำเร็จ");
                    refreshEquipmentList(equipmentListView);
                }
            }
        });

        VBox mainLayout = new VBox(10, new Label("รายการอุปกรณ์"), equipmentListView, bookButton, cancelButton);
        stage.setScene(new Scene(mainLayout, 400, 300));
    }

    private void refreshEquipmentList(ListView<String> listView) {
        listView.getItems().clear();
        for (Equipment e : bookingSystem.getEquipmentList()) {
            listView.getItems().add(e.getName() + " (" + e.getQuantity() + " ชิ้น)");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
