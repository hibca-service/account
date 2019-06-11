package bit5.team2.account.repo;

import org.springframework.stereotype.Repository;

import bit5.team2.account.model.entity.User;

import java.util.ArrayList;

@Repository
public class UserRepo {
    private ArrayList<User> users;

    public UserRepo() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void save(User user) {
        this.users.add(user);
    }

    public int nextId() {
        if (users.isEmpty()) return 1;
        else {
            int id = 0;
            for(User user : this.users) {
                if (Integer.valueOf(user.getId()) > id) {
                    id = Integer.valueOf(user.getId()) + 1;
                }
            }
            return id;
        }
    }
    
    public User findById(String id) {
    	for (User user : users) {
    		if (user.getId().equals(id)) {
    			return user;
    		}
    	}
    	return null;
    }
    
    public User findByEmail(String email) {
    	for (User user : users) {
    		if (user.getEmail().equals(email)) {
    			return user;
    		}
    	}
    	return null;
    }
    
    public User findByPhoneNumber(String phoneNumber) {
    	for (User user : users) {
    		if (user.getEmail().equals(phoneNumber)) {
    			return user;
    		}
    	}
    	return null;
    }
    
    public User findByUsername(String username) {
    	for (User user : users) {
    		if (user.getEmail().equals(username)) {
    			return user;
    		}
    	}
    	return null;
    }
}
