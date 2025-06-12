package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: There is something wrong here. A few things actually... 
 */
public class NaivePyramidSolver implements PyramidSolver {
    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
    }

    /*
    The efficiency problem in this algorithm is that it recalculates
    multiple nodes without memorizing the already computed values.
    This makes it ultimately inefficient and unusable for larger pyramids.
    Also, the problem is that if the pyramid is too deep, it will cause a stack overflow.
    It is not good practice to use recursion in Java for this reason.
    There are also missing checks for method parameters.
    */
    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        /*
        The problem was with the termination condition.
        It should include values from the last row too and not return 0.
         */
        if (row == 0) return pyramid.get(row, column);

        int myValue = pyramid.get(row, column);
        long left  = myValue + getTotalAbove(row - 1, column, pyramid);
        long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
        return Math.max(left, right);
    }
}