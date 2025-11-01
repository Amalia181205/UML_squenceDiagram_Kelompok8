import java.util.ArrayList;

public class User {
    protected String id;
    protected String nama;
    protected String username;
    protected String password;
    protected ArrayList<PaketFoto> paketDipesan = new ArrayList<>();

    public User(String id, String nama, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    public String getNama() {
        return nama;
    }

    public void tambahPaket(PaketFoto paket) {
        paketDipesan.add(paket);
    }

    public ArrayList<PaketFoto> getPaketDipesan() {
        return paketDipesan;
    }

    public void tampilkanData() {
        System.out.println(id + " | " + nama + " | " + username);
    }
}
