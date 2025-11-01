import java.util.ArrayList;
import java.util.Scanner;

public class AplikasiFotoStudio {
    private ArrayList<Admin> daftarAdmin = new ArrayList<>();
    private ArrayList<User> daftarUser = new ArrayList<>();
    private ArrayList<PaketFoto> daftarPaket = new ArrayList<>();
    private ArrayList<RiwayatPembelian> riwayat = new ArrayList<>();
    private Pemesanan pemesanan;

    public void inisialisasi() {
        daftarAdmin.add(new Admin("A1", "Admin Studio", "admin", "123"));
        daftarUser.add(new User("U1", "Budi", "budi", "111"));
        daftarUser.add(new User("U2", "Siti", "siti", "222"));

        daftarPaket.add(new PaketFoto("P01", "Paket Personal", 100000, 5));
        daftarPaket.add(new PaketFoto("P02", "Paket Couple", 150000, 3));
        daftarPaket.add(new PaketFoto("P03", "Paket Keluarga", 250000, 2));

        pemesanan = new Pemesanan(daftarPaket, riwayat);
    }

    public ArrayList<Admin> getDaftarAdmin() {
        return daftarAdmin;
    }

    public ArrayList<User> getDaftarUser() {
        return daftarUser;
    }

    public void menuAdmin(Scanner scanner) {
        while (true) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Tambah Paket Foto");
            System.out.println("2. Lihat Daftar Paket");
            System.out.println("3. Lihat Riwayat Pemesanan");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1 -> {
                    System.out.print("ID Paket: ");
                    String id = scanner.nextLine();
                    System.out.print("Nama Paket: ");
                    String nama = scanner.nextLine();
                    System.out.print("Harga: Rp");
                    int harga = scanner.nextInt();
                    System.out.print("Slot: ");
                    int slot = scanner.nextInt();
                    scanner.nextLine();
                    daftarPaket.add(new PaketFoto(id, nama, harga, slot));
                    System.out.println(" Paket berhasil ditambahkan!");
                }
                case 2 -> {
                    System.out.println("\n=== DAFTAR PAKET FOTO ===");
                    for (PaketFoto p : daftarPaket) System.out.println(p);
                }
                case 3 -> {
                    System.out.println("\n=== RIWAYAT PEMESANAN ===");
                    if (riwayat.isEmpty()) System.out.println("Belum ada transaksi.");
                    else for (RiwayatPembelian r : riwayat) System.out.println(r);
                }
                default -> { return; }
            }
        }
    }

    public void menuUser(Scanner scanner, User user) {
        while (true) {
            System.out.println("\n=== MENU USER ===");
            System.out.println("1. Booking Paket (tanpa bayar)");
            System.out.println("2. Beli Paket (langsung bayar)");
            System.out.println("3. Pembatalan");
            System.out.println("4. Lihat Riwayat");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1 -> mulaiPemesanan(scanner, user);
                case 2 -> pemesanan.mulaiPembelian(scanner, user);
                case 3 -> new Pembatalan(riwayat).batalkan(scanner, user);
                case 4 -> {
                    System.out.println("\n=== RIWAYAT PEMBELIAN ===");
                    if (riwayat.isEmpty()) System.out.println("Belum ada riwayat.");
                    else for (RiwayatPembelian r : riwayat) System.out.println(r);
                }
                default -> { return; }
            }
        }
    }

    public void mulaiPemesanan(Scanner scanner, User user) {
        System.out.println("\n=== DAFTAR PAKET ===");
        for (PaketFoto p : daftarPaket) System.out.println(p);

        System.out.print("Masukkan ID Paket: ");
        String id = scanner.nextLine();

        PaketFoto paketDipilih = null;
        for (PaketFoto p : daftarPaket) {
            if (p.getId().equalsIgnoreCase(id) && p.getSlot() > 0) {
                paketDipilih = p;
                break;
            }
        }

        if (paketDipilih != null) {
            paketDipilih.kurangiSlot();
            user.tambahPaket(paketDipilih);
            riwayat.add(new RiwayatPembelian(user.getNama(), new ArrayList<>(java.util.List.of(paketDipilih)), paketDipilih.getHarga()));
            System.out.println(" Booking berhasil!");
        } else {
            System.out.println(" Paket tidak ditemukan atau slot habis!");
        }
    }
}
