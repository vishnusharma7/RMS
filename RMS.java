
import java.rmi.server.SocketSecurityException;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class RMS {
    public static void main(String[] args) {
        int i = 0, count = 0;
        Scanner sc = new Scanner(System.in);
        // Rms r = new Rms();
        Rms[] ob = new Rms[7];
        String name;
        int id = 100;
        Long phone;
        int date;
        int bill = 0;
        while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("1.Book Your Table");
            System.out.println("2.Show Menu ");
            System.out.println("3.Check Table Availability");
            System.out.println("4.Check Your order");
            System.out.println("5.Exit");
            System.out.println("6.Total customer of the day");
            System.out.println("7.Highest bill of the day");
            System.out.println("8.Total day collection");
            System.out.println("9.Exit ");
            System.out.println("10.All customer Order details");
            System.out.println("---------------------------------------------------------");
            System.out.print("Enter choice \t:");
            int key = sc.nextInt();
            switch (key) {
                case 1:
                    boolean q = true;
                    do {
                        System.out.print("Enter name \t:");
                        name = sc.next();
                        System.out.print("Enter phone \t:");
                        phone = sc.nextLong();
                        System.out.print("Enter Date \t:");
                        date = sc.nextInt();

                        ob[i] = new Rms(name, id, phone, date);

                        System.out.println("Do you want to book more table: (1.Yes or 2.No");
                        int h = sc.nextInt();
                        if (h == 1) {
                            count++;
                            id++;
                            System.out.print("Enter name \t:");
                            name = sc.next();
                            System.out.print("Enter phone \t:");
                            phone = sc.nextLong();
                            System.out.print("Enter Date \t:");
                            date = sc.nextInt();
                            ob[i++] = new Rms(name, id, phone, date);
                            break;

                        } else {

                            System.out.println("-------------------------------------------------------");
                            System.out.println("Successfully Booked!!");
                            System.out.println("Your id no:" + ob[i].id);
                            System.out.println("For more details press 4");
                            System.out.println("------------------------------------------------------");
                            i++;
                            count++;
                            id++;
                            q = false;
                        }
                    } while (q);
                    break;
                case 2:
                    System.out.println("\t\t-----------------------MENU------------------------");
                    System.out.println("\t\t1.idly  \tRs.30  \t\t6.Pesarattu \tRs.30");
                    System.out.println("\t\t2.wada  \tRs.35  \t\t7.uttapam   \tRs.25");
                    System.out.println("\t\t3.idly  \tRs.40  \t\t8.bonda     \tRs.10");
                    System.out.println("\t\t4.dosa  \tRs.30  \t\t9.tea       \tRs.15");
                    System.out.println("\t\t5.poori \tRs.25  \t\t10.Quit     \tRs.0");
                    System.out.println("\t\t----------------------------------------------------");
                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("Enter your id no:");
                    int k = sc.nextInt();
                    for (int j = 0; j < count; j++) {
                        if (k == ob[j].id) {
                            System.out.println("----------------------------------------------");
                            System.out.println("Status : Successfully booked");
                            System.out.println("Name\t:" + ob[j].name);
                            System.out.println("Phone\t:" + ob[j].phone);
                            System.out.println("date\t:" + ob[j].date);
                            for (int a = 0; a < 10; a++) {
                                if (ob[j].qt[a] != 0) {
                                    System.out.println(ob[j].s[a] + " * " + ob[j].qt[a] + "==" + "Rs. "
                                            + ob[j].qt[a] * ob[j].rate[a]);
                                }
                            }
                            System.out.println("Bill\t:" + ob[j].sum);
                            System.out.println("Thank you");
                            System.out.println("----------------------------------------------");
                            break;
                        } else {
                            System.out.println("----------------------------------------");
                            System.out.println("No records found!!!");
                            System.out.println("----------------------------------------");
                            break;
                        }

                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                    System.out.println("----------------------------------------------");
                    System.out.println("Total customers:" + count);
                    System.out.println("----------------------------------------------");
                    break;
                case 7:
                    int max = ob[0].sum;
                    for (int j = 0; j < count; j++) {
                        if (ob[j].sum > max) {
                            max = ob[j].sum;
                            System.out.println("---------------------------------------------------");
                            System.out.println("Max spend user Name: " + ob[j].name);
                            System.out.println(ob[j].name + "Spend " + ob[j].sum + " Thanks for choosing us");
                            System.out.println("---------------------------------------------------");
                        }

                    }
                    break;
                case 8:
                    for (int j = 0; j < count; j++) {
                        bill = bill + ob[j].sum;
                    }
                    System.out.println("Total collection: " + bill);

                    break;

                case 10:
                    for (int j = 0; j < count; j++) {

                        System.out.println("----------------------------------------------");
                        System.out.println("Status : Successfully booked");
                        System.out.println("customer id:" + ob[j].id);
                        System.out.println("Name\t:" + ob[j].name);
                        System.out.println("Phone\t:" + ob[j].phone);
                        System.out.println("date\t:" + ob[j].date);
                        for (int a = 0; a < 10; a++) {
                            if (ob[j].qt[a] != 0) {
                                System.out.println(ob[j].s[a] + " * " + ob[j].qt[a] + "==" + "Rs. "
                                        + ob[j].qt[a] * ob[j].rate[a]);
                            }
                        }
                        System.out.println("Bill\t:" + ob[j].sum);
                        System.out.println("Thank you");
                        System.out.println("----------------------------------------------");
                    }
                    break;

            }
        }
    }
}

