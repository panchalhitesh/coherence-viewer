package com.zh.coherence.viewer.tools.statistic.report;

import com.zh.coherence.viewer.jmx.JMXManager;
import com.zh.coherence.viewer.tools.statistic.report.cache.CacheInfo;
import com.zh.coherence.viewer.tools.statistic.report.cache.CacheNodeInfo;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CacheReport implements Named {

    private Map<String, CacheInfo> data = new HashMap<String, CacheInfo>();

    private long totalUnits = 0;

    public void updateData() {
        long time = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(5);
        try {
            data.clear();
            totalUnits = 0;

            final MBeanServerConnection server = JMXManager.getInstance().getServer();
            Set<ObjectName> cacheNamesSet = server.queryNames(new ObjectName("Coherence:type=Cache,*"), null);

            for (final Object aCacheNamesSet : cacheNamesSet) {
                es.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ObjectName cacheNameObjName = (ObjectName) aCacheNamesSet;
                            String name = cacheNameObjName.getKeyProperty("name");
                            if (!data.containsKey(name)) {
                                data.put(name, new CacheInfo(name));
                            }
                            CacheNodeInfo cacheNodeInfo = new CacheNodeInfo();
                            cacheNodeInfo.setName(cacheNameObjName.getKeyProperty("nodeId"));
                            AttributeList attrs = server.getAttributes(
                                    cacheNameObjName,
                                    new String[]{"Size", "TotalPuts", "TotalGets", "CacheHits", "AverageGetMillis", "Units"});
                            List<Attribute> attributes = attrs.asList();
                            Integer size = (Integer) attributes.get(0).getValue();
                            cacheNodeInfo.setSize(size);
                            totalUnits += size;
                            Long totalPuts = (Long) attributes.get(1).getValue();
                            cacheNodeInfo.setTotalPuts(totalPuts);
                            Long totalGets = (Long) attributes.get(2).getValue();
                            cacheNodeInfo.setTotalGets(totalGets);
                            Long cacheHits = (Long) attributes.get(3).getValue();
                            cacheNodeInfo.setCacheHits(cacheHits);
                            Double averageGetMillis = (Double) attributes.get(4).getValue();
                            cacheNodeInfo.setAverageGetMillis(averageGetMillis);
                            cacheNodeInfo.setUnits(Long.valueOf(attributes.get(5).getValue().toString()));

                            data.get(name).addCacheNodeInfo(cacheNodeInfo);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
            System.err.println("cache report time : " + (System.currentTimeMillis() - time));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            es.shutdown();
            try {
                if (!es.awaitTermination(7, TimeUnit.MINUTES)) {
                    es.shutdownNow();
                    if (!es.awaitTermination(5, TimeUnit.MINUTES)) {
                        System.err.println("ScheduledCalculate pool did not terminate.");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<CacheInfo> getData() {
        return new ArrayList<CacheInfo>(data.values());
    }

    public void setData(Map<String, CacheInfo> data) {
        this.data = data;
    }

    public long getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(long totalUnits) {
        this.totalUnits = totalUnits;
    }

    @Override
    public String getName() {
        return "Cache report";
    }
}
