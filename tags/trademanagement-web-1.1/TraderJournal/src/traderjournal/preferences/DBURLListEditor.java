package traderjournal.preferences;

import java.util.StringTokenizer;

import javax.xml.soap.Text;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;

public class DBURLListEditor extends ListEditor {
	
	public static final String DELIM = "#$#";

	public DBURLListEditor() {
		super();
		
	}

	public DBURLListEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
		
	}

	@Override
	protected String createList(String[] items) {
		String ret = "";
		for(int i=0 ; i<items.length; i ++){
			ret += items[i] + DELIM;
		}
		System.out.println(ret);
		return ret;
	}

	@Override
	protected String getNewInputObject() {
		InputDialog inp = new InputDialog(getShell(), "JDBC URL",
				"Provide new JDBC URL:",
				"jdbc:hsqldb:hsql://localhost/TradeTrack",
				new IInputValidator(){

			@Override
			public String isValid(String newText) {
				// TODO Auto-generated method stub
				return null;
			}});
		inp.open();
		return inp.getValue();
	}

	@Override
	protected String[] parseString(String stringList) {
		System.out.println(stringList);
		if(stringList.equals(""))
			return new String[0];
		else{
			
			StringTokenizer st = new StringTokenizer(stringList,DELIM);
			String [] ars = new String[st.countTokens()];
			int i =0;
			while(st.hasMoreTokens()){
				ars[i] = st.nextToken();
				i++;
			}
			return ars;
			
		}
	}
	
	public static String getFirstDBURL(String stringList){
		StringTokenizer st = new StringTokenizer(stringList,DELIM);
		
		if(st.hasMoreTokens())
			return st.nextToken();
		else
			return null;
	}
	

}
