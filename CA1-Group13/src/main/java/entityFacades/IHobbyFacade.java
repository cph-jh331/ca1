/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityFacades;

import entity.Hobby;
import java.util.List;

/**
 *
 * @author bloch
 */
public interface IHobbyFacade {

    public Hobby addHobby(Hobby hobby);

    public Hobby editHobby(Hobby hobby);

    public Hobby deleteHobby(Hobby hobby);

    public Hobby deleteHobby(int id);

    public List<Hobby> getAllHobbies();

}
