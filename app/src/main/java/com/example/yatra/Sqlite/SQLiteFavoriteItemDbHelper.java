package com.example.yatra.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.yatra.Models.RecyclerFavoriteItemModel;
import com.example.yatra.Models.RecyclerItemInCartModel;

import java.util.ArrayList;

public class SQLiteFavoriteItemDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favorite";
    public static final String TABLE_NAME = "favoriteTable";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String PRICE = "price";

    public SQLiteFavoriteItemDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + IMAGE + " TEXT,"
                + PRICE + " INTEGER)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewData(String name, String image, Integer price) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME, name);
        values.put(IMAGE, image);
        values.put(PRICE, price);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        db.close();
    }
    public ArrayList<RecyclerFavoriteItemModel> readData(Context context) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDbHelper dbHelper = new SQLiteDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<RecyclerFavoriteItemModel> FavoriteArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses != null) {
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    FavoriteArrayList.add(new RecyclerFavoriteItemModel(cursorCourses.getInt(0),
                            cursorCourses.getString(1), cursorCourses.getString(2), cursorCourses.getInt(3)));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();
        }else {
            return null;
        }
        // at last closing our cursor
        // and returning our array list.

        return FavoriteArrayList;
    }

    public void deleteData(String id) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "id=?", new String[]{id});
        db.close();
    }

}
