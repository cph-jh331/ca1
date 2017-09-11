package main;

import java.util.HashMap;
import javax.persistence.Persistence;

public class TestMain {
    
    public static void main(String[] args)
    {
        Persistence.generateSchema("devPU", new HashMap());
    }
    
}
