import java.util.*;
import java.sql.*;

public class TelefonListen {

    //Nummeret på den studerende er nøglen, på den måde kan vi have flere af samme navn, men kun én af samme nummer
    private Map<Integer, String> telefonListe;
    private Scanner scanner = new Scanner(System.in);
    public TelefonListen(){
        telefonListe = new HashMap<>();
    }

    public Map<Integer, String> getListen(){
        return telefonListe;
    }

    public void tilfoejPerson() throws SQLException {
        System.out.println("Indtast navn:");
        String navn = scanner.nextLine();
        System.out.println("Indtast nr:");
        int nummer = scanner.nextInt();
        scanner.nextLine();
        telefonListe.put(nummer,navn);
        SQLDriver.saveToTable(telefonListe);
    }

    public void printNavne(){
        telefonListe.values().forEach(System.out::println);
    }

    public void printNumre(){
        telefonListe.keySet().forEach(System.out::println);
    }

    public void printListe(){
        List<Map.Entry<Integer,String>> list = new ArrayList<>(telefonListe.entrySet());
        list.sort(Map.Entry.comparingByValue());
        list.forEach(entry -> System.out.println("Navn: " + entry.getValue() + " Nummer: " + entry.getKey()));
    }

    public void sletFraListen() throws SQLException {
        System.out.println("Indtast navnet på den studerende du vil slette: ");
        String navn = scanner.nextLine();
        boolean found = false;
        for (Map.Entry<Integer, String> entry : telefonListe.entrySet()) {
                if(entry.getValue().equalsIgnoreCase(navn)){
                int nr = entry.getKey();
                telefonListe.remove(nr,navn);
                found = true;
                SQLDriver.deleteFromTable(nr,navn);
                break;
            }
        }
        if(!found){
            System.out.println(navn + " blev ikke fundet i listen.");
        }
    }

    public void soegEfterNavn() {
        System.out.println("Indtast navnet på den studerende du vil søge efter:");
        String navn = scanner.nextLine();
        boolean found = false;
        for(Map.Entry<Integer,String> entry : telefonListe.entrySet()){
            if(entry.getValue().toLowerCase().contains(navn.toLowerCase())){
                System.out.println("Navn: " + entry.getValue());
                System.out.println("Nummer: " + entry.getKey());
                found = true;
            }
        }
        if(!found){
            System.out.println(navn + " blev ikke fundet i listen.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Telefonliste:\n");

        List<Map.Entry<Integer, String>> list = new ArrayList<>(telefonListe.entrySet());
        list.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Integer, String> entry : list) {
            sb.append("Navn: ").append(entry.getValue())
                    .append(", Nummer: ").append(entry.getKey())
                    .append("\n");
        }
        return sb.toString();
    }

}
