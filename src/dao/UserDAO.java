package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import model.User;

public class UserDAO extends DAO{
	
    public UserDAO() {
            super();
    }

    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT * FROM tblUser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setDateOfBirth(rs.getDate("dateofbirth"));
                user.setIdentityNumber(rs.getString("identityNumber"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                result = true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
