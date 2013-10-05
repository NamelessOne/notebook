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
            System.out.println("****************MENU*****************");
            System.out.println("[1] Add Contact\n[2] Show all contacts \n[3] Search contact (by name) \n[4] Remove contact \n" +
                    "[5] Save changes and exit \n[6] Exit without saving");
            System.out.println("**************************************");
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
                    //TODO добавление контакта
                    showAddContactMenu();
                    break;
                case 2:
                    //TODO показать все контакты
                    NotesCollection.getInstance().showAll();
                    break;
                case 3:
                    //TODO поиск контакта
                    showSearchContactMenu();
                    break;
                case 4:
                    //TODO удаление контакта
                    showDeleteContactMenu();
                    break;
                case 5:
                    //TODO выход из программы
                    NotesCollection.getInstance().saveToJsonFile();
                    System.exit(0);
                case 6:
                    //TODO выход из программы
                    System.exit(0);
                    break;
                default:
                    System.err.println("Unrecognized option");
            }

            System.out.println("**************************************");
            System.out.println("Press enter to continue");
            System.in.read();
        }
    }

    /**
     * Show adding contact menu
     */
    public void showAddContactMenu() {
        System.out.println("****************Add Contact*****************");
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.next();
        System.out.print("e-mail: ");
        String email = in.next();
        System.out.println("**************************************");
        ContactEntity contact = new ContactEntity(name, phone, email);
        NotesCollection.getInstance().getContacts().add(contact);
    }

    /**
     * Show search menu.
     */
    public void showSearchContactMenu() {
        System.out.println("****************Search Contact*****************");
        System.out.print("Name: ");
        String name = in.next();
        List<ContactEntity> searchResults = NotesCollection.getInstance().search(name);
        for (ContactEntity result : searchResults) {
            System.out.println(result.toString());
        }
    }

    /**
     * Show delete contact menu.
     */
    public void showDeleteContactMenu() {
        NotesCollection.getInstance().showAll();
        System.out.println("**************************************");
        System.out.println("Enter number of contact that must be removed: ");
        try {
            int input = in.nextInt();
            NotesCollection.getInstance().getContacts().remove(input);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input.");
        }
        in.nextLine();
    }
}
