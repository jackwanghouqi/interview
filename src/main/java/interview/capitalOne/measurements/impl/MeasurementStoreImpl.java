package interview.capitalOne.measurements.impl;

import interview.capitalOne.measurements.Measurement;
import interview.capitalOne.measurements.MeasurementStore;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * An implementation of {@link MeasurementStore} supporting in-memory storage of reported metrics (Measurement).
 * This class supports query methods.
 * For one timestamp querying the time complexity is O(1)
 * For a range of timestamp querying, the time complexity is O(log(n))
 * It supports thread-safe by leveraging {@link ReentrantReadWriteLock}.
 * By using write lock, the query speed might be impacted when using add() function at the same time from other threads.
 */
@Service
public class MeasurementStoreImpl implements MeasurementStore {

  private final HashMap<ZonedDateTime, Measurement> cache;
  private final TreeSet<ZonedDateTime> indexs;
  private final ReentrantReadWriteLock lock;

  public MeasurementStoreImpl() {
    cache = new LinkedHashMap<>();
    indexs = new TreeSet<>();
    lock = new ReentrantReadWriteLock();
  }

  /**
   * Add a measurement to the store
   * @param measurement
   */
  @Override
  public void add(Measurement measurement) {
    if(measurement != null) {
      lock.writeLock().lock();
      try {
        ZonedDateTime zonedDateTime = withZoneUTC(measurement.getTimestamp());
        cache.put(zonedDateTime, measurement);
        indexs.add(zonedDateTime);
      } finally {
        lock.writeLock().unlock();
      }
    }
  }

  /**
   * Fetch Measurement with given timestamp
   * @param timestamp
   * @return Measurement
   */
  @Override
  public Measurement fetch(ZonedDateTime timestamp) {
    if(timestamp == null) {
      return null;
    }
    lock.readLock().lock();
    try {
      return cache.get(withZoneUTC(timestamp));
    } finally {
      lock.readLock().unlock();
    }
  }

  //To make sure we are saving the timestamp key with same zone
  private ZonedDateTime withZoneUTC(ZonedDateTime timestamp) {
    return timestamp.withZoneSameInstant(ZoneId.of("UTC"));
  }

  /**
   * Query the measurements with a given time range
   * @param from
   * @param to
   * @return List<Measurement>
   */
  @Override
  public List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to) {
    if(from == null || to == null) {
      return null;
    }
    lock.readLock().lock();
    try {
      NavigableSet<ZonedDateTime> zonedDateTimes = indexs.subSet(withZoneUTC(from), true, withZoneUTC(to), false);
      List<Measurement> result = new ArrayList<>();
      zonedDateTimes.stream().forEach(t -> result.add(cache.get(t)));
      return result;
    } finally {
      lock.readLock().unlock();
    }
  }
}
