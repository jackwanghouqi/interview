package interview.capitalOne.measurements.impl;

import interview.capitalOne.measurements.Measurement;
import interview.capitalOne.measurements.MeasurementStore;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@Service
public class MeasurementStoreImpl implements MeasurementStore {

  private final SortedMap<ZonedDateTime, Measurement> cache;
  private final ReentrantReadWriteLock lock;

  public MeasurementStoreImpl() {
    cache = new TreeMap<>();
    lock = new ReentrantReadWriteLock();
  }

  public void add(Measurement measurement) {
    if(measurement != null) {
      lock.writeLock().lock();
      try {
        cache.put(measurement.getTimestamp(), measurement);
      } finally {
        lock.writeLock().unlock();
      }
    }
  }

  public Measurement fetch(ZonedDateTime timestamp) {
    if(timestamp == null) {
      return null;
    }
    lock.readLock().lock();
    try {
      return cache.get(timestamp);
    } finally {
      lock.readLock().unlock();
    }
  }

  public List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to) {
    if(from == null || to == null) {
      return null;
    }
    lock.readLock().lock();
    try {
      final SortedMap<ZonedDateTime, Measurement> subMap = cache.subMap(ZonedDateTime.parse(""), ZonedDateTime.parse(""));
      return subMap == null ?
              new ArrayList<Measurement>()
              :
              subMap.values().stream().collect(Collectors.toList());
    } finally {
      lock.readLock().unlock();
    }
  }
}