class Rms {
    Scanner sc = new Scanner(System.in);
    String name, food, ch;
    String[] s = new String[] { "idly    ", "wada    ", "dosa    ", "poori     ", "pesarattu", "uttapam",
            "bonda     ", "tea   ", "coffee    ", "Quit     " };
    int[] rate = new int[] { 30, 35, 40, 30, 25, 30, 25, 10, 15, 0 };
    int id = 100, date, x, y; // x and y used for table booked markup
    static int w = 0;// for table update
    long phone;
    int sum = 0, n; // sum use for billing
    static int table[][] = new int[100][100]; // for slot booking
    int[] qt = new int[10];

    String fd[];

    int b = 0;

    public Rms(String name, int id, Long phone, int date) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.id = id;
        order();
        tablebook();
        // order();
    }

    void order() {

        Scanner input = new Scanner(System.in);

        boolean quit = true;
        do {
            showMenu();
            System.out.print("Enter Item According to number:\t");

            int ch = input.nextInt();
            if (ch > 0 && ch < 10) {
                System.out.println("enter the no of Plates: " + s[ch - 1]);
                int q = input.nextInt();
                qt[ch - 1] += q;

            }
            System.out.println("do you want to add more dish:? (1.Yes or 2.No");
            int k = sc.nextInt();
            if (k == 1) {
                continue;
            } else {
                quit = false;

            }

        } while (quit);
        System.out.println("----------------------------------------------------");
        System.out.println("Your Orders  picked for:\n");
        for (int i = 0; i < 10; i++) {
            if (qt[i] != 0) {
                this.sum += qt[i] * rate[i];
                // this.b = this.b + this.sum;
                System.out.print(s[i] + " * " + qt[i] + " = " + " Rs." + qt[i] * rate[i]);
            }
        }
        System.out.println("----------------------------------------------------");

    }

    void tablebook() { // Table Book
        int k = 10;
        int j = 11;
        int m = 0;
        w++;
        if (w == 1) {
            System.out.println(
                    "-------------------------------------------Available Slots -----------------------------------------");
            System.out.print("\t    ");
            for (int col = 1; col <= 7; col++) {
                System.out.print("Table" + col + "    ");
            }
            System.out.println();
            for (int row = 1; row <= 12; row++) {
                k++;
                j++;
                m++;
                System.out.print(m + "." + k + "-" + j + "     ");

                for (int col = 1; col <= 7; col++) {
                    table[row][col] = 0;

                    System.out.print("   " + table[row][col] + "        ");
                }
                System.out.println();
            }
            System.out.println(
                    "---------------------------------------------------------------------------------------------------");
        } else {
            updateTabel();
        }
        boolean quit = true;
        do {
            System.out.println("Enter your slot:");
            this.x = sc.nextInt();
            this.y = sc.nextInt();
            System.out.println("Time:" + x);
            System.out.println("Table:" + y);
            updateTabel();
            System.out.println("do you want add 1 more hour :? (1.Yes or 2.No");
            int g = sc.nextInt();
            if (g == 1) {
                updateTabel();
                continue;
            } else {
                System.out.println("------------------------------------");
                System.out.println("Your id no:" + id);
                System.out.println("Status : Successfully booked");
                System.out.println("Name:" + name);
                System.out.println("Phone:" + phone);
                System.out.println("Your Orderred:");
                for (int i = 0; i < 10; i++) {
                    if (qt[i] != 0) {
                        System.out.println(s[i] + " * " + qt[i] + "==" + "Rs. " + qt[i] * rate[i]);
                    }
                }
                System.out.println("Your total bill=" + sum);
                System.out.println("Thank you");
                System.out.println("------------------------------------");
                quit = false;

            }

        } while (quit);

    }

    void updateTabel() {
        int k = 10;
        int j = 11;
        int m = 0;
        System.out.println(
                "-------------------------------------------Available Slots -----------------------------------------");
        System.out.print("\t    ");
        for (int col = 1; col <= 7; col++) {
            System.out.print("Table" + col + "    ");
        }
        System.out.println();
        for (int row = 1; row <= 12; row++) {
            k++;
            j++;
            m++;
            System.out.print(m + "." + k + "-" + j + "     ");

            for (int col = 1; col <= 7; col++) {
                this.table[x][y] = id;

                System.out.print("   " + table[row][col] + "        ");
            }
            System.out.println();
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------");
    }

    void showMenu() {
        System.out.println("\t\t---------------------MENU------------------------");
        System.out.println("\t\t1.idly      \tRs.30        \t\t6.uttapam   \tRs.30");
        System.out.println("\t\t2.wada      \tRs.35        \t\t7.bonda     \tRs.25");
        System.out.println("\t\t3.dosa      \tRs.40        \t\t8.tea       \tRs.10");
        System.out.println("\t\t4.poori     \tRs.30        \t\t9.coffee    \tRs.15");
        System.out.println("\t\t5.Pesarattu \tRs.25        \t\t10.Quit     \tRs.0");
        System.out.println("\t\t--------------------------------------------------");
    }
}

