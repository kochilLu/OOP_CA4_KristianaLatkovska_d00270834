package Exceptions;

import java.sql.SQLException;

//class for dealing with SQL exceptions in the Data Access Layer
//used code sample 4 provided on Moodle by Dermot Logue
public class DaoException extends SQLException {

    public DaoException(String aMessage)
    {
        super(aMessage);
    }
}
