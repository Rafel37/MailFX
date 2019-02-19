package controladoras;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controladora_Login implements Initializable {

    @FXML
    JFXTextField usuario;
    @FXML
    JFXPasswordField pass;
    @FXML
    JFXButton inicio, cancelar;

    Controladora_Envio controladora_envio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acciones();
    }

    private void acciones() {

        // BOTON INICIO
        inicio.setOnAction(new ManejoAction());
        // BOTON CANCELAR
        cancelar.setOnAction(new ManejoAction());
    }

    class ManejoAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Parent root;
            Scene scene;
            Stage stage;

            // BOTON INICIO
            if (event.getSource() == inicio) {
                if (usuario.getText().isEmpty() && pass.getText().isEmpty()) {
                    System.out.println("INTRODUCE USUARIO Y CONTRASEÑA");
                    JOptionPane.showMessageDialog(null, "INTRODUCE USUARIO Y CONTRASEÑA!");
                }
                else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/correo.fxml"));
                        root = loader.load();
                        scene = new Scene(root, 600, 400);
                        controladora_envio = loader.getController();
                        controladora_envio.setCredencial(usuario.getText(),pass.getText());
                        stage = (Stage) inicio.getScene().getWindow();
                        stage.setScene(scene);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // BOTON CANCELAR
            else if (event.getSource() == cancelar) {
                stage = (Stage) inicio.getScene().getWindow();
                stage.close();
            }
        }
    }
}
