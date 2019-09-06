package ubs.var;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ubs.var.entity.Book;
import ubs.var.entity.Slide;

/**
 * ARisk & ETL --> DS2 DB
 * */
public class CentralDB {
	
	public static final int MOCK_COB_DATE = 20180101;
	public static final String MOCK_BOOK_NAME = "MOCKED";
	
	protected static Map<Integer, List<Book>> DS2 = new LinkedHashMap<>();
	static {
		Map<Double, Double> shockAndValues = new LinkedHashMap<Double, Double>();
		shockAndValues.put(-1000.0, -1000.0);
		shockAndValues.put(1000.0, 1000.0);
		
		Book book = new Book(MOCK_BOOK_NAME);
		book.addSlides(new Slide(RiskFactorDB.MRM_RISKFACTORCLASS_CM_PRICE, shockAndValues, RiskFactorDB.MOCK_RISKFACTOR_CM_PRICE_1,RiskFactorDB.MOCK_RISKFACTOR_CM_PRICE_2,RiskFactorDB.MOCK_RISKFACTOR_CM_PRICE_3));
		book.addSlides(new Slide(RiskFactorDB.MRM_RISKFACTORCLASS_EQ_PRICE, shockAndValues, RiskFactorDB.MOCK_RISKFACTORS_EQ_PRICE));
		
		if(!DS2.containsKey(MOCK_COB_DATE)) {
			DS2.put(MOCK_COB_DATE, new ArrayList<Book>());
		}
		DS2.get(MOCK_COB_DATE).add(book);
		
	}
	
	public static List<Book> getBooks(int cob) {
		return DS2.get(cob);
	}
	
	public static Book getBook(int cob, String book) {
		List<Book> list = DS2.get(cob);
		for(Book b : list) {
			if(b.getBookName().equals(book)) {
				return b;
			}
		}
		return null;
	}
	
}
