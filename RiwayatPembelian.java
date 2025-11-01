import java.util.ArrayList;

public class RiwayatPembelian {

private String namaPelanggan;
private ArrayList<PaketFoto> paketDibeli;
private int totalBayar;
private String status;

// Constructor untuk 1 paket
public RiwayatPembelian(User pembeli, PaketFoto paket) {
    this.namaPelanggan = pembeli.getNama();
    this.paketDibeli = new ArrayList<>();
    this.paketDibeli.add(paket);
    this.totalBayar = paket.getHarga();
    this.status = "Selesai";
}

// Constructor untuk beberapa paket (ArrayList)
public RiwayatPembelian(String nama, ArrayList<PaketFoto> paket, int total) {
    this.namaPelanggan = nama;
    this.paketDibeli = new ArrayList<>(paket);
    this.totalBayar = total;
    this.status = "Selesai";
}

// Constructor untuk pembelian dibatalkan
public RiwayatPembelian(String nama, ArrayList<PaketFoto> paket, int total, String alasanBatal) {
    this.namaPelanggan = nama;
    this.paketDibeli = new ArrayList<>(paket);
    this.totalBayar = total;
    this.status = "Dibatalkan (" + alasanBatal + ")";
}

// Getter opsional
public String getNamaPelanggan() {
    return namaPelanggan;
}

public ArrayList<PaketFoto> getPaketDibeli() {
    return paketDibeli;
}

public int getTotalBayar() {
    return totalBayar;
}

public String getStatus() {
    return status;
}

// Tampilkan riwayat dengan format rapi
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(namaPelanggan).append(" | ");

    if (paketDibeli.isEmpty()) {
        sb.append(status);
    } else {
        for (int i = 0; i < paketDibeli.size(); i++) {
            PaketFoto p = paketDibeli.get(i);
            sb.append(p.getNamaPaket())
              .append(" (Rp").append(p.getHarga()).append(")");
            if (i != paketDibeli.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" | Total Rp").append(totalBayar)
          .append(" | ").append(status);
    }
    return sb.toString();
}
}
