import junit.framework.Assert;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class lesson6Test {

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
}