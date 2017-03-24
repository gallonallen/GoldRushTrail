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
    public ArrayList<GoldCoastLocation> getGoldCoastLocations(Context context) {
        ArrayList<GoldCoastLocation> listRows = new ArrayList<>();

        try
        {
            Reader reader = new InputStreamReader(
                    context.getAssets().open("GoldCoastData.txt"),
                    StandardCharsets.UTF_8
            );
            final CSVParser parser = new CSVParser( reader, CSVFormat.EXCEL );
            for (CSVRecord record : parser.getRecords())
            {
                GoldCoastLocation gcl = new GoldCoastLocation(
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

}
