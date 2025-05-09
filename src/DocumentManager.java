import java.util.ArrayList;
import java.util.List;
public class DocumentManager {
    private List<Document> documentList;
    public DocumentManager() {
        documentList = new ArrayList<>();
    }

    public void addDocument(Document document) {
        documentList.add(document);
        System.out.println("Đã thêm tài liệu");
    }

    public void removeDocumentById(String documentId) {
        for (int i = 0; i < documentList.size(); i++) {
            if (documentList.get(i).getDocumentId().equals(documentId)) {
                documentList.remove(i);
                System.out.println("Đã xóa tài liệu");
                return;
            }
        }
        System.out.println("Không có tài liệu");
    }

    public void displayAllDocuments() {
        if (documentList.isEmpty()) {
            System.out.println("Không có tài liệu");
            return;
        }
        System.out.println("Danh sách tài liệu:");
        for (Document document : documentList) {
            printDocumentDetails(document);
        }
    }

    public void searchByType(String type) {
        boolean check = false;
        for (Document document : documentList) {
            if (type.equals("Book") && document instanceof Book) {
                printDocumentDetails(document);
                check = true;
            } else if (type.equals("Magazine") && document instanceof Magazine) {
                printDocumentDetails(document);
                check = true;
            } else if (type.equals("Newspaper") && document instanceof Newspaper) {
                printDocumentDetails(document);
                check = true;
            }
        }
        if (!check) {
            System.out.println("Không có tài liệu");
        }
    }

    public void exitProgram() {
        System.out.println("Thoát");
        System.exit(0);
    }


    private void printDocumentDetails(Document doc) {
        if (doc instanceof Book) {
            Book book = (Book) doc;
            System.out.println("Book - ID: " + book.getDocumentId() + ", Publisher: " + book.getPublisher() + ", Copies: " + book.getNumberOfCopies() + ", Author: " + book.getAuthor() + ", Pages: " + book.getNumberOfPages());
        } else if (doc instanceof Magazine) {
            Magazine magazine = (Magazine) doc;
            System.out.println("Magazine - ID: " + magazine.getDocumentId() + ", Publisher: " + magazine.getPublisher() + ", Copies: " + magazine.getNumberOfCopies() + ", Issue: " + magazine.getIssueNumber() + ", Month: " + magazine.getPublicationMonth());
        } else if (doc instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) doc;
            System.out.println("Newspaper - ID: " + newspaper.getDocumentId() + ", Publisher: " + newspaper.getPublisher() + ", Copies: " + newspaper.getNumberOfCopies() + ", Day: " + newspaper.getPublicationDay());
        }
    }
}