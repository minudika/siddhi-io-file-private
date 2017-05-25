/*
 * Copyright (c)  2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import org.apache.log4j.Logger;
import org.junit.Test;
import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.util.EventPrinter;
import org.wso2.siddhi.core.util.transport.InMemoryBroker;

import java.util.concurrent.atomic.AtomicInteger;

public class FileSinkTestCase {
    private static final Logger log = Logger.getLogger(FileSinkTestCase.class);
    private AtomicInteger count = new AtomicInteger();

    @Test
    public void fileSinkTest1() throws InterruptedException {
        log.info("test FileSinkMapper 1");

        String streams = "" +
                "@Plan:name('TestExecutionPlan')" +
                "define stream FooStream (symbol string, price float, volume long); " +
                "@sink(type='file', @map(type='text'),  uri='/home/minudika/Projects/WSO2/siddhi-io-file/testDir/out_test1.txt') " +
                "define stream BarStream (symbol string, price float, volume long); ";

        String query = "" +
                "from FooStream " +
                "select * " +
                "insert into BarStream; ";

        SiddhiManager siddhiManager = new SiddhiManager();
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(streams + query);
        InputHandler stockStream = executionPlanRuntime.getInputHandler("FooStream");

        executionPlanRuntime.start();
        Event wso2Event = new Event();
        Event ibmEvent = new Event();
        Object[] wso2Data = {"WSO2", 55.6f, 100L};
        Object[] ibmData = {"IBM", 32.6f, 160L};

        wso2Event.setData(wso2Data);
        ibmEvent.setData(ibmData);
        stockStream.send(new Object[]{"WSO2", 55.6f, 100L});
        stockStream.send(new Object[]{"WSO2", 57.678f, 100L});
        stockStream.send(new Object[]{"WSO2", 50f, 100L});
        stockStream.send(new Object[]{"WSO2#$%", 50f, 100L});
        Thread.sleep(100);

        //assert event count
       // Assert.assertEquals(5, wso2Count.get());
        executionPlanRuntime.shutdown();

        //unsubscribe from "inMemory" broker per topic
        //InMemoryBroker.unsubscribe(subscriberWSO2);
    }

    @Test
    public void fileSinkTest2() throws InterruptedException {
        log.info("test FileSinkMapper 2");

        String streams = "" +
                "@Plan:name('TestExecutionPlan')" +
                "define stream FooStream (symbol string, price float, volume long); " +
                "@sink(type='file', @map(type='json'),  uri='/home/minudika/Projects/WSO2/siddhi-io-file/testDir/out_test2.txt') " +
                "define stream BarStream (symbol string, price float, volume long); ";

        String query = "" +
                "from FooStream " +
                "select * " +
                "insert into BarStream; ";

        SiddhiManager siddhiManager = new SiddhiManager();
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(streams + query);
        InputHandler stockStream = executionPlanRuntime.getInputHandler("FooStream");

        executionPlanRuntime.start();
        Event wso2Event = new Event();
        Event ibmEvent = new Event();
        Object[] wso2Data = {"WSO2", 55.6f, 100L};
        Object[] ibmData = {"IBM", 32.6f, 160L};

        wso2Event.setData(wso2Data);
        ibmEvent.setData(ibmData);
        stockStream.send(new Object[]{"WSO2", 55.6f, 100L});
        stockStream.send(new Object[]{"WSO2", 57.678f, 100L});
        stockStream.send(new Object[]{"WSO2", 50f, 100L});
        stockStream.send(new Object[]{"WSO2#$%", 50f, 100L});
        Thread.sleep(100);

        //assert event count
        // Assert.assertEquals(5, wso2Count.get());
        executionPlanRuntime.shutdown();

        //unsubscribe from "inMemory" broker per topic
        //InMemoryBroker.unsubscribe(subscriberWSO2);
    }

    @Test
    public void fileSinkTest3() throws InterruptedException {
        log.info("test FileSinkMapper 3");

        String streams = "" +
                "@Plan:name('TestExecutionPlan')" +
                "define stream FooStream (symbol string, price float, volume long); " +
                "@sink(type='file', @map(type='xml'),  uri='/home/minudika/Projects/WSO2/siddhi-io-file/testDir/out_test3.txt') " +
                "define stream BarStream (symbol string, price float, volume long); ";

        String query = "" +
                "from FooStream " +
                "select * " +
                "insert into BarStream; ";

        SiddhiManager siddhiManager = new SiddhiManager();
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(streams + query);
        InputHandler stockStream = executionPlanRuntime.getInputHandler("FooStream");

        executionPlanRuntime.start();
        Event wso2Event = new Event();
        Event ibmEvent = new Event();
        Object[] wso2Data = {"WSO2", 55.6f, 100L};
        Object[] ibmData = {"IBM", 32.6f, 160L};

        wso2Event.setData(wso2Data);
        ibmEvent.setData(ibmData);
        stockStream.send(new Object[]{"WSO2", 55.6f, 100L});
        stockStream.send(new Object[]{"WSO2", 57.678f, 100L});
        stockStream.send(new Object[]{"WSO2", 50f, 100L});
        stockStream.send(new Object[]{"WSO2#$%", 50f, 100L});
        Thread.sleep(100);

        //assert event count
        // Assert.assertEquals(5, wso2Count.get());
        executionPlanRuntime.shutdown();

        //unsubscribe from "inMemory" broker per topic
        //InMemoryBroker.unsubscribe(subscriberWSO2);
    }
}