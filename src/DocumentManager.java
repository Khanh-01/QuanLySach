import java.sql.*;
public class DocumentManager {
    private Connection connection;

    public DocumentManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/library?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "261206";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối cơ sở dữ liệu thành công");
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            System.exit(1);
        }
    }

    public void addDocument(Document document) {
        try {
            if (isDocumentIdExists(document.getDocumentId())) {
                System.out.println("Đã có tài liệu này");
                return;
            }

            String sql = "INSERT INTO documents (document_id, type, publisher, number_of_copies, author, number_of_pages, issue_number, publication_month, publication_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, document.getDocumentId());
            stmt.setString(2, document.getClass().getSimpleName());
            stmt.setString(3, document.getPublisher());
            stmt.setInt(4, document.getNumberOfCopies());

            if (document instanceof Book) {
                Book book = (Book) document;
                stmt.setString(5, book.getAuthor());
                stmt.setInt(6, book.getNumberOfPages());
                stmt.setNull(7, Types.INTEGER);
                stmt.setNull(8, Types.INTEGER);
                stmt.setNull(9, Types.INTEGER);
            } else if (document instanceof Magazine) {
                Magazine magazine = (Magazine) document;
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.INTEGER);
                stmt.setInt(7, magazine.getIssueNumber());
                stmt.setInt(8, magazine.getPublicationMonth());
                stmt.setNull(9, Types.INTEGER);
            } else if (document instanceof Newspaper) {
                Newspaper newspaper = (Newspaper) document;
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.INTEGER);
                stmt.setNull(7, Types.INTEGER);
                stmt.setNull(8, Types.INTEGER);
                stmt.setInt(9, newspaper.getPublicationDate());
            }

            stmt.executeUpdate();
            System.out.println("Đã thêm tài liệu");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm tài liệu: " + e.getMessage());
        }
    }

    public void removeDocumentById(String documentId) {
        try {
            String sql = "DELETE FROM documents WHERE document_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, documentId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã xóa tài liệu");
            } else {
                System.out.println("Không có tài liệu");
            }
        } catch (SQLException e) {
            System.err.println("Invalid");
        }
    }

    public void displayAllDocuments() {
        try {
            String sql = "SELECT * FROM documents";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean check = false;

            System.out.println("Danh sách tài liệu:");
            while (rs.next()) {
                check = true;
                printDocumentDetails(createDocumentFromResultSet(rs));
            }

            if (!check) {
                System.out.println("Không có tài liệu");
            }
        } catch (SQLException e) {
            System.err.println("Invalid");
        }
    }

    public void searchByType(String type) {
        try {
            String normalizedType = normalizeType(type);
            if (normalizedType.isEmpty()) {
                System.out.println("Loại tài liệu không hợp lệ");
                return;
            }

            String sql = "SELECT * FROM documents WHERE type = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, normalizedType);
            ResultSet rs = stmt.executeQuery();
            boolean check = false;

            while (rs.next()) {
                check = true;
                printDocumentDetails(createDocumentFromResultSet(rs));
            }

            if (!check) {
                System.out.println("Không có tài liệu");
            }
        } catch (SQLException e) {
            System.err.println("Invalid");
        }
    }

    public void exitProgram() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Invalid");
        }
        System.out.println("Thoát");
        System.exit(0);
    }

    private boolean isDocumentIdExists(String documentId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM documents WHERE document_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, documentId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    private String normalizeType(String type) {
        type = type.toLowerCase();
        switch (type) {
            case "sach":
            case "book":
                return "Book";
            case "tapchi":
            case "magazine":
                return "Magazine";
            case "bao":
            case "newspaper":
                return "Newspaper";
            default:
                return "";
        }
    }

    private Document createDocumentFromResultSet(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        String documentId = rs.getString("document_id");
        String publisher = rs.getString("publisher");
        int numberOfCopies = rs.getInt("number_of_copies");

        if ("Book".equals(type)) {
            String author = rs.getString("author");
            int numberOfPages = rs.getInt("number_of_pages");
            return new Book(documentId, publisher, numberOfCopies, author, numberOfPages);
        } else if ("Magazine".equals(type)) {
            int issueNumber = rs.getInt("issue_number");
            int publicationMonth = rs.getInt("publication_month");
            return new Magazine(documentId, publisher, numberOfCopies, issueNumber, publicationMonth);
        } else if ("Newspaper".equals(type)) {
            int publicationDate = rs.getInt("publication_date");
            return new Newspaper(documentId, publisher, numberOfCopies, publicationDate);
        }
        return null;
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
            System.out.println("Newspaper - ID: " + newspaper.getDocumentId() + ", Publisher: " + newspaper.getPublisher() + ", Copies: " + newspaper.getNumberOfCopies() + ", Day: " + newspaper.getPublicationDate());
        }
    }
}