package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {

    @FXML
    public AnchorPane registerpane;
    @FXML
    public Label noticeLabel;
    @FXML
    private JFXTextField FirstName;
    @FXML
    private JFXTextField LastName;
    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private JFXPasswordField ConfirmPassword;



    public void registernewuser(ActionEvent actionEvent) throws IOException {
        if (FirstName.getText().equals("")||LastName.getText().equals("")||UserName.getText().equals("")||Password.getText().equals("")||ConfirmPassword.getText().equals(""))
        {
            noticeLabel.setText("Please Fill All The Fields");
        }
        else
        {
            if (Password.getText().equals(ConfirmPassword.getText()))
            {
                insert(FirstName.getText(),LastName.getText(),UserName.getText(),Password.getText());
            }else
            {
                noticeLabel.setText("passwords do not match");
            }
        }

        //Redirect to login page
        loadlogin();

    }


    //Data inserting method
    private static void insert(String firstname,String lastname,String username,String password)
    {
        Connection connection = SqliteConnection.Connector();
        PreparedStatement preparedStatement = null;

        try
        {
            String sql = "INSERT INTO users(UserName, Password, FName, LName) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,firstname);
            preparedStatement.setString(4,lastname);
            preparedStatement.execute();
            System.out.println("data inserted");

        } catch (SQLException e)
        {
            System.out.println("Something wrong with the database!");
        }

    }

    public void loadlogin() throws IOException
    {
        AnchorPane loginpane = FXMLLoader.load
                (
                        getClass().getResource("Login.fxml")
                );
        registerpane.getChildren().setAll(loginpane);
    }
}
