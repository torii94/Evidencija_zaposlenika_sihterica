/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Interfaces;

import java.util.List;

/**
 *
 * @author toni
 */

public interface model <Object> {
    public Object spasi(Object object);
    public Object uredi(Object object);
    public boolean brisi(Object object);
    public List <Object> sveIzBaze();
    public Object izBazePremaId(int id);
}

    