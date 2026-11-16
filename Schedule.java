import java.io.*;
import java.util.Scanner;

public class Schedule {

    //DATA
    private String scheduleID;
    private String movieID;
    private String date;
    private String time;
    private int hallNo;

    //STORAGE 
    private static final String FILE_NAME = "ScheduleData.txt";
    private static Schedule[] schedules = new Schedule[100];
    private static int count = 0;

    //CONSTRUCTOR
    public Schedule(String scheduleID, String movieID, String date, String time, int hallNo) {
        this.scheduleID = scheduleID;
        this.movieID = movieID;
        this.date = date;
        this.time = time;
        this.hallNo = hallNo;
    }
    public Schedule() {
    }

    // ================= GETTER =================
    public String getScheduleID() { 
    	return scheduleID; 
    }
    public String getMovieID() { 
    	return movieID; 
    }
    public String getDate() { 
    	return date; 
    }
    public String getTime() {
    	 return time; 
    }
    public int getHallNo() { 
    	return hallNo; 
    }

    // ================= DISPLAY =================
    public void display() {
	    Movie movie = Movie.findMovie(movieID);
	    String title = (movie != null) ? movie.getTitle() : "Unknown";
	
	    System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-6d |\n",
	        scheduleID, title, date, time, hallNo);
	}

    public String toFileString() {
        return scheduleID + "," + movieID + "," + date + "," + time + "," + hallNo;
    }

   
    // ================= STATIC MANAGER =========================

    // Load
    public static void load() {
        try {
            File f = new File(FILE_NAME);
            if (!f.exists())
            	 return;

            Scanner sc = new Scanner(f);
            count = 0;

            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                schedules[count++] = new Schedule(
                        d[0], d[1], d[2], d[3], Integer.parseInt(d[4])
                );
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading schedule file.");
        }
    }

    //Save
    public static void save() {
        try {
            PrintWriter pw = new PrintWriter(FILE_NAME);
            for (int i = 0; i < count; i++) {
                pw.println(schedules[i].toFileString());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving schedule file.");
        }
    }

    //Generate ID
    public static String generateNextScheduleID() {
        int max = 0;

        for (int i = 0; i < count; i++) {
            String id = schedules[i].getScheduleID();

            if (id.matches("S\\d+")) {
                int num = Integer.parseInt(id.substring(1));
                if (num > max) max = num;
            }
        }
        return "S" + (max + 1);
    }

    //Add Schedule(Movie)
    public static void addSchedule() {
        Scanner sc = CinemaSystem.Input.scanner;
        Movie.loadMovies();

        //(from Movie)
        Movie.displayAllMovies();

        System.out.print("Enter Movie ID: ");
        String movieID = sc.nextLine();

        // movie check
        if (Movie.findMovie(movieID) == null) {
            System.out.println("Invalid Movie ID!");
            return;
        }

        System.out.print("Enter Date (yyyy/mm/dd): ");
        String date = sc.nextLine();

        System.out.print("Enter Time (e.g. 14:00pm): ");
        String time = sc.nextLine();

        int hall;
        while (true) {
            System.out.print("Enter Hall (1-8): ");
            String input = sc.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("Numbers only!");
                continue;
            }

            hall = Integer.parseInt(input);

            if (hall < 1 || hall > 8) {
                System.out.println("Hall must be 1-8!");
            } else break;
        }

        String id = generateNextScheduleID();

        schedules[count++] = new Schedule(id, movieID, date, time, hall);

        save();
        System.out.println("Schedule Added!");
    }

    //Display All
    public static void displayAll() {
	    load();
	    Movie.loadMovies();
	
	    if (count == 0) {
	        System.out.println("No schedules.");
	        return;
	    }
	
	    System.out.println("+------------+----------------------+------------+----------+--------+");
	    System.out.println("| ScheduleID | Movie Title          | Date       | Time     | Hall   |");
	    System.out.println("+------------+----------------------+------------+----------+--------+");
	
	    for (int i = 0; i < count; i++) {
	        if (schedules[i] != null) {
	            schedules[i].display();
	        }
	    }
	
	    System.out.println("+------------+----------------------+------------+----------+--------+");
	}

    //Display by Movie
    public static void displayByMovie(String id) {
    	load();
	    Movie.loadMovies();
	    boolean found = false;
	
	    System.out.println("+------------+----------------------+------------+----------+--------+");
	    System.out.println("| ScheduleID | Movie Title          | Date       | Time     | Hall   |");
	    System.out.println("+------------+----------------------+------------+----------+--------+");
	
	    for (int i = 0; i < count; i++) {
	        if (schedules[i] == null) continue;
	
	        String mID = schedules[i].getMovieID();
	
	        if (mID != null && mID.equalsIgnoreCase(id)) {
	            Movie movie = Movie.findMovie(mID);
	            String title = (movie != null) ? movie.getTitle() : "Unknown";
	            System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-6s |\n",
	                schedules[i].getScheduleID(), title,
	                schedules[i].getDate(), schedules[i].getTime(), schedules[i].getHallNo());
	            found = true;
	        }
	    }
	
	    System.out.println("+------------+----------------------+------------+----------+--------+");
	
	    if (!found) {
	        System.out.println("No schedules for this movie.");
	    }
	}

    // Menu
    public static void menu() {
        Scanner sc = CinemaSystem.Input.scanner;
        int choice = -1;

        load();

        do {
        	System.out.println("\n+------------------------------------------------------------------------+");
            System.out.println("|                               Schedule Menu                             |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.println("|   1   |   Add Schedule                                                  |");
            System.out.println("|   2   |   Display All Schedule                                          |");
            System.out.println("|   3   |   Display by Movie                                              |");
            System.out.println("|   0   |   EXIT                                                          |");
            System.out.println("+------------------------------------------------------------------------+");

            System.out.print("Enter choice: ");
            String input = sc.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("Invalid!");
                continue;
            }

            choice = Integer.parseInt(input);

            switch (choice) {
                case 1: addSchedule(); break;
                case 2: displayAll(); break;
                case 3: System.out.print("Enter Movie ID: ");
					    String mid = sc.nextLine();
					    displayByMovie(mid);
					    break;
                case 0: System.out.println("Exit."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
      public static Schedule getScheduleAt(int index) {
            if (index >= 0 && index < count) {
                return schedules[index];
            }
            return null;
        }
}