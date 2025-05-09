public abstract class Document {
    protected String documentId; // Mã tài liệu
    protected String publisher; // Nhà xuất bản
    protected int numberOfCopies; // Số bản phát hành

    public Document(String documentId, String publisher, int numberOfCopies) {
        this.documentId = documentId;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public abstract String getPublicationDay();
}
