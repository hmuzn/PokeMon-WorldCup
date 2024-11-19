package com.example.pokemon_worldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static ImageButton img1;
    public static ImageButton img2;
    public static int[] pics = new int[8];
    public static int[] n = new int[2];
    public static int cnt, game;
    public static String[] pokemonNames = new String[8];
    public static int currentRound = 0;
    public static int currentMatch = 1;
    public static TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = this.findViewById(R.id.imageButton1);
        img2 = this.findViewById(R.id.imageButton2);
        textView5 = findViewById(R.id.textView5);

        // 포켓몬 이름 및 이미지 리소스 설정
        pokemonNames[0] = "치코리타";
        pokemonNames[1] = "푸린";
        pokemonNames[2] = "뮤";
        pokemonNames[3] = "피카츄";
        pokemonNames[4] = "리오르";
        pokemonNames[5] = "꼬부기";
        pokemonNames[6] = "님피아";
        pokemonNames[7] = "토게피";

        init(getApplicationContext());

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedImage = n[0];
                int unselectedImage = n[1];
                pics[selectedImage] += 1;
                pics[unselectedImage] -= 1;
                currentMatch++;
                if (pics[selectedImage] == 3) {
                    Intent intent = new Intent(getBaseContext(), Result.class);
                    startActivity(intent);
                    finish();
                }
                if (cnt != 3) {
                    if(game == 4 || game == 6) {
                        currentMatch = 1;
                        currentRound++;
                    }
                    n = getRandomNumber();
                    setImages(n[0], n[1], getApplicationContext());
                    changeTextInTextView3(pokemonNames[n[0]]);
                    changeTextInTextView4(pokemonNames[n[1]]);
                    updateRoundText();
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedImage = n[1];
                int unselectedImage = n[0];
                pics[selectedImage] += 1;
                pics[unselectedImage] -= 1;
                currentMatch++;
                if (pics[selectedImage] == 3) {
                    Intent intent = new Intent(getBaseContext(), Result.class);
                    startActivity(intent);
                }
                if (cnt != 3) {
                    if(game == 4 || game == 6) {
                        currentMatch = 1;
                        currentRound++;
                    }
                    n = getRandomNumber();
                    setImages(n[0], n[1], getApplicationContext());
                    changeTextInTextView3(pokemonNames[n[0]]);
                    changeTextInTextView4(pokemonNames[n[1]]);
                    updateRoundText();
                }
            }
        });
    }

    public static void init(Context context) {
        for (int i = 0; i < pics.length; i++) {
            pics[i] = 0;
        }
        n = getRandomNumber();
        setImages(n[0], n[1], context);
        setInitialText(context);
    }

    public static void setImages(int n1, int n2, Context context) {
        int drawable1 = context.getResources().getIdentifier("@drawable/p" + n1, "drawable", context.getPackageName());
        int drawable2 = context.getResources().getIdentifier("@drawable/p" + n2, "drawable", context.getPackageName());
        img1.setImageResource(drawable1);
        img2.setImageResource(drawable2);
    }

    public static int[] getRandomNumber() {
        int n1 = -1;
        int n2 = -1;
        switch (cnt) {
            case 0: // 8강
                while (true) {
                    n1 = (int) (Math.random() * 8);
                    n2 = (int) (Math.random() * 8);
                    if (n1 != n2 && pics[n1] == 0 && pics[n2] == 0) {
                        break;
                    }
                }
                break;
            case 1: // 4강
                while (true) {
                    n1 = (int) (Math.random() * 8);
                    n2 = (int) (Math.random() * 8);
                    if (n1 != n2 && pics[n1] == 1 && pics[n2] == 1) {
                        break;
                    }
                }
                break;
            case 2: // 결승
                while (true) {
                    n1 = (int) (Math.random() * 8);
                    n2 = (int) (Math.random() * 8);
                    if (n1 != n2 && pics[n1] == 2 && pics[n2] == 2) {
                        game++;
                        if(game == 7) cnt++;
                        break;
                    }
                }
                break;
            default:
                break;
        }
        game++;
        if(game == 4 || game == 6) cnt++;
        return new int[]{n1, n2};
    }

    public void changeTextInTextView3(String newText) {
        TextView textView = findViewById(R.id.textView3);
        textView.setText(newText);
    }

    public void changeTextInTextView4(String newText) {
        TextView textView = findViewById(R.id.textView4);
        textView.setText(newText);
    }

    // 초기 텍스트를 설정하는 메서드
    public static void setInitialText(Context context) {
        TextView textView3 = img1.getRootView().findViewById(R.id.textView3);
        TextView textView4 = img2.getRootView().findViewById(R.id.textView4);
        if (textView3 != null && textView4 != null) {
            textView3.setText(pokemonNames[n[0]]);
            textView4.setText(pokemonNames[n[1]]);
        }
    }

    public void updateRoundText() {
        // 라운드 및 경기 정보에 따라 텍스트 업데이트
        if (currentRound == 0) {
            textView5.setText("8강 " + currentMatch + "경기");
        } else if (currentRound == 1) {
            textView5.setText("4강 " + currentMatch + "경기");
        } else {
            textView5.setText("결승");
        }
    }
}