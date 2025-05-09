public class Newspaper extends Document {
    private int publicationDate; // Ngày phát hành

    public Newspaper(String documentId, String publisher, int numberOfCopies, int publicationDate) {
        super(documentId, publisher, numberOfCopies);
        this.publicationDate = publicationDate;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }
}