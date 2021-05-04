package iut.lp.devmob.MesContacts;

import java.util.ArrayList;

public class DataBaseConstants {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "db_contact";
    public static final String TABLE_NAME = "contact";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME ="name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_EMAIL = "email";
    public static final ArrayList<Contact> contact_list = new ArrayList<Contact>();
}
