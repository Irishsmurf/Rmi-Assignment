import Clinic.*;
import Clinic.Clinic;
import com.paddez.distro.AuthService;
import com.paddez.distro.Day;
import com.paddez.distro.Time;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

//WSDL
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

class RmiClient
{ 
    // "obj" is the reference of the remote object

    private Clinic obj = null; 

    //Challange the Clinic to see ifs running.
    public boolean connectable()
    {
        try{    
            System.out.println(obj.connectable() + " loop");
            return obj.connectable();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;        
        }
    }

    public String cancel(int hour, int minute, int day)
    {
        return obj.cancelAppointment(hour, minute, day);
    }

    //Return a string containing the Information of the passed arguements. (Returns Appointment Type.)
    public String getInfo(int hour, int minute, int day)
    {        
        try{
            return obj.getInfo(hour, minute, day);
        } catch (Exception e)
        {
            System.err.println("RmiClient exception: " + e); 
            e.printStackTrace(); 
        }

        return null;  
    }


    //Returns a List of Appointments from a given day.
    public ObservableList<RmiClientUI.Appointment> getAppointments(int day)
    {
        try{
            List<String> slots = obj.getAppointments(day);
            ObservableList<RmiClientUI.Appointment> ret = FXCollections.observableArrayList();
            for (String tmp: slots ) {
                String[] delim = tmp.split(" ");
                ret.add(new RmiClientUI.Appointment(delim[3], delim[0], delim[1], Integer.parseInt(delim[2])));
            }

            return ret;
        }
        catch(Exception e){e.printStackTrace();}

        return null;

    }

