package bit5.team2.account.repo;

import java.util.ArrayList;

import bit5.team2.account.model.entity.Admin;

public class AdminRepo {
	private ArrayList<Admin> admins;

    public AdminRepo() {
        this.admins = new ArrayList<>();
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void save(Admin admin) {
        this.admins.add(admin);
    }

    public int nextId() {
        if (admins.isEmpty()) return 1;
        else {
            int id = 0;
            for(Admin admin : this.admins) {
                if (Integer.valueOf(admin.getId()) > id) {
                    id = Integer.valueOf(admin.getId()) + 1;
                }
            }
            return id;
        }
    }
    
    public Admin findByUsernameAndPassword(String username,String password) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
}
