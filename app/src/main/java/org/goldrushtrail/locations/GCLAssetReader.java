package org.goldrushtrail.locations;

import android.content.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by cs211d on 3/11/2017.
 */

public class GCLAssetReader
{
    public ArrayList<GoldRushLocation> getGoldRushLocations(Context context) {
        ArrayList<GoldRushLocation> listRows = new ArrayList<>();

        try
        {
            Reader reader = new InputStreamReader(
                    context.getAssets().open("GoldRushLocations.txt"),
                    "UTF-8"
            );
            final CSVParser parser = new CSVParser( reader, CSVFormat.EXCEL );
            for (CSVRecord record : parser.getRecords())
            {
                GoldRushLocation gcl = new GoldRushLocation(
                    record.getRecordNumber(),
                    record.get(0),
                    record.get(1),
                    record.get(2),
                    Double.parseDouble(record.get(3)),
                    Double.parseDouble(record.get(4)),
                    record.get(5),
                    Integer.parseInt(record.get(6))
                );
                listRows.add(gcl);
            }
            parser.close();
            reader.close();
        } catch ( IOException ex) {
            throw new RuntimeException(ex);
        }
        return listRows;
    }

    public ArrayList<GoldRushTour> getGoldRushTours(Context context) {
        ArrayList<GoldRushTour> listRows = new ArrayList<>();

        try
        {
            Reader reader = new InputStreamReader(
                    context.getAssets().open("GoldRushTours.txt"),
                    "UTF-8"
            );
            final CSVParser parser = new CSVParser( reader, CSVFormat.EXCEL );
            for (CSVRecord record : parser.getRecords())
            {
                GoldRushTour tour = new GoldRushTour(
                        record.getRecordNumber(),
                        record.get(0),
                        record.get(1),
                        record.get(2),
                        Double.parseDouble(record.get(3)),
                        Double.parseDouble(record.get(4)),
                        Double.parseDouble(record.get(5)),
                        record.get(6)
                );
                listRows.add(tour);
            }
            parser.close();
            reader.close();
        } catch ( IOException ex) {
            throw new RuntimeException(ex);
        }
        return listRows;
    }
}
