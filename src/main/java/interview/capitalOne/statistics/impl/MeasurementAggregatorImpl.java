package interview.capitalOne.statistics.impl;

import interview.capitalOne.measurements.Measurement;
import interview.capitalOne.statistics.AggregateResult;
import interview.capitalOne.statistics.MeasurementAggregator;
import interview.capitalOne.statistics.Statistic;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * An implementation of {@link MeasurementAggregator} provide service of analyzing reported metrics with given measurements data.
 * The analyze function will do in-memory compute for the given statistics.
 */
@Service
public class MeasurementAggregatorImpl implements MeasurementAggregator {

  /**
   * Analyze measurements with given metrics and statistics.
   * In-memory compute will cache the mid result for each metrics with {@link MeasurementValueHolder}
   * It will produce a list of AggregateResult for each metric & statistic.
   * @param measurements
   * @param metrics
   * @param stats
   * @return List<AggregateResult>
   */
  public List<AggregateResult> analyze(List<Measurement> measurements, List<String> metrics, List<Statistic> stats) {
    List<AggregateResult> results = new ArrayList<>();
    if(CollectionUtils.isEmpty(measurements) || CollectionUtils.isEmpty(metrics) || CollectionUtils.isEmpty(stats)) {
      return results;
    }

    Map<String, MeasurementValueHolder> valueHolders = new LinkedHashMap<>();
    for (Measurement measurement : measurements) {
      for (String metric : metrics) {
        final Double metricValue = measurement.getMetric(metric);
        if(!valueHolders.containsKey(metric)) {
          valueHolders.put(metric, new MeasurementValueHolder());
        }
        valueHolders.get(metric).add(metricValue);
      }
    }

    for (String metric : metrics) {
      MeasurementValueHolder valueHolder = valueHolders.get(metric);
      saveAggregateResult(stats, metric, valueHolder, results);
    }
    return results;
  }

  /**
   * fetch the metric values from valueHolder and save to aggregateResult.
   * @param stats
   * @param metric
   * @param valueHolder
   * @param results
   */
  private void saveAggregateResult(List<Statistic> stats, String metric, MeasurementValueHolder valueHolder, List<AggregateResult> results) {
    if(valueHolder != null && !valueHolder.isEmpty()) {
      if(stats.contains(Statistic.MIN)) {
        results.add(new AggregateResult(metric, Statistic.MIN, valueHolder.getMin().get()));
      }
      if(stats.contains(Statistic.MAX)) {
        results.add(new AggregateResult(metric, Statistic.MAX, valueHolder.getMax().get()));
      }
      if(stats.contains(Statistic.AVERAGE)) {
        results.add(new AggregateResult(metric, Statistic.AVERAGE, valueHolder.getAverage().get()));
      }
    }
  }

  /**
   * This inner class is used for holding the min, max and sum values when iterating the measurements.
   * It also counts the number of measurements that been calculated.
   * It uses add function to store/refresh the values.
   * if the caller added a null value it will ignore that value, and it will not update the counter.
   */
  protected class MeasurementValueHolder {
    //for Precise calculation, use this to control the precision. set as 12 for now, Adjust this when need more precision.
    private final MathContext precision = new MathContext(12, RoundingMode.HALF_UP);
    private Double min;
    private Double max;
    private BigDecimal sum;
    private int count;

    public void add(Double value) {
      if(value != null) {
        min = min == null? value : Math.min(value, min);
        max = max == null? value : Math.max(value, max);
        sum = sum == null? new BigDecimal(value,precision) : sum.add(new BigDecimal(value,precision));
        count++;
      }
    }

    public boolean isEmpty() {
      return count == 0;
    }

    public Optional<Double> getMin() {
      return isEmpty() ? Optional.empty() : Optional.of(min);
    }

    public Optional<Double> getMax() {
      return isEmpty() ? Optional.empty() : Optional.of(max);
    }

    public Optional<Double> getAverage() {
      return isEmpty() ? Optional.empty() : Optional.of(sum.divide(new BigDecimal(count,precision), precision).doubleValue());
    }
  }

  public static void main(String[] args) {
    MeasurementAggregatorImpl test = new MeasurementAggregatorImpl();
    MeasurementValueHolder valueHolder = test.new MeasurementValueHolder();
    valueHolder.getAverage();
    valueHolder.add(1.24335454);
    valueHolder.add(1.275768);
    valueHolder.add(1.28787);
    System.out.println(valueHolder.getAverage());
    System.out.println(51.3/3);
    System.out.println(0.3/3);
    System.out.println(513.0/30);
    BigDecimal decimal1 = new BigDecimal(51.3, new MathContext(12, RoundingMode.HALF_UP));
    BigDecimal decimal2 = new BigDecimal(3, new MathContext(12, RoundingMode.HALF_UP));
    System.out.println(decimal1.divide(decimal2).doubleValue());
  }
}
