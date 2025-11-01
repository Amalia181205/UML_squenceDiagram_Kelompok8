import java.util.ArrayList;
import java.util.Scanner;

public class Pemesanan {

private ArrayList<PaketFoto> daftarPaket;
private ArrayList<RiwayatPembelian> riwayatPemesanan;

// Constructor menerima daftar paket dan riwayat pembelian
public Pemesanan(ArrayList<PaketFoto> daftarPaket, ArrayList<RiwayatPembelian> riwayatPemesanan) {
    this.daftarPaket = daftarPaket;
    this.riwayatPemesanan = riwayatPemesanan;
}

// === METODE PEMBELIAN ===
public void mulaiPembelian(Scanner scanner, User pembeli) {
    System.out.println("\n=== DAFTAR PAKET FOTO ===");
    for (PaketFoto p : daftarPaket) {
        System.out.println(p);
    }

    System.out.print("\nMasukkan jumlah paket yang ingin dibeli: ");
    int jumlah = scanner.nextInt();
    scanner.nextLine(); // Bersihkan newline

    int totalBayar = 0;
    ArrayList<PaketFoto> paketDibeli = new ArrayList<>();

    for (int i = 0; i < jumlah; i++) {
        System.out.print("Masukkan ID Paket ke-" + (i + 1) + ": ");
        String id = scanner.nextLine().toUpperCase();

        PaketFoto paketDipilih = null;
        for (PaketFoto p : daftarPaket) {
            if (p.getId().equalsIgnoreCase(id) && p.getSlot() > 0) {
                paketDipilih = p;
                break;
            }
        }

        if (paketDipilih != null) {
            paketDipilih.kurangiSlot(); // Kurangi slot paket
            paketDibeli.add(paketDipilih);
            totalBayar += paketDipilih.getHarga();

            // Simpan ke riwayat pembelian
            RiwayatPembelian riwayat = new RiwayatPembelian(pembeli, paketDipilih);
            riwayatPemesanan.add(riwayat);

            System.out.println("✅ " + paketDipilih.getNamaPaket() + " berhasil ditambahkan ke pesanan.");
        } else {
            System.out.println("❌ Paket tidak ditemukan atau slot habis!");
            i--; // Ulang input jika salah
        }
    }

    // Tampilkan rincian pembelian
    System.out.println("\n=== RINCIAN PEMBELIAN ===");
    for (PaketFoto p : paketDibeli) {
        System.out.println(p.getNamaPaket() + " - Rp" + p.getHarga());
    }
    System.out.println("TOTAL BAYAR: Rp" + totalBayar);

    // Proses pembayaran
    System.out.print("Masukkan nominal pembayaran: Rp");
    int bayar = scanner.nextInt();
    int kembali = bayar - totalBayar;

    if (kembali < 0) {
        System.out.println("❌ Uang Anda kurang Rp" + Math.abs(kembali));
    } else {
        System.out.println("✅ Pembayaran berhasil! Kembalian: Rp" + kembali);
        System.out.println("Terima kasih sudah menggunakan jasa Foto Studio kami!");
    }
}

// === METODE UNTUK MENAMPILKAN RIWAYAT PEMESANAN ===
public void tampilkanRiwayat() {
    System.out.println("\n=== RIWAYAT PEMESANAN ===");
    if (riwayatPemesanan.isEmpty()) {
        System.out.println("Belum ada riwayat pembelian.");
    } else {
        for (RiwayatPembelian r : riwayatPemesanan) {
            System.out.println(r);
        }
    }
}
}