    //Returns a List of all the appointments within the Clinic.
    public ObservableList<RmiClientUI.Appointment> getAppointmentsWeek()
    {
        try{
            List<String> slots = obj.getAppointments(7);
            ObservableList<RmiClientUI.Appointment> ret = FXCollections.observableArrayList();
            for(String tmp : slots)
            {
                String[] delim = tmp.split(" ");
                ret.add(new RmiClientUI.Appointment(delim[3], delim[0], delim[1], Integer.parseInt(delim[2])));
            }

            return ret;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    //Send arguements to Clinic to add to the given time and day.
    public boolean addAppointment(int type, int hour, int minute, int day, String name)
    {
        try{
            return obj.addAppointment(type, hour, minute, day, name);
        } catch (Exception e){
            System.err.println("RmiClient exception: " + e); 
            e.printStackTrace(); 
        }

        return false;
    }

    public boolean isAvailable(int hour, int minute, int day)
    {
        try{
            return obj.isAvailable(hour, minute, day);
        } catch (Exception e){
            System.err.println("RmiClient exception: " + e); 
            e.printStackTrace(); 
        }
        return false;
    }

    public RmiClient() throws Exception
    {
        ClinicService service = new ClinicService();
        this.obj = service.getClinicPort();
    }
}




public class RmiClientUI extends Application
{
    public static RmiClient cli;
    private TableView table;
    private ObservableList<Appointment> data;
    private static final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final int CONSULT = 1;
    private static final int MINOR = 2;
    public static String s = null;
    public Image ico = new Image("/icon.png", true);

    public MenuBar menu = new MenuBar();

    private AuthService auth = new AuthService();


    private Stage stage;

    private static RmiClientUI instance;

    public RmiClientUI()
    {
        instance = this;
    }

    public static Object getInstance()
    {
        return instance;
    }

//Initial Login Screen
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Clinic Management Admin");
        GridPane grid = new GridPane();
        primaryStage.setWidth(370);
        primaryStage.setHeight(220);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        VBox vbox = new VBox(8);

        Scene scene = new Scene(vbox, 300, 275);

        scene.setFill(Color.FLORALWHITE);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label user = new Label("User Name");
        grid.add(user, 0, 1);

        final TextField userTF = new TextField(); 
        grid.add(userTF, 1, 1);

        Label pw = new Label("Password");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        final Text connecterror = new Text("Clinic not Responding. \nCheck Connection");
        connecterror.setVisible(false);
        connecterror.setFill(Color.FIREBRICK);

        final Text autherror = new Text("Cannot Authenticate.\nCheck Password");
        autherror.setVisible(false);
        autherror.setFill(Color.FIREBRICK);


        Button btn = new Button("Authenticate");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(!cli.connectable())
                {
                    System.out.println(cli.connectable());
                    connecterror.setVisible(true);
                    return;
                }

                String str_user = userTF.getCharacters().toString();
                String str_pw = pwBox.getCharacters().toString();

                if(auth.validate(str_user, str_pw)){
                openMainScreen(primaryStage);
                }
                else
                    autherror.setVisible(true);
            }
        });

        grid.add(btn, 1, 4, 3, 4);
        grid.add(connecterror, 1, 6, 3, 6);
        grid.add(autherror, 1, 6, 3, 6);
        Menu mFile = new Menu("File");
        Menu mEdit = new Menu("Edit");
        Menu mAppo = new Menu("View");

        MenuItem logout = new MenuItem("Logout");
        MenuItem close = new MenuItem("Quit");

        MenuItem add = new MenuItem("Add Appointment");
        MenuItem check = new MenuItem("Check Time");
        MenuItem cancel = new MenuItem("Cancel Appointment");

        MenuItem monday = new MenuItem("Monday");
        MenuItem tues = new MenuItem("Tuesday");
        MenuItem wed = new MenuItem("Wednesday");
        MenuItem thur = new MenuItem("Thursday");
        MenuItem fri = new MenuItem("Friday");
        MenuItem wv = new MenuItem("Week");

        cancel.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                openCancelScreen(primaryStage);
            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                menu = new MenuBar();
                start(primaryStage);
            }
        });

        close.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                System.exit(0);
            }
        });

        add.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openAddScreen(primaryStage);
            }
        });

        check.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openCheckScreen(primaryStage);
            }
        });

        monday.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openDayScreen(primaryStage, 1);
            }
        });
        tues.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openDayScreen(primaryStage, 2);
            }
        });
        wed.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openDayScreen(primaryStage, 3);
            }
        });
        thur.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openDayScreen(primaryStage, 4);
            }
        });
        fri.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openDayScreen(primaryStage, 5);
            }
        });

        wv.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            {
                openMainScreen(primaryStage);
            }
        });

        mFile.getItems().addAll(logout, close);
        mEdit.getItems().addAll(add, check, cancel);
        mAppo.getItems().addAll(monday, tues, wed, thur, fri, wv);


        menu.getMenus().addAll(mFile, mEdit, mAppo);

        ((VBox)scene.getRoot()).getChildren().addAll(grid);
        primaryStage.getIcons().add(ico);

        primaryStage.show();
    }

    public void openCancelScreen(final Stage primaryStage)
    {
        Scene scene = new Scene(new VBox(), 400, 500);
        final ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", 
                                                                            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
                                                                            "15:30", "16:00", "16:30"));
        final ChoiceBox<String> day = new ChoiceBox<String>(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        GridPane grid = new GridPane();
        primaryStage.setHeight(300);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Cancel Appointment");
        scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 2, 0);

        Label lday = new Label("Select Day: ");
        grid.add(lday, 2, 1);
        grid.add(day, 3, 1);

        Label lbox = new Label("Time");
        grid.add(lbox, 2, 2);
        grid.add(cb, 3, 2);

        Button btn = new Button("Cancel");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 3, 4, 3);

        final Text checked = new Text();
        checked.setVisible(false);
        checked.setFill(Color.BLACK);
        checked.setTextAlignment(TextAlignment.CENTER);
        checked.setFont(new Font(15));

        grid.add(checked, 2, 6, 5, 6);
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                String[] time = cb.getValue().split(":");
                checked.setText(time[0]+":"+time[1] + " - " + cli.cancel(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Day.getDayNum(day.getValue())));
                checked.setVisible(true);
            }
        });

        ((VBox)scene.getRoot()).getChildren().addAll(menu, grid);


        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void openDayScreen(final Stage primaryStage, int day)
    {
        Scene scene = new Scene(new VBox(), 400, 500);
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);

        data = cli.getAppointments(day);
        final Label label = new Label(days[day-1] + " View");
        label.setFont(new Font("Helvetica", 20));

        table = new TableView();
        table.setMaxWidth(370);
        table.setMaxHeight(470);

        table.setEditable(false);

        TableColumn time = new TableColumn("Time");
        time.setMinWidth(100);
        time.setCellValueFactory( new PropertyValueFactory<Appointment, String>("time"));
        TableColumn type = new TableColumn("Appointment");
        type.setMinWidth(150);
        type.setCellValueFactory( new PropertyValueFactory<Appointment, String>("type"));
        TableColumn patient = new TableColumn("Patient");
        patient.setMinWidth(100);
        patient.setCellValueFactory( new PropertyValueFactory<Appointment, String>("name"));

        table.setItems(data);
        table.getColumns().addAll(time, type, patient);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 5, 0, 5));
        vbox.getChildren().addAll(label, table);

        ((VBox) scene.getRoot()).getChildren().addAll(menu, vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void openAddScreen(final Stage primaryStage)
    {
        Scene scene = new Scene(new VBox(), 400, 500);
        final ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", 
                                                                            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
                                                                            "15:30", "16:00", "16:30"));
        final ChoiceBox<String> type = new ChoiceBox<String>(FXCollections.observableArrayList("Consultation", "Minor-Surgical"));
        final ChoiceBox<String> day = new ChoiceBox<String>(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        final TextField name = new TextField();

        GridPane grid = new GridPane();
        primaryStage.setHeight(300);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Appointment Creation");
        scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 2, 0);

        Label lname = new Label("Patient Name: ");
        grid.add(lname, 2, 1);
        grid.add(name, 3, 1);
        Label lday = new Label("Select Day: ");
        grid.add(lday, 2, 2);
        grid.add(day, 3, 2);

        Label lbox = new Label("Book a slot: ");
        grid.add(lbox, 2, 3);
        grid.add(cb, 3, 3);

        Label lType = new Label("Appointment type: ");
        grid.add(lType, 2, 4);
        grid.add(type, 3, 4);

        Button btn = new Button("Book Appointment");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 5, 5, 5);

        final Text error = new Text("Error - Time Selected is not available.");
        error.setVisible(false);
        error.setFill(Color.FIREBRICK);
        grid.add(error, 2, 6, 5, 6);

        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                boolean success = false;
                String time = cb.getValue();
                int dayNum = Day.getDayNum(day.getValue());
                String booked = type.getValue();
                String fname = name.getText();
                String [] t = time.split(":");
                int hour = Integer.parseInt(t[0]);
                int minute = Integer.parseInt(t[1]);
                if(booked.compareTo("Consultation") == 0)
                {
                    success = cli.addAppointment(CONSULT, hour, minute, dayNum, fname);
                }
                else if(booked.compareTo("Minor-Surgical") == 0)
                {
                    success = cli.addAppointment(MINOR, hour, minute, dayNum, fname);
                }

                if(!success)
                {
                    error.setVisible(true);
                }
                else
                    openMainScreen(primaryStage);
            }
        });


        ((VBox)scene.getRoot()).getChildren().addAll(menu, grid);

        primaryStage.setScene(scene);
        primaryStage.show();
            


    }

    public void openCheckScreen(final Stage primaryStage)
    {
        Scene scene = new Scene(new VBox(), 400, 500);
        final ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", 
                                                                            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
                                                                            "15:30", "16:00", "16:30"));
        final ChoiceBox<String> type = new ChoiceBox<String>(FXCollections.observableArrayList("Consultation", "Minor-Surgical"));
        final ChoiceBox<String> day = new ChoiceBox<String>(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        GridPane grid = new GridPane();
        primaryStage.setHeight(300);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Check Appointments");
        scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 2, 0);

        Label lday = new Label("Select Day: ");
        grid.add(lday, 2, 1);
        grid.add(day, 3, 1);

        Label lbox = new Label("Time");
        grid.add(lbox, 2, 2);
        grid.add(cb, 3, 2);

        Button btn = new Button("Check Time");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 3, 4, 3);

        final Text checked = new Text();
        checked.setVisible(false);
        checked.setFill(Color.BLACK);
        checked.setTextAlignment(TextAlignment.CENTER);
        checked.setFont(new Font(15));

        grid.add(checked, 2, 6, 5, 6);
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                String[] time = cb.getValue().split(":");
                checked.setText(time[0]+":"+time[1] + " - " + cli.getInfo(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Day.getDayNum(day.getValue())));
                checked.setVisible(true);
            }
        });

        ((VBox)scene.getRoot()).getChildren().addAll(menu, grid);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void openMainScreen(final Stage primaryStage)
    {

        Scene scene = new Scene(new VBox(), 400, 500);
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);

        data = cli.getAppointmentsWeek();
        final Label label = new Label("Week View");
        label.setFont(new Font("Helvetica", 20));

        table = new TableView();
        table.setEditable(false);
        table.setMaxWidth(370);
        table.setMaxHeight(400);

        TableColumn day = new TableColumn("Day");
        day.setMinWidth(100);
        day.setCellValueFactory( new PropertyValueFactory<Appointment, String>("day"));
        TableColumn time = new TableColumn("Time");
        time.setMinWidth(100);
        time.setCellValueFactory( new PropertyValueFactory<Appointment, String>("time"));
        TableColumn type = new TableColumn("Appointment");
        type.setMinWidth(150);
        type.setCellValueFactory( new PropertyValueFactory<Appointment, String>("type"));
        TableColumn names = new TableColumn("Patient");
        names.setMinWidth(100);
        names.setCellValueFactory( new PropertyValueFactory<Appointment, String>("name"));
        table.setItems(data);
        table.getColumns().addAll(day, time, names, type);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 5, 0, 5));
        vbox.getChildren().addAll(label, table);

        ((VBox) scene.getRoot()).getChildren().addAll(menu, vbox);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    


    public static void main(String args[]) throws Exception
    {

        cli = new RmiClient();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        } 

        launch(args);
    }

    public static class Appointment
    {
        private final SimpleStringProperty name;
        private final SimpleStringProperty type;
        private final SimpleStringProperty time;
        private final SimpleStringProperty day;

        public Appointment(String name, String type, String t, int day)
        {
            this.name = new SimpleStringProperty(name);
            this.type = new SimpleStringProperty(type);
            this.time = new SimpleStringProperty(t);

            switch(day)
            {
                case 1: this.day = new SimpleStringProperty("Monday");
                        break;
                case 2: this.day = new SimpleStringProperty("Tuesday");
                        break;
                case 3: this.day = new SimpleStringProperty("Wednesday");
                        break;
                case 4: this.day = new SimpleStringProperty("Thursday");
                        break;
                case 5: this.day = new SimpleStringProperty("Friday");
                        break;
                case 6: this.day= new SimpleStringProperty("Saturday");
                        break;
                case 7: this.day= new SimpleStringProperty("Sunday");
                        break;
                default: this.day = new SimpleStringProperty("error");
            }
        }

        public String getDay()
        {
            return day.get();
        }

        public String getName()
        {
            return name.get();
        }

        public String getType()
        {
            return type.get();
        }

        public String getTime()
        {
            return time.get();
        }

        public void setTime(Time t)
        {
            //
        }

        public void setName(String name)
        {
            this.name.set(name);
        }

        public void setType(String type)
        {
            this.type.set(type);
        }

    }

}
