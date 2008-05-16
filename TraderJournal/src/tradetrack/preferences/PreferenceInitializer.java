package tradetrack.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import tradetrack.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setDefault(PreferenceConstants.P_DBURL,
				"jdbc:hsqldb:hsql://localhost/TradeTrack");
		store.setDefault(PreferenceConstants.P_DBURLLIST, "jdbc:hsqldb:hsql://localhost/TradeTrack" +DBURLListEditor.DELIM+"jdbc:hsqldb:file:dbdata/TradeTrack" );
		
	}

}
