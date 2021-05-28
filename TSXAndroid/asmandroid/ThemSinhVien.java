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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asmandroid.control.LopControl;
import com.example.asmandroid.control.SVControl;
import com.example.asmandroid.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class ThemSinhVien extends AppCompatActivity {
    EditText name,phone,id1;
    Spinner spinner;
    ListView listView;
    SVControl svControl;
    String TextItem1,subItem1;
    int po1=0;
    List<String> list1;
    LopControl lopControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien);
        name=findViewById(R.id.txtName);
        phone=findViewById(R.id.txtsdt);
        spinner=findViewById(R.id.spinner);
        listView=findViewById(R.id.lvsv);
        id1=findViewById(R.id.txtid);
        svControl=new SVControl(this);

         list1 = new ArrayList<String>();
        list1=svControl.getAllSVString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemSinhVien.this,android.R.layout.simple_list_item_1,list1);
        listView.setAdapter(adapter);
       registerForContextMenu(listView);
       lopControl = new LopControl(this);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(ThemSinhVien.this,android.R.layout.simple_spinner_dropdown_item,lopControl.getAllLopString());
        spinner.setAdapter(adapter5);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               TextItem1=(String)listView.getItemAtPosition(position);
               subItem1=TextItem1.substring(0,TextItem1.indexOf(" "));
               po1=position;

               String vl1 = TextItem1.substring(TextItem1.indexOf(" ")+1,TextItem1.indexOf(" - ")+1);
               String vl2 = TextItem1.substring(TextItem1.indexOf(" - ")+2,TextItem1.indexOf(" . "));
               id1.setText(subItem1);
               name.setText(vl1);
               phone.setText(vl2);
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
                Toast.makeText(ThemSinhVien.this,"XÓA THÀNH CÔNG",Toast.LENGTH_LONG).show();
                try {
                    Toast.makeText(ThemSinhVien.this,"XÓA THÀNH CÔNG",Toast.LENGTH_LONG).show();
                    svControl.delSV(subItem1);
                    list1.remove(po1);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemSinhVien.this,android.R.layout.simple_list_item_1,list1);
                    listView.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(ThemSinhVien.this,"XÓA KHÔNG THÀNH CÔNG",Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                Toast.makeText(ThemSinhVien.this,"Context ID: "+item.getItemId()+"+ Listview Item ID: "+menuInfo.position, Toast.LENGTH_LONG).show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    public void addSV(View view) {
        SinhVien sinhVien = new SinhVien();
        sinhVien.setIdSV(id1.getText().toString());
        sinhVien.setName(name.getText().toString());
        sinhVien.setSdt(phone.getText().toString());
        if(svControl.insertSV(sinhVien)<0){
            Toast.makeText(ThemSinhVien.this,"Thêm ko thành công",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ThemSinhVien.this,"Thêm thành công",Toast.LENGTH_LONG).show();
        }

    }

    public void xemSV(View view) {
        list1 = new ArrayList<String>();
        list1=svControl.getAllSVString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemSinhVien.this,android.R.layout.simple_list_item_1,list1);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
    }


    public void capNhatSV(View view) {
        SinhVien sinhVien = new SinhVien();
        sinhVien.setIdSV(id1.getText().toString());
        sinhVien.setName(name.getText().toString());
        sinhVien.setSdt(phone.getText().toString());
        if(svControl.upDSV(sinhVien)<0){
            Toast.makeText(ThemSinhVien.this,"Cap nhat ko thành công",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ThemSinhVien.this,"Cap nhat thành công",Toast.LENGTH_LONG).show();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
            listView.setAdapter(adapter);
        }
    }
}
