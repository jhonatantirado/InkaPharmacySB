package app.salesorder.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import app.salesorder.domain.entity.Salesorder;
import app.salesorderdetall.domain.entity.Saleorderdetall;

@Service
public class SalesorderDAO implements ISalesorderDAO {

	JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	

	@Override
	public int saveSaveorder(Salesorder p) {
	      String sql="INSERT INTO sale_order (sale_date,customer_id,employee_id,status)"
     + "values('"+p.getSale_date()+"',"+p.getCustomer_id()+","+p.getEmployee_id()+","+p.getStatus()+")";	    
	System.out.println(sql);
	template.update(sql);	
	int  ok = template.queryForObject("SELECT MAX(sale_order_id) FROM sale_order", int.class);	
	System.out.println( "que se inserto " + ok);
		return ok;	
	}
	
	@Override
	public void saveSaveorderd(Saleorderdetall s, long productid,int result) {
		 String sql="INSERT INTO sale_order_detail (sale_order_id,product_id,quantity,price,currency,status)"
			     + "values("+result+","+s.getProduct_id()+","+s.getQuantity()+","+s.getPrice()+",'"+s.getCurrency()+"',"+s.getStatus()+")";
	System.out.println(sql);
	template.update(sql);	
	
	}
	
	

	@Override
	public List<Salesorder> getallSaveorder(int page, int size, String DateFrom, String DateTo) {
		String sql = "SELECT sale_order_id, sale_date,customer_id, employee_id,status FROM sale_order where (sale_date BETWEEN "
				+ "'" + DateFrom + "'" + " AND " + "'" + DateTo + "'" + ") LIMIT " + "" + page + ", " + "" + size + "";
		System.out.println(sql);
		return template.query(sql, new ResultSetExtractor<List<Salesorder>>() {
			public List<Salesorder> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Salesorder> list = new ArrayList<Salesorder>();
				List<Saleorderdetall> list2 = new ArrayList<Saleorderdetall>();
				while (rs.next()) {
					Salesorder salesorder = new Salesorder();
					salesorder.setId(rs.getInt(1));
					salesorder.setSale_date(rs.getDate(2));
					salesorder.setCustomer_id(rs.getInt(3));
					salesorder.setEmployee_id(rs.getInt(4));
					salesorder.setStatus(rs.getInt(5));
					list.add(salesorder);
				}
				return list;
			}
		});
	}	

}
