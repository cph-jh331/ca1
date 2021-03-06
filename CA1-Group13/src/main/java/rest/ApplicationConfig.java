/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author bloch
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources)
    {
        resources.add(exceptionmappers.CityInfoDoesNotExistExceptionMapper.class);
        resources.add(exceptionmappers.CompanyNotFoundExceptionMapper.class);
        resources.add(exceptionmappers.EmailAlreadyExistsExceptionMapper.class);
        resources.add(exceptionmappers.GenericExceptionMapper.class);
        resources.add(exceptionmappers.NoCityInfoExceptionMapper.class);
        resources.add(exceptionmappers.NoPersonsAtZipcodeExceptionMapper.class);
        resources.add(exceptionmappers.NoPhoneNumbersFoundExceptionMapper.class);
        resources.add(exceptionmappers.NotAllowedExceptionMapper.class);
        resources.add(exceptionmappers.NotFoundExceptionMapper.class);
        resources.add(exceptionmappers.PathParamExceptionMapper.class);
        resources.add(exceptionmappers.PersonNotFoundExceptionMapper.class);
        resources.add(exceptionmappers.PhoneNumberAlreadyExistsExceptionMapper.class);
        resources.add(exceptionmappers.PhoneNumberNotANumberExceptionMapper.class);
        resources.add(exceptionmappers.UnsupportedMediaTypeExceptionMapper.class);
        resources.add(exceptionmappers.ZipCodeNotValidExceptionMapper.class);
        resources.add(rest.CompanyResource.class);
        resources.add(rest.PersonResource.class);
        resources.add(rest.ZipcodeResource.class);
    }
    
}
