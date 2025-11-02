import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AplikasiFotoStudio app = new AplikasiFotoStudio();

        app.inisialisasi();

        while (true) {
            System.out.println("\n=== LOGIN FOTO STUDIO ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            boolean berhasilLogin = false;

            for (Admin a : app.getDaftarAdmin()) {
                if (a.login(username, password)) {
                    System.out.println("Login sebagai ADMIN: " + a.getNama());
                    app.menuAdmin(scanner);
                    berhasilLogin = true;
                    break;
                }
            }

            if (!berhasilLogin) {
                for (User u : app.getDaftarUser()) {
                    if (u.login(username, password)) {
                        System.out.println("Login sebagai USER: " + u.getNama());
                        app.menuUser(scanner, u);
                        berhasilLogin = true;
                        break;
                    }
                }
            }

            if (!berhasilLogin) {
                System.out.println(" Username / password salah!");
            }
        }
    }
}
