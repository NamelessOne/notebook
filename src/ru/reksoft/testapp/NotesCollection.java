package ru.reksoft.testapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton
 */
public class NotesCollection {
    private List<ContactEntity> contacts = new ArrayList<ContactEntity>();
    private Type contactsListType = new TypeToken<List<ContactEntity>>() {
    }.getType();
    private static NotesCollection instance = new NotesCollection();

    private NotesCollection() {
        loadFromJsonFile();
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    public void loadFromJsonFile() {
        try {
            FileReader fileReader = new FileReader("notes.json");
            contacts = new Gson().fromJson(fileReader, contactsListType);
        } catch (Exception e) {
        }
    }

    public void saveToJsonFile() {
        try {
            FileWriter fileWriter = new FileWriter("notes.json");
            fileWriter.write(new Gson().toJson(contacts, contactsListType));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NotesCollection getInstance() {
        return instance;
    }

    public void showAll() {
        for (ContactEntity c : contacts) {
            System.out.println(contacts.indexOf(c) + " " + c.toString());
        }
    }

    public List<ContactEntity> search(String name) {
        List<ContactEntity> result = new ArrayList<ContactEntity>();
        for (ContactEntity c : contacts) {
            if (c.getName().contains(name))
                result.add(c);
        }
        return result;
    }
}
