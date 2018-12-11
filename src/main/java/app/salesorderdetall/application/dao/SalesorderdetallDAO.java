package app.salesorderdetall.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import app.salesorderdetall.domain.entity.Saleorderdetall;

@Service
public class SalesorderdetallDAO implements ISalesorderdetallDAO {
	
JdbcTemplate template;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public List<Saleorderdetall> saveSaveorder(Saleorderdetall salesorderdetall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Saleorderdetall> getid(long idsaleorderdetall ) {	  
	String sql="SELECT 	sale_order_detail_id,sale_order_id,product_id,quantity,price,currency,status FROM examen.sale_order_detail  where sale_order_id = "+""+idsaleorderdetall+"";
		//System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<Saleorderdetall>>(){  		    
		     public List<Saleorderdetall> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException { 
		        List<Saleorderdetall> list=new ArrayList<Saleorderdetall>(); 		        
		        while(rs.next()){  
		        	Saleorderdetall saleorderdetall=new Saleorderdetall();
		        	saleorderdetall.setId(rs.getInt(1));
		        	saleorderdetall.setSale_order_id(rs.getInt(2));
		        	saleorderdetall.setProduct_id(rs.getInt(3));		        					     
		        	saleorderdetall.setQuantity(rs.getInt(4));		        	
		        	saleorderdetall.setPrice(rs.getInt(5));
		        	saleorderdetall.setCurrency(rs.getString(6));
		        	saleorderdetall.setStatus(rs.getInt(7));
		        list.add(saleorderdetall);  
		        }  
		        return list;  
		        }  
		    });  
		  }

}
