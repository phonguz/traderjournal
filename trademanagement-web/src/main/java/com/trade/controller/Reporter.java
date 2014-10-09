package com.trade.controller;

import static com.trade.util.ViewConstant.MANAGETRADE;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
















import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 














import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trade.helper.bean.TradeBean;
import com.trade.helper.bean.TradeEventBean;
import com.trade.helper.bean.TradeReportBean;
import com.trade.model.Accounts;
import com.trade.model.Sharedaccount;
import com.trade.model.Trade;
import com.trade.model.Tradeevent;
import com.trade.model.Trader;
import com.trade.service.HibernateEntityService;





import com.trade.util.ObjectErrorFactory;
import com.trade.util.ViewConstant;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Controller
@RequestMapping("/report")
@Repository
public class Reporter {

	@Autowired
	SessionFactory factory;

	
	@Autowired
	HibernateEntityService<Accounts> accountService;
	
	@Autowired
	HibernateEntityService<Trade> tradeService;
	
	@Autowired
	HibernateEntityService<Sharedaccount> sharedAccountService;
	
	private SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMMMM-yyyy_hh:mm:ss_aaa");

	
	@RequestMapping(method = RequestMethod.GET, value = "/reportPDFAll")
	@Transactional
	public  @ResponseBody String generatePdfReportAll(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		JasperReport jasperReport;
		Query query =  factory.getCurrentSession().createSQLQuery("SELECT"+
			     " this_.`id` AS id4_5_,"+
			     " this_.`accounts_id` AS accounts18_4_5_,"+
			     " this_.`carrycost` AS carrycost4_5_,"+
			     " this_.`closeprice` AS closeprice4_5_,"+
			     " this_.`closetradedate` AS closetra4_4_5_,"+
			     " this_.`entrycoms` AS entrycoms4_5_,"+
			     " this_.`entryfees` AS entryfees4_5_,"+
			     " this_.`exitcoms` AS exitcoms4_5_,"+
			     " this_.`exitfees` AS exitfees4_5_,"+
			     " this_.`instrument_id` AS instrument19_4_5_,"+
			     " this_.`lastupdtdate` AS lastupdt9_4_5_,"+
			     " this_.`openprice` AS openprice4_5_,"+
			     " this_.`opentradedate` AS opentra11_4_5_,"+
			     " this_.`quantity` AS quantity4_5_,"+
			     " this_.`reference` AS reference4_5_,"+
			     " this_.`stoploss` AS stoploss4_5_,"+
			     " this_.`tp` AS tp4_5_,"+
			     " this_.`trader_id` AS trader20_4_5_,"+
			     " this_.`tradetype` AS tradetype4_5_,"+
			     " this_.`updatedby` AS updatedby4_5_,"+
			     " accounts2_.`id` AS id0_0_,"+
			     " accounts2_.`balance` AS balance0_0_,"+
			     " accounts2_.`ccy_id` AS ccy7_0_0_,"+
			     " accounts2_.`lastupdtdate` AS lastupdt3_0_0_,"+
			     " accounts2_.`name` AS name0_0_,"+
			     " accounts2_.`percent_risk` AS percent5_0_0_,"+
			     " accounts2_.`trader_id` AS trader8_0_0_,"+
			     " accounts2_.`updatedby` AS updatedby0_0_,"+
			     " ccy3_.`id` AS id1_1_,"+
			     " ccy3_.`lastupdtdate` AS lastupdt2_1_1_,"+
			     " ccy3_.`name` AS name1_1_,"+
			     " ccy3_.`updatedby` AS updatedby1_1_,"+
			     " instrument4_.`id` AS id2_2_,"+
			     " instrument4_.`ccy_id` AS ccy5_2_2_,"+
			     " instrument4_.`lastupdtdate` AS lastupdt2_2_2_,"+
			     " instrument4_.`name` AS name2_2_,"+
			     " instrument4_.`updatedby` AS updatedby2_2_,"+
			     " ccy5_.`id` AS id1_3_,"+
			     " ccy5_.`lastupdtdate` AS lastupdt2_1_3_,"+
			     " ccy5_.`name` AS name1_3_,"+
			     " ccy5_.`updatedby` AS updatedby1_3_,"+
			     " trader6_.`id` AS id8_4_,"+
			     " trader6_.`firstname` AS firstname8_4_,"+
			     " trader6_.`lastname` AS lastname8_4_,"+
			     " trader6_.`lastupdtdate` AS lastupdt4_8_4_,"+
			     " trader6_.`password` AS password8_4_,"+
			     " trader6_.`tmppassword` AS tmppassw6_8_4_,"+
			     " trader6_.`type` AS type8_4_,"+
			     " trader6_.`updatedby` AS updatedby8_4_,"+
			     " trader6_.`username` AS username8_4_"+
			     " FROM"+
			     " `trademgmt`.`trade` this_ INNER JOIN `trademgmt`.`accounts` accounts2_ ON this_.`accounts_id` = accounts2_.`id`"+
			     " LEFT OUTER JOIN `trademgmt`.`ccy` ccy3_ ON accounts2_.`ccy_id` = ccy3_.`id`"+
			     " INNER JOIN `trademgmt`.`instrument` instrument4_ ON this_.`instrument_id` = instrument4_.`id`"+
			     " INNER JOIN `trademgmt`.`trader` trader6_ ON this_.`trader_id` = trader6_.`id`"+
			     " LEFT OUTER JOIN `trademgmt`.`ccy` ccy5_ ON instrument4_.`ccy_id` = ccy5_.`id`"+
			     " WHERE"+
			     " this_.`trader_id`  = :tradeid");
		query.setParameter("tradeid", Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
		JasperPrint jasperPrint;
		// connection is the data source we used to fetch the data from
		// String file="report1.jrxml";
		// jasperParameter is a Hashmap contains the parameters
		// passed from application to the jrxml layout
		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		request.getSession().getAttribute("loggedUserId");
		List<Accounts> accountsList = null;
		List<Trade> tradeList =query.list();
		 accountsList = accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString());
		 tradeList = tradeService.getByName(new Trade(), "trader_id", request.getSession().getAttribute("loggedUserId").toString());
		BigDecimal balance=null;
		Integer risk=null;
		List<TradeReportBean>  list=new ArrayList<TradeReportBean> ();
		BigDecimal add=null;
		TradeReportBean reportBean;
		for(Trade trade:tradeList)
		{
			reportBean=new TradeReportBean();
			Accounts accounts = accountService.getById(new Accounts(), trade.getAccounts().getId());
			balance=accounts.getBalance();
		
			BigDecimal subtract = (trade.getOpenprice().subtract(trade.getStoploss()));
			BigDecimal multiply = subtract.multiply(new BigDecimal(trade.getQuantity()));
			BigDecimal riskDecimal = multiply.divide(accounts.getBalance(), BigDecimal.ROUND_HALF_UP);
			reportBean.setAccname(accounts.getName());
			reportBean.setBal(balance);
			reportBean.setCloseprc(trade.getCloseprice());
			reportBean.setOpenprc(trade.getOpenprice());
			reportBean.setInstmnt(trade.getInstrument().getName());
			reportBean.setOpendt(trade.getOpentradedate());
			reportBean.setClosedt(trade.getClosetradedate());
			reportBean.setQnt(trade.getQuantity());
			
			BigDecimal finalRisk = new BigDecimal(Math.round(riskDecimal.floatValue()));
			reportBean.setRisk(finalRisk.intValue());
			reportBean.setStoploss(trade.getStoploss());
			list.add(reportBean);
		}
		SessionFactoryImplementor impl = (SessionFactoryImplementor) factory;
		ConnectionProvider cp = impl.getConnectionProvider();
		java.sql.Connection conn = cp.getConnection();
		String path = request.getSession().getServletContext().getRealPath("/");

		jasperReport = JasperCompileManager.compileReport(path
	 	+ "/pdf/Report_UserTrade.jrxml");
		JRBeanCollectionDataSource hqlResultDataSource = new JRBeanCollectionDataSource(list);
		 byte[] byteStream =JasperRunManager.runReportToPdf(jasperReport,
					jasperParameter, hqlResultDataSource);
		OutputStream outStream = response.getOutputStream();
		String date=dateFormat.format(new Date());
		response.setHeader("Content-Disposition",
				"inline; filename=Report_UserTrade_"+date+".pdf");
		// response.setContentType("application/pdf");
		response.setContentType("application/pdf");
		response.setContentLength(byteStream.length);
		outStream.write(byteStream, 0, byteStream.length);
		outStream.close();
		return "redirect:/trade";
	}

	 

	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/reportPDFAdmin")
	@Transactional
	public  @ResponseBody String generatePdfReportAdmin(HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		 JasperReport jasperReport;
		 Query query =  factory.getCurrentSession().createSQLQuery("SELECT"+
				 " this_.`id` AS id4_5_,"+
			     " this_.`accounts_id` AS accounts18_4_5_,"+
			     " this_.`carrycost` AS carrycost4_5_,"+
			     " this_.`closeprice` AS closeprice4_5_,"+
			     " this_.`closetradedate` AS closetra4_4_5_,"+
			     " this_.`entrycoms` AS entrycoms4_5_,"+
			     " this_.`entryfees` AS entryfees4_5_,"+
			     " this_.`exitcoms` AS exitcoms4_5_,"+
			     " this_.`exitfees` AS exitfees4_5_,"+
			     " this_.`instrument_id` AS instrument19_4_5_,"+
			     " this_.`lastupdtdate` AS lastupdt9_4_5_,"+
			     " this_.`openprice` AS openprice4_5_,"+
			     " this_.`opentradedate` AS opentra11_4_5_,"+
			     " this_.`quantity` AS quantity4_5_,"+
			     " this_.`reference` AS reference4_5_,"+
			     " this_.`stoploss` AS stoploss4_5_,"+
			     " this_.`tp` AS tp4_5_,"+
			     " this_.`trader_id` AS trader20_4_5_,"+
			     " this_.`tradetype` AS tradetype4_5_,"+
			     " this_.`updatedby` AS updatedby4_5_,"+
			     " accounts2_.`id` AS id0_0_,"+
			     " accounts2_.`balance` AS balance0_0_,"+
			     " accounts2_.`ccy_id` AS ccy7_0_0_,"+
			     " accounts2_.`lastupdtdate` AS lastupdt3_0_0_,"+
			     " accounts2_.`name` AS name0_0_,"+
			     " accounts2_.`percent_risk` AS percent5_0_0_,"+
			     " accounts2_.`trader_id` AS trader8_0_0_,"+
			     " accounts2_.`updatedby` AS updatedby0_0_,"+
			     " ccy3_.`id` AS id1_1_,"+
			     " ccy3_.`lastupdtdate` AS lastupdt2_1_1_,"+
			     " ccy3_.`name` AS name1_1_,"+
			     " ccy3_.`updatedby` AS updatedby1_1_,"+
			     " instrument4_.`id` AS id2_2_,"+
			     " instrument4_.`ccy_id` AS ccy5_2_2_,"+
			     " instrument4_.`lastupdtdate` AS lastupdt2_2_2_,"+
			     " instrument4_.`name` AS name2_2_,"+
			     " instrument4_.`updatedby` AS updatedby2_2_,"+
			     " ccy5_.`id` AS id1_3_,"+
			     " ccy5_.`lastupdtdate` AS lastupdt2_1_3_,"+
			     " ccy5_.`name` AS name1_3_,"+
			     " ccy5_.`updatedby` AS updatedby1_3_,"+
			     " trader6_.`id` AS id8_4_,"+
			     " trader6_.`firstname` AS firstname8_4_,"+
			     " trader6_.`lastname` AS lastname8_4_,"+
			     " trader6_.`lastupdtdate` AS lastupdt4_8_4_,"+
			     " trader6_.`password` AS password8_4_,"+
			     " trader6_.`tmppassword` AS tmppassw6_8_4_,"+
			     " trader6_.`type` AS type8_4_,"+
			     " trader6_.`updatedby` AS updatedby8_4_,"+
			     " trader6_.`username` AS username8_4_"+
			     " FROM"+
			     " `trademgmt`.`trade` this_ INNER JOIN `trademgmt`.`accounts` accounts2_ ON this_.`accounts_id` = accounts2_.`id`"+
			     " LEFT OUTER JOIN `trademgmt`.`ccy` ccy3_ ON accounts2_.`ccy_id` = ccy3_.`id`"+
			     " INNER JOIN `trademgmt`.`instrument` instrument4_ ON this_.`instrument_id` = instrument4_.`id`"+
			     " INNER JOIN `trademgmt`.`trader` trader6_ ON this_.`trader_id` = trader6_.`id`"+
				 " LEFT OUTER JOIN `trademgmt`.`ccy` ccy5_ ON instrument4_.`ccy_id` = ccy5_.`id`");
		JasperPrint jasperPrint;
		// connection is the data source we used to fetch the data from
		// String file="report1.jrxml";
		// jasperParameter is a Hashmap contains the parameters
		// passed from application to the jrxml layout
		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		request.getSession().getAttribute("loggedUserId");

		List<Accounts> accountsList = null;
		List<Trade> tradeList =query.list();
		accountsList = accountService.getDataList(new Accounts());
		tradeList = tradeService.getDataList(new Trade());
		List<TradeReportBean>  list=new ArrayList<TradeReportBean>();
		TradeReportBean reportBean;
		for(Trade trade:tradeList)
		{
			reportBean=new TradeReportBean();
			Accounts accounts = accountService.getById(new Accounts(), trade.getAccounts().getId());
			BigDecimal subtract = (trade.getOpenprice().subtract(trade.getStoploss()));
			BigDecimal multiply = subtract.multiply(new BigDecimal(trade.getQuantity()));
			BigDecimal riskDecimal = multiply.divide(accounts.getBalance(), BigDecimal.ROUND_HALF_UP);
			reportBean.setTrader(trade.getTrader().getFirstname());
			reportBean.setAccname(accounts.getName());
			reportBean.setBal(accounts.getBalance());
			reportBean.setCloseprc(trade.getCloseprice());
			reportBean.setOpenprc(trade.getOpenprice());
			reportBean.setInstmnt(trade.getInstrument().getName());
			reportBean.setOpendt(trade.getOpentradedate());
			reportBean.setClosedt(trade.getClosetradedate());
			reportBean.setQnt(trade.getQuantity());
			BigDecimal finalRisk = new BigDecimal(Math.round(riskDecimal.floatValue()));
			reportBean.setRisk(finalRisk.intValue());
			reportBean.setStoploss(trade.getStoploss());
			 
			list.add(reportBean);
		}
		SessionFactoryImplementor impl = (SessionFactoryImplementor) factory;
		ConnectionProvider cp = impl.getConnectionProvider();
		java.sql.Connection conn = cp.getConnection();
		String path = request.getSession().getServletContext().getRealPath("/");
		jasperReport = JasperCompileManager.compileReport(path+ "/pdf/Report_AllTrades.jrxml");
		JRBeanCollectionDataSource hqlResultDataSource = new JRBeanCollectionDataSource(list);
		byte[] byteStream =JasperRunManager.runReportToPdf(jasperReport,jasperParameter, hqlResultDataSource);
		OutputStream outStream = response.getOutputStream();
		String date=dateFormat.format(new Date());
		response.setHeader("Content-Disposition","inline; filename=Report_AllTrades_"+date+".pdf");
		response.setContentType("application/pdf");
		response.setContentLength(byteStream.length);
		outStream.write(byteStream, 0, byteStream.length);
		outStream.close();
		return "redirect:/trade";
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/reportPDFTrade")
	@Transactional
	public  @ResponseBody String generatePdfReportTrade(HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		JasperReport jasperReport;
		//FINAL QUERY for trade event with event type:::::SELECT distinct tr.description,tr.eventdate,tr.newvalue,te.name FROM `tradeevent` as tr,tradeeventtype as te where tr.trade_id=3 and te.id=tr.tradeeventtype_id ;
		// FInale Query for trade ::::::::::::::: SELECT description , filename FROM `tradeattachment` where tradeevent_id in (select id from tradeevent where trade_id="3") ;
		//RUNNIN:::select acnt.name,inst.name,acnt.balance,trade0_.id as id4_,trade0_.closeprice as closeprice4_, trade0_.closetradedate as closetra4_4_, trade0_.openprice as openprice4_, trade0_.opentradedate as opentra11_4_, trade0_.quantity as quantity4_,trade0_.stoploss as stoploss4_,  trade0_.trader_id as trader20_4_, trade0_.tradetype as tradetype4_ from trademgmt.trade trade0_ ,accounts as acnt ,instrument as inst where trade0_.id=3 and acnt.id=trade0_.accounts_id and inst.id=trade0_.instrument_id
		Query query =  factory.getCurrentSession().createSQLQuery(" select acnt.name as accname,inst.name as instname,"
				+ " acnt.balance as accbal,trade0_.id as id4_,"
				+ " trade0_.closeprice as closeprice4_, "
				+ " trade0_.closetradedate as closetra4_4_, "
				+ " trade0_.openprice as openprice4_, "
				+ " trade0_.opentradedate as opentra11_4_, "
				+ " trade0_.quantity as quantity4_,"
				+ " trade0_.stoploss as stoploss4_, "
				+ " acnt.id as id, "
				+ " trade0_.trader_id as trader20_4_, "
				+ " trade0_.tradetype as tradetype4_ "
				+ " from trademgmt.trade trade0_ INNER JOIN "
				+ " `trademgmt`.`accounts` acnt "
				+ " ON `accounts_id` = acnt.`id` "
				+ " INNER JOIN `trademgmt`.`instrument` "
				+ " inst ON `instrument_id` = inst.`id` "
				+ " where trade0_.id=:tradeid1");
		query.setParameter("tradeid1", Integer.parseInt(request.getParameter("refId")));
		List<TradeReportBean>  tradeReportBeans=new ArrayList<TradeReportBean>();
		TradeReportBean reportBean=new TradeReportBean();
		Iterator it =query.list().iterator();
		while (it.hasNext()) {
			 Object  objectTrade[] = (Object[]) it.next();
			 BigDecimal balance=null;
				Integer risk=null;
				BigDecimal add=null;
				Accounts accounts = accountService.getById(new Accounts(), Integer.parseInt(objectTrade[10].toString()));
				balance=accounts.getBalance();
				BigDecimal subtract = ((new BigDecimal(objectTrade[6].toString()).subtract(new BigDecimal(objectTrade[9].toString()))));
				BigDecimal multiply = subtract.multiply(new BigDecimal(Integer.parseInt(objectTrade[8].toString())));
				BigDecimal riskDecimal = multiply.divide(accounts.getBalance(), BigDecimal.ROUND_HALF_UP);
				reportBean.setAccname(accounts.getName());
				reportBean.setBal(balance);
				if(objectTrade[4]!=null){
				reportBean.setCloseprc(new BigDecimal(objectTrade[4].toString()));
				}
				reportBean.setOpenprc(new BigDecimal(objectTrade[6].toString()));
				reportBean.setInstmnt(objectTrade[1].toString());
				SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date openDate = (Date) objectTrade[7];
				Date closeDate = (Date) objectTrade[5];
				reportBean.setOpendt(openDate);
				if(closeDate!=null)
				{
				reportBean.setClosedt(closeDate);
				}
				reportBean.setQnt(Integer.parseInt(objectTrade[8].toString()));
				BigDecimal finalRisk = new BigDecimal(Math.round(riskDecimal.floatValue()));
				reportBean.setRisk(finalRisk.intValue());
				reportBean.setStoploss(new BigDecimal(objectTrade[9].toString()));		
		} 
		Query query1 =  factory.getCurrentSession().createSQLQuery(" SELECT distinct tr.description as eventDesc,"
				+ " tr.eventdate,"
				+ " tr.newvalue,"
				+ " te.name,"
				+ " tr.id,"
				+ " ta.filename,"
				+ " ta.description as attachDesc"
				+ " FROM `tradeevent`"
				+ " as tr,tradeeventtype as te ,tradeattachment as ta where tr.trade_id=:tradeid2 and"
				+ " te.id=tr.tradeeventtype_id and tr.id=ta.tradeevent_id");
		query1.setParameter("tradeid2", Integer.parseInt(request.getParameter("refId")));
		JasperPrint jasperPrint;
		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		request.getSession().getAttribute("loggedUserId");
		List<Accounts> accountsList = null;
		List<Tradeevent> tradeEventList =query1.list();
		Iterator it1 =query1.list().iterator();
	//	List<TradeAttachBean> attachBeans=new ArrayList<TradeAttachBean>();
		List<TradeEventBean> eventBeans=new ArrayList<TradeEventBean>();
		int id=0;
		int index=1;
		while(it1.hasNext())
		{
			String fullPath = request.getServletContext().getRealPath("/WEB-INF/attachments");
			TradeEventBean bean=new TradeEventBean();
			  Object objectEvent[]= (Object[]) it1.next();
			if(index==1)
			{
					bean.setEventdescription(objectEvent[0].toString());
					Date eventDate = (Date) objectEvent[1];
					bean.setEventdate(eventDate);
					bean.setNewvalue(new BigDecimal(objectEvent[2].toString()));
					bean.setEventtype(objectEvent[3].toString());
					bean.setAttachdescription(objectEvent[6].toString());
					try {
						File f = new File(fullPath + File.separator + objectEvent[5].toString());
						if(f.exists()){
							bean.setFilename(fullPath + File.separator + objectEvent[5].toString());
						}
						else
						{
							bean.setFilename(fullPath + File.separator + "noimage.jpg");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						
					}
			}
			else{
				if(id==Integer.parseInt(String.valueOf(objectEvent[4])))
				 {
					File f = new File(fullPath + File.separator + objectEvent[5].toString());
					if(f.exists()){
						bean.setFilename(fullPath + File.separator + objectEvent[5].toString());
					}
					else
					{
						bean.setFilename(fullPath + File.separator + "noimage.jpg");
					}
				}
				else
				{
					bean.setEventdescription(objectEvent[0].toString());
					Date eventDate = (Date) objectEvent[1];
					bean.setEventdate(eventDate);
					bean.setNewvalue(new BigDecimal(objectEvent[2].toString()));
					bean.setEventtype(objectEvent[3].toString());
					bean.setAttachdescription(objectEvent[6].toString());
					File f = new File(fullPath + File.separator + objectEvent[5].toString());
					if(f.exists()){
						bean.setFilename(fullPath + File.separator + objectEvent[5].toString());
					}
					else
					{
						bean.setFilename(fullPath + File.separator + "noimage.jpg");
					}
				}
			}
				eventBeans.add(bean);
				id=Integer.parseInt(String.valueOf(objectEvent[4]));
				index++;
		}
		reportBean.setEvents(eventBeans);
		tradeReportBeans.add(reportBean);
		SessionFactoryImplementor impl = (SessionFactoryImplementor) factory;
		ConnectionProvider cp = impl.getConnectionProvider();
		String path = request.getSession().getServletContext().getRealPath("/");
		jasperReport = JasperCompileManager.compileReport(path+ "/pdf/Report_TradeDetail.jrxml");
		JRBeanCollectionDataSource hqlResultDataSource = new JRBeanCollectionDataSource(tradeReportBeans,false);
		byte[] byteStream =JasperRunManager.runReportToPdf(jasperReport,jasperParameter, hqlResultDataSource);
		OutputStream outStream = response.getOutputStream();
		String date=dateFormat.format(new Date());
		response.setHeader("Content-Disposition","inline; filename=Report_TradeDetail_"+date+".pdf");
		response.setContentType("application/pdf");
		response.setContentLength(byteStream.length);
		outStream.write(byteStream, 0, byteStream.length);
		outStream.close();
		return "redirect:/trade";
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/reportIndividual")
	@Transactional
	public   @ResponseBody String generatePdfReportIndividual(HttpServletResponse response,
			HttpServletRequest request,final RedirectAttributes redirectAttributes   ) throws Exception {
		 
		String[] sharedid=request.getParameterValues("sharedid");
	 
			JasperReport jasperReport;
			Query query =  factory.getCurrentSession().createSQLQuery(" select acnt.name as accname,inst.name as instname,"
				+ " acnt.balance as accbal,trade0_.id as id4_,"
				+ " trade0_.closeprice as closeprice4_, "
				+ " trade0_.closetradedate as closetra4_4_, "
				+ " trade0_.openprice as openprice4_, "
				+ " trade0_.opentradedate as opentra11_4_, "
				+ " trade0_.quantity as quantity4_,"
				+ " trade0_.stoploss as stoploss4_, "
				+ " acnt.id as id, "
				+ " trade0_.trader_id as trader20_4_, "
				+ " trd.firstname as fname, "
				+ " trade0_.tradetype as tradetype4_ "
				+ " from trademgmt.trade trade0_ INNER JOIN "
				+ " `trademgmt`.`accounts` acnt "
				+ " ON `accounts_id` = acnt.`id` "
				+ " INNER JOIN `trademgmt`.`trader` trd "
				+ " ON trade0_.trader_id = trd.`id` "
				+ " INNER JOIN `trademgmt`.`instrument` "
				+ " inst ON `instrument_id` = inst.`id` "
				+ " where trade0_.accounts_id in (:idr) ");
		List<String> ids = Arrays.asList(sharedid);
		 query.setParameterList("idr",ids);
		List<TradeReportBean>  tradeReportBeans=new ArrayList<TradeReportBean>();
		TradeReportBean reportBean = null;
		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		Iterator it =query.list().iterator();
		while (it.hasNext()) {
			reportBean=new TradeReportBean();
			 Object  objectTrade[] = (Object[]) it.next();
			 	BigDecimal balance=null;
			 	
				Integer risk=null;
				BigDecimal add=null;
				Accounts accounts = accountService.getById(new Accounts(), Integer.parseInt(objectTrade[10].toString()));
				
				balance=accounts.getBalance();
				BigDecimal subtract = ((new BigDecimal(objectTrade[6].toString()).subtract(new BigDecimal(objectTrade[9].toString()))));
				BigDecimal multiply = subtract.multiply(new BigDecimal(Integer.parseInt(objectTrade[8].toString())));
				BigDecimal riskDecimal = multiply.divide(accounts.getBalance(), BigDecimal.ROUND_HALF_UP);
				reportBean.setAccname(accounts.getName());
				reportBean.setBal(balance);
				if(objectTrade[4]!=null){
				reportBean.setCloseprc(new BigDecimal(objectTrade[4].toString()));
				}
				//System.out.println("Multi Name :::"+objectTrade[8].toString());
				reportBean.setOpenprc(new BigDecimal(objectTrade[6].toString()));
				reportBean.setInstmnt(objectTrade[1].toString());
				SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date openDate = (Date) objectTrade[7];
				Date closeDate = (Date) objectTrade[5];
				reportBean.setOpendt(openDate);
				if(closeDate!=null)
				{
				reportBean.setClosedt(closeDate);
				}
				reportBean.setQnt(Integer.parseInt(objectTrade[8].toString()));
				BigDecimal finalRisk = new BigDecimal(Math.round(riskDecimal.floatValue()));
				reportBean.setRisk(finalRisk.intValue());
				reportBean.setStoploss(new BigDecimal(objectTrade[9].toString()));	
				reportBean.setTrader(objectTrade[12].toString());
				tradeReportBeans.add(reportBean);
				 
		} 
		
		SessionFactoryImplementor impl = (SessionFactoryImplementor) factory;
		ConnectionProvider cp = impl.getConnectionProvider();
		String path = request.getSession().getServletContext().getRealPath("/");
		jasperReport = JasperCompileManager.compileReport(path+ "/pdf/Report_TradesPart.jrxml");
		JRBeanCollectionDataSource hqlResultDataSource = new JRBeanCollectionDataSource(tradeReportBeans,false);
		byte[] byteStream =JasperRunManager.runReportToPdf(jasperReport,jasperParameter, hqlResultDataSource);
		OutputStream outStream = response.getOutputStream();
		String date=dateFormat.format(new Date());
		response.setHeader("Content-Disposition","inline; filename=Report_TradesPart_"+date+".pdf");
		response.setContentType("application/pdf");
		response.setContentLength(byteStream.length);
		outStream.write(byteStream, 0, byteStream.length);
		outStream.close();
		 
		return "redirect:/trade";
	}
	
	
 
	
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/reportJSP")
	public String reportPage(){
		return ViewConstant.TRADE_REPORT;
	}
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/reportPDFParts")
	public @ResponseBody String mainHome(@ModelAttribute("tradeBean") TradeBean tradeBean,Model model,HttpServletRequest request){
		JSONArray traderJSONArray = new JSONArray();
		List<Trade> tradeList = new ArrayList<Trade>();
		List<Sharedaccount> sharedaccountList = new ArrayList<Sharedaccount>();
		 if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			tradeList = tradeService.getDataList(new Trade());
		}else{ 
			tradeList = tradeService.getByName(new Trade(), "trader_id", request.getSession().getAttribute("loggedUserId").toString()); 
			sharedaccountList = sharedAccountService.getByName(new Sharedaccount(), "share_trader_id", request.getSession().getAttribute("loggedUserId").toString());
			 if(sharedaccountList != null && sharedaccountList.size() > 0){
				List<Trade> tempTradeList = tradeService.getDataList(new Trade());
				if(tempTradeList != null &&  tempTradeList.size() > 0){
					for(int i=0; i< sharedaccountList.size(); i++ ){
						for(int j=0; j< tempTradeList.size(); j++){
							if(tempTradeList.get(j).getAccounts().getId().equals(sharedaccountList.get(i).getAccounts().getId()) &&
									tempTradeList.get(j).getTrader().getId().equals(sharedaccountList.get(i).getTraderByTraderId().getId())){
								tradeList.add(tempTradeList.get(j));
							}
						}
					}
				}
			}
			
		}
		Collections.reverse(tradeList);
		List<Trade> tradelist = new ArrayList<Trade>(tradeList);
		//tradeList = new ArrayList<Trade>(tradelist);
		List testId = new ArrayList();
		for(Trade trade:tradelist){
			JSONObject json = new JSONObject();
			try {
			if(testId.contains(trade.getAccounts().getId())){
				tradeList.remove(trade);
			}else{			
				json.put("id", trade.getAccounts().getId());
				json.put("accountName", trade.getAccounts().getName());
				json.put("firstName",trade.getAccounts().getTrader().getFirstname());
				traderJSONArray.put(json);
			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			testId.add(trade.getAccounts().getId());
		}
	 
		/*model.addAttribute("tradeLists", tradeList);
		model.addAttribute("sharedList", sharedaccountList);
		request.getSession().removeAttribute("eventupdate");
		request.getSession().removeAttribute("eventattachmentList");
		request.getSession().removeAttribute("filesList");*/
		return traderJSONArray.toString();
	}
	
	
	
}
