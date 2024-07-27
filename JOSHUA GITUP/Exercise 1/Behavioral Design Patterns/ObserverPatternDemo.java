/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package newsagency;
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String news);
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}

class Newspaper implements Observer {
    private String name;

    public Newspaper(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        Newspaper times = new Newspaper("Times");
        Newspaper herald = new Newspaper("Herald");

        agency.addObserver(times);
        agency.addObserver(herald);

        agency.setNews("Breaking News!");
    }
}

