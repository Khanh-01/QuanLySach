public class Book extends Document {
    private String author; // Tên tác giả
    private int numberOfPages; // Số trang

    public Book(String documentId, String publisher, int numberOfCopies, String author, int numberOfPages) {
        super(documentId, publisher, numberOfCopies);
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
