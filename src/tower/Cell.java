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
    public static Unit current_player;
    
    
    public void initCell(Board board){
        
        Rectangle rec = new Rectangle();
        rec.setFill(Color.TRANSPARENT);        
        rec.setStroke(Color.BLACK);        
        rec.setHeight(20);       
        rec.setWidth(20);   
        this.getChildren().add(rec);
        
             
        this.setOnMouseClicked((MouseEvent event) -> {                                 
              
            //Si un joueur est sélectionné, on le déplace
            if(current_player!=null){
                 if(this.getChildren().size()>1 && this.getChildren().get(1) instanceof Unit){  
                     System.out.println("Vous ne pouvez pas vous déplacer sur cette case ! ");
                 }
                 else{
                Cell tem = (Cell)current_player.getParent();                     
                tem.getChildren().remove(current_player);            
                
                if(this.getChildren().size()>1 && this.getChildren().get(1) instanceof Collectible){   
                    this.getChildren().remove(1);
                    cpt++;    
                    System.out.println(cpt);                    
                }
                 this.getChildren().add(1, current_player);
                
                current_player=null; 
                }
            }
            // Sinon on sélectionne le joueur sous la souris
            else{
                if(this.getChildren().size()>1 && this.getChildren().get(1) instanceof Unit ){
                current_player = (Unit) this.getChildren().get(1);  
        
               }
            }
                     
        });
        
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
