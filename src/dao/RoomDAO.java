package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Room;

public class RoomDAO extends DAO{
	
	public RoomDAO() {
		super();
	}


	/**
	 * search all rooms in the tblRoom whose name contains the @key
	 * @param key
	 * @return list of room whose name contains the @key
	 */
	public ArrayList<Room> searchRoom(Float key, String date){
		ArrayList<Room> result = new ArrayList();
		String sql = "SELECT * \n" +
                            "FROM tblRoom \n" +
                            "WHERE (NOT EXISTS(SELECT id FROM tblContract WHERE roomid = tblRoom.id ) AND tblRoom.price <= ?)\n" +
                            "	OR EXISTS(SELECT * FROM tblContract \n" +
                            "    WHERE tblContract.roomid = tblRoom.id \n" +
                            "	AND (tblRoom.price <= ?) \n" +
                            "    AND (DATEDIFF(?, date_add(tblContract.checkin, INTERVAL tblContract.contractDuration MONTH))) >= 0\n" +
                            "    AND (DATEDIFF(date_add(tblContract.checkin, INTERVAL tblContract.contractDuration MONTH), current_date())) >= 0);";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setFloat(1,key);
			ps.setFloat(2,key);
			ps.setInt(3,Integer.parseInt(date));
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Room rm = new Room();
                                rm.setId(rs.getInt("id"));
                                rm.setName(rs.getString("name"));
                                rm.setPrice(rs.getFloat("price"));
                                rm.setType(rs.getString("type"));
				result.add(rm);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	

	/**
	 * update the @room
	 * @param rm
	 */
//	public boolean updateRoom(Room rm){
//		String sql = "UPDATE tblroom SET name=?, type=?, price=?, des=? WHERE id=?";
//		try{
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, rm.getName());
//			ps.setString(2, rm.getType());
//			ps.setFloat(3, rm.getPrice());
//			ps.setString(4, rm.getDes());
//			ps.setInt(5, rm.getId());
//
//			ps.executeUpdate();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}		
//		return true;
//	}
//	
//	/**
//	 * Search available rooms in the period from @checkin to @checkout
//	 * @param checkin
//	 * @param checkout
//	 * @return
//	 */
//	public ArrayList<Room> searchFreeRoom(Date checkin, Date checkout){
//		ArrayList<Room> result = new ArrayList<Room>();
//		String sql = "SELECT * FROM tblRoom WHERE id NOT IN (SELECT idroom FROM tblBookedRoom WHERE checkout > ? AND checkin < ?)";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try{
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, sdf.format(checkin));
//			ps.setString(2, sdf.format(checkout));
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//				Room rm = new Room();
//				rm.setId(rs.getInt("id"));
//				rm.setName(rs.getString("name"));
//				rm.setType(rs.getString("type"));
//				rm.setPrice(rs.getFloat("price"));
//				rm.setDes(rs.getString("des"));
//				result.add(rm);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}	
//		return result;
//	}
//        
//        public ArrayList<Room> searchFreeRoom(Date checkin, Float money){
//            ArrayList<Room> rs = new ArrayList<Room>();
//            String sql = "SELECT * FROM tblRoom WHERE id NOT IN (SELECT )"
//        }
}
