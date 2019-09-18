package interview.capitalOne.statistics;

import interview.capitalOne.measurements.Measurement;

import java.util.List;

public interface MeasurementAggregator {
  List<AggregateResult> analyze(List<Measurement> measurements, List<String> metrics, List<Statistic> stats);
}
