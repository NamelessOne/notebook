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
 * Singleton.
 * Contains collection of all notes in notebook.
 */
public class NotesCollection {
    final String DATA_FILE = "note.json";
    private List<ContactEntity> contacts = new ArrayList<ContactEntity>();
    private Type contactsListType = new TypeToken<List<ContactEntity>>() {
    }.getType();
    private static NotesCollection instance;

    private NotesCollection() {
        loadFromJsonFile();
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    /**
     * Load list of notes from Json file.
     */
    public void loadFromJsonFile() {
        try {
            FileReader fileReader = new FileReader(DATA_FILE);
            contacts = new Gson().fromJson(fileReader, contactsListType);
        } catch (IOException e) {
            System.out.println("Cannot load json file. New file will be created.");
        }
    }

    /**
     * Save list of notes to Json file.
     */
    public void saveToJsonFile() {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            fileWriter.write(new Gson().toJson(contacts, contactsListType));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error when writes json file.");
        }
    }

    public static NotesCollection getInstance() {
        if (instance == null)
            instance = new NotesCollection();
        return instance;
    }

    /**
     * Search element by name in the list.
     *
     * @param name - name of searched element
     * @return elements that name contains target string
     */
    public List<ContactEntity> search(String name) {
        List<ContactEntity> result = new ArrayList<ContactEntity>();
        for (ContactEntity c : contacts) {
            if (c.getName().toLowerCase().contains(name.toLowerCase()))
                result.add(c);
        }
        return result;
    }
}
