package fall2020.mobileapp.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ClassHandlingDatabase extends SQLiteOpenHelper {
    final static String DATABASENAME = "MyList.db";
    final static String TABLE = "myTable";
    final static String ID = "_id";
    final static String LIST_String = "name";
    final static String STATUS = "status";
    final private static String CREATECommand =
            "CREATE TABLE "+TABLE+" (" + ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LIST_String + " TEXT NOT NULL, "+
                    STATUS +" TEXT NOT NULL)";




    public ClassHandlingDatabase(Context context){
        super(context, DATABASENAME, null, 1);

    }

    public ClassHandlingDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATECommand);
        ContentValues values = new ContentValues();

        values.put(LIST_String, "Lab5-krimal");
        values.put(STATUS,"Not Done");
        db.insert(TABLE,null,values);
        values.clear();
        values.put(LIST_String, "Lab5-krimal");
        values.put(STATUS,"Done");
        db.insert(TABLE,null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);

    }



    public boolean addData(String name, String attribute){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(LIST_String, name);
        val.put(STATUS,attribute);
        long result = database.insert(TABLE, null, val);


        boolean isValue = (result == -1) ? false: true;

        return isValue;
    }



    public ArrayList<String> getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String first_query = " select name from "+TABLE;
        String second_query = " select status from "+TABLE;
        Cursor first_data,second_data;
        String saveStr="";
        String newStr="";
        ArrayList<String> List = new ArrayList<>();
        ArrayList<String> List2 = new ArrayList<>();
        // select name from TABLE_NAME
        first_data = db.rawQuery(first_query, null);
        first_data.moveToFirst();
        while(first_data.moveToNext()){
            List.add(first_data.getString(0));
        }
        first_data.moveToFirst();

        second_data=db.rawQuery(second_query, null);
        second_data.moveToFirst();
        while(second_data.moveToNext()){
            List2.add(second_data.getString(0));
        }
        second_data.moveToFirst();

        for (int i = 0;i < List.size();i++) {
            if (List2.get(i).equals("Done")) {
                saveStr = List.get(i);
                newStr = "Done " + saveStr;
                List.remove(i);
                List.add(newStr);
            }

        }


        return List;

    }
    public void delete(String condition){
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL(" DELETE FROM " + TABLE + " WHERE " + LIST_String + "=\"" + condition + "\";");

    }
    public void deleteDone(String condition){
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL(" DELETE FROM " + TABLE + " WHERE " + STATUS + "=\"" + condition + "\";");

    }

}
