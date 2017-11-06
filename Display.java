import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.Path;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.util.HashMap;
import java.util.Random;             
import javafx.geometry.Rectangle2D;

public class Display extends Application {
    private Game game = new Game();  
    private Canvas canvas = new Canvas(900, 750); //the 900x750 pixel canvas that we will be outputting graphics to
    private GraphicsContext g = canvas.getGraphicsContext2D(); 
    private Group root = new Group(canvas); 
    private Scene scene = new Scene(root); 
    private int grd[][] = new int[10][15]; //the grid of 150 cells which we have split our display canvas into
    private Image knight1 = new Image("knighttpbg.png"); //red knight image import
    private Image knight2 = new Image("knight2tpbg.png"); //blue knight image import
    private Image selectoutline = new Image("selectoutline.png");  //select outline image import
    private Image redsquare = new Image("redsquare.png"); //red square movement shadow image import
    private Text text1 = new Text(25, 670, "Please select one of your knights by clicking on it.");
    private Text text2 = new Text(25, 670, "Select where you would like to move your knight by clicking on a shaded square.");
    private Text text3 = new Text(25, 645, "Red player:");
    private Text text4 = new Text(25, 645, "Blue player:");
    private int playersturn = 1;  //a counter for which player's turn it currently is (1 is red's turn, 2 is blue's turn)
    private int somethingselected=0; //a flag to indicate whether the user has selected something or not (not a boolean for later extension into multiple select actions)
    private int savedx = 0;
    private int savedy = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        int i=0;
        Path path = new Path();        
        primaryStage.setScene(scene);
        grd=game.playgame(game); //sets up a new game...
        drawGRID(g, grd);
        definetext();
        scene.setOnMouseClicked(mouseHandler);
        root.getChildren().add(path);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getEventType()==MouseEvent.MOUSE_CLICKED){
                double x=mouseEvent.getSceneX();
                double y=mouseEvent.getSceneY();
                int coordy=(int)((x-(x%60))/60);
                int coordx=(int)((y-(y%60))/60);
                //default: square clicked is not one of your knights while ss is 0
                if(grd[coordx][coordy]!=playersturn+1 && grd[coordx][coordy]!=4){
                    root.getChildren().remove(text2);
                    root.getChildren().remove(text1);
                    root.getChildren().add(text1);
                    undodrawMOVEBUBBLE (g, grd);
                    somethingselected=0;
                }
                // if the square that has been chosen is one of your knights and ss is 0
                if(grd[coordx][coordy]==playersturn+1 && somethingselected==0){ //i.e. if the selected item is on the player's team
                    root.getChildren().remove(text1);
                    root.getChildren().remove(text2);
                    root.getChildren().add(text2);
                    savedx=coordx;
                    savedy=coordy;
                    drawSELECT(g, coordx, coordy, grd);
                    somethingselected=1;
                }
                // if the square that has been chosen is an applicable movement space with ss as 1
                if(grd[coordx][coordy]==4 && somethingselected==1){
                    root.getChildren().remove(text1);
                    root.getChildren().remove(text2);
                    root.getChildren().add(text1);
                    grd[savedx][savedy]=0;
                    grd[coordx][coordy]=playersturn+1;
                    undodrawMOVEBUBBLE (g, grd);
                    somethingselected=0;
                    if(playersturn==2){
                        playersturn=1;
                        root.getChildren().remove(text4);
                        root.getChildren().add(text3);
                    }
                    else{
                        playersturn=2;
                        root.getChildren().remove(text3);
                        root.getChildren().add(text4);
                    }
                }
            }
        } 
    };

    //a method to define the formats of various texts which come in
    void definetext(){
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        text2.setFont(Font.font(java.awt.Font.SERIF, 25));
        text3.setFont(Font.font(java.awt.Font.SERIF, 25));
        text4.setFont(Font.font(java.awt.Font.SERIF, 25));
        text3.setStroke(Color.RED);
        text4.setStroke(Color.BLUE);
        root.getChildren().add(text1); 
        root.getChildren().add(text3);
    }

    //a method to draw the grid
    void drawGRID(GraphicsContext g, int rack[][]){
        int i, j, temp=0;
        for(i=0; i<10; i++){
            for(j=0; j<15; j++){
                temp=rack[i][j];
                if(temp==0 || temp==1 || temp==2 || temp==3){
                    drawTERRAIN(g, i, j, temp);
                }
            }
        }
        for(i=0; i<10; i++){
            for(j=0; j<15; j++){
                temp=rack[i][j];
                if(temp==2||temp==3){
                    drawPERSON(g, i, j, temp);
                }  
            }
        }
        drawtextarea(g);

      for(i=0; i<10; i++){
         for(j=0; j<15; j++){
             System.out.print(rack[i][j] + " ");
         }
         System.out.print("\n");
      }
    }

    //a method to draw the background text area at the bottom of the canvas
    void drawtextarea(GraphicsContext g){
        g.setFill(Color.BLACK);
        g.fillRect(0, 600, 900, 10);
        g.setFill(Color.YELLOW);
        g.fillRect(0, 610, 900, 140);
        g.setFill(Color.WHITE);
        g.fillRect(15, 625, 870, 110);
    }

    //a method to draw the background of the grid in blue and green - randomised colour variations on set sizes of squares
    void drawTERRAIN(GraphicsContext g, int i, int j, int temp) {
        int x=0, y=0, a=0, b=0, c=0, limit=0, blue=0, green=0, random=0;
        if(temp==1){
            limit=10;
            blue=0;
            green=195;
            random=60; //these are aesthetic pixel choices for the colour of the water background
        }
        else{
            limit=5;
            blue=215;
            green=20;
            random=40; //these are aesthetic pixel choices for the colour of the grass background
        }
        y=(60*i);
        x=(60*j);
        for(int k=0; k<limit; k++){
            for(int m=0; m<limit; m++){
                Random randa = new Random(); 
                Random randb = new Random(); 
                Random randc = new Random(); 
                a=randa.nextInt(random);
                b=randb.nextInt(random);
                c=randc.nextInt(random);
                g.setFill(Color.rgb(a,blue+b,green+c));   //can use this line temporarily disabled randomised colour (in final version will have a button to do this)
                g.fillRect(x+((60/limit)*k), y+((60/limit)*m), (60/limit), (60/limit));
            }
        }
    }

    //a method to draw use the imported Gimp images of knights
    void drawPERSON (GraphicsContext g, int i, int j, int allegiance){
        int x=0, y=0;
        y=(60*i);
        x=(60*j);
        if(allegiance==2){
            g.drawImage(knight1, x-20, y-20, 100, 100); //magic numbers are aesthetic pixel choices so that the image fits a square
        }
        if(allegiance==3){
            g.drawImage(knight2, x-20, y-20, 100, 100); //magic numbers are aesthetic pixel choices so that the image fits a square
        }
    }

    //a method to draw the areas that a user can move a selected knight to and the yellow select brackets on the knight itself
    void drawSELECT (GraphicsContext g, int i, int j, int grd[][]){
        int x=0, y=0;
        y=(60*i);
        x=(60*j);
        g.drawImage(selectoutline, x-70, y-47, 280, 175); //magic numbers are aesthetic pixel choices so that the image fits a square
        drawMOVEBUBBLE(g, i, j, grd);
    }

    //a method to remove the graphics showing the yellow select brackets
    void removeSELECT (GraphicsContext g, int grd[][]){
        drawGRID(g, grd);
    }

    //a method to highlight the bubble of squares around a knight that it can move to
    void drawMOVEBUBBLE(GraphicsContext g, int i, int j, int grd[][]){
        int k=0, l=0, x=0, y=0, temp=0;
        Movement mv = new Movement();
        grd=mv.calculateMovement(i, j, grd);  
        for(k=0; k<10; k++){
            for(l=0; l<15; l++){
                temp=grd[k][l];
                if(temp==4){
                    y=(60*k);
                    x=(60*l);
                    g.drawImage(redsquare, x-4, y-4, 68, 68); //magic numbers are aesthetic pixel choices so that the image fits a square
                }
            }
        }     
    }

    //a method to remove the graphics showing the selected areas
    void undodrawMOVEBUBBLE (GraphicsContext g, int grd[][]){
        int l=0, k=0, temp=0;
        for(k=0; k<10; k++){
            for(l=0; l<15; l++){
                temp=grd[k][l];
                if(temp==4){
                    grd[k][l]=0;
                }
            }
        }
        drawGRID(g, grd);  
    }
}






