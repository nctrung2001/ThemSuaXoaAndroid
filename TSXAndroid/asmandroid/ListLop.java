package com.example.asmandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asmandroid.control.LopControl;

import java.util.ArrayList;
import java.util.List;

public class ListLop extends AppCompatActivity {
    ListView listView3;
    List<String> list3;
    LopControl lopControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop);
        listView3=findViewById(R.id.lv3);
        list3 = new ArrayList<String>();
        lopControl=new LopControl(this);
        list3= lopControl.getAllLopString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListLop.this,android.R.layout.simple_list_item_1,list3);
        listView3.setAdapter(adapter);
    }
}
