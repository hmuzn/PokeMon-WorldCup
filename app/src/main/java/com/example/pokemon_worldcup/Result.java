package com.example.pokemon_worldcup;

import static com.example.pokemon_worldcup.MainActivity.pics;
import static com.example.pokemon_worldcup.MainActivity.pokemonNames;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {
    public static int winner;
    public static ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
        img = this.findViewById(R.id.imageView);

        Result.init(this);

        Button buttonExit = findViewById(R.id.buttonExit);

        // "앱 종료" 버튼 클릭 이벤트 처리
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 앱을 종료
                finishAffinity();
            }
        });
    }
    public static void init(Context context) {
        winner = -1;
        for(int i = 0; i < pics.length; i++) {
            if(pics[i] == 3) {
                winner = i;
                break;
            }
        }
        setImage(winner, context);
        // TextViewWinner에 우승자 이름 설정
        TextView textViewWinner = ((AppCompatActivity)context).findViewById(R.id.textViewWinner);
        if (winner != -1) {
            textViewWinner.setText(pokemonNames[winner]);
        }
    }
    public static void setImage(int n, Context context) {
        int drawable = context.getResources().getIdentifier("@drawable/p" + n, "drawable", context.getPackageName());
        img.setImageResource(drawable);
    }
}