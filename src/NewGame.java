import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


public class NewGame extends Application {
    private static JFrame frame;
    private int size_x;
    private int size_y;
    private ArrayList<Snake> tab;
    private ArrayList<Snake> snake;
    private ArrayList<Snake> points;
    private int punkty;
    private Timeline timeline;
    private int kierunek;
    private ArrayList<Result> wyniki;

    public static void main(String[] args) {
        frame = new Menu();
    }

    public NewGame(int size_x, int size_y){
        this.size_x=size_x;
        this.size_y=size_y;
    }


    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();


        Image image = new Image("/background-snake.jpg",size_x,size_y,false,false);

        BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        GridPane game = new GridPane();
        game.setBackground(new Background(myBI));
        game.setMinSize(size_x,size_y);
        tab =new ArrayList<>();
        snake = new ArrayList<>();
        points = new ArrayList<>();
        punkty=0;


        Label label=new Label("Punkty "+ punkty+ " Czas: " + 0);
        label.setFont(Font.font ("Verdana", 20));
        root.setTop(label);
        root.setCenter(game);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

         scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.RIGHT && kierunek!=3)
                kierunek=1;
            if(event.getCode() == KeyCode.LEFT && kierunek!=1)
                kierunek=3;
            if(event.getCode() == KeyCode.UP && kierunek!=2)
                kierunek=0;
            if(event.getCode() == KeyCode.DOWN && kierunek!=0)
                kierunek=2;


                 if (event.getCode() == KeyCode.SHIFT && event.isControlDown()) {
                     primaryStage.close();
                     frame.setVisible(true);
                     timeline.stop();
                 }

        });

        primaryStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            public void handle(javafx.stage.WindowEvent we) {
                System.out.println("Stage is closing");
                primaryStage.close();
                frame.setVisible(true);
                timeline.stop();
            }
        });


        for(int i=0;i<size_x/10;i++){
            for(int j=0;j<size_y/10;j++){
                tab.add(new Snake(i,j,10,10));
            }
        }

        for(Snake a:tab){
            a.setFill(Color.TRANSPARENT);
            game.add(a,a.getXc(),a.getYc());
        }
        //RANDOM GENERATE
        int random=(int)(Math.random()*tab.size())-5;
        tab.get(random).setFill(Color.BLACK);
        snake.add(tab.get(random));


        timeline = new Timeline(new KeyFrame(
                Duration.millis(300), new EventHandler<ActionEvent>() {
            double czas=0;
            @Override
                public void handle(ActionEvent event) {
                czas+=0.3;
                int x_temp;
                int y_temp;
                switch (kierunek){
                    case 0:
                        x_temp=snake.get(0).getXc();
                        y_temp=snake.get(0).getYc();

                        for(Snake a:tab){
                            if(a.getXc()==x_temp && a.getYc()==y_temp-1) {
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                Snake temp_s=snake.get(0);
                                Snake temp;
                                Snake to_trans=snake.get(snake.size()-1);
                                snake.set(0,a);

                                for(int i=1;i<snake.size();i++) {
                                    temp=snake.get(i);
                                    if(i==1)
                                        snake.set(i,temp_s);
                                    else {
                                        snake.set(i, temp_s);
                                    }
                                    temp_s = temp;
                                }
                                tab.get(to_trans.getIndex()).setFill(Color.TRANSPARENT);
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                break;
                            }
                        }
                        break;
                    case 1:
                        x_temp=snake.get(0).getXc();
                        y_temp=snake.get(0).getYc();

                        for(Snake a:tab){
                            if(a.getXc()==x_temp+1 && a.getYc()==y_temp) {
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                Snake temp_s=snake.get(0);
                                Snake temp;
                                Snake to_trans=snake.get(snake.size()-1);
                                snake.set(0,a);

                                for(int i=1;i<snake.size();i++) {
                                    temp=snake.get(i);
                                    if(i==1)
                                        snake.set(i,temp_s);
                                    else {
                                        snake.set(i, temp_s);
                                    }
                                    temp_s = temp;
                                }
                                tab.get(to_trans.getIndex()).setFill(Color.TRANSPARENT);
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                break;
                            }
                        }
                        break;
                    case 2:
                        x_temp=snake.get(0).getXc();
                        y_temp=snake.get(0).getYc();

                        for(Snake a:tab){
                            if(a.getXc()==x_temp && a.getYc()==y_temp+1) {
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                Snake temp_s=snake.get(0);
                                Snake temp;
                                Snake to_trans=snake.get(snake.size()-1);
                                snake.set(0,a);

                                for(int i=1;i<snake.size();i++) {
                                    temp=snake.get(i);
                                    if(i==1)
                                        snake.set(i,temp_s);
                                    else {
                                        snake.set(i, temp_s);
                                    }
                                    temp_s = temp;
                                }
                                tab.get(to_trans.getIndex()).setFill(Color.TRANSPARENT);
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                break;
                            }
                        }
                        break;
                    case 3:
                        x_temp=snake.get(0).getXc();
                        y_temp=snake.get(0).getYc();

                        for(Snake a:tab){
                            if(a.getXc()==x_temp-1 && a.getYc()==y_temp) {
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                Snake temp_s=snake.get(0);
                                Snake temp;
                                Snake to_trans=snake.get(snake.size()-1);
                                snake.set(0,a);

                                for(int i=1;i<snake.size();i++) {
                                    temp=snake.get(i);
                                    if(i==1)
                                        snake.set(i,temp_s);
                                    else {
                                        snake.set(i, temp_s);
                                    }
                                    temp_s = temp;
                                }
                                tab.get(to_trans.getIndex()).setFill(Color.TRANSPARENT);
                                for(Snake b:snake){
                                    tab.get(b.getIndex()).setFill(Color.BLACK);
                                }
                                break;
                            }
                        }
                        break;
                }

                boolean wall=snake.get(0).getXc()==0 || snake.get(0).getXc()==size_x/10-1 || snake.get(0).getYc()==0 || snake.get(0).getYc()==size_y/10-1;
                boolean on=false;
                //check is lose
                for(int i=1;i< snake.size();i++){
                    if(snake.get(0).equals(snake.get(i)))
                        on=true;
                }
                if(wall || on ) {
                    System.out.println("LOSE");

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            timeline.stop();
                            primaryStage.close();
                            TextInputDialog dialog = new TextInputDialog("walter");
                            dialog.setTitle("Wyniki");
                            dialog.setHeaderText("THE END");
                            dialog.setContentText("Wpisz swoje imie:");

// Traditional way to get the response value.
                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()){
                                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("results.bin"))) {

                                   wyniki = (ArrayList<Result>) inputStream.readObject();
                                    wyniki.add(new Result(result.get(),punkty/czas));
                                }
                                catch (Exception e){

                                }
                                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("results.bin"))) {
                                    outputStream.writeObject(wyniki);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Your name: " + result.get());
                            }
                            frame.setVisible(true);
                        }
                    });




                }
                //generate points
                if((int)czas%5==0 && points.size()==0)
                    points.add(tab.get((int)(Math.random()*300)));
                if((int)czas%9==0) {
                    for(Snake a:points) {
                        tab.get(a.getIndex()).setFill(Color.TRANSPARENT);
                    }
                    points.clear();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                            if(points.size()>0) {
                                points.get(0).setFill(Color.GREEN);
                                if (snake.get(0).getXc() == points.get(0).getXc() && snake.get(0).getYc() == points.get(0).getYc()) {
                                    punkty++;
                                    snake.add(tab.get(snake.get(snake.size() - 1).getIndex() + 1));
                                    tab.get(points.get(0).getIndex()).setFill(Color.BLACK);
                                    points.remove(points.get(0));

                                }
                            }

                    }
                });

    label.setText("Punkty "+ punkty+ " Czas: " + (int)czas);
            }
        }
    ));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();





    }



}
