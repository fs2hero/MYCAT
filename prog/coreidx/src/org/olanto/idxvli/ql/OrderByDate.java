/**********
Copyright © 2010-2012 Olanto Foundation Geneva

This file is part of myCAT.

myCAT is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

myCAT is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with myCAT.  If not, see <http://www.gnu.org/licenses/>.

 **********/
package org.olanto.idxvli.ql;

import java.util.Comparator;


/** pour implémenter le tri sur les dates
 */
public class OrderByDate implements Comparator{
    
   public int compare(Object _r1, Object _r2){  
    
        long r1 = ((OrderResult)_r1).date;        
        long r2 = ((OrderResult)_r2).date;
       
        if(r1 > r2)
            return -1;  // pour un ordre décroissant
        else if(r1 < r2)
            return 1;
        else
            return 0;    
    }
}
 