package mx.ipn.tworisteando.services;

import mx.ipn.tworisteando.model.ProcessedRoute;
import mx.ipn.tworisteando.model.WishedRoute;

public interface RouteProcessor {
	ProcessedRoute process(WishedRoute wishedRoute);
}
