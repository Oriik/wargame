/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Guillaume
 */
public class Board extends GridPane{
    
    public void initBoard(Player player){
        for(int x=0;x<10;x++){
            for(int y=0;y<10;y++){
                 Rectangle rec = new Rectangle();
                 rec.setFill(Color.TRANSPARENT);
                 rec.setStroke(Color.BLACK);
                 rec.setHeight(20);
                 rec.setWidth(20);
                 
                 StackPane _case = new StackPane();
                
                 _case.setOnMouseClicked((MouseEvent event) -> {
                                 
                     StackPane tem = (StackPane)player.getParent();
                     tem.getChildren().remove(player);
                     
                     int a = (int) (_case.getLayoutX()/21);
                     int b = (int) (_case.getLayoutY()/21);
                    
                    tem = (StackPane)getNode(this, a,b);
                    tem.getChildren().add(player);
                     
                 });
                 _case.getChildren().add(rec);
                 
                 this.add(_case, y, x);
            }
        }
    }
     
    private Node getNode(GridPane gridPane, int x, int y){
        for(Node node : gridPane.getChildren()){
            if(GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node)== y){
                return node;
            }
        }
        return null;
    } 
}


