
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class dbConnection {
    
    
    private String username = "root";
    private String password = "";
    
    private String db_name = "demo";
    
    private String host =  "localhost";
    
    private int port = 3306;
    
    private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public void preparedGetEmployee(int id) {
       
       String query = "Select * From employee where id > ? and name like ? ";
       
       
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,"a%");
            
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name  = rs.getString("name");
                String surname = rs.getString("surname");
                String department =  rs.getString("department");
                
                System.out.println("name : " + name + " surname : " + surname + " department : " + department);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
       
        
        
    }
    public void addEmployee() {
        
        
        
        try {
            statement = con.createStatement();
            String name = "Thorin";
            String surname = "Eichenschild";
            String department = "Sons of Durin";
            
            // Insert Into employee (name,surname,department) VALUES('Darth','Vader','darkside')
            String query = "Insert Into employee (name,surname,department) VALUES(" + "'" + name + "'," + "'" + surname + "'," + "'" + department + "')";
            
            statement.executeUpdate(query);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void deleteEmployee() {
        
        try {
            statement = con.createStatement();
            
            String query = "Delete from employee where id > 3";
            
            int a = statement.executeUpdate(query);
            System.out.println(a + " data have been deleted....");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void updateEmployee() {
        
        
        try {
            statement = con.createStatement();
            
            String query = "Update employee Set department = 'Justice League' where id > 3";
            
            statement.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void getEmployee() {
        
        String query = "Select * From employee";
      
        try {
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String department = rs.getString("department");
                
                System.out.println("Id : " + id + "Name: " + name + "Surname : " + surname + " Department : " + department);
                
                
            }
            
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public dbConnection() {
        
        // "jbdc:mysql://localhost:3306/demo" 
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver is not found....");
        }
        
        
        try {
            con = DriverManager.getConnection(url,username, password);
            System.out.println("Connection success...");
            
            
        } catch (SQLException ex) {
            System.out.println("Sql connection was not succesful...");
            //ex.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        dbConnection connection = new dbConnection();
        connection.preparedGetEmployee(2);
        
    }
}