package company.example.recycleviewexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import company.example.recycleviewexample.models.Adreess;
import company.example.recycleviewexample.models.Contact;

/**
 * Created by admin on 1/15/2016.
 */
public class DbHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    // Address TABLE
    private static final String TABLE_ADREESS  = "direction";
    //Address Columns names
    private static final String KEY_ID_USER = "id_user";
    private static final String KEY_STREET  = "Street";
    private static final String KEY_NUMBER  = "Number";
    private static final String KEY_STATE   = "State";
    private static final String KEY_FK      = "ID_CON";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";

        //  TODO example http://stackoverflow.com/questions/5289861/sqlite-android-foreign-key-syntax
        //Address Table
        String CREATE_ADREESS_TABLE = "CREATE TABLE " + TABLE_ADREESS + "("
                + KEY_ID_USER + " INTEGER PRIMARY KEY autoincrement," + KEY_STREET + " TEXT,"
                + KEY_NUMBER + " TEXT," + KEY_STATE + " TEXT,"+KEY_FK +" INTEGER,"
                + " FOREIGN KEY ("+KEY_FK+") REFERENCES "+TABLE_CONTACTS+"("+KEY_ID+"));";


        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_ADREESS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADREESS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    public void addAdress(Adreess ad)
    {
        //DB structure
        //int id_user, String street, String number, String state, int fk
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_USER,ad.getId_user());
        values.put(KEY_STREET, ad.getStreet());
        values.put(KEY_NUMBER, ad.getState());
        values.put(KEY_STATE, ad.getState());
        values.put(KEY_FK,ad.getFk());


        db.insert(TABLE_ADREESS,null,values);
    }
    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
        //values.put(KEY_STREET, contact.getStreet()); //Street name
        //values.put(KEY_NUMBER, contact.getNumber());
        //values.put(KEY_STATE, contact.getNumber());


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);



        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+" , "
                +TABLE_ADREESS + " WHERE "+TABLE_CONTACTS+"."+KEY_ID+"="+TABLE_ADREESS+"."+KEY_FK+";" ;

        //String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " AS C JOIN " + TABLE_ADREESS +" AS P ON C." +KEY_ID
          //      + " = " +"P."+ KEY_ID_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getInt(2));
                Log.d("DB ->", String.valueOf(cursor.getString(3)));
                Log.d("DB->",String.valueOf(cursor.getInt(4)));
                //       contact.setState(cursor.getString(5));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] { String.valueOf(contact.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}

