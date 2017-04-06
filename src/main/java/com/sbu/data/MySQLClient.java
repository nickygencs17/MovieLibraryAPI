package com.sbu.data;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.sbu.exceptions.MySQLNotConnectedException;

/**
 * Created by nicholasgenco on 3/1/17.
 */
public class MySQLClient extends MySQLDialect {


    public void ConnectToDB() {

        if(true == false){
            throw new MySQLNotConnectedException("overidden message");
        }
    }

}





