package simperf.sample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simperf.annotations.AfterInvoke;
import simperf.annotations.AfterRunTask;
import simperf.annotations.BeforeInvoke;
import simperf.annotations.BeforeRunTask;
import simperf.annotations.Simperf;
import simperf.annotations.WarmUp;
import simperf.controllers.TimeSteppingThreadTrigger;
import simperf.junit.SimperfTestCase;

public class SimperfTestCaseTest extends SimperfTestCase {
    private static final Logger logger = LoggerFactory.getLogger(SimperfTestCaseTest.class);

    private SimpleDateFormat    sdf;
    private Random              rand;

    @Before
    public void before() {
        TimeSteppingThreadTrigger t = new TimeSteppingThreadTrigger(2000, 2);
        t.startWork(simperf);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        rand = new Random();
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.before()");
    }

    @After
    public void after() {
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.after()");
    }

    @BeforeInvoke
    public void beforeInvoke() {
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.beforeInvoke()");
    }

    @AfterInvoke
    public void afterInvoke() {
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.afterInvoke()");
    }

    @BeforeRunTask
    public void beforeRunTask() {
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.beforeRunTask()");
    }

    @AfterRunTask
    public void afterRunTask() {
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.afterRunTask()");
    }

    @WarmUp
    public void warmUp() {
        logger.debug(Thread.currentThread().getId() + "SimperfTestCaseTest.warmUp()");
    }

    @Test
    @Simperf(thread = 2, count = 5, interval = 1000)
    public void testXxx() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        sdf.format(new Date());
        boolean result = rand.nextInt(10) > 1;
        logger.debug(Thread.currentThread().getId() + "==================");
        Assert.assertTrue("xxxx", result);
    }
}
