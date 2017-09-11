package entityFacades;

import entity.CityInfo;
import java.util.List;

public interface ICityInfoFacade {

    CityInfo getCityInfo(String zipcode);

    CityInfo addCityInfo(CityInfo cityInfo);

    CityInfo editCityInfo(CityInfo cityInfo);

    CityInfo deleteCityInfo(CityInfo cityInfo);

    CityInfo deleteCityInfo(String zipCode);

    List<CityInfo> getAllCityInfos();

}
