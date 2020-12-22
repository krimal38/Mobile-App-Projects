package fall2020.mobileapp.finalproject_krimal_radhika2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    final static String DBNAME = "finalproject.db";
    final static String TABLE_NAME = "myTable";
    final static String ID = "_id";
    final static String NAME = "name";
    final static String IMAGE = "image";
    //final static String SETS = "sets";
    // final static String WEIGHT = "weight";
    // final static String NOTES = "notes";



    final private static String CREATE_CMD =
            "CREATE TABLE "+TABLE_NAME+" (" + ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, "+
                    // REPS + " TEXT NOT NULL, "+
                    //    SETS + " TEXT NOT NULL, "+
                    //  WEIGHT + " TEXT NOT NULL, "+
                    IMAGE +" TEXT NOT NULL)";

    final private static Integer VERSION = 1;




    public Database(Context context){
        super(context, DBNAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
        ContentValues values = new ContentValues();

        values.put(NAME, "pin");
        values.put(IMAGE,"");
        // values.put(SETS,"2");
        //  values.put(WEIGHT,"30");
        // values.put(NOTES,"my first exercise");
        db.insert(TABLE_NAME,null,values);

        values.clear();
        values.put(NAME, "passwords");
        values.put(IMAGE,"");
        // values.put(SETS,"4");
        //  values.put(WEIGHT,"30");
        // values.put(NOTES,"my second exercise");
        db.insert(TABLE_NAME,null,values);

        // values.clear();
        //  values.put(NAME, "jump and jack");
        //  values.put(REPS,"30");
        //  values.put(SETS,"6");
        //  values.put(WEIGHT,"10");
        //  values.put(NOTES,"my third exercise");
        //  db.insert(TABLE_NAME,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }



    public boolean addData(String taskName, String imageVal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, taskName.toLowerCase());
        values.put(IMAGE, imageVal);
        // values.put(SETS,sets);
        //  values.put(WEIGHT,weight);
        // values.put(NOTES,notes);
        //db.insert(TABLE_NAME,null,values);
        long result = db.insert(TABLE_NAME, null, values);

        if(result == -1)
            return false;
        else{
            return true;

        }

    }



    public ArrayList<String> getDataBase(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " select name from "+TABLE_NAME;
        Cursor data;
        ArrayList<String> myList = new ArrayList<>();
        // select name from TABLE_NAME
        data = db.rawQuery(query, null);
        data.moveToFirst();
        data.moveToNext();
        while(data.moveToNext()){
            myList.add(data.getString(0));
        }
        data.moveToFirst();
        return myList;

    }
    public void delete(String condition){
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + NAME + "=\"" + condition + "\";");

    }

    public Cursor findTask(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " +
                NAME + " = '" + taskName + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public String getRow(String taskName,String attr) {
        SQLiteDatabase db = this.getWritableDatabase();
        String attribute ="";
        String query = "SELECT " + attr + " FROM " + TABLE_NAME + " WHERE " +
                NAME + " = '" + taskName + "'";
        Cursor data = db.rawQuery(query, null);
        while(data.moveToNext())
            attribute = data.getString(0);
        return attribute;

    }

    public boolean updateData(String nomain, String attr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(!attr.equals(""))
            values.put(IMAGE,attr);
        // if(!sets.equals(""))
        //   values.put(SETS,sets);
        //if(!weight.equals(""))
        //  values.put(WEIGHT,weight);
        //if(!notes.equals(""))
        //  values.put(NOTES,notes);
        db.update("myTable",values,"name=?",new String[]{nomain});


        return true;
    }
}
