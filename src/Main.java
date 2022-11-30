import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner terminalInput = new Scanner(System.in);
        String pilihanUser;
        boolean isContinue=true;

        while (isContinue) {
            clearScreen();
            System.out.println("database perpustakaan\n");
            System.out.println("1.\tLihat seluruh buku");
            System.out.println("2.\tCari data buku");
            System.out.println("3.\tTambah data buku");
            System.out.println("4.\tUbah data buku");
            System.out.println("5.\tHapus data buku");

            System.out.print("\n\nPilihan anda : ");
            pilihanUser = terminalInput.next();
            switch (pilihanUser) {
                case "1":
                    System.out.println("\n=================");
                    System.out.println("LIST SELURUH BUKU");
                    System.out.println("=================");
                    showBooks();
                    break;
                case "2":
                    System.out.println("\n=========");
                    System.out.println("CARI BUKU");
                    System.out.println("=========");
                    findBook();
                    break;
                case "3":
                    System.out.println("\n================");
                    System.out.println("TAMBAH DATA BUKU");
                    System.out.println("================");
                    break;
                case "4":
                    System.out.println("\n==============");
                    System.out.println("UBAH DATA BUKU");
                    System.out.println("==============");
                    break;
                case "5":
                    System.out.println("\n===============");
                    System.out.println("HAPUS DATA BUKU");
                    System.out.println("===============");
                    break;
                default:
                    System.err.println("\nInput tidak ditemukan!\nSilahkan pilih [1-5]");
            }
            isContinue= getYorN("Apakah kamu ingin melanjutkan");
        }
    }
    private static void showBooks() throws IOException {
        FileReader fileInput;
        BufferedReader bufferInput;

        try {
            fileInput= new FileReader("database.txt");
            bufferInput= new BufferedReader(fileInput);
        }catch (Exception e){
            System.err.println("database tidak ditemukan!");
            System.err.println("Silahkan tambah database terlebih dahulu.");
            return;
        }

        int nomor=0;
        String data=bufferInput.readLine();
        System.out.print("\n|\tNo. |\tTahun |\tPenulis  |\tPenerbit |\tJudul Buku");
            System.out.print("\n--------------------------------------------------------");

        while (data !=null){
            nomor++;
            StringTokenizer st= new StringTokenizer(data, ",");

            st.nextToken();
            System.out.printf("\n|\t%d   ",nomor);
            System.out.printf("|\t%s  ",st.nextToken());
            System.out.printf("|\t%-8s ",st.nextToken());
            System.out.printf("|\t%-8s ",st.nextToken());
            System.out.printf("|\t%s ",st.nextToken());

            data= bufferInput.readLine();
        }
            System.out.print("\n--------------------------------------------------------");
    }

    private static void findBook() throws IOException{
        try {
            File fileInput= new File("database.txt");
        }catch (Exception e){
            System.err.println("database tidak ditemukan!");
            System.err.println("Silahkan tambah database terlebih dahulu.");
            return;
        }
        Scanner terminalInput= new Scanner(System.in);
        System.out.print("Masukkan kata kunci : ");
        String cari= terminalInput.nextLine();
        String[] keywords= cari.split("\\s+");

        chekBook(keywords);
    }
    private static void chekBook(String[] keywords) throws IOException{
        FileReader fileInput= new FileReader("database.txt");
        BufferedReader bufferInput= new BufferedReader(fileInput);

        String data=bufferInput.readLine();
        boolean isExist;
        int jmlData=0;

        System.out.print("\n|\tNo. |\tTahun |\tPenulis  |\tPenerbit |\tJudul Buku");
        System.out.print("\n--------------------------------------------------------");
        while (data !=null){
            isExist=true;
            for (String keyword : keywords) {
                isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
            }
            if (isExist){
                jmlData++;
                StringTokenizer st= new StringTokenizer(data, ",");

                st.nextToken();
                System.out.printf("\n|\t%d   ",jmlData);
                System.out.printf("|\t%s  ",st.nextToken());
                System.out.printf("|\t%-8s ",st.nextToken());
                System.out.printf("|\t%-8s ",st.nextToken());
                System.out.printf("|\t%s ",st.nextToken());
            }
            data= bufferInput.readLine();
        }
                System.out.print("\n--------------------------------------------------------");

    }
    private static void clearScreen(){
        try{
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else {
                System.out.print("\033\143");
            }
        }catch (Exception ex){
            System.err.println("tidak bisa clear screen😒");
        }
    }
    private static boolean getYorN(String msg){
        Scanner terminalInput = new Scanner(System.in);
        System.out.println("\n"+msg+" (y/n)?");
        String pilihanUser= terminalInput.next();

        while (!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")){
            System.err.println("Pilihan anda bukan y atau n");
            System.out.println("\n"+msg+" (y/n)?");
            pilihanUser= terminalInput.next();
        }
        return pilihanUser.equalsIgnoreCase("y");
    }

}