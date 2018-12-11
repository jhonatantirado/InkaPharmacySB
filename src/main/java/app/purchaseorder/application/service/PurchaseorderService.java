package app.purchaseorder.application.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.pucharseorderdetall.application.dto.PurchaseorderdetallListDto;
import app.pucharseorderdetall.application.service.PurchaseorderdetallService;
import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;
import app.purchaseorder.application.assembler.PurchaseCreateAssembler;
import app.purchaseorder.application.dao.PurchaseorderDAO;
import app.purchaseorder.application.dto.PurchaseCreateDto;
import app.purchaseorder.application.dto.PurchaseListDto;
import app.purchaseorder.domain.entity.Purchaseorder;

@Service
public class PurchaseorderService {

	@Autowired
	PurchaseCreateAssembler purchaseCreateAssembler;

	@Autowired
	PurchaseorderDAO purchaseorderDAO;

	@Autowired
	PurchaseorderService purchaseorderService;

	@Autowired
	PurchaseorderdetallService purchaseorderdetallService;

	@Transactional
	public ResponseEntity<Object> salesordercreate(PurchaseCreateDto purchaseCreateDto) throws Exception {

		Purchaseorder purchaseorder = new Purchaseorder();
		purchaseorder.setId(purchaseCreateDto.getEmployee_id());
		purchaseorder.setPurchase_date(obtenerFechaActual());
		purchaseorder.setEmployee_id(purchaseCreateDto.getEmployee_id());
		purchaseorder.setProvider_id(purchaseCreateDto.getProvider_id());

		Pucharseorderdetall pucharseorderdetall = new Pucharseorderdetall();
		pucharseorderdetall.setCurrency(purchaseCreateDto.getPucharseorderdetall().getCurrency());
		pucharseorderdetall.setPrice(purchaseCreateDto.getPucharseorderdetall().getPrice());
		pucharseorderdetall.setProduct_id(purchaseCreateDto.getPucharseorderdetall().getProduct_id());
		pucharseorderdetall.setQuantity(purchaseCreateDto.getPucharseorderdetall().getQuantity());
		pucharseorderdetall.setSale_order_id(purchaseCreateDto.getPucharseorderdetall().getSale_order_id());
		pucharseorderdetall.setStatus(purchaseCreateDto.getPucharseorderdetall().getStatus());
		purchaseorder.setPucharseorderdetall(pucharseorderdetall);
		purchaseorderDAO.saveSavepurchase(purchaseorder);
		purchaseorderDAO.saveSavepurchase(pucharseorderdetall, pucharseorderdetall.getSale_order_id());
		return ResponseEntity.ok().body("ok");
	}

	@Transactional
	public List<PurchaseListDto> getAllpurchaseorder(int page, int size, String DateFrom, String DateTo)
			throws SQLException {
		List<Purchaseorder> listado = purchaseorderDAO.getallSavepurchase(page, size, DateFrom, DateTo);
		List<Purchaseorder> purchaseorder2 = new ArrayList<Purchaseorder>();
		for (Purchaseorder p : listado) {
			Purchaseorder Purchaseorder1 = new Purchaseorder();
			Purchaseorder1.setId(p.getId());
			Purchaseorder1.setPurchase_date(FechaHora());
			Purchaseorder1.setEmployee_id(p.getEmployee_id());
			Purchaseorder1.setProvider_id(p.getProvider_id());

			List<PurchaseorderdetallListDto> listado2 = purchaseorderdetallService.getIdSales(Purchaseorder1.getId());
			for (PurchaseorderdetallListDto q : listado2) {
				Pucharseorderdetall pucharseorderdetall = new Pucharseorderdetall();
				pucharseorderdetall.setId(q.getId());
				pucharseorderdetall.setCurrency(q.getCurrency());
				pucharseorderdetall.setPrice(q.getPrice());
				pucharseorderdetall.setProduct_id(q.getProduct_id());
				pucharseorderdetall.setSale_order_id(q.getSale_order_id());
				pucharseorderdetall.setQuantity(q.getQuantity());
				pucharseorderdetall.setStatus(q.getStatus());
				Purchaseorder1.setPucharseorderdetall(pucharseorderdetall);
			}
			purchaseorder2.add(Purchaseorder1);
		}
		List<PurchaseListDto> listadoS = purchaseCreateAssembler.toDtoList(purchaseorder2);
		return listadoS;
	}

	private Date FechaHora() {
		Date utilDate = new Date();
		long lnMilisegundos = utilDate.getTime();
		Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
		return sqlTimestamp;
	}

	Date obtenerFechaActual() throws ParseException {
		java.util.Date datesdd = new Date();
		java.sql.Date fechaActual = new java.sql.Date(datesdd.getTime());
		return fechaActual;
	}

}
