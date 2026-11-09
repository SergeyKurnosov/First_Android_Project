package com.example.first_android_project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    /*


     */

    Button btnAdd;
    Button btnRead;
    Button btnClear;
    Button btnDelID;

    EditText etNAme;
    EditText etEmail;
    EditText etPhone;
    EditText etDelID;


    AppDataBAse db;
    UserDao userDao;

    ArrayList<User> userArrayList;
    RecyclerView recyclerView;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);
        btnClear = findViewById(R.id.btnClear);
        btnDelID = findViewById(R.id.btnDelID);

        etEmail = findViewById(R.id.etEmail);
        etNAme = findViewById(R.id.etName);
        etPhone= findViewById(R.id.etPhone);
        etDelID= findViewById(R.id.etID);

        userArrayList = new ArrayList<>();

      //  users.add(new User("nnn", "eee", "123"));


        db = Room.databaseBuilder(getApplicationContext(), AppDataBAse.class, "myDB").addMigrations(AppDataBAse.MIGRATION_1_2).allowMainThreadQueries().build();
        userDao = db.userDao();

        List<User> users = userDao.getAll();
        userArrayList.addAll(users);
        recyclerView = findViewById(R.id.list2);

        userAdapter = new UserAdapter(MainActivity.this , userArrayList);
        recyclerView.setAdapter(userAdapter);

     //   userAdapter.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNAme.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();

                User user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;

                long id = userDao.insert(user);

                userArrayList.add(user);
                userAdapter.notifyDataSetChanged();

                Log.d("SQL", "Add user id " + id);
                etNAme.setText("");
                etEmail.setText("");
                etPhone.setText("");
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = userDao.getAll();
                if (users.size() == 0) {
                    Log.d("SQL", "Empty");
                    return;
                }
                for (User user:
                     users) {
                    Log.d("SQL","ID "+user.id+" name "+user.name+" email + "+user.email+ " phone "+ user.phone);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userArrayList.clear();
                userAdapter.notifyDataSetChanged();
                int count = userDao.clearAll();
                userDao.reset();
                Log.d("SQL","Delete rows count  "+count);
            }
        });

        btnDelID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText= etDelID.getText().toString();
                if(idText.isEmpty()){
                    Log.d("SQL","Enter id  ");
                    return;
                }
                int id = Integer.parseInt(idText);
                int deleteCount = userDao.deleteById(id);
                User forDel =  userArrayList.stream().filter(u -> u.id == id).findFirst().get();
                userArrayList.remove(forDel);
                userAdapter.notifyDataSetChanged();
                if(deleteCount>0){
                    Log.d("SQL","Delete user with id   "+id);
                }
                else {
                    Log.d("SQL","User not found   "+id);
                }
                etDelID.setText("");
            }
        });


    }


}





