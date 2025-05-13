import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DocumentManager manager = new DocumentManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Quản Lý Sách");
            System.out.println("1. Thêm tài liệu");
            System.out.println("2. Xóa tài liệu theo mã");
            System.out.println("3. Hiển thị tất cả tài liệu");
            System.out.println("4. Tìm kiếm theo loại tài liệu");
            System.out.println("5. Thoát chương trình");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Nhập loại tài liệu (Sach/TapChi/Bao): ");
                String type = scanner.nextLine();
                System.out.print("Nhập mã tài liệu: ");
                String id = scanner.nextLine();
                System.out.print("Nhập nhà xuất bản: ");
                String publisher = scanner.nextLine();
                System.out.print("Nhập số lượng bản: ");
                int copies = scanner.nextInt();
                scanner.nextLine();

                if (type.equalsIgnoreCase("Book")) {
                    System.out.print("Nhập tác giả: ");
                    String author = scanner.nextLine();
                    System.out.print("Nhập số trang: ");
                    int pages = scanner.nextInt();
                    scanner.nextLine();
                    manager.addDocument(new Book(id, publisher, copies, author, pages));
                } else if (type.equalsIgnoreCase("Magazine")) {
                    System.out.print("Nhập số phát hành: ");
                    int issue = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tháng phát hành: ");
                    int month = scanner.nextInt();
                    scanner.nextLine();
                    manager.addDocument(new Magazine(id, publisher, copies, issue, month));
                } else if (type.equalsIgnoreCase("Newspaper")) {
                    System.out.print("Nhập ngày phát hành: ");
                    int day = scanner.nextInt();
                    scanner.nextLine();
                    manager.addDocument(new Newspaper(id, publisher, copies, day));
                } else {
                    System.out.println("Invalid");
                }

            } else if (choice == 2) {
                System.out.print("Nhập mã tài liệu cần xóa: ");
                String removeId = scanner.nextLine();
                manager.removeDocumentById(removeId);
            } else if (choice == 3) {
                manager.displayAllDocuments();
            } else if (choice == 4) {
                System.out.print("Nhập loại tài liệu cần tìm (Sach/TapChi/Bao): ");
                String searchType = scanner.nextLine();
                manager.searchByType(searchType);
            } else if (choice == 5) {
                manager.exitProgram();
            } else {
                System.out.println("Invalid");
            }
        }
    }
}