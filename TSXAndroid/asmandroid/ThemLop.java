package com.example.asmandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.asmandroid.control.LopControl;
import com.example.asmandroid.model.Lop;

import java.util.ArrayList;
import java.util.List;


public class ThemLop extends AppCompatActivity {
  EditText txtmalop,txttenlop;
  Button btnThem;
  LopControl lopControl;
  ListView listView;
  String textItem,subItem;
  int po=0;
  List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        txtmalop=findViewById(R.id.txtmalop);
        txttenlop=findViewById(R.id.txttenlop);
        listView=findViewById(R.id.lv);

        lopControl = new LopControl(this);
        list = new ArrayList<String>();
        list=lopControl.getAllLopString();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemLop.this,android.R.layout.simple_list_item_1,list);
        
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textItem = (String)listView.getItemAtPosition(position);
                subItem=textItem.substring(0,textItem.indexOf(" "));
                 po=position;

                 String vl = textItem.substring(textItem.indexOf(" ")+1,textItem.indexOf(" . "));
                 txtmalop.setText(subItem);
                 txttenlop.setText(vl);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,0,0,"XOA");
        menu.add(Menu.NONE,1,0,"SUA");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch(item.getItemId()){
            case 0:
                    try {
                        Toast.makeText(ThemLop.this,"XÓA THÀNH CÔNG",Toast.LENGTH_LONG).show();
                        lopControl.Dellop(subItem);
                        list.remove(po);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemLop.this,android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(adapter);
                    }catch (Exception e){
                        Toast.makeText(ThemLop.this,"XÓA KHONG THÀNH CÔNG",Toast.LENGTH_LONG).show();
                    }
                break;
            case 1:

                break;
        }
        return super.onContextItemSelected(item);
    }

    public void themLop(View view) {
        Lop lop = new Lop();
        lop.setMaLop(txtmalop.getText().toString());
        lop.setTenLop(txttenlop.getText().toString());
        if(lopControl.insertLop(lop)<0){
            Toast.makeText(ThemLop.this,"Thêm ko thành công",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ThemLop.this,"Thêm thành công",Toast.LENGTH_LONG).show();
        }
    }

    public void xemLop(View view) {
        list = new ArrayList<String>();
        list=lopControl.getAllLopString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemLop.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
    }

    public void capNhatLop(View view) {
        Lop lop = new Lop();
        lop.setMaLop(txtmalop.getText().toString());
        lop.setTenLop(txttenlop.getText().toString());
        if(lopControl.upDlop(lop)<0){
            Toast.makeText(ThemLop.this,"Cap nhat ko thành công",Toast.LENGTH_LONG).show();
        }else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);

            Toast.makeText(ThemLop.this,"Cap nhat thành công",Toast.LENGTH_LONG).show();
        }
    }
}
