package app.purchaseorder.application.dao;

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
import app.purchaseorder.domain.entity.Purchaseorder;
import app.salesorder.domain.entity.Salesorder;
import app.salesorderdetall.domain.entity.Saleorderdetall;

@Service
public class PurchaseorderDAO implements IPurchaseorderDAO{

	JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveSavepurchase(Purchaseorder p) {
		String sql="INSERT INTO purchase_order (purchase_date,provider_id,employee_id)"
			     + "values('"+p.getPurchase_date()+"',"+p.getProvider_id()+","+p.getEmployee_id()+")";
	System.out.println(sql);
	template.update(sql);		
		
	}

	@Override
	public void saveSavepurchase(Pucharseorderdetall s, long productid) {
		String sql="INSERT INTO purchase_order_detail(purchase_order_id,product_id,quantity,cost,currency)"
			     + "values("+productid+","+s.getProduct_id()+","+s.getQuantity()+","+s.getPrice()+",'"+s.getCurrency()+"')";
	System.out.println(sql);
	template.update(sql);		
	}

	@Override
	public List<Purchaseorder> getallSavepurchase(int page, int size, String DateFrom, String DateTo) {
		String sql = "SELECT purchase_order_id,purchase_date,provider_id,employee_id	FROM purchase_order  where (purchase_date BETWEEN "
				+ "'" + DateFrom + "'" + " AND " + "'" + DateTo + "'" + ") LIMIT " + "" + page + ", " + "" + size + "";
		return template.query(sql, new ResultSetExtractor<List<Purchaseorder>>() {
			public List<Purchaseorder> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Purchaseorder> list = new ArrayList<Purchaseorder>();				
				while (rs.next()) {
					Purchaseorder purchaseorder = new Purchaseorder();
					purchaseorder.setId(rs.getInt(1));					
					purchaseorder.setPurchase_date(rs.getDate(2));
					purchaseorder.setProvider_id(rs.getInt(3));
					purchaseorder.setEmployee_id(rs.getInt(4));
					list.add(purchaseorder);
				}
				return list;
			}
		});
	}

}
