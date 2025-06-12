package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

import java.util.Arrays;
import java.util.Objects;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        Objects.requireNonNull(pyramid, "pyramid should not be null");
        if (pyramid.getRows() == 0) {
            return 0;
        }
        
        var rows = pyramid.getRows();
        var data = pyramid.getData();
        var recalculatedData = new long[rows];
        
        // Initialize with the top (inverse) row
        recalculatedData[0] = data[rows-1][0];
        
        // Process from top (inverse) to bottom
        for (var row = rows-1; row > 0; row--) {
            var rowToBeCalculated = new long[rows];
            /* For each position in the rowToBeCalculated, calculate a maximum path sum and save it (Dynamic programming)
               Do not overwrite the values that were already calculated with a bigger path sum
            */
            for (var col = 0; col <= rows - row - 1; col++) {
                rowToBeCalculated[col] = Math.max(data[row-1][col] + recalculatedData[col], rowToBeCalculated[col]);
                rowToBeCalculated[col+1] = Math.max(data[row-1][col+1] + recalculatedData[col], rowToBeCalculated[col+1]);
            }
            recalculatedData = rowToBeCalculated;
        }

        return Arrays.stream(recalculatedData).max().getAsLong();
    }
}