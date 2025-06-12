package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.PyramidGenerator;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class BasePyramidSolverTest {
    protected static final int MAX_DEPTH = 100;

    protected static final int[][] SAMPLE_DATA = {
            { 5, 9, 8, 4 },
            { 6, 4, 5, 0 },
            { 6, 7, 0, 0 },
            { 3, 0, 0, 0 }
    };

    protected static final int[][] SAMPLE_DATA_LEFT = {
            { 100, 1, 1, 1 },
            { 1, 1, 10, 0 },
            { 1, 10, 0, 0 },
            { 1, 0, 0, 0 }
    };

    protected static final int[][] SAMPLE_DATA_RIGHT = {
            { 1, 1, 1, 100 },
            { 10, 1, 1, 0 },
            { 10, 1, 0, 0 },
            { 1, 0, 0, 0 }
    };

    protected static final int[][] DEMO_DATA = {
            { 59, 207, 98, 95 },
            { 87,   1, 70,  0 },
            { 36,  41,  0,  0 },
            { 23,   0,  0,  0 }
    };

    protected PyramidSolver solver;

    // Abstract method to be implemented by subclasses
    protected abstract PyramidSolver createSolver();

    @Test
    public void solverHandlesSampleData() {
        testPyramidSolver(SAMPLE_DATA, 24, "sample");
    }

    @Test
    public void solverHandlesSampleDataLeft() {
        testPyramidSolver(SAMPLE_DATA_LEFT, 103, "left-biased sample");
    }

    @Test
    public void solverHandlesSampleDataRight() {
        testPyramidSolver(SAMPLE_DATA_RIGHT, 103, "right-biased sample");
    }

    @Test
    public void solverHandlesDemoData() {
        testPyramidSolver(DEMO_DATA, 353, "demo");
    }

    @Test
    public void solverSurvivesLargeData() {
        PyramidGenerator generator = new RandomPyramidGenerator(MAX_DEPTH, 1000);
        Pyramid pyramid = generator.generatePyramid();
        assertTrue("Max path in a large pyramid not positive", solver.pyramidMaximumTotal(pyramid) > 0L);
    }

    @Test
    public void solverHandlesRandomData() {
        RandomPyramidGenerator.setRandSeed(25321L);  // ensure pyramid contents
        final PyramidGenerator generator = new RandomPyramidGenerator(5, 99);
        final Pyramid pyramid = generator.generatePyramid();

        assertEquals("Max path in 'random' pyramid", 398, this.solver.pyramidMaximumTotal(pyramid));
    }

    private void testPyramidSolver(int[][] pyramidData, int expectedResult, String pyramidType) {
        Pyramid pyramid = new Pyramid(pyramidData);
        assertEquals("Max path in " + pyramidType + " pyramid", expectedResult, solver.pyramidMaximumTotal(pyramid));
    }
}