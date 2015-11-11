package main.UI;

import data.transfer.Paginator;
import main.domain.News;
import main.domain.dto.NewsDto;
import main.manager.Manager;
import main.utils.Constants;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
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
                case Constants.INPUT_END_CURRENT: work = false;
                    break;
                case "1": this.addNews();
                    break;
                case "2": this.displayAllNews();
                    break;
                case "3": this.displayPaginatedNews();
                    break;
            }
        }

//        displayMenu();
    }

    private void displayMenu() {
        System.out.println("Menu------------------");
        System.out.println("1: Add news");
        System.out.println("2: Display all news");
        System.out.println("3: Display news by page");
        System.out.println("x: Exit");
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

    private void displayPaginatedNews(){
        Paginator<NewsDto> paginator = manager.getNewsPages();

        String command = Constants.NO_INITIAL_INPUT;

        while (true){

            // process user input
            //by default displays first page
            if (command.equals(Constants.PAGE_FORWARD)){
                if (paginator.canPageForward()) {
                    paginator.nextPage();
                } else {
                    System.out.println("Nu mai sunt pagini la dreapta!");
                }
            } else if (command.equals(Constants.PAGE_BACKWARDS)){
                if (paginator.canPageBackwards()){
                    paginator.previousPage();
                } else {
                    System.out.println("Nu mai sunt pagini la stanga!");
                }
            } else if (command.equals(Constants.INPUT_END_CURRENT)){
                break;
            }

            //get the page from the paginator
            List<NewsDto> news = paginator.getPage();

            //print the elements of said page
            for (NewsDto item : news) {
                System.out.println(item.toString());
            }

            System.out.println(Constants.PAGE_BACKWARDS +
                    "- back one page | " +
                    Constants.PAGE_FORWARD +
                    "- forward one page | " +
                    Constants.INPUT_END_CURRENT +
                    "- stop displaying news");

            Scanner in = new Scanner(System.in);
            command = in.nextLine();

        }
    }
}
