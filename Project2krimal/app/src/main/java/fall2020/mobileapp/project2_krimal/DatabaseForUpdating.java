package fall2020.mobileapp.project2_krimal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseForUpdating extends SQLiteOpenHelper {
    final static String DATABASENAME = "Project2.db";
    final static String TABLE = "TableOfMine";
    final static String ID = "_id";
    final static String NAME = "name";
    final static String REPS = "reps";
    final static String SETS = "sets";
    final static String WEIGHT = "weight";
    final static String NOTES = "notes";

    final private static String CREATECommand =
            "CREATE TABLE "+TABLE+" (" + ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, "+
                    REPS + " TEXT NOT NULL, "+
                    SETS + " TEXT NOT NULL, "+
                    WEIGHT + " TEXT NOT NULL, "+
                    NOTES + " TEXT NOT NULL)";



    public DatabaseForUpdating(Context context){
        super(context, DATABASENAME, null, 1);

    }

    public DatabaseForUpdating(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATECommand);
        ContentValues values = new ContentValues();

        values.put(NAME, "Bicep Curl".toLowerCase());
        values.put(REPS,"15");
        values.put(SETS, "3");
        values.put(WEIGHT, "20");
        values.put(NOTES, "First set of bicep curl");
        db.insert(TABLE,null,values);
        values.clear();
        values.put(NAME, "Chest workout".toLowerCase());
        values.put(REPS,"10");
        values.put(SETS, "3");
        values.put(WEIGHT, "25.5");
        values.put(NOTES, "First set of chest workout");
        db.insert(TABLE,null,values);
        values.clear();
        values.put(NAME, "Soldier workout".toLowerCase());
        values.put(REPS,"15");
        values.put(SETS, "3");
        values.put(WEIGHT, "15");
        values.put(NOTES, "First set of soldier workout");
        db.insert(TABLE,null,values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(String name, String reps, String sets, String weight, String notes){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(NAME, name);
        val.put(REPS,reps);
        val.put(SETS, sets);
        val.put(WEIGHT, weight);
        val.put(NOTES, notes);
        long result = database.insert(TABLE, null, val);

        boolean isValue = (result == -1) ? false: true;

        return isValue;
    }



    public Cursor findTask(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ID + " FROM " + TABLE + " WHERE " +
                NAME + " = '" + taskName + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public ArrayList<String> getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String first_query = " select name from "+TABLE;
        Cursor data;
        ArrayList<String> List = new ArrayList<>();

        data = db.rawQuery(first_query, null);
        //data.moveToFirst();
        while(data.moveToNext()){
            List.add(data.getString(0));
        }
        data.moveToFirst();

        return List;
    }

    public String findval(String taskName, String attribute) {
        SQLiteDatabase db = this.getWritableDatabase();
        String val = "";
        String query = "SELECT " + attribute + " FROM " + TABLE + " WHERE " +
                NAME + " = '" + taskName + "'";
        Cursor data = db.rawQuery(query, null);

        while(data.moveToNext()){
            val = data.getString(0);
        }
        return val;
    }

    public void updateDB(String getName, String reps, String sets, String weight, String notes ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(!reps.equals("")) {
            cv.put(REPS, reps);
        }
        if(!sets.equals("")) {
            cv.put(SETS, sets);
        }
        if(!weight.equals("")) {
            cv.put(WEIGHT, weight);
        }
        if(!notes.equals("")) {
            cv.put(NOTES, notes);
        }
        String selection = NAME + " LIKE ?";
        String [] selection_args = {getName};
        db.update(TABLE, cv, selection, selection_args);

    }

    public void delete(String condition){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(" DELETE FROM " + TABLE + " WHERE " + NAME + "=\"" + condition + "\";");

    }


}
