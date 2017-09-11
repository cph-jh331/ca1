package entityFacades;

import entity.Company;
import java.util.List;

public interface ICompanyFacade {
   
    public Company getCompany(int id);
    public Company getCompany(Long cvr);
    public Company addCompany(Company c);
    public Company editCompany(Company c);
    public Company deleteCompany(int id);
    public Company deleteCompany(Long cvr);
    public Company deleteCompany(Company c);
    public List<Company> getCompanies();

}
