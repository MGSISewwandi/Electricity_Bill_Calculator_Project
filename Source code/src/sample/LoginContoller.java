package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginContoller implements Initializable {


    @FXML
    public JFXTextField txtusername;
    @FXML
    public JFXPasswordField txtpassword;
    @FXML
    public AnchorPane loginpane;
    @FXML
    private Label isConnected;

    public LoginModel loginModel = new LoginModel();


    //Start of initialize method

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        if (loginModel.isDbConnected())
        {
            isConnected.setText("");
        } else
        {
            isConnected.setText("Not Connected");
        }
    }
    //End of initialize method

    //Login method

    public void Login(ActionEvent actionEvent)
    {
        try
        {
            if (loginModel.isLogin(txtusername.getText(), txtpassword.getText()))
            {
                isConnected.setText("user name and password is correct");
                loadhome();
            }else
            {
                isConnected.setText("user name and password is incorrect");
            }
        } catch (SQLException | IOException e)
        {
            isConnected.setText("user name and password is incorrect");
            e.printStackTrace();
        }
    }

    //Display home page method

    public void loadhome() throws IOException
    {
        AnchorPane homepane = FXMLLoader.load
                (
                getClass().getResource("sample.fxml")
                );
        loginpane.getChildren().setAll(homepane);
    }

    //Display register page method

    public void loadregister() throws IOException
    {
        AnchorPane registerpane = FXMLLoader.load
                (
                        getClass().getResource("register.fxml")
                );
        loginpane.getChildren().setAll(registerpane);
    }
}
