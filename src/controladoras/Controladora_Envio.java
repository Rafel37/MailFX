package controladoras;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controladora_Envio implements Initializable {


    String user,pas;


    @FXML
    TextArea tContenido;
    @FXML
    JFXTextField tDestino, tAsunto;
    @FXML
    JFXButton enviar, cancelar, limpiar;
    @FXML
    Label usuarioFrom;

    String to;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioFrom.setText(user);

        to = String.valueOf(tDestino);
        acciones();
    }

    private void acciones() {

        // BOTON ENVIAR
        enviar.setOnAction(new ManejoAction());

        // BOTON CANCELAR
        cancelar.setOnAction(new ManejoAction());

        // BOTON LIMPIAR
        limpiar.setOnAction(new ManejoAction());
    }

    class ManejoAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Stage stage;

            // BOTON ENVIAR
            if (event.getSource() == enviar) {
                //Se recoge la información y se envía el email
                tDestino.toString();
                tAsunto.toString();
                tContenido.toString();
                SendMail();
            }

            // BOTON CANCELAR
            else if (event.getSource() == cancelar) {
                stage = (Stage) cancelar.getScene().getWindow();
                stage.close();


                //manuel.garcia.bot@gmail.com
                System.out.println(tDestino.getText());
                System.out.println(tAsunto.getText());
                System.out.println(tContenido.getText());
            }

            // BOTON LIMPIAR
            else if (event.getSource() == limpiar) {
                tDestino.setText("");
                tAsunto.setText("");
                tContenido.setText("");
            }
        }
    }

    // todo usuario y contraseña de la controladora_login
    public void SendMail() {
        Properties props = new Properties();
        // conexiones con gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.user", user);
        props.put("mail.password", pas);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // recoger usuario y contraseña
//        Session session = Session.getInstance(props, null);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pas);
                    }
                });
        try {
            // rellenar el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tDestino.getText())); //todo
            message.setSubject(String.valueOf(tAsunto.getText()));
            message.setText(String.valueOf(tContenido.getText()));
            // mandar mensaje y popup de exito
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "MENSAJE ENVIADO CON EXITO!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCredencial (String  sU, String sP){
        this.user = sU;
        this.pas = sP;
    }
}
