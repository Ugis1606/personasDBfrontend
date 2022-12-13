package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller  implements Initializable {
    //<editor-fold desc="FXML">
    @FXML
    private Label getAgeLb;

    @FXML
    private Button getDeleteBt;

    @FXML
    private TextField getEmail;

    @FXML
    private Label getEmailLb;

    @FXML
    private Label getNameLb;

    @FXML
    private TextField putAge;

    @FXML
    private Button putCancelBt;

    @FXML
    private TextField putEmail;

    @FXML
    private TextField putName;

    @FXML
    private Button putOkBt;
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getEmail.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !getEmail.getText().isEmpty()) {
                    new PersonService().getPersonData(getEmail.getText().trim()).ifPresent(data -> {
                        getNameLb.setText(data.getName());
                        getEmailLb.setText(data.getEmail());
                        getAgeLb.setText(String.valueOf(data.getAge()));
                    });
            };
        });

    }

    @FXML
    public void darbibaAction(ActionEvent e) {

        if (e.getSource() == getDeleteBt) new PersonService().deletePersonData(getEmail.getText());

        if (e.getSource() == putOkBt) {
            new PersonService().getPersonData(putEmail.getText().trim()).ifPresentOrElse(
                    value -> new PersonService().putPersonData(putName.getText(), putEmail.getText(), Integer.valueOf(putAge.getText())),
                    () -> new PersonService().postPersonData(putName.getText(), putEmail.getText(), Integer.valueOf(putAge.getText()))
            );
        }

        if (e.getSource() == putCancelBt) clear();

    }


    public void clear(){
        putName.clear();
        putEmail.clear();
        putAge.clear();
    }



}
