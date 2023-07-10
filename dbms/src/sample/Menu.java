package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Menu {

    @FXML
    void ButtonEdit_Profile(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Editprofilecustomer.fxml"));
        Main.nextScene(new Scene(root),"Edit Profile");
    }

    @FXML
    void ButtonPrevious_Order(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("PreviousOrders.fxml"));
        Main.nextScene(new Scene(root),"Previous Orders");
    }

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void ButtonSignOut(ActionEvent event) {
        Main.back();
        Main.back();
    }

}
