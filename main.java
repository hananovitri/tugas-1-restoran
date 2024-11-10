import java.util.Scanner;

class Menu {
    String name;
    int price;
    String category;

    Menu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class main {
    static Menu[] daftarMenu = {
            // Daftar menu makanan
            new Menu("Nasi Goreng Special", 25000, "Makanan"),
            new Menu("Nasi Ayam Katsu", 30000, "Makanan"),
            new Menu("Paket Sate Padang", 40000, "Makanan"),
            new Menu("Mie Ayam Komplit", 20000, "Makanan"),

            // Daftar menu minuman
            new Menu("Jus Mangga", 15000, "Minuman"),
            new Menu("Sop Buah", 20000, "Minuman"),
            new Menu("Air Mineral", 8000, "Minuman"),
            new Menu("Es Jeruk", 10000, "Minuman"),
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Menampilkan daftar menu makanan
        System.out.println("=== Daftar Menu ===");
        System.out.println("\nMakanan");
        for (Menu menu : daftarMenu) {
            if (menu.category.equals("Makanan")) {
                System.out.println(menu.name + " = Rp" + menu.price);
            }

        }
        // Menampilkan daftar menu minuman
        System.out.println("\nMinuman");
        for (Menu menu : daftarMenu) {
            if (menu.category.equals("Minuman")) {
                System.out.println(menu.name + " = Rp" + menu.price);
            }
        }

        String[] menuPesanan = new String[4];
        int[] jumlahPesanan = new int[4];
        int jumlahMenu = 0;

        System.out.println("\n====Pemesanan====");
        System.out.println("masukkan nama menu (maksimal 4 menu)");

        while (jumlahMenu < 4) {
            System.out.print("\nmasukkan nama menu (ketik 'selesai' untuk mengakhiri): ");
            String namaPesanan = input.nextLine();

            if (namaPesanan.equalsIgnoreCase("selesai")) {
                break;
            }

            boolean menuDitemukan = false;
            for (Menu menu : daftarMenu) {
                if (menu.name.equalsIgnoreCase(namaPesanan)) {
                    menuDitemukan = true;
                    break;
                }
            }

            if (!menuDitemukan) {
                System.out.println("Menu tidak di temukan silahkan coba lagi.");
                continue;
            }

            System.out.print("Jumlah: ");
            int Jumlah = 0;
            try {
                Jumlah = Integer.parseInt(input.nextLine());
                if (Jumlah <= 0) {
                    System.out.println("Jumlah pesanan harus lebih dari 0!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan jumlah yang valid!");
                continue;
            }
            // Menyimpan pesanan dalam Array
            menuPesanan[jumlahMenu] = namaPesanan;
            jumlahPesanan[jumlahMenu] = Jumlah;
            jumlahMenu++;

            System.out.println("Pesanan berhasil ditambahkan");
        }
        // jika tidak ada pesanan yang dimasukkan
        if (jumlahMenu == 0) {
            System.out.println("\nTidak ada pesanan yang dimasukkan.");
            input.close();
            ;
            return;
        }

        // Menampilkan total biaya pesanan
        int totalBiaya = 0;
        boolean adaMinuman = false; // flag untuk mengecek apakah ada minuman

        // menampilkan struk pesanan
        System.out.println("\n=== Struk Pesanan ===");
        for (int i = 0; i < jumlahMenu; i++) {
            for (Menu menu : daftarMenu) {
                if (menu.name.equalsIgnoreCase(menuPesanan[i])) {
                    int subtotal = menu.price * jumlahPesanan[i];
                    System.out.println(menu.name + " x " + jumlahPesanan[i] + " = Rp" + subtotal);
                    totalBiaya += subtotal;
                }
                if (menu.category.equals("Minuman")) {
                    adaMinuman = true;
                }
            }
        }
        System.out.println("\n subtotal: Rp" + totalBiaya);

        // menghitung pajak dan biaya layanan
        int pajak = (int) (totalBiaya * 0.1);
        int biayaLayanan = 7000;

        System.out.println("Pajak (10%): Rp" + pajak);
        System.out.println("Biaya Layanan: Rp" + biayaLayanan);

        int totalAkhir = totalBiaya + pajak + biayaLayanan;

        // mengecek apakah pesanan memenuhi syarat diskon
        if (totalBiaya > 100000) {
            int diskon = (int) (totalBiaya * 0.1); // diskon 10% untuk pembelian di atas Rp100.000
            System.out.println("Diskon 10%: -Rp" + diskon);
            totalAkhir -= diskon;
        }

        // mengecek apakah memenuhi promo
        if (totalBiaya > 50000 && adaMinuman) {
            System.out.println("Promo beli 1 gratis 1!"); // mendapatkan promo jika pembelian minuman Rp50.000
        }

        // menampilkan total akhir setelah pajak, biaya layanan, diskon (jika ada)
        System.out.println("\nTotal bill: Rp" + totalAkhir);

        input.close();
    }
}