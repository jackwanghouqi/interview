package interview.capitalOne.statistics.impl;

import interview.capitalOne.measurements.Measurement;
import interview.capitalOne.statistics.AggregateResult;
import interview.capitalOne.statistics.MeasurementAggregator;
import interview.capitalOne.statistics.Statistic;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MeasurementAggregatorImpl implements MeasurementAggregator {


  public List<AggregateResult> analyze(List<Measurement> measurements, List<String> metrics, List<Statistic> stats) {

    Map<String, MeasurementValueHolder> valueHolders = new LinkedHashMap();

    for (Measurement measurement : measurements) {
      for (String metric : metrics) {
        final Double metricValue = measurement.getMetric(metric);
        if(!valueHolders.containsKey(metric)) {
          valueHolders.put(metric, new MeasurementValueHolder());
        }
        valueHolders.get(metric).add(metricValue);
      }
    }

    List<AggregateResult> results = new ArrayList<>();
    for (String metric : metrics) {
      MeasurementValueHolder valueHolder = valueHolders.get(metric);
      saveAggregateResult(stats, metric, valueHolder, results);
    }
    return results;
  }

  private void saveAggregateResult(List<Statistic> stats, String metric, MeasurementValueHolder measurementValueHolder, List<AggregateResult> results) {
    if(!measurementValueHolder.isEmpty()) {
      if(stats.contains(Statistic.MIN)) {
        results.add(new AggregateResult(metric, Statistic.MIN, measurementValueHolder.getMin().get()));
      }
      if(stats.contains(Statistic.MAX)) {
        results.add(new AggregateResult(metric, Statistic.MAX, measurementValueHolder.getMax().get()));
      }
      if(stats.contains(Statistic.AVERAGE)) {
        results.add(new AggregateResult(metric, Statistic.AVERAGE, measurementValueHolder.getAverage().get()));
      }
    }
  }

  protected class MeasurementValueHolder {
    private Double min;
    private Double max;
    private BigDecimal sum;
    private int count;

    public void add(Double value) {
      if(value != null) {
        min = min == null? value : Math.min(value, min);
        max = max == null? value : Math.max(value, min);
        sum = sum == null? new BigDecimal(value) : sum.add(new BigDecimal(value));
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
      return isEmpty() ? Optional.empty() : Optional.of(sum.divide(new BigDecimal(count)).doubleValue());
    }
  }
}
