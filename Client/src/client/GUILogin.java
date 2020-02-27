/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import static java.lang.System.exit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author yep
 */
public class GUILogin extends Application {
    
    Stage primaryStage;
    boolean connected = false;
    Client client;
    
    //Start the GUI
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        
        primaryStage.setTitle("Medical Information Center");
        primaryStage.setScene(ipScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //GUI for logging into server
    private Scene ipScene() {
        
        Button b1 = new Button("Connect");
        Button b2 = new Button("Cancel");
        
        Label ip = new Label("Server IP");
        Label port = new Label("Port");
        //Label pw = new Label("Server Password");
        Label header = new Label("Connect To Server");
        
        TextField ipT = new TextField();
        TextField portT = new TextField();
        //TextField pwT = new TextField();
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               
                try {
                
                    client = new Client(ipT.getText(), Integer.parseInt(portT.getText()));
                }
                
                catch (IOException ex) {
                    
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Could Not Connect To Server");
                    alert.setContentText("Confirm the server's IP address, and that it is entered correctly. \n"
                            + "Confirm the server's port number, and that it is entered correctly. \n"
                            + "Confirm that you are connected to the same network as the server.");
                    alert.showAndWait();
                }
                
                if(client.isConnected())
                    primaryStage.setScene(loginScene());
            }
        });
        
        b2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                exit(0);
            }
        });
        
        HBox hb1 = new HBox(header);
        hb1.setAlignment(Pos.CENTER);
        hb1.setScaleX(2);
        hb1.setScaleY(2);
        
        HBox hb2 = new HBox(10, ip, ipT);
        hb2.setAlignment(Pos.CENTER);
        
        HBox hb3 = new HBox(10, port, portT);
        hb3.setAlignment(Pos.CENTER);
        
        HBox hb4 = new HBox(10, b1, b2);
        hb4.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox vb = new VBox(25, hb1, hb2, hb3, hb4);
        vb.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vb, 300, 200);
        
        return scene;
    }
    
    //GUI for logging into user account
    private Scene loginScene() {
        
        primaryStage.setTitle("Logging In");
        Button b1 = new Button("Login");
        Button b2 = new Button("Register");
        Button b3 = new Button("Disconnect");
        
        b2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                primaryStage.setScene(registerScene());
            }
        });
        
        Label un = new Label("Username");
        Label pw = new Label("Password");
        Label header = new Label("Medical Information Center"); 
        
        TextField unT = new TextField();
        TextField pwT = new TextField();
        
        HBox hb1 = new HBox(header);
        hb1.setAlignment(Pos.CENTER);
        hb1.setScaleX(2);
        hb1.setScaleY(2);
        
        HBox hb2 = new HBox(10, un, unT);
        hb2.setAlignment(Pos.CENTER);
        
        HBox hb3 = new HBox(10, pw, pwT);
        hb3.setAlignment(Pos.CENTER);
        
        HBox hb4 = new HBox(10, b1, b2);
        hb4.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox vb = new VBox(25, hb1, hb2, hb3, hb4);
        vb.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vb, 400, 200);
        
        return scene;
    }
    
    //GUI for registering new user
    private Scene registerScene() {
        
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().add("Doctor");
        cb.getItems().add("Nurse");
        cb.getItems().add("Patient");
        
        boolean reg = false;
         
        Button b1 = new Button("Register");
        Button b2 = new Button("Cancel");
        
        Label un = new Label("New Username");
        Label pw = new Label("New Password");
        Label pwV = new Label("Verify Password");
        Label header = new Label("Registration");
        Label c = new Label("You are a");
        Label reged = new Label();
        
        TextField unT = new TextField();
        TextField pwT = new TextField();
        TextField pwVT = new TextField();
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(reg)
                    primaryStage.setScene(loginScene());
                
                b1.setText("Login");
                reged.setText("Registered");
            }
        });
        
        b2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                primaryStage.setScene(loginScene());
            }
        });
        
        HBox hb1 = new HBox(header);
        hb1.setAlignment(Pos.CENTER);
        hb1.setScaleX(2);
        hb1.setScaleY(2);
        
        HBox hb2 = new HBox(10, un, unT);
        hb2.setAlignment(Pos.CENTER);
        
        HBox hb3 = new HBox(10, pw, pwT);
        hb3.setAlignment(Pos.CENTER);
        
        HBox hb4 = new HBox(10, pwV, pwVT);
        hb4.setAlignment(Pos.CENTER);

        HBox hb5 = new HBox(10, c, cb);
        hb5.setAlignment(Pos.CENTER);
       
        HBox hb6 = new HBox(10, reged);
        hb6.setAlignment(Pos.CENTER);
        
        HBox hb7 = new HBox(10, b1, b2);
        hb7.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox vb = new VBox(25, hb1, hb5, hb2, hb3, hb4, hb6, hb7);
        vb.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vb, 350, 350);
        
        return scene;
    }
}
