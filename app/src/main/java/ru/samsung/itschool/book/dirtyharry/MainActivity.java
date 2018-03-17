package ru.samsung.itschool.book.dirtyharry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    private final String LOG_ARGS = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(myButtonClickListener);

        ContactsHelper ch = new ContactsHelper(getApplicationContext()); //инициализация нашего помошника управления контактами в базе данных

        //ch.insert("Alex","555-45-44-5","06.03.1998"); // создаем в таблице новую запись, передав параметры


       // ArrayList<Contact> ara = ch.getAll();

        ListView lv = (ListView)findViewById(R.id.listView);
        ContactAdapter adapter = new ContactAdapter(this, ch.getAll());
        lv.setAdapter(adapter);

//        Button removeb = (Button)findViewById(R.id.delete_button);
//        removeb.setOnClickListener(removeListener);






//        Log.d(LOG_ARGS,ch.getAll().toString()); // выводи в логи все записи из таблицы



    }


    View.OnClickListener removeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContactsHelper ch = new ContactsHelper(getApplicationContext()); //инициализация нашего помошника управления контактами в базе данных

            ch.delete(12);

        }
    };

    View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            ContactsHelper ch = new ContactsHelper(getApplicationContext());
//            ch.insert("Alex","555-45-44-5","06.03.1998");
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);

        }
    };




}
