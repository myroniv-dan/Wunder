package com.example.bogdan.wunder;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;


public class RetrofitInstanceTest {
    @Test
    public void testCheckResultsFetchCount() {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger fetchCount = new AtomicInteger(0);

        PlacemarksList.queryPlacemarks((placemarks, t) -> {
            if(t != null){
                fail(t.getMessage());
            } else {
                fetchCount.set(placemarks.size());
            }

            latch.countDown();
        });

        try {
            latch.await(10, TimeUnit.SECONDS);
            assertEquals(fetchCount.get(), 423);
        } catch (InterruptedException exception) {
            fail("Fetch timeout exceeded");
        }
    }

}