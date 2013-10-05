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
    private List<ContactEntity> contacts = new ArrayList<ContactEntity>();
    private Type contactsListType = new TypeToken<List<ContactEntity>>() {
    }.getType();
    private static NotesCollection instance;

    private NotesCollection() throws IOException {
        loadFromJsonFile();
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    /**
     * Load list of notes from Json file.
     */
    public void loadFromJsonFile() throws IOException {
        FileReader fileReader = new FileReader("notes.json");
        contacts = new Gson().fromJson(fileReader, contactsListType);
    }

    /**
     * Save list of notes to Json file.
     */
    public void saveToJsonFile() {
        try {
            FileWriter fileWriter = new FileWriter("notes.json");
            fileWriter.write(new Gson().toJson(contacts, contactsListType));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error when writes json file.");
        }
    }

    public static NotesCollection getInstance() {
        if (instance == null)
            try {
                instance = new NotesCollection();
            } catch (IOException e) {
                System.out.println("Error when load json file.");
            }
        return instance;
    }

    /**
     * Show all elements.
     */
    public void showAll() {
        for (ContactEntity c : contacts) {
            System.out.println(contacts.indexOf(c) + " " + c.toString());
        }
    }

    /**
     * Search element by name in the list.
     *
     * @param name - name of searched element
     * @return element that name contains target string
     */
    public List<ContactEntity> search(String name) {
        List<ContactEntity> result = new ArrayList<ContactEntity>();
        for (ContactEntity c : contacts) {
            if (c.getName().contains(name))
                result.add(c);
        }
        return result;
    }
}
