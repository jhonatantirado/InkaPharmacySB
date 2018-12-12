package app.pucharseorderdetall.application.dao;

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
import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;
import app.salesorderdetall.domain.entity.Saleorderdetall;

@Service
public class PurchaseorderdetallDAO implements IPurchaseorderdetallDAO{

	
JdbcTemplate template;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Pucharseorderdetall> saveSaveorder(Pucharseorderdetall pucharseorderdetall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pucharseorderdetall> getid(long idsaleorderdetall) {
		String sql="SELECT 	purchase_order_detail_id,purchase_order_id, product_id, quantity, cost, currency FROM purchase_order_detail  where purchase_order_id = "+""+idsaleorderdetall+"";
		System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<Pucharseorderdetall>>(){  		    
		     public List<Pucharseorderdetall> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException { 
		        List<Pucharseorderdetall> list=new ArrayList<Pucharseorderdetall>(); 		        
		        while(rs.next()){  
		        	Pucharseorderdetall saleorderdetall=new Pucharseorderdetall();
		        	saleorderdetall.setId(rs.getInt(1));
		        	saleorderdetall.setSale_order_id(rs.getInt(2));
		        	saleorderdetall.setProduct_id(rs.getInt(3));
		        	saleorderdetall.setQuantity(rs.getShort(4));
		        	saleorderdetall.setPrice(rs.getInt(5));
		        	saleorderdetall.setCurrency(rs.getString(6));		        	
		        list.add(saleorderdetall);  
		        }  
		        return list;  
		        }  
		    }); 
	}

}
