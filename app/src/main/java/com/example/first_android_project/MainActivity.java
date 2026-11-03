package com.example.first_android_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
Создайте Android-приложение, которое позволяет пользователю формировать список покупок. Используйте ListView с возможностью добавления и удаления элементов.
📋 Требования:
•	Создайте экран с полем ввода (EditText), кнопкой добавления и списком (ListView).
•	При вводе текста и нажатии кнопки элемент должен добавляться в список.

Создайте приложение, отображающее список контактов (имя, телефон, аватар). Используйте RecyclerView для отображения списка.
📋 Требования:
•	Создайте класс-модель Contact с полями: имя (String), телефон (String) и аватар (int, идентификатор ресурса изображения).
•	Используйте RecyclerView.Adapter и кастомный ViewHolder для отображения данных.
•	Реализуйте обработку кликов по элементам списка: при нажатии на контакт выводите Toast-сообщение с именем и телефоном выбранного контакта.


 */

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    ListView listView;
    List<String> items;

    ArrayList<Contact> contacts;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.ok);
        listView = findViewById(R.id.list);
        items = new ArrayList<>();
        contacts = new ArrayList<>();
        initDate();
        recyclerView = findViewById(R.id.list2);

        ContactAdapter.OnStateClickListener onStateClickListener = new ContactAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Contact contact, int position) {
                Toast.makeText(getApplicationContext(), contact.getName() + " / "+contact.getNumber(),
                        Toast.LENGTH_SHORT).show();
            }
        };


        contactAdapter = new ContactAdapter(MainActivity.this, contacts, onStateClickListener);
       recyclerView.setAdapter(contactAdapter);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1 , items);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.valueOf(editText.getText());
                if (!text.isEmpty()){
                    editText.setText("");
                    adapter.add(text);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String forDelete = items.get(position);
                if(!forDelete.isEmpty()){
                    adapter.remove(forDelete);
                    return true;
                }
                return false;
            }
        });





    }

    private void initDate() {
        contacts.add(new Contact("One","1234567890", R.drawable.ic_launcher_background));
        contacts.add(new Contact("Two","2345678901", R.drawable.ic_launcher_foreground));
        contacts.add(new Contact("Three","3456789012", R.drawable.ic_launcher_background));
        contacts.add(new Contact("Four","4567890123", R.drawable.ic_launcher_foreground));
        contacts.add(new Contact("Five","5678901234", R.drawable.ic_launcher_background));
        contacts.add(new Contact("Six","6789012345", R.drawable.ic_launcher_foreground));
        contacts.add(new Contact("Seven","7890123456", R.drawable.ic_launcher_background));
        contacts.add(new Contact("Eight","8901234567", R.drawable.ic_launcher_foreground));
        contacts.add(new Contact("Nine","9012345678", R.drawable.ic_launcher_background));
        contacts.add(new Contact("Ten","0123456789", R.drawable.ic_launcher_foreground));
    }
}





