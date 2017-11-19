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
public class Cell extends StackPane {
    
    private static int cpt = 0;
    
    
    public void initCell(Board board, Player player, Collectible collectible){
        
        Rectangle rec = new Rectangle();
        rec.setFill(Color.TRANSPARENT);        
        rec.setStroke(Color.BLACK);        
        rec.setHeight(20);       
        rec.setWidth(20);       
        
              
                 
        this.setOnMouseClicked((MouseEvent event) -> {                                 
                     
            Cell tem = (Cell)player.getParent();                     
            tem.getChildren().remove(player);                         
            int a = (int) (this.getLayoutX()/21);                     
            int b = (int) (this.getLayoutY()/21);                         
            tem = (Cell)getNode(board, a,b);    
            
            if(tem.getChildren().remove(collectible)){                      
                cpt++;    
                System.out.println(cpt);                    
            }
            tem.getChildren().add(player);
                     
        });
        this.getChildren().add(rec);
    }
    private Node getNode(Board board, int x, int y){
        for(Node node : board.getChildren()){
            if(GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node)== y){
                return node;
            }
        }
        return null;
    } 
    
}
