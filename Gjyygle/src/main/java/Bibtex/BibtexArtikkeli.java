package Bibtex;
import java.util.HashMap;

public class BibtexArtikkeli {
    private HashMap<String, String> arvot;
    public BibtexArtikkeli (String author, String title, String journal, int year) {
        arvot = new HashMap<String, String>();
        arvot.put("Author", author);
        arvot.put("Title", title);
        arvot.put("Journal", journal);
        arvot.put("Year", ""+year);
    }
    private int findInteger(String key) {
        String found = arvot.get(key);
        if (found == null) {
            return -1;
        }
        return new Integer(found);
    }
    public String getAuthor() {
        return arvot.get("Author");
    }
    public String getTitle() {
        return arvot.get("Title");
    }
    public String getJournal() {
        return arvot.get("Journal");
    }
    //keyn arvotyyppiä (int/string/?) ei ole löytynyt mistään, oletetaan string
    public String getKey() {
        return arvot.get("Key");
    }
    public void setKey(String key) {
        arvot.put("Key", key);
    }
    public void setNote(String note) {
        arvot.put("Note", ""+note);
    }
    public String getNote() {
        return arvot.get("Note");
    }
    public int getYear() {
        return findInteger("Year");
    }
    public void setAuthor(String author) {
        arvot.put("Author", author);
    }
    public void setTitle(String title) {
        arvot.put("Title", title);
    }
    public void setJournal(String journal) {
        arvot.put("Journal", journal);
    }
    public void setYear(int year) {
        arvot.put("Year", ""+year);
    }
    public int getVolume() {
        return findInteger("Volume");
    }
    public int getNumber() {
        return findInteger("Number");
    }
    public int getPagesStart() {
        return findInteger("PagesStart");
    }
    public int getPagesEnd() {
        return findInteger("PagesEnd");
    }
    public int getMonth() {
        return findInteger("Month");
    }
    public void setVolume(int volume) {
        arvot.put("Volume", ""+volume);
    }
    public void setNumber(int number) {
        arvot.put("Number", ""+number);
    }
    public void setMonth(int month) {
        arvot.put("Month", ""+month);
    }
    public void setPages(int pagesStart, int pagesEnd) {
        arvot.put("PagesStart", ""+pagesStart);
        arvot.put("PagesEnd", ""+pagesEnd);
    }
}
