public class Magazine extends Document {
    private int issueNumber; // Số phát hành
    private int publicationMonth; // Tháng phát hành

    public Magazine(String documentId, String publisher, int numberOfCopies, int issueNumber, int publicationMonth) {
        super(documentId, publisher, numberOfCopies);
        this.issueNumber = issueNumber;
        this.publicationMonth = publicationMonth;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getPublicationMonth() {
        return publicationMonth;
    }

    public void setPublicationMonth(int publicationMonth) {
        this.publicationMonth = publicationMonth;
    }
}
