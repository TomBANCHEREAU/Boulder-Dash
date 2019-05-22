import java.sql.SQLException;

import com.TomBAN.mySQL.MySQL;

public abstract class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MySQL.Connect("", "", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
