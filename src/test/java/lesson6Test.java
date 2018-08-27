import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

class lesson6Test {
    lesson6 ls6 = new lesson6();
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    @Test
    void task1() {

        int[] test = {1,2,3,4,5,6,7,8,9};
        int[] res = {4,5,6,7,8,9};
        Assert.assertTrue( Arrays.equals( res, lesson6.task1(test) ) );
    }

    @Test
    void task2() {
        int[] test = {1,4};
        Assert.assertEquals( true, lesson6.task2( test ) );
    }

    @Test
    void insert() {
        lesson6.createDB();
        lesson6.insert( "Ivanov", 5 );
        int lastID = lesson6.readID();
        Assert.assertEquals( "Ivanov 5", lesson6.read( lastID ) );
        lesson6.delete( lastID );
    }
    @Test
    void updateSurnameTest(){
        lesson6.createDB();
        lesson6.insert( "Ivanov", 5 );
        int lastID = lesson6.readID();
        lesson6.updateSurname( "Petrov", lastID );
        Assert.assertEquals( "Petrov 5", lesson6.read( lastID ) );
        lesson6.delete( lastID );
    }
    @Test
    void updateScoreTest(){
        lesson6.createDB();
        lesson6.insert( "Ivanov", 5 );
        int lastID = lesson6.readID();
        lesson6.updateScore( 0, lastID );
        Assert.assertEquals( "Ivanov 0", lesson6.read( lastID ) );
        lesson6.delete( lastID );
    }

}