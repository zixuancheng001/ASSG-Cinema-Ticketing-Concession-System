import java.io.*;

public class Hall {
	
    // data fields
    private int hallNo;
    private String[][] seats;
    private boolean[][] available;
    private String movieId;
    private String movieTitle;
    private String date;
    private String time;
    private String bookingFile;
	private double price;
	
    // constructor no argument
    public Hall() {
        this(1, "default");
    }

    // constructor
    public Hall(int hallNo, String movieId) {
        this.hallNo = hallNo;
        this.movieId = movieId;
        this.bookingFile = "SeatBooking.txt";

        seats = new String[5][6];
        available = new boolean[5][6];

        initSeats();
        loadBookedSeats(movieId);
    }

    private void initSeats() {
        char row = 'A';
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = row + String.valueOf(j + 1);
                available[i][j] = true;
            }
            row++;
        }
    }

    // set method
    public void setMovieInfo(String movieTitle, String date, String time, double price) {
        this.movieTitle = movieTitle;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    // get method
    
    public String getHall() {
	    return "Hall " + hallNo;
	}

    public double getPrice() {
	    return price;
	}
	
    public int getHallNo() {
        return hallNo;
    }

    public String getMovieName() {
        return movieTitle;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
    
    public int getBookedSeatCount() {
	    int count = 0;
	    for (int i = 0; i < available.length; i++) {
	        for (int j = 0; j < available[i].length; j++) {
	            if (!available[i][j]) {
	                count++;
	            }
	        }
	    }
	    return count;
	}
	
	public String[] getBookedSeats() {
	    int count = getBookedSeatCount();
	    String[] booked = new String[count];
	    int index = 0;
	    for (int i = 0; i < seats.length; i++) {
	        for (int j = 0; j < seats[i].length; j++) {
	            if (!available[i][j]) {
	                booked[index++] = seats[i][j];
	            }
	        }
	    }
	    return booked;
	}

    // display seat
    public void displaySeats() {
        System.out.println("\nHall " + hallNo);
        System.out.println("-----------screen-----------");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (available[i][j]) {
                    System.out.print("[" + seats[i][j] + "] ");
                } else {
                    System.out.print("[N/A] ");
                }
            }
            System.out.println();
        }
    }

    // booking seat
    public boolean bookSeat(String seatNo) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equalsIgnoreCase(seatNo)) {
                    if (!available[i][j]) {
                        System.out.println("Seat already booked.");
                        return false;
                    }

                    boolean saved = saveSeatToFile(movieId, hallNo, seatNo);

                    if (!saved) {
                        System.out.println("Booking could not be saved.");
                        return false;
                    }

                    available[i][j] = false;
                    System.out.println("Seat " + seatNo + " booked!");
                    return true;
                }
            }
        }

        System.out.println("Seat does not exist.");
        return false;
    }

    // unbook seat
    public void unbookSeat(String seatNo) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equalsIgnoreCase(seatNo)) {
                    if (available[i][j]) {
                        System.out.println("Seat is not booked.");
                        return;
                    }

                    boolean removed = removeSeatFromFile(movieId, hallNo, seatNo);

                    if (!removed) {
                        System.out.println("Seat cancellation could not be saved.");
                        return;
                    }

                    available[i][j] = true;
                    System.out.println("Seat " + seatNo + " cancelled.");
                    return;
                }
            }
        }

        System.out.println("Seat does not exist.");
    }

    // save booking to file
    private boolean saveSeatToFile(String movieId, int hallNo, String seatNo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(bookingFile, true))) {
            bw.write(movieId + "," + hallNo + "," + seatNo);
            bw.newLine();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // load all bookings from file
    private String[][] loadSeatsFromFile() {
        // count valid lines
        int count = 0;
        try {
            File f = new File(bookingFile);
            if (!f.exists()) return new String[0][0];

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split(",").length == 3) count++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new String[0][0];
        }

        // fill array
        String[][] list = new String[count][3];
        int index = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(bookingFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    list[index][0] = data[0];
                    list[index][1] = data[1];
                    list[index][2] = data[2];
                    index++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    // remove a booking from file
    private boolean removeSeatFromFile(String movieId, int hallNo, String seatNo) {
        File inputFile = new File(bookingFile);
        File tempFile = new File(bookingFile + ".tmp");
        boolean removed = false;

        if (!inputFile.exists()) {
            return false;
        }

        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length != 3) {
                    continue;
                }

                String fileMovie = data[0];
                int fileHall;

                try {
                    fileHall = Integer.parseInt(data[1]);
                } catch (NumberFormatException e) {
                    continue;
                }

                String fileSeat = data[2];

                if (fileMovie.equals(movieId) && fileHall == hallNo && fileSeat.equalsIgnoreCase(seatNo)) {
                    removed = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            tempFile.delete();
            return false;
        }

        if (!removed) {
            tempFile.delete();
            return false;
        }

        if (inputFile.exists() && !inputFile.delete()) {
            System.out.println("Error: Could not update booking file.");
            tempFile.delete();
            return false;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Error: Could not update booking file.");
            return false;
        }

        return true;
    }

    // load booked seats
    private void loadBookedSeats(String movieId) {
        String[][] list = loadSeatsFromFile();

        for (String[] data : list) {
            if (data.length != 3) {
                continue;
            }

            String fileMovie = data[0];
            int hall;

            try {
                hall = Integer.parseInt(data[1]);
            } catch (NumberFormatException e) {
                continue;
            }

            String seatNo = data[2];

            if (fileMovie.equals(movieId) && hall == hallNo) {
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        if (seats[i][j].equalsIgnoreCase(seatNo)) {
                            available[i][j] = false;
                        }
                    }
                }
            }
        }
    }

    // clear all bookings
    public void clearBooking() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(bookingFile));
            bw.write("");
            bw.close();

            System.out.println("All seat bookings cleared.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Staff function: disable a seat
    public boolean disableSeat(String seatNo) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equalsIgnoreCase(seatNo)) {
                    if (!available[i][j]) {
                        System.out.println("Seat is already unavailable.");
                        return false;
                    }

                    available[i][j] = false;
                    System.out.println("Seat " + seatNo + " has been disabled.");
                    return true;
                }
            }
        }

        System.out.println("Seat does not exist.");
        return false;
    }

    // Staff function: restore seat to available
    public boolean enableSeat(String seatNo) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equalsIgnoreCase(seatNo)) {
                    if (available[i][j]) {
                        System.out.println("Seat is already available.");
                        return false;
                    }

                    available[i][j] = true;
                    System.out.println("Seat " + seatNo + " has been enabled.");
                    return true;
                }
            }
        }

        System.out.println("Seat does not exist.");
        return false;
    }
}
