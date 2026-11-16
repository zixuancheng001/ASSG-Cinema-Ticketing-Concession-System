import java.io.*;
import java.util.Scanner;

public class Movie {

    // DATA 
    private String movieID;
    private String title;
    private String genre;
    private int duration;
    private String ageRating;
    private String category;

    // STORAGE 
    private static final String FILE_NAME = "MovieData.txt";
    private static Movie[] movies = new Movie[100];
    private static int count = 0;

    //CONSTRUCTOR 
    public Movie(String movieID, String title, String genre, int duration,String ageRating, String category) {
        this.movieID = movieID;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ageRating = ageRating;
        this.category = category;
    }

    // GETTER 
    public String getMovieID() 	{ 
    	return movieID; 
   	}
    public String getTitle() {
    	 return title; 
    }
    public String getGenre() { 
    	return genre; 
    }
    public int getDuration() { 
    	return duration; 
    }
    public String getAgeRating() { 
    	return ageRating; 
    }
    public String getCategory() {
    	 return category; 
    }

    // ================= SETTER =================
    public void setTitle(String title) {
    	 this.title = title; 
    }
    public void setGenre(String genre) {
    	 this.genre = genre;
     }
    public void setDuration(int duration) {
    	 this.duration = duration;
     }
    public void setAgeRating(String ageRating) {
    	 this.ageRating = ageRating; 
    }
    public void setCategory(String category) {
    	 this.category = category; 
    }

    //  DISPLAY 
    public void displayMovie() {
	    System.out.println("+-----------+--------------------------------+");
	    System.out.println("|Movie ID   | " + getMovieID());
	    System.out.println("|Title      | " + getTitle());
	    System.out.println("|Genre      | " + getGenre());
	    System.out.println("|Duration   | " + getDuration() + " mins");
	    System.out.println("|Age Rating | " + getAgeRating());
	    System.out.println("|Category   | " + getCategory());
	    System.out.println("+-----------+--------------------------------+");
	}

    public String toFileString() {
        return movieID + "," + title + "," + genre + "," + duration + "," + ageRating + "," + category;
    }

    
    // ================= STATIC MANAGER =========================


    //Load
    public static void loadMovies() {
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) 
            	return;

            Scanner sc = new Scanner(f);
            count = 0;

            while (sc.hasNextLine()) {
			    String line = sc.nextLine().trim();
			    if (line.isEmpty()) continue;
			    
			    String[] d = line.split(",");
			    if (d.length < 6) continue;
			    
			    movies[count++] = new Movie(
			        d[0], d[1], d[2],
			        Integer.parseInt(d[3]),
			        d[4], d[5]
			    );
			}
			sc.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    //Save
    public static void saveMovies() {
        try {
            PrintWriter pw = new PrintWriter(FILE_NAME);
            for (int i = 0; i < count; i++) {
                pw.println(movies[i].toFileString());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    //Generate ID
    public static String generateNextMovieID() {
        int max = 0;

        for (int i = 0; i < count; i++) {
            String id = movies[i].getMovieID();

            if (id.matches("M\\d{3}")) {
                int num = Integer.parseInt(id.substring(1));
                if (num > max) max = num;
            }
        }
        return String.format("M%03d", max + 1);
    }

    //Add Movie
    public static void addMovie() {
        Scanner sc = CinemaSystem.Input.scanner;

        String id = generateNextMovieID();
        System.out.println("Generated ID: " + id);

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();

        int duration;
        while (true) {
            System.out.print("Enter Duration: ");
            String input = sc.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Numbers only!");
                continue;
            }
            duration = Integer.parseInt(input);
            if (duration > 0) break;
        }

        System.out.print("Enter Age Rating: ");
        String rating = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        movies[count++] = new Movie(id, title, genre, duration, rating, category);

        saveMovies();
        System.out.println("Movie Added!");
    }

    //Display All
    public static void displayAllMovies() {
    	loadMovies();
        if (count == 0) {
            System.out.println("No movies.");
            return;
        }

        for (int i = 0; i < count; i++) {
            movies[i].displayMovie();
        }
    }

    //Find
    public static Movie findMovie(String id) {
        for (int i = 0; i < count; i++) {
            if (movies[i].getMovieID().equalsIgnoreCase(id)) {
                return movies[i];
            }
        }
        return null;
    }

    //Edit
    public static void editMovie() {
        Scanner sc = CinemaSystem.Input.scanner;

        System.out.print("Enter Movie ID: ");
        String id = sc.nextLine();

        Movie m = findMovie(id);
        if (m == null) {
            System.out.println("Not found!");
            return;
        }

        System.out.print("New Title: ");
        String input = sc.nextLine();
        if (!input.isEmpty()) m.setTitle(input);

        System.out.print("New Genre: ");
        input = sc.nextLine();
        if (!input.isEmpty()) m.setGenre(input);

        System.out.print("New Duration: ");
        input = sc.nextLine();
        if (!input.isEmpty()) m.setDuration(Integer.parseInt(input));

        saveMovies();
        System.out.println("Updated!");
    }

    //Delete
    public static void deleteMovie() {
        Scanner sc = CinemaSystem.Input.scanner;

        System.out.print("Enter Movie ID: ");
        String id = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (movies[i].getMovieID().equalsIgnoreCase(id)) {

                for (int j = i; j < count - 1; j++) {
                    movies[j] = movies[j + 1];
                }

                movies[--count] = null;
                saveMovies();

                System.out.println("Deleted!");
                return;
            }
        }

        System.out.println("Movie not found!");
    }

    //Menu
    public static void menu() {
        Scanner sc = CinemaSystem.Input.scanner;
        int choice = -1;

        loadMovies();

        do {
        	System.out.println("\n+------------------------------------------------------------------------+");
            System.out.println("|                               Movie Menu                                |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.println("|   1   |   Add Movies                                                    |");
            System.out.println("|   2   |   Edit Movies                                                   |");
            System.out.println("|   3   |   Delete Movies                                                 |");
            System.out.println("|   4   |   Show all Movies                                               |");
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
                case 1: addMovie(); break;
                case 2: editMovie(); break;
                case 3: deleteMovie(); break;
                case 4: displayAllMovies(); break;
                case 0: System.out.println("Exit."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}