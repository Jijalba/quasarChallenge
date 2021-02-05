package com.meli.quasarchallenge.domainservices;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.quasarchallenge.model.DomainException;
import com.meli.quasarchallenge.model.IInterceptionRepository;
import com.meli.quasarchallenge.model.ILocationService;
import com.meli.quasarchallenge.model.ISatellitesRepository;
import com.meli.quasarchallenge.model.LocationServiceRequest;
import com.meli.quasarchallenge.model.Position;

@Service
public class LocationService implements ILocationService {

	@Autowired
	ISatellitesRepository satellites;
	
	@Autowired
	IInterceptionRepository interceptions;

	public Position getLocation(List<LocationServiceRequest> locationSources) throws DomainException {

		double[][] interceptorsPositions = new double[locationSources.size()][2];

		double[] distances = new double[locationSources.size()];

		DecimalFormat decimalFormatter = new DecimalFormat("#.#");

		if(locationSources.isEmpty() || locationSources == null)
			throw new DomainException("No se recibió información para la fuente de transmisión");

		if(locationSources.size() < 3)
			throw new DomainException("No hay suficientes transmisiones interceptadas");
		
		for(int i = 0;i< locationSources.size();i++)
		{

			var interceptor = satellites.Get(locationSources.get(i).getSatelliteName());
		
			if(interceptor == null)
				throw new DomainException("Uno de los satélites no existe");
			
			interceptorsPositions[i] = new double[]{ 
					interceptor.getPosition().getX(),
					interceptor.getPosition().getY()
			};
			
			distances[i] = locationSources.get(i).getDistance();	

		}

		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(
						interceptorsPositions,
						distances
						), 
				new LevenbergMarquardtOptimizer()
				);

		Optimum optimum = solver.solve();

		double[] calculatedPosition = optimum.getPoint().toArray();

		return new Position(Double.parseDouble(decimalFormatter.format(calculatedPosition[0])),
							Double.parseDouble(decimalFormatter.format(calculatedPosition[1]))
							);

	}

}
