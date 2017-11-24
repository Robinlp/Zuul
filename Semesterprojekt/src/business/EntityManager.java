package business;

// IMPORTS
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Entity Manager class. Keeps track of all the entities currently in the game.
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class EntityManager {

    // Containers for the different type entities.
    private ArrayList<Item> itemlist = new ArrayList<>();
    private Player player;
    private ArrayList<Student> studentlist = new ArrayList<>();
    private ArrayList<Furniture> furniturelist = new ArrayList<>();
    private static HashMap<String, Entity> entityTypes = new HashMap<>();
    private static HashMap<String, String[][]> entityCSV = new HashMap<>();
    private HashMap<Room, Entity[][]> entityArray = new HashMap<>();
    private static RoomManager rm;
    private static HashMap<String, Room> roomlist;

    public EntityManager(RoomManager rm) {
        this.rm = rm;
        this.roomlist = rm.getRoomlist();
        loadPresetEntities();
        String playerName = "Jason";
        addEntitiesToRooms(playerName);
    }

    // ENTITY MANAGMENT METHODS
    public void addStudent(Student s) {
        studentlist.add(s);
    }

    public void removeStudent(Student s) {
        studentlist.remove(s);
    }

    public void addFurniture(Furniture f) {
        furniturelist.add(f);
    }

    public void removeFurniture(Furniture f) {
        furniturelist.remove(f);
    }

    public void addItem(Item i) {
        itemlist.add(i);
    }

    public void removeItem(Item i) {
        itemlist.remove(i);
    }

    // SETTERS & GETTERS
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Student> getStudentList() {
        return studentlist;
    }

    public void setStudentList(ArrayList<Student> studentlist) {
        this.studentlist = studentlist;
    }

    public ArrayList<Furniture> getFurnitureList() {
        return furniturelist;
    }

    public void setFurnitureList(ArrayList<Furniture> furniturelist) {
        this.furniturelist = furniturelist;
    }

    public ArrayList<Item> getItemList() {
        return itemlist;
    }

    public void setItemList(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    // Reads tile IDs from csv file.
    public static void loadPresetEntities() {
        String csvFile = "res/presets/roomEntities.csv";
        BufferedReader fileReader = null;
        String line = "";
        String splitBy = ",";
        String roomName = "";
        int lineNo = 0;

        try {
            fileReader = new BufferedReader(new FileReader(csvFile));
            while ((line = fileReader.readLine()) != null) {
                if (!"".equals(line)) {
                    line = line.trim(); // Trim leading and tailing whitespaces.
                    String[] segments = line.split(splitBy);
                    // If reached new room in csv file, new entry in hashmap.
                    if ("-".equals(segments[0])) {
                        lineNo = 0;
                        roomName = segments[1].trim();
                        String[][] idList = new String[10][10];
                        entityCSV.put(roomName, idList);
                        // Otherwise assign ID to grid position in hashmap value[][] 
                    } else {
                        for (int i = 0; i < segments.length; i++) {
                            if (!"".equals(segments[i].trim())) {
                                entityCSV.get(roomName)[lineNo][i] = segments[i].trim();
                            }
                        }
                        lineNo++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* goes through all rooms and each grid position and assigns an entity if
       preset has assigned an entity to be there. */
    public static void addEntitiesToRooms(String playerName) {
        Entity entity;
        String IDnum;
        for (String name : roomlist.keySet()) {
            for (int i = 0; i < entityCSV.get(name).length; i++) {
                for (int j = 0; j < entityCSV.get(name)[i].length; j++) {
                    // ID number in the specific grid position.
                    IDnum = entityCSV.get(name)[i][j];
                    
                    // Switch cases for what type of entity it is
                    switch (IDnum.toUpperCase()) {
                        // Instance of player to be added.
                        case "ID50":
                            entity = new Player(j,
                                    i,
                                    playerName,
                                    rm.getCurrentRoom());
                            break;
                            
                        // Instance of white t-shirt, brunette student.
                        case "ID51":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student1.png");
                            break;
                            
                        // Instance of red t-shirt, brunette student.
                        case "ID52":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    true,
                                    "/textures/student2.png");
                            break;
                            
                        // Instance of green t-shirt, brunette student.
                        case "ID53":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student3.png");
                            break;
                            
                        // Instance of white t-shirt, blond student.
                        case "ID54":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student4.png");
                            break;
                            
                        // Instance of red t-shirt, blond student.
                        case "ID55":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    true,
                                    "/textures/student5.png");
                            break;
                            
                        // Instance of green t-shirt, blond student.
                        case "ID56":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student6.png");
                            break;
                            
                        // Instance of white t-shirt, black student.
                        case "ID57":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student7.png");
                            break;
                            
                        // Instance of red t-shirt, black student.
                        case "ID58":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    true,
                                    "/textures/student8.png");
                            break;
                            
                        // Instance of green t-shirt, black student.
                        case "ID59":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student9.png");
                            break;
                            
                        // Instance of white t-shirt, asian student.
                        case "ID60":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student10.png");
                            break;
                            
                        // Instance of red t-shirt, asian student.
                        case "ID61":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    true,
                                    "/textures/student11.png");
                            break;
                            
                        // Instance of green t-shirt, asian student.
                        case "ID62":
                            entity = new Student(j,
                                    i,
                                    rm.getCurrentRoom(),
                                    false,
                                    "/textures/student12.png");
                            break;
                            
                        default:
                            entity = null;
                    }
                    
                    // Call setTile function and place tile object.
                    roomlist.get(name).setEntity(i, j, entity);
                }
            }
        }
    }
    
    // LOAD & SAVE METHODS
    
    public void saveGame() {
        makeSaveFolder();
        saveItems();
        savePlayers();
        saveStudents();
        saveFurniture();
        saveInventory();
    }

    public void loadGame() {
        loadItems();
        loadPlayers();
        loadStudents();
        loadFurniture();
        loadInventory();
    }

    public void makeSaveFolder(){
        File folder = new File(System.getProperty("user.home")+"\\Documents\\zuul");
            if(!folder.exists()){
                folder.mkdirs();
            }
    }
    public void saveItems() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveItemsTest.txt")));

            for (Item item : itemlist) {
                fileWriter.append(item.getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(item.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(item.getY()));
                fileWriter.append(",");
                fileWriter.append(item.getCurrentRoom().getName());
                fileWriter.append("\n");

                System.out.println("Saved items");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE ITEMS... "
                    + "please check the save directory in the code.");
        }
    }

    public void saveInventory() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveInventoryTest.txt")));
            for (Item item : player.inventory().getInventory()) {
                fileWriter.append(item.getName());
            }
            fileWriter.append("\n");
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE INVENTORY... "
                    + "please check the save directory in the code.");
        }
    }

    public void savePlayers() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SavePlayersTest.txt")));

            
            fileWriter.append(String.valueOf(player.getX()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(player.getY()));
            fileWriter.append(",");
            fileWriter.append(player.getName());
            fileWriter.append(",");
            fileWriter.append(player.getCurrentRoom().getName());
            fileWriter.append("\n");

            System.out.println("Saved Player");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE PLAYERS... "
                    + "please check the save directory in the code.");
        }
    }

    public void saveStudents() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveStudentTest.txt")));
            for (Student student : studentlist) {
                fileWriter.append(String.valueOf(student.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(student.getY()));
                fileWriter.append(",");
                fileWriter.append(student.getCurrentRoom().getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(
                        student.getHasQuestionToPlayer()));
                fileWriter.append("\n");

                System.out.println("Saved Students");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE STUDENTS... "
                    + "please check the save directory in the code.");
        }
    }

    public void saveFurniture() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveFurnitureTest.txt")));

            for (Furniture furniture : furniturelist) {
                fileWriter.append(String.valueOf(furniture.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(furniture.getY()));
                fileWriter.append(",");
                fileWriter.append(furniture.getFurnitureName());
                fileWriter.append(",");
                fileWriter.append(furniture.getCurrentRoom().getName());
                fileWriter.append("\n");
                System.out.println("Saved Furniture");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE FURNITURE... "
                    + "please check the save directory in the code.");
        }
    }

    public void loadItems() {
        try {
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveItemsTest.txt")));
            itemlist.clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "Adderal":
                            Adderall d = new Adderall(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(d);
                            break;
                        case "Coffee":
                            Coffee c = new Coffee(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(c);
                            break;
                        case "Assignment":
                            Assignment a = new Assignment(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(a);
                            break;
                        case "Key":
                            Key k = new Key(Integer.parseInt(
                                    tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(k);
                            break;
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    rm.getCurrentRoom().getExit(tokens[2]));
                            itemlist.add(e);
                            break;
                        default:
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD ITEMS... "
                    + "please check the save directory in the code.");
        }
    }

    public void loadInventory() {
        try {
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveInventoryTest.txt")));
            player.inventory().getInventory().clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "Adderal":
                            Adderall d = new Adderall(Integer.parseInt(
                                    tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(d);
                            break;
                        case "Coffee":
                            Coffee c = new Coffee(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(c);
                            break;
                        case "Assignment":
                            Assignment a = new Assignment(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(a);
                            break;
                        case "Key":
                            Key k = new Key(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(k);
                            break;
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    rm.getCurrentRoom().getExit(tokens[2]));
                            player.inventory().addItem(e);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... "
                    + "please check the save directory in the code.");
        }
    }

    public void loadPlayers() {
        try {
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SavePlayersTest.txt")));
            this.player = null;
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    player = new Player(Integer.parseInt(tokens[0]),
                            Integer.parseInt(tokens[1]),
                            tokens[2],
                            (Room) rm.getRoomlist().get(tokens[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... "
                    + "please check the save directory in the code.");
        }
    }

    public void loadStudents() {
        try {
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveStudentsTest.txt")));
            studentlist.clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    Student student = new Student(Integer.parseInt(tokens[0]),
                            Integer.parseInt(tokens[1]),
                            (Room) rm.getRoomlist().get(tokens[2]),
                            Boolean.parseBoolean(tokens[3]));
                    studentlist.add(student);
                }

            }
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD STUDENTS... "
                    + "please check the save directory in the code.");

        }
    }

    public void loadFurniture() {
        try {
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveItemsTest.txt")));
            furniturelist.clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "Chair":
                            Chair c = new Chair(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            furniturelist.add(c);
                            break;
                        case "Table":
                            Table t = new Table(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]),
                                    true,
                                    2);
                            furniturelist.add(t);
                            break;
                        default:
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD FURNITURE... "
                    + "please check the save directory in the code.");
        }
    }
}
