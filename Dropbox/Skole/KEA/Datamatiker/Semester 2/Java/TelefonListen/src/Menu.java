import java.sql.SQLException;
import java.util.*;


public class Menu {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);  
        TelefonListen tl = new TelefonListen();
        SQLDriver.getTable(tl.getListen());

        System.out.println(" __      __     _      _____  ______ __  __          _____   _____    \n" +
                " \\ \\    / /\\   | |    |  __ \\|  ____|  \\/  |   /\\   |  __ \\ / ____|   \n" +
                "  \\ \\  / /  \\  | |    | |  | | |__  | \\  / |  /  \\  | |__) | (___     \n" +
                "   \\ \\/ / /\\ \\ | |    | |  | |  __| | |\\/| | / /\\ \\ |  _  / \\___ \\    \n" +
                "    \\  / ____ \\| |____| |__| | |____| |  | |/ ____ \\| | \\ \\ ____) |   \n" +
                "  ___\\/_/ ___\\_\\______|_____/|______|_|__|_/_/  _ \\_\\_|  \\_\\_____/___ \n" +
                " |__   __|  ____| |    |  ____|  ____/ __ \\| \\ | |  _ \\ / __ \\ / ____|\n" +
                "    | |  | |__  | |    | |__  | |__ | |  | |  \\| | |_) | |  | | |  __ \n" +
                "    | |  |  __| | |    |  __| |  __|| |  | | . ` |  _ <| |  | | | |_ |\n" +
                "    | |  | |____| |____| |____| |   | |__| | |\\  | |_) | |__| | |__| |\n" +
                "    |_|  |______|______|______|_|    \\____/|_| \\3_|____/ \\____/ \\_____|\n" +
                "                                                                      \n" +
                "                                                                      \n" +
                "                       ,..........   ..........,\n" +
                "                   ,..,'          '.'          ',..,\n" +
                "                  ,' ,'            :            ', ',\n" +
                "                 ,' ,'             :             ', ',\n" +
                "                ,' ,'              :              ', ',\n" +
                "               ,' ,'............., : ,.............', ',\n" +
                "              ,'  '............   '.'   ............'  ',\n" +
                "               '''''''''''''''''';''';''''''''''''''''''\n" +
                "                                  '''\n");

        while(true){
            System.out.println(
                            "                     1. Tilføj ny studerende \n\n"+
                            "                     2. Se listen over numre og navne \n\n"+
                            "                     3. Se alle navne på listen\n\n"+
                            "                     4. Se alle numre på listen\n\n" +
                            "                     5. Find nummer efter navn\n\n" +
                            "                     6. Slet fra listen\n\n" +
                            "                     0. Gem listen og afslut program\n\n");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    tl.tilfoejPerson();
                    break;
                case 2:
                    tl.printListe();
                    break;
                case 3:
                    tl.printNavne();
                    break;
                case 4:
                    tl.printNumre();
                    break;
                case 5:
                    tl.soegEfterNavn();
                    break;
                case 6:
                    tl.sletFraListen();
                    break;
                case 0:
                    SQLDriver.saveToTable(tl.getListen());
                    return;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.\n");
            }
        }
    }
}