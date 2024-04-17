package com.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group; //Gruppe
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent; //Keyboard input
import javafx.scene.paint.Color; //Baggrund farve

/**
 * JavaFX App
 */
public class App extends Application {
    private World world;
    private Group root;
    private Rectangle player;

    public void displayWalls(){
        for (int y = 0; y < world.yLength(); y++ ){
            for (int x = 0; x < world.xLength(); x++){
                if (world.getEntity(x,y).getVariant() == Variant.wall){
                    System.out.print(x + " " + y + ", ");
                    Rectangle rect = new Rectangle(x*10, y*10, 10, 10);
                    rect.setFill(Color.DARKBLUE);
                    root.getChildren().add(rect);
                }
            }
        }
    }

    public void moveCharacter(){
        world.movePlayerPos(world.playerChar().speed, 0);

        
    }

    public void displayCharacter(){
        player.setX(world.getPlayerPos()[0]*10);
        player.setY(world.getPlayerPos()[1]*10);
    }


    @Override
    public void start(Stage stage) {
        world = new World();
        System.out.println(world.xLength() + " " + world.yLength());
        //Canvas canvas = new Canvas(640, 480);
        root = new Group();
        Scene scene = new Scene(root, 640, 480, Color.BLACK);

        //Image pacMan = new Image("pacMan.png")
        //imageView = new ImageView(pacMan);
        //imageView.setX(##);
        //imageView.setY(##);

        displayWalls();
        player = new Rectangle(world.getPlayerPos()[0]*10, world.getPlayerPos()[1]*10, 10 ,10);
        player.setFill(Color.YELLOW);
        root.getChildren().add(player);

        new AnimationTimer(){
            public void handle(long currentNanoTime){
                displayCharacter();
                moveCharacter();

                
            }
        }.start();
        
        stage.setTitle("Pacman");
        stage.setScene(scene);
        stage.show();

        

        scene.setOnKeyPressed(this::keyPress);

    }

    public static void main(String[] args) {
        launch();
    }

    public void keyPress(KeyEvent event){
        switch(event.getCode()){
            case UP:
                //
                break;
            case DOWN:
                //
                break;
            case LEFT:
                world.setSpeedPlayer(0.01f);
                break;
            case RIGHT:
                //
                break;
            default:
                break;
        }
    }

}