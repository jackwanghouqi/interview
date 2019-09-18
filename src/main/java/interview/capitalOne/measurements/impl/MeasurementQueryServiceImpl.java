package interview.capitalOne.measurements.impl;

import interview.capitalOne.measurements.Measurement;
import interview.capitalOne.measurements.MeasurementQueryService;
import interview.capitalOne.measurements.MeasurementStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class MeasurementQueryServiceImpl implements MeasurementQueryService {

    @Autowired
    private MeasurementStore measurementStore;

    @Override
    public List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to) {
        return measurementStore.queryDateRange(from, to);
    }
}
