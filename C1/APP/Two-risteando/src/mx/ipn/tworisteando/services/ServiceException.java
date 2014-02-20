package mx.ipn.tworisteando.services;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 5618852599111787945L;

	public ServiceException(String m) {
		super(m);
	}
	
	public ServiceException(String m, Throwable cause) {
		super(m, cause);
	}
}
