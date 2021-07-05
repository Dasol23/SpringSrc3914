package com.oracle.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oracle.board.dto.BDto;

public class BDao {
	DataSource dataSource;
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("생성자 dataSource -->"+e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<BDto> list(){
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
	         connection = dataSource.getConnection();
	         
	         String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
	         preparedStatement = connection.prepareStatement(query);
	         resultSet = preparedStatement.executeQuery();
	         
	         while (resultSet.next()) {
	            int bId = resultSet.getInt("bId");
	            String bName = resultSet.getString("bName");
	            String bTitle = resultSet.getString("bTitle");
	            String bContent = resultSet.getString("bContent");
	            Timestamp bDate = resultSet.getTimestamp("bDate");
	            int bHit = resultSet.getInt("bHit");
	            int bGroup = resultSet.getInt("bGroup");
	            int bStep = resultSet.getInt("bStep");
	            int bIndent = resultSet.getInt("bIndent");
	            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
	            dtos.add(dto);            
	         }
			
		} catch (Exception e) {
		
			System.out.println("list dataSource-->" +e.getMessage());
			e.printStackTrace();

		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null)connection.close();
				
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
 	}
	// 목록 띄우기
	public BDto contentView(String pBId) {
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
			
			try {
		         connection = dataSource.getConnection();
		         String query = "select * from  mvc_board where bID =? ";
		         preparedStatement = connection.prepareStatement(query);
		         preparedStatement.setInt(1, Integer.parseInt(pBId));
		         resultSet = preparedStatement.executeQuery();
		   
		         if(resultSet.next()) {
		        	 int bId = resultSet.getInt("bId");
			            String bName = resultSet.getString("bName");
			            String bTitle = resultSet.getString("bTitle");
						String bContent = resultSet.getString("bContent");
						Timestamp bDate = resultSet.getTimestamp("bDate");
						int bHit = resultSet.getInt("bHit");
						int bGroup = resultSet.getInt("bGroup");
						int bStep = resultSet.getInt("bStep");
						int bIndent = resultSet.getInt("bIndent");
						dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
					}

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					try {
						if (resultSet != null)
							resultSet.close();
						if (preparedStatement != null)
							preparedStatement.close();
						if (connection != null)
							connection.close();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				return dto;
			}
//조회수 1씩 증가
		private void upHit(String bId)  {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				try {
					connection = dataSource.getConnection();
					String query = "update mvc_board set bHit= bHit+1 where bID =? ";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1,bId);
					
					int rn = preparedStatement.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
						if (connection != null)
							connection.close();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
		}
	
				
					public void write(String bName, String bTitle, String bContent ){
						int bStep = 0;
						int bIndent = 0;
						Connection connection = null;
						PreparedStatement preparedStatement = null;
					 

						try {
							connection = dataSource.getConnection();
							String query = "insert into mvc_board (bId, bName, bTitle,bContent, bHit, bGroup, bStep, bIndent, bDate)"
									+ "values(mvc_board_seq.nextval,?,?,?, 0, mvc_board_seq.currval, 0, 0, sysdate)";
							preparedStatement = connection.prepareStatement(query);
							preparedStatement.setString(1,bName);
							preparedStatement.setString(2,bTitle);
							preparedStatement.setString(3,bContent);
							int rn = preparedStatement.executeUpdate();
						
							 

						} catch (Exception e) {
							e.printStackTrace();

						} finally {
							try {
								if (preparedStatement != null)
									preparedStatement.close();
								if (connection != null)
									connection.close();

							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
					public void modify(String bId,String bName, String bTitle, String bContent ){
						
						Connection connection = null;
						PreparedStatement preparedStatement = null;
					 

						try {
							connection = dataSource.getConnection();
							
							String query = "update mvc_board set bName=? , bTitle =?, bContent=? bwhere bID =? ";
							preparedStatement = connection.prepareStatement(query);
							preparedStatement.setString(1,bName);
							preparedStatement.setString(2,bTitle);
							preparedStatement.setString(3,bContent);
							preparedStatement.setInt(4,Integer.parseInt(bId));
							int rn = preparedStatement.executeUpdate();

						} catch (Exception e) {
							e.printStackTrace();

						} finally {
							try {
								if (preparedStatement != null)
									preparedStatement.close();
								if (connection != null)
									connection.close();

							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
		public BDto reply_view(String str) {
			BDto dto = null;
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			
			
			try {
				connection = dataSource.getConnection();
				String query = "select *from mvc_board where bId =?";
				preparedStatement.setInt(1, Integer.parseInt(query));
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
		        	 int bId = resultSet.getInt("bId");
			            String bName = resultSet.getString("bName");
			            String bTitle = resultSet.getString("bTitle");
						String bContent = resultSet.getString("bContent");
						Timestamp bDate = resultSet.getTimestamp("bDate");
						int bHit = resultSet.getInt("bHit");
						int bGroup = resultSet.getInt("bGroup");
						int bStep = resultSet.getInt("bStep");
						int bIndent = resultSet.getInt("bIndent");
						dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
					}

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					try {
						if (resultSet != null)
							resultSet.close();
						if (preparedStatement != null)
							preparedStatement.close();
						if (connection != null)
							connection.close();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				return dto;
		
			}
}




					
