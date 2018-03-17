package ru.samsung.itschool.book.dirtyharry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Максим on 13.03.2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, ArrayList<Contact> arr) {
        super(context, R.layout.adapter_contact, arr);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Contact contact = getItem(position);

      //  if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_contact, null);
       // }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.textView)).setText(contact.name);
        ((TextView) convertView.findViewById(R.id.textView2)).setText(String.valueOf(contact.phone));
        ((TextView) convertView.findViewById(R.id.textView3)).setText(String.valueOf(contact.birthday));
// Выбираем картинку для месяца
        ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(R.drawable.user_avatar);

        return convertView;
    }
}