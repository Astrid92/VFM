package mx.ipn.tworisteando.model;

import java.io.Serializable;

public class ProcessedRoute implements Serializable {
	private static final long serialVersionUID = -8187861186567980100L;
	private WishedRoute wishedRoute;
	private RouteFragment[] route;
	
	public WishedRoute getWishedRoute() {
		return wishedRoute;
	}
	
	public void setWishedRoute(WishedRoute wishedRoute) {
		this.wishedRoute = wishedRoute;
	}
	
	public RouteFragment[] getRoute() {
		return route;
	}
	
	public void setRoute(RouteFragment[] route) {
		this.route = route;
	}
}
