package visma;


import visma.Dto.Item;

import java.util.List;
import java.util.Scanner;

import static visma.DateUtils.matchPattern;
import static visma.FileParser.sort;
import static visma.FilterUtils.filterBBE;
import static visma.FilterUtils.filterQty;

public class App {


    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        List<Item> list = fileParser.csvReader();
        menu(list);
    }

    public static void menu(List<Item> list) {
        boolean exit = false;
        do {
            System.out.println("\n\n" +
                    "Iveskite norimos funkcijos skaiciu ir spauskite ENTER \n" +
                    "\n" +
                    "1. Parodyti prekiu sarasa \n" +
                    "2. Perziureti trukstamu prekiu kiekius   \n" +
                    "3. Patikrinti prekiu galiojimo laika\n" +
                    "4. Iseiti");
            Scanner in = new Scanner(System.in);
            Scanner in2 = new Scanner(System.in);
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    print(sort(list));
                    break;
                case "2":
                    System.out.println("Nurodykite kieki");
                    int qty = in.nextInt();
                    print(filterQty(list, qty));
                    break;
                case "3":
                    String date;
                    do {
                        System.out.println("Iveskite data. Datos formatas: yyyy-MM-dd");
                        date = in2.nextLine();
                        if (!matchPattern(date)) {
                            System.out.println("\nTokia data neegzizuotja arba blogas formatas. Datos formatas: yyyy-MM-dd");
                        }

                    } while (!matchPattern(date));
                    print(filterBBE(list, date));
                    break;
                case "4":
                    exit = true;
            }
        } while (!exit);
    }

    static void print(List<Item> list) {
        if (list.isEmpty()) {
            System.out.println("\nPagal siuos kriterijus duomenu nerasta");
        }
        System.out.println("");
        for (Item item : list) {
            System.out.println(item.getName() + " " + item.getId() + " " + item.getQty() + " " + item.getDate());
        }
    }
}




