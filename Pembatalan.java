import java.util.ArrayList;
import java.util.Scanner;

public class Pembatalan {
    private ArrayList<RiwayatPembelian> riwayat;

    public Pembatalan(ArrayList<RiwayatPembelian> riwayat) {
        this.riwayat = riwayat;
    }

    public void batalkan(Scanner scanner, User user) {
        System.out.println("\n=== PEMBATALAN PESANAN ===");
        if (user.getPaketDipesan().isEmpty()) {
            System.out.println("Belum ada pesanan untuk dibatalkan.");
            return;
        }

        for (int i = 0; i < user.getPaketDipesan().size(); i++) {
            PaketFoto p = user.getPaketDipesan().get(i);
            System.out.println((i + 1) + ". " + p.getNamaPaket());
        }

        System.out.print("Pilih nomor paket yang ingin dibatalkan: ");
        int pilih = scanner.nextInt();
        scanner.nextLine();

        if (pilih > 0 && pilih <= user.getPaketDipesan().size()) {
            PaketFoto dibatalkan = user.getPaketDipesan().remove(pilih - 1);
            System.out.print("Alasan pembatalan: ");
            String alasan = scanner.nextLine();

            System.out.println("❌ Pesanan " + dibatalkan.getNamaPaket() + " dibatalkan. Alasan: " + alasan);
            riwayat.add(new RiwayatPembelian(user.getNama(), new ArrayList<>(), 0, alasan));
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }
}
