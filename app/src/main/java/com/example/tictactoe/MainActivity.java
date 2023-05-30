package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ImageView MyBitmap;
    Bitmap Screen;
    Canvas MyCanvas;
    Paint MyBrush;
    Integer ScreenWidth;
    Integer ScreenHeight;
    Button Reset;
    Boolean Win = false;
    Boolean Tie = false;
    TextView Current;
    Integer CurrentPlayer; //0 for o, 1 for x
    Integer MyMatrix[][] = new Integer[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;        //gets screen width and height
        ScreenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        CurrentPlayer = 1;              //sets first player as x
        Reset = findViewById(R.id.Reset);
        Current = findViewById(R.id.Current);

        for (int i = 0; i <= 2; i++){           //fills 2D matrix with -1 values
            for (int j = 0; j <= 2; j++){
                MyMatrix[i][j] = -1;
            }
        }

        MyBitmap = findViewById(R.id.MyBitmap);         //creates bitmap and canvas
        Screen = Bitmap.createBitmap(ScreenWidth, ScreenHeight, Bitmap.Config.ARGB_8888);
        MyCanvas = new Canvas(Screen);
        MyCanvas.drawColor(Color.WHITE);
        MyBitmap.setImageBitmap(Screen);
        MyBitmap.setOnTouchListener(new myTouchListener(this));

        MyBrush = new Paint();      //creates paint object
        MyBrush.setStrokeWidth(10);

        MyCanvas.drawLine(2 * (ScreenWidth/5), (ScreenWidth/5), 2 * (ScreenWidth/5), 4 *(ScreenWidth/5), MyBrush);          //draws grid on screen
        MyCanvas.drawLine(3 * (ScreenWidth/5), (ScreenWidth/5), 3 * (ScreenWidth/5), 4 *(ScreenWidth/5), MyBrush);
        MyCanvas.drawLine(ScreenWidth/5, 2 * (ScreenWidth/5), 4 * (ScreenWidth/5), 2 *(ScreenWidth/5), MyBrush);
        MyCanvas.drawLine(ScreenWidth/5, 3 * (ScreenWidth/5), 4 * (ScreenWidth/5), 3 *(ScreenWidth/5), MyBrush);

        Reset.setOnClickListener(new View.OnClickListener() {           //sets listener for reset button
            @Override
            public void onClick(View view) {
                ClearBoard();
            }
        });

    }

    class myTouchListener implements View.OnTouchListener {
        public Context MyContext;

        public myTouchListener(Context context) {       //gets context for toasts
            MyContext = context;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {     //listener for on click of screen

            if (Win == true || Tie == true){            //checks if game is won or tied, and prints toast if yes
                Toast.makeText(MyContext, "The game has ended, to keep playing press reset", Toast.LENGTH_LONG).show();
            } else{
                if (event.getAction() == MotionEvent.ACTION_UP) { //fake it for tap.
                    v.performClick();
                    Float X = event.getX();         //gets pixel coordinates of tap
                    Float Y = event.getY();
                    //finds the square inside which the tap was made
                    if (X >= 1 * (ScreenWidth/5) && X < 2 * (ScreenWidth/5) && Y >= 1 * (ScreenWidth/5) && Y < 2 * (ScreenWidth/5) && MyMatrix[0][0] == -1){    //Top Left
                        MyMatrix[0][0] = CurrentPlayer;     //updates 2D matrix
                        Play(1 * (ScreenWidth/5), ScreenWidth/5);       //calls play function with top left x and y coordinates
                    } else if (X >= 2 * (ScreenWidth/5) && X < 3 * (ScreenWidth/5) && Y >= 1 * (ScreenWidth/5) && Y < 2 * (ScreenWidth/5) && MyMatrix[0][1] == -1){    //Top Middle
                        MyMatrix[0][1] = CurrentPlayer;     //updates 2D matrix
                        Play(2 * (ScreenWidth/5), ScreenWidth/5);       //calls play function with top left x and y coordinates
                    } else if (X >= 3 * (ScreenWidth/5) && X < 4 * (ScreenWidth/5) && Y >= 1 * (ScreenWidth/5) && Y < 2 * (ScreenWidth/5) && MyMatrix[0][2] == -1){    //Top Right
                        MyMatrix[0][2] = CurrentPlayer;     //updates 2D matrix
                        Play(3 * (ScreenWidth/5), ScreenWidth/5);       //calls play function with top left x and y coordinates
                    } else if (X >= 1 * (ScreenWidth/5) && X < 2 * (ScreenWidth/5) && Y >= 2 * (ScreenWidth/5) && Y < 3 * (ScreenWidth/5) && MyMatrix[1][0] == -1){    //Middle Left
                        MyMatrix[1][0] = CurrentPlayer;     //updates 2D matrix
                        Play(ScreenWidth/5, 2 * (ScreenWidth/5));       //calls play function with top left x and y coordinates
                    } else if (X >= 2 * (ScreenWidth/5) && X < 3 * (ScreenWidth/5) && Y >= 2 * (ScreenWidth/5) && Y < 3 * (ScreenWidth/5) && MyMatrix[1][1] == -1){    //Middle Middle
                        MyMatrix[1][1] = CurrentPlayer;     //updates 2D matrix
                        Play(2 * (ScreenWidth/5), 2 * (ScreenWidth/5));       //calls play function with top left x and y coordinates
                    } else if (X >= 3 * (ScreenWidth/5) && X < 4 * (ScreenWidth/5) && Y >= 2 * (ScreenWidth/5) && Y < 3 * (ScreenWidth/5) && MyMatrix[1][2] == -1){    //Middle Right
                        MyMatrix[1][2] = CurrentPlayer;     //updates 2D matrix
                        Play(3 * (ScreenWidth/5), 2 * (ScreenWidth/5));       //calls play function with top left x and y coordinates
                    } else if (X >= 1 * (ScreenWidth/5) && X < 2 * (ScreenWidth/5) && Y >= 3 * (ScreenWidth/5) && Y < 4 * (ScreenWidth/5) && MyMatrix[2][0] == -1){    //Bottom Left
                        MyMatrix[2][0] = CurrentPlayer;     //updates 2D matrix
                        Play(1 * (ScreenWidth/5), 3 * (ScreenWidth/5));       //calls play function with top left x and y coordinates
                    } else if (X >= 2 * (ScreenWidth/5) && X < 3 * (ScreenWidth/5) && Y >= 3 * (ScreenWidth/5) && Y < 4 * (ScreenWidth/5) && MyMatrix[2][1] == -1){    //Bottom Middle
                        MyMatrix[2][1] = CurrentPlayer;     //updates 2D matrix
                        Play(2 * (ScreenWidth/5), 3 * (ScreenWidth/5));       //calls play function with top left x and y coordinates
                    } else if (X >= 3 * (ScreenWidth/5) && X < 4 * (ScreenWidth/5) && Y >= 3 * (ScreenWidth/5) && Y < 4 * (ScreenWidth/5) && MyMatrix[2][2] == -1){    //Bottom Right
                        MyMatrix[2][2] = CurrentPlayer;     //updates 2D matrix
                        Play(3 * (ScreenWidth/5), 3 * (ScreenWidth/5));       //calls play function with top left x and y coordinates
                    } else{
                        Toast.makeText(MyContext, "Please click an empty square inside the grid", Toast.LENGTH_LONG).show();      //catches if click is in square which already has a move
                    }

                    return true;
                }
            }

            return false;
        }

        public void Play(float TopLeftX, float TopLeftY){
            if (CurrentPlayer == 1){            //depending on current player, draws the appropriate symbol in the grid square
                DrawX(TopLeftX, TopLeftY);
            } else{
                DrawO(TopLeftX, TopLeftY);
            }

            if (CheckWin() == true){            //checks if win has occurred after playing move
                if (CurrentPlayer == 0){
                    Current.setText("Player X Wins!");
                } else{
                    Current.setText("Player O Wins!");
                }
            }
            if (Tie == true){               //checks if tie has occurred after playing move
                Current.setText("Tie!");
            }

        }
    }

    public void ClearBoard(){               //overwrites the board, and resets all variables to their starting states
        MyCanvas.drawColor(Color.WHITE);
        Current.setText("Player X");
        MyCanvas.drawLine(2 * (ScreenWidth/5), (ScreenWidth/5), 2 * (ScreenWidth/5), 4 *(ScreenWidth/5), MyBrush);
        MyCanvas.drawLine(3 * (ScreenWidth/5), (ScreenWidth/5), 3 * (ScreenWidth/5), 4 *(ScreenWidth/5), MyBrush);
        MyCanvas.drawLine(ScreenWidth/5, 2 * (ScreenWidth/5), 4 * (ScreenWidth/5), 2 *(ScreenWidth/5), MyBrush);
        MyCanvas.drawLine(ScreenWidth/5, 3 * (ScreenWidth/5), 4 * (ScreenWidth/5), 3 *(ScreenWidth/5), MyBrush);
        MyMatrix = new Integer[3][3];
        for (int i = 0; i <= 2; i++){
            for (int j = 0; j <= 2; j++){
                MyMatrix[i][j] = -1;
            }
        }
        Tie = false;
        Win = false;
        CurrentPlayer = 1;
    }

    public void DrawX(float TopLeftX, float TopLeftY){          //draws x symbol in the box specified by the input variables
        Integer Width = ScreenWidth/5;
        Width = Width/10;
        float[] Points = {TopLeftX + Width, TopLeftY + Width, TopLeftX + (ScreenWidth/5) - Width, TopLeftY + (ScreenWidth/5) - Width, TopLeftX + (ScreenWidth/5) - Width, TopLeftY + Width, TopLeftX + Width, TopLeftY + (ScreenWidth/5) - Width};
        MyCanvas.drawLines(Points, MyBrush);
        CurrentPlayer = 0;              //updates the current player
        Current.setText("Player O");
    }

    public void DrawO(float TopLeftX, float TopLeftY){          //draws o symbol in the box specified by the input variables
        MyBrush.setStyle(Paint.Style.STROKE);
        Integer Width = ScreenWidth/5;
        Width = Width/10;
        MyCanvas.drawCircle(TopLeftX + (ScreenWidth/10), TopLeftY + (ScreenWidth/10), (ScreenWidth/10) -20,MyBrush);
        MyBrush.setStyle(Paint.Style.FILL);
        CurrentPlayer = 1;              //updates the current player
        Current.setText("Player X");
    }

    public boolean CheckWin(){          //checks to see if win or tie
        for (int x = 0; x <= 2; x++){       //vertical check
            if (MyMatrix[x][0] == 0 && MyMatrix[x][1] == 0 && MyMatrix[x][2] == 0){
                Win = true;
                return true;
            } else if (MyMatrix[x][0] == 1 && MyMatrix[x][1] == 1 && MyMatrix[x][2] == 1){
                Win = true;
                return true;
            }
        }

        for (int y = 0; y <= 2; y++){       //horizontal check
            if (MyMatrix[0][y] == 0 && MyMatrix[1][y] == 0 && MyMatrix[2][y] == 0){
                Win = true;
                return true;
            } else if (MyMatrix[0][y] == 1 && MyMatrix[1][y] == 1 && MyMatrix[2][y] == 1){
                Win = true;
                return true;
            }
        }

        if (MyMatrix[0][0] == 0 && MyMatrix[1][1] == 0 && MyMatrix[2][2] == 0){   //diagonal left to right
            Win = true;
            return true;
        } else if (MyMatrix[0][0] == 1 && MyMatrix[1][1] == 1 && MyMatrix[2][2] == 1){
            Win = true;
            return true;
        }

        if (MyMatrix[2][0] == 0 && MyMatrix[1][1] == 0 && MyMatrix[0][2] == 0){   //diagonal right to left
            Win = true;
            return true;
        } else if (MyMatrix[2][0] == 1 && MyMatrix[1][1] == 1 && MyMatrix[0][2] == 1){
            Win = true;
            return true;
        }

        Tie = true;                 //checks if any square is still -1, if none are, and the win has not been found, then it must be a tie
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++){
                if (MyMatrix[x][y] == -1) {
                    Tie = false;
                }
            }
        }
        if (Tie == true){
            return false;
        }
        return false;
    }

}