/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume
 */
public class Home extends BorderPane {

    public Button btnStart;
    private final Label labelPlayer1;
    public TextField player1Name;
    private final Label labelPlayer2;
    public TextField player2Name;
    private final Label title;
   

    //Ecran d'accuiel sur lequel les joueurs saisissent leurs noms et couleurs
    //Que de l'affichage, rien d'intéressant
    public Home() {
        title = new Label("----------- TOWER -----------");
        btnStart = new Button("START");
        btnStart.prefWidth(200);
        labelPlayer1 = new Label("Humain - Joueur 1 : ");
        labelPlayer2 = new Label("Orc - Joueur 2 : ");
        player1Name = new TextField("Joueur1");
        player1Name.setMaxWidth(100);
        player2Name = new TextField("Joueur2");
        player2Name.setMaxWidth(100);
      
        

        HBox hboxStart = createHBox(btnStart);
        HBox hboxTitle = createHBox(title);
                
        VBox vboxPlayer1 = createPlayerVbox(labelPlayer1,player1Name);       
        VBox vboxPlayer2 = createPlayerVbox(labelPlayer2,player2Name);
 
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(vboxPlayer1);
        vbox.getChildren().add(vboxPlayer2);
                
        this.setBottom(hboxStart);
        this.setTop(hboxTitle);
        this.setCenter(vbox);
    }

    private HBox createHBox(Control ctrl) {
        HBox hboxStart = new HBox();
        hboxStart.setPrefHeight(50);
        hboxStart.setAlignment(Pos.CENTER);
        hboxStart.getChildren().add(ctrl);
        return hboxStart;
    }

    private VBox createPlayerVbox(Label labelPlayer, TextField playerName) {
        VBox vboxPlayer1 = new VBox();
        vboxPlayer1.setAlignment(Pos.TOP_CENTER);
        vboxPlayer1.setPadding(new Insets(30));
        vboxPlayer1.getChildren().add(labelPlayer);
        vboxPlayer1.getChildren().add(playerName);
        return vboxPlayer1;
    }

}
