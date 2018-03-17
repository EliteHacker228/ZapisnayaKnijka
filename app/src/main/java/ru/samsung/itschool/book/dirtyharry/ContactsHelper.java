package ru.samsung.itschool.book.dirtyharry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static ru.samsung.itschool.book.dirtyharry.DBHelper.COLUMN_ID;
import static ru.samsung.itschool.book.dirtyharry.DBHelper.TABLE_NAME;


/**
 * Created by aleksej on 03.03.2018.
 */

public class ContactsHelper {

    SQLiteDatabase db; // объект для манипулирования данными в базе.

    ContactsHelper(Context context) {
        DBHelper dbHelper = new DBHelper(context); //инициализируем нашего помошника базы данных
        db = dbHelper.getWritableDatabase(); // получаем доступ к базе с возможностью записи/чтения
    }

    //метод для того, чтобы положить данные в базу
    long insert(String name, String phone, String birthday) {
        ContentValues cv = new ContentValues();// хранилище с принципом ключ-значени

        cv.put(DBHelper.COLUMN_NAME, name);
        cv.put(DBHelper.COLUMN_PHONE, phone);
        cv.put(DBHelper.COLUMN_BIRTHDAY, birthday);

        return db.insert(TABLE_NAME, null, cv);// метод insert возвращает id, помещенного объекта в таблицу.
        // указали имя таблицы и хранилище данных
    }

    public int update(Contact md) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, md.getName());
        cv.put(DBHelper.COLUMN_PHONE, md.getPhone());
        cv.put(DBHelper.COLUMN_BIRTHDAY, md.getBirthday());
        return db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(md.getId())});
    }

    public void delete(long id) {
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }


    //метод для получения всех записей из таблицы
    ArrayList<Contact> getAll() {

        Cursor mCursor = db.query(TABLE_NAME, null, null, null, null, null,
                null);
        ArrayList<Contact> arr = new ArrayList<>();

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {

            //дедай пока записи есть в таблице
            do {
                long id = mCursor.getLong(DBHelper.NUM_COLUMN_ID);
                String name = mCursor.getString(DBHelper.NUM_COLUMN_NAME);
                String phone = mCursor.getString(DBHelper.NUM_COLUMN_PHONE);
                String birthday = mCursor.getString(DBHelper.NUM_COLUMN_BIRTHDAY);

                // получем значения соотвествующих полей и формируем объект, добавив его в коллекцию.
                arr.add(new Contact(id, name, phone, birthday));

            } while (mCursor.moveToNext());
        }
        db.close(); // закрыли транзакцию
        return arr; // вернули коллекцию
    }

    /** для просмотра примеров с удалением и обновлением
     * перейдите по ссылке
     * http://myitschool.ru/book/pluginfile.php/8672/mod_resource/content/1/4.5.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20SQL%20%D0%BD%D0%B0%20%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94%20SQLite_180216.pdf
     **/
}
