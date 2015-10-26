package main.UI;

import main.domain.News;
import main.manager.Manager;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 1 on 10/8/2015.
 */
public class ConsoleUI {

    private Manager manager;

    public ConsoleUI(Manager manager) {
        this.manager = manager;
    }

    public void run(){
        String command;
        boolean work = true;

        while (work) {
            displayMenu();

            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            switch (command) {
                case "x": work = false;
                    break;
                case "1": this.addNews();
                    break;
                case "2": this.displayAllNews();
                    break;
            }
        }

//        displayMenu();
    }

    private void displayMenu() {
        System.out.println("Menu------------------");
        System.out.println("1: Adauga stire");
        System.out.println("2: Afiseaza toate stirile");
        System.out.println("x: Iesire");
    }

    private void addNews(){
        String title;
        String content;

        Scanner scanIn = new Scanner(System.in);

        try {
            System.out.println("Titlul:");
            title = scanIn.next();

            System.out.println("Continut");
            content = scanIn.next();

            News news = new News(title, content);

            manager.save(news);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAllNews(){
        ArrayList<News> allNews = (ArrayList) manager.getAllNews();

        for (News news : allNews){
            System.out.println(news.getTitle());
            System.out.println(news.getContent());
        }

        if (allNews.isEmpty()) {
            System.out.println("No news added!");
        }
    }
}
