package com.example.allisonkim.sqlapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by allisonkim on 5/11/17.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rosterDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENT = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String GRADE = "grade";
    private static final String CLUSTER = "cluster";
    private static final String FEATURES = "features";

    public DatabaseManager (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "create table " + TABLE_STUDENT + "( "+ ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + GRADE;
        sqlCreate += " text, " + CLUSTER;
        sqlCreate += " text, " + FEATURES + " real )";

        db.execSQL(sqlCreate);
    }

    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_STUDENT );
        // Re-create tables
        onCreate( db );
    }

    public void insert( Student student ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_STUDENT;
        sqlInsert += " values( null, '" + student.getName( );
        sqlInsert += "', '" + student.getGrade( );
        sqlInsert += "', '" + student.getCluster( );
        sqlInsert += "', '" + student.getFeatures( ) + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_STUDENT;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateById( int id, String name, String grade, String cluster, String features ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_STUDENT;
        sqlUpdate += " set " + NAME + " = '" + name + "', ";
        sqlUpdate += GRADE + " = '" + grade + "'";
        sqlUpdate += CLUSTER + " = '" + cluster + "'";
        sqlUpdate += FEATURES + " = '" + features + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL( sqlUpdate );
        db.close( );
    }

    public ArrayList<Student> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Student> students = new ArrayList<Student>( );
        while( cursor.moveToNext( ) ) {
            Student currentStudent
                    = new Student(  cursor.getString( 1 ),
                    cursor.getString( 2 ), cursor.getString( 3 ), cursor.getString( 4 ), cursor.getString(0) );
            students.add( currentStudent );
        }
        db.close( );
        return students;
    }

    public Student selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_STUDENT;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        Student student = null;
        if( cursor.moveToFirst( ) )
            student = new Student(  cursor.getString( 1 ),
                cursor.getString( 2 ), cursor.getString( 3 ), cursor.getString( 4 ), cursor.getString(0) );
        return student;
    }

}
