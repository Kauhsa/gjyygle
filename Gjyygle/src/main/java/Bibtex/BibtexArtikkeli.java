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
    public String getAuthor() {
        return arvot.get("Author");
    }
    public String getTitle() {
        return arvot.get("Title");
    }
    public String getJournal() {
        return arvot.get("Journal");
    }
    public int getKey() {
        return new Integer(arvot.get("Key"));
    }
    public void setKey(int key) {
        arvot.put("Key", ""+key);
    }
    public int getYear() {
        return new Integer(arvot.get("Year"));
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
        return new Integer(arvot.get("Volume"));
    }
    public int getNumber() {
        return new Integer(arvot.get("Number"));
    }
    public int getPagesStart() {
        return new Integer(arvot.get("PagesStart"));
    }
    public int getPagesEnd() {
        return new Integer(arvot.get("PagesEnd"));
    }
    public int getMonth() {
        return new Integer(arvot.get("Month"));
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
