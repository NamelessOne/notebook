package ru.reksoft.testapp;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Интерактивное меню приложения.
 */
public class UI {
    private Scanner in = new Scanner(System.in);

    /**
     * Show main menu.
     *
     * @throws IOException
     */
    public void showMainMenu() throws IOException {
        while (true) {
            System.out.println("****************MAIN MENU*****************");
            System.out.println("[1] Add Contact\n[2] Show all contacts \n[3] Search contact (by name) \n[4] Remove contact \n" +
                    "[5] Save changes \n[6] Save changes and exit \n" +
                    "[7] Exit without saving");
            System.out.println("******************************************");
            System.out.println("Selection: ");
            int input = -1;
            try {
                input = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input.");
            }
            in.nextLine();
            switch (input) {
                case 1:
                    showAddContactMenu();
                    break;
                case 2:
                    showContacts(NotesCollection.getInstance().getContacts(), "List is empty.");
                    break;
                case 3:
                    showSearchContactMenu();
                    break;
                case 4:
                    showDeleteContactMenu();
                    break;
                case 5:
                    NotesCollection.getInstance().saveToJsonFile();
                    break;
                case 6:
                    NotesCollection.getInstance().saveToJsonFile();
                    System.exit(0);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Unrecognized option");
            }
            System.out.println("******************************************");
            System.out.println("Press enter to continue");
            System.in.read();
        }
    }

    /**
     * Show adding contact menu
     */
    private void showAddContactMenu() {
        System.out.println("****************ADD CONTACT*****************");
        String name = "";
        System.out.print("Name: ");
        while ((name = in.nextLine()).length() == 0) {
            System.out.println("No empty name allowed. Please try again.");
            System.out.print("Name: ");
        }
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.print("e-mail: ");
        String email = in.nextLine();
        System.out.println("******************************************");
        ContactEntity contact = new ContactEntity(name, phone, email);
        NotesCollection.getInstance().getContacts().add(contact);
    }

    /**
     * Show search menu.
     */
    private void showSearchContactMenu() {
        System.out.println("********************SEARCH****************");
        System.out.print("Name: ");
        String name = in.nextLine();
        List<ContactEntity> searchResults = NotesCollection.getInstance().search(name);
        showContacts(searchResults, "No results found.");
    }

    /**
     * Show delete contact menu.
     */
    private void showDeleteContactMenu() {
        showContacts(NotesCollection.getInstance().getContacts(), "List is empty.");
        if (NotesCollection.getInstance().getContacts().isEmpty()) return;
        System.out.println("******************REMOVE CONTACT********************");
        System.out.println("Enter number of contact that must be removed: ");
        try {
            int input = in.nextInt();
            NotesCollection.getInstance().getContacts().remove(input);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Wrong input.");
        }
        in.nextLine();
    }

    /**
     * @param contacts List of contacts.
     * @param emptyMessage Message that shown if list is empty.
     */
    private void showContacts(List<ContactEntity> contacts, String emptyMessage)
    {
        if (contacts.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            System.out.println(String.format("%-5s%-25s%-20s%-25s", "№", "| " + "Name", "| " + "Phone number", "| " + "E-mail"));
            System.out.println("----------------------------------------------------------------------");
            for (ContactEntity c : contacts) {
                System.out.println(String.format("%-5s%-70s", contacts.indexOf(c), c.toString()));
            }
        }
    }
}
