import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * CalendarModel class is the model component in MVC of the simple calendar program. It handles all the data related processes.
*/
public class CalendarModel {

    private final String FILENAME = "event.txt";
    private ArrayList<Event> eventList;

    public CalendarModel() {
        createFile();
    }

    /**
     * SetupEvent method will put a new event in to file
     * @param Event e
     * @return void
    */
    public void SetupEvent(Event e){
 
        //read from file :
        eventList = readFromFile(FILENAME);

        // iteritivly check each line => Event
        if(hasNoConflict(e)){
            writeToFile(e);
        }
    }

    private boolean hasNoConflict(Event e){
        boolean conflickFlag = false;
        for(int i=0; i< eventList.size(); i++){
            conflickFlag = e.isNOTConflictWith(eventList.get(i));
            if(conflickFlag == false){
                System.out.println("has conflick");
                return conflickFlag;
            }
        }
        return true;
    }

    private ArrayList<Event> readFromFile(String fileName){
        ArrayList<Event> newEventList = new ArrayList<Event>();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line!=null) {

                String s[] = line.split(" ");
                int cut = s[0].length() + 1 + s[1].length() + 1;

                long startTime = Long.valueOf(s[0]);
                long endTime = Long.valueOf(s[1]);
                String eventString = line.substring(cut, line.length()-1);
         
                Event newEvent = new Event(startTime, endTime, eventString);
            
                newEventList.add(newEvent);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        

        return newEventList;
    }

    // create a file if no file exists.
    private void createFile() {
        try {
            File newFile = new File(FILENAME);
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException err) {
            System.out.println("An error occurred.");
            err.printStackTrace();
        }
    }

    public void writeToFile(Event e) {

        try {
            FileWriter myWriter = new FileWriter(FILENAME, true);
            myWriter.write(String.valueOf(e.getStartTime()) + " " + String.valueOf(e.getEndTime()) + " "
                    + e.getEventString() + "\n");
            myWriter.close();
            System.out.println("Sucessfully wrote to the file");
        } catch (IOException err) {
            System.out.println("An error occurred");
            err.printStackTrace();
        }
    }


// for test purpose:
    public void printEvent(){
        for(int i=0; i<eventList.size(); i++){
            System.out.println(eventList.get(i).getStartTime() + " " +eventList.get(i).getEndTime() + " " + eventList.get(i).getEventString());
        }
    }
}