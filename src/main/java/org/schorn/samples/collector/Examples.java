/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.samples.collector;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import static java.util.Comparator.naturalOrder;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author bschorn
 */
public class Examples {

    enum Topic {
        TECH,
        FINANCE,
        DIY,
        AUTOMOTIVE,
        MUSIC;
    }

    class Book {

        private final String title;
        private final Topic topic;
        private final String author;
        private final LocalDate pubDate;
        private final int pageCount;

        Book(String title, Topic topic, String author, LocalDate pubDate, int pageCount) {
            this.title = title;
            this.topic = topic;
            this.author = author;
            this.pubDate = pubDate;
            this.pageCount = pageCount;
        }

        public String getTitle() {
            return this.title;
        }

        public Topic getTopic() {
            return this.topic;
        }

        public String getAuthor() {
            return this.author;
        }

        public LocalDate getPubDate() {
            return this.pubDate;
        }

        public Year getPubDateYear() {
            return Year.from(this.pubDate);
        }

        public int getPageCount() {
            return this.pageCount;
        }
    }

    private final List<Book> library = new ArrayList<>();

    public void run() {
        Map<Topic, List<Book>> booksByTopic = library.stream()
                .collect(groupingBy(Book::getTopic));

        Map<String, Year> titleToPubDate = library.stream()
                .collect(toMap(Book::getTitle,
                        Book::getPubDateYear,
                        BinaryOperator.maxBy(naturalOrder()),
                        TreeMap::new));
    }

    static public void main(String[] args) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
