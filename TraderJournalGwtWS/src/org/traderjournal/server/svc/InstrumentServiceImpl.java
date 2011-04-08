package org.traderjournal.server.svc;

import java.util.List;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.HibernateUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;

import org.hibernate.Session;
import org.traderjournal.TJHibernateUtil;
import org.traderjournal.model.dao.InstrumentHome;
import org.traderjournal.model.gen.Instrument;
import org.traderjournal.web.client.svc.InstrumentService;

public class InstrumentServiceImpl extends PersistentRemoteService implements InstrumentService {
	Session currentSession= null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public InstrumentServiceImpl(){
		HibernateUtil hibernateUtil = new HibernateUtil(TJHibernateUtil.getSessionFactory());
		currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
		PersistentBeanManager persistentBeanManager = GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateUtil);
		setBeanManager(persistentBeanManager);

	}
	
	
	@Override
	public List<Instrument> getAllInstruments() {
		
		
		return InstrumentHome.findAll(currentSession);
	}
}
