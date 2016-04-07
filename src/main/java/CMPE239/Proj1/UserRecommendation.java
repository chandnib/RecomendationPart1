package CMPE239.Proj1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.mahout.cf.taste.impl.recommender.RandomRecommender;

class UserRecommendation {

	public static void main(String[] args) throws SQLException {
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://cmpe239db.ck5vokjdxtrp.us-west-2.rds.amazonaws.com:3306/MOVIE_NIGHT";
		
		Connection conn = null;
	    Statement stmt = null;
	    
	    final String USER = "root";
		final String PASS = "rootroot";
		
		DataParser.filename = "D:/II Semester/239/MovieNight.xlsx";
		
		
		try
		{
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			stmt = conn.createStatement();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		
		
		ArrayList<Profile> userInfo = new ArrayList<Profile>();
		userInfo = DataParser.getUserDetails();
		for(Profile p : userInfo)
		{
			System.out.println(p.getId() + "," + p.getAge() + "," + p.getGender());
			String profileSql = "INSERT INTO USER_INFO(`USERID`,`AGE`,`GENDER`) VALUES('" + p.getId() + "','" + p.getAge() + "','" + p.getGender()+"');";
			stmt.executeUpdate(profileSql);
		}
		
		ArrayList<String> movieList = new ArrayList<String>();
		movieList = DataParser.getMovieList();
		for(String movie : movieList)
		{
			System.out.println(movie);
			String movieSql = "INSERT INTO MOVIE_INFO(`MOVIE_NAME`) VALUES('"+movie +"');";
			stmt.executeUpdate(movieSql);
		}
		
		LinkedHashMap<Long,HashMap<String,Float>> preferenceMap= new LinkedHashMap<Long, HashMap<String,Float>>();
		preferenceMap = DataParser.getInformation();
		
		for(Map.Entry<Long,HashMap<String,Float>> pme : preferenceMap.entrySet())
		{
			HashMap<String,Float> ratingMap = pme.getValue();
			for(Map.Entry<String,Float> rme : ratingMap.entrySet())
			{
				System.out.println(pme.getKey() + "," + rme.getKey() + "," + rme.getValue());
				String preferenceSql = "INSERT INTO PREFERENCE(`USERID`,`MOVIEID`,`RATING`) SELECT '" + pme.getKey() + "',MOVIEID,'" + rme.getValue()+"' FROM `MOVIE_INFO` WHERE MOVIE_NAME = '"+rme.getKey()+"';";
				stmt.executeUpdate(preferenceSql);
			}
		}

	}

}
