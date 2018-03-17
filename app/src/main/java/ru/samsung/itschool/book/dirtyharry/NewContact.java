package ru.samsung.itschool.book.dirtyharry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends Activity {

    Button btn;
    EditText name;
    EditText phone;
    EditText bday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        btn = (Button)findViewById(R.id.edit_create);
        btn.setOnClickListener(btnclicker);

    }

    View.OnClickListener btnclicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btn = (Button)findViewById(R.id.edit_create);
            name = (EditText)findViewById(R.id.edit_name);
            phone = (EditText)findViewById(R.id.edit_phone);
            bday = (EditText)findViewById(R.id.edit_birth);

            String arg1 =  name.getText().toString();
            String arg2 =  phone.getText().toString();
            String arg3 =  bday.getText().toString();
            if(arg1.equals("")||arg2.equals("")||arg3.equals("")){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Нельзя оставлять пустые поля", Toast.LENGTH_SHORT);
                toast.show();
            }else{

            ContactsHelper ch = new ContactsHelper(getApplicationContext());
            ch.insert(arg1,arg2,arg3);

            Contact abc = new Contact( 12 ,arg1, arg2, arg3);
            ch.update(abc);

            Intent intent = new Intent(NewContact.this, MainActivity.class);
            startActivity(intent);
            }


        }
    };
}
