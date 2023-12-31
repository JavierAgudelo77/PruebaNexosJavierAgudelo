package com.bankinc.credibanco.exception;

/**
* @author Javier Ricardo Agudelo
* 
*/

public class BankIncMessManager extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public static final String ALL = "All ";
	public static final String ENTCHILD = "related tables(childs)";
	public static final String FOREIGNDATA = "foreign classes data: ";
	public static final String ENTITY_SUCCESFULLYSAVED = "Entity succesfully saved";
	public static final String ENTITY_SUCCESFULLYDELETED = "Entity succesfully deleted";
	public static final String ENTITY_SUCCESFULLYMODIFIED = "Entity succesfully modified";
	public static final String ENTITY_WITHSAMEKEY = "Another Entity with the same key was found";
	public static final String ENTITY_NOENTITYTOUPDATE = "No Entity was found, with the typed key ";
	public static final String NOT_FUNDS = "The card does not have sufficient funds";
	public static final String DEADLINE = "deadline expiration";
	
	
	

	public BankIncMessManager() {
	}

	public BankIncMessManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super("The value for the field: \"" + info + "\" is not valid");
		}
	}
	
	public class NullEntityExcepcion extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public NullEntityExcepcion(String info) {
			super("The " + info + " Entity can not be null or empty");
		}
	}

	public class EmptyFieldException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super("The value for the field: \"" + info
					+ "\" can not be null or empty");
		}
	}

	public class NotValidFormatException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super("The Format or length for the field: \"" + info
					+ "\" is not valid");
		}
	}

	public class DeletingException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super("The Entity you are trying to delete "
					+ "may have related information, "
					+ "please before trying again, "
					+ "check the data on the entity, \"" + info+"\"");
		}
	}
	
	public class ForeignException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super("There was no data related with the input \"" + info+ "\"");
		}
	}	

	public class GettingException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super("There was an exception getting " + info);
		}
	}

	public class FindingException extends BankIncMessManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super("There was an exception trying to find " + info);
		}
	}

}
