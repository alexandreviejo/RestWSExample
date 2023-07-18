package aar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
	
	Logger log = Logger.getLogger(UserDao.class.getName());

    final DatabaseService d = new DatabaseService();
	
    public int addUser(String name, String profession) {
        try {
            User user = new User(name, profession);
            d.insert(user);
        } catch (Exception ex) {	
           log.log(Level.SEVERE, null, ex);
        }		
        return 1;
    }	
	
    public List<User> getAllUsers() {
    	try {
    		return d.findAll();
    	} catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
    	}
    	return null;
    }
   
    public User getUser(Integer id) {
        return d.read(id);
    }
   
    public boolean deleteUser(Integer id) {
    	return d.delete(id);
    }

}