package client_app.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client_app.models.Position;
import client_app.services.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    private ListView<Position> listPositions;

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if(event.getSource().equals(mnItemAdd)){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/position_edit.fxml"));
            try {
                loader.load();
                Parent parent = loader.getRoot();
                stage.setScene(new Scene(parent));
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        List<Position> positions = PositionService.INSTANCE.findAll();

        ObservableList<Position> observableList = FXCollections.observableList(positions);

        listPositions.setItems(observableList);
    }
}

