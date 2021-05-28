package com.example.asmandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnadd,btnlist,btnQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd=findViewById(R.id.btnThemLop);
        btnlist=findViewById(R.id.list);
        btnQL=findViewById(R.id.btnQuanLySinhVien);

    }

    public void ThemLop(View view) {
        Intent intent = new Intent(MainActivity.this,ThemLop.class);
        startActivity(intent);
    }

    public void listlop(View view) {
        Intent intent = new Intent(MainActivity.this,ListLop.class);
        startActivity(intent);
    }

    public void QLSV(View view) {
        Intent intent = new Intent(MainActivity.this,ThemSinhVien.class);
        startActivity(intent);
    }
}
